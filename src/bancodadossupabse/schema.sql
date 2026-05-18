-- Schema para Sistema de Gerenciamento Escolar com Supabase
-- Execute todos os comandos em sequência no Supabase

-- 1. Criar tabela de endereços
CREATE TABLE IF NOT EXISTS endereco (
    id SERIAL PRIMARY KEY,
    rua VARCHAR(255) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    cep VARCHAR(10) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Criar tabela de cursos
CREATE TABLE IF NOT EXISTS curso (
    codigo VARCHAR(10) PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 3. Criar tabela de pessoas (base para aluno e professor)
CREATE TABLE IF NOT EXISTS pessoa (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    endereco_id INTEGER REFERENCES endereco(id),
    tipo VARCHAR(20) NOT NULL CHECK (tipo IN ('ALUNO', 'PROFESSOR')),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 4. Criar tabela específica de alunos
CREATE TABLE IF NOT EXISTS aluno (
    id SERIAL PRIMARY KEY,
    pessoa_id INTEGER NOT NULL UNIQUE REFERENCES pessoa(id) ON DELETE CASCADE,
    matricula VARCHAR(20) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 5. Criar tabela específica de professores
CREATE TABLE IF NOT EXISTS professor (
    id SERIAL PRIMARY KEY,
    pessoa_id INTEGER NOT NULL UNIQUE REFERENCES pessoa(id) ON DELETE CASCADE,
    salario DECIMAL(10, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 6. Criar tabela de status de matrícula
CREATE TABLE IF NOT EXISTS status_matricula (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Inserir status padrão
INSERT INTO status_matricula (descricao) VALUES ('ATIVA') ON CONFLICT DO NOTHING;
INSERT INTO status_matricula (descricao) VALUES ('TRANCADA') ON CONFLICT DO NOTHING;
INSERT INTO status_matricula (descricao) VALUES ('CANCELADA') ON CONFLICT DO NOTHING;

-- 7. Criar tabela de matrículas
CREATE TABLE IF NOT EXISTS matricula (
    id SERIAL PRIMARY KEY,
    aluno_id INTEGER NOT NULL REFERENCES aluno(id) ON DELETE CASCADE,
    curso_codigo VARCHAR(10) NOT NULL REFERENCES curso(codigo),
    professor_id INTEGER REFERENCES professor(id),
    data DATE NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'ATIVA' CHECK (status IN ('ATIVA', 'TRANCADA', 'CANCELADA')),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 8. Criar tabela de tipos de pagamento
CREATE TABLE IF NOT EXISTS tipo_pagamento (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Inserir tipos padrão
INSERT INTO tipo_pagamento (descricao) VALUES ('PIX') ON CONFLICT DO NOTHING;
INSERT INTO tipo_pagamento (descricao) VALUES ('CARTAO') ON CONFLICT DO NOTHING;
INSERT INTO tipo_pagamento (descricao) VALUES ('BOLETO') ON CONFLICT DO NOTHING;

-- 9. Criar tabela de pagamentos
CREATE TABLE IF NOT EXISTS pagamento (
    id SERIAL PRIMARY KEY,
    matricula_id INTEGER NOT NULL REFERENCES matricula(id) ON DELETE CASCADE,
    valor DECIMAL(10, 2) NOT NULL,
    tipo VARCHAR(20) NOT NULL CHECK (tipo IN ('PIX', 'CARTAO', 'BOLETO')),
    data_pagamento TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    processado BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 10. Criar índices para melhor performance
CREATE INDEX IF NOT EXISTS idx_aluno_pessoa ON aluno(pessoa_id);
CREATE INDEX IF NOT EXISTS idx_professor_pessoa ON professor(pessoa_id);
CREATE INDEX IF NOT EXISTS idx_matricula_aluno ON matricula(aluno_id);
CREATE INDEX IF NOT EXISTS idx_matricula_curso ON matricula(curso_codigo);
CREATE INDEX IF NOT EXISTS idx_pagamento_matricula ON pagamento(matricula_id);
CREATE INDEX IF NOT EXISTS idx_pessoa_email ON pessoa(email);

-- 11. Criar view para listar alunos com detalhes
CREATE OR REPLACE VIEW vw_alunos_detalhes AS
SELECT
    a.id,
    a.matricula,
    p.nome,
    p.email,
    e.rua,
    e.cidade,
    e.cep,
    (SELECT COUNT(*) FROM matricula WHERE aluno_id = a.id) as total_matriculas
FROM aluno a
JOIN pessoa p ON a.pessoa_id = p.id
LEFT JOIN endereco e ON p.endereco_id = e.id;

-- 12. Criar view para listar professores com detalhes
CREATE OR REPLACE VIEW vw_professores_detalhes AS
SELECT
    pr.id,
    p.nome,
    p.email,
    pr.salario,
    e.rua,
    e.cidade,
    e.cep,
    (SELECT COUNT(*) FROM matricula WHERE professor_id = pr.id) as total_turmas
FROM professor pr
JOIN pessoa p ON pr.pessoa_id = p.id
LEFT JOIN endereco e ON p.endereco_id = e.id;

-- 13. Criar view para relatório de matrículas
CREATE OR REPLACE VIEW vw_relatorio_matriculas AS
SELECT
    m.id,
    a.matricula,
    p_aluno.nome as nome_aluno,
    c.nome as nome_curso,
    p_prof.nome as nome_professor,
    m.data,
    m.status,
    COUNT(pg.id) as total_pagamentos,
    COALESCE(SUM(pg.valor), 0) as total_pago
FROM matricula m
JOIN aluno a ON m.aluno_id = a.id
JOIN pessoa p_aluno ON a.pessoa_id = p_aluno.id
JOIN curso c ON m.curso_codigo = c.codigo
LEFT JOIN professor pr ON m.professor_id = pr.id
LEFT JOIN pessoa p_prof ON pr.pessoa_id = p_prof.id
LEFT JOIN pagamento pg ON m.id = pg.matricula_id
GROUP BY m.id, a.matricula, p_aluno.nome, c.nome, p_prof.nome, m.data, m.status;
