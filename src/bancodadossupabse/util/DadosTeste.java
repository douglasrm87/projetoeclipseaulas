package bancodadossupabse.util;

import java.util.Date;
import bancodadossupabse.dao.AlunoDAO;
import bancodadossupabse.dao.ProfessorDAO;
import bancodadossupabse.dao.CursoDAO;
import bancodadossupabse.dao.MatriculaDAO;
import bancodadossupabse.dao.PagamentoDAO;
import bancodadossupabse.model.Aluno;
import bancodadossupabse.model.Professor;
import bancodadossupabse.model.Curso;
import bancodadossupabse.model.Matricula;
import bancodadossupabse.model.Pagamento;
import bancodadossupabse.model.PagamentoPix;
import bancodadossupabse.model.PagamentoCartao;
import bancodadossupabse.model.Endereco;
import bancodadossupabse.model.StatusMatricula;

public class DadosTeste {

    public static void popularBancoDados() {
        LoggerUtil.separador();
        LoggerUtil.info("Iniciando população de dados de teste...");
        LoggerUtil.separador();

        try {
            CursoDAO cursoDAO = new CursoDAO();
            AlunoDAO alunoDAO = new AlunoDAO();
            ProfessorDAO professorDAO = new ProfessorDAO();
            MatriculaDAO matriculaDAO = new MatriculaDAO();
            PagamentoDAO pagamentoDAO = new PagamentoDAO();

            // 1. Criar cursos
            LoggerUtil.info("Criando cursos...");
            Curso cursoProgramacao = new Curso("PROG001", "Programação em Java");
            Curso cursoWEB = new Curso("WEB001", "Desenvolvimento Web");
            Curso cursoAI = new Curso("AI001", "Inteligência Artificial");

            cursoDAO.salvar(cursoProgramacao);
            cursoDAO.salvar(cursoWEB);
            cursoDAO.salvar(cursoAI);

            // 2. Criar endereços
            LoggerUtil.info("Criando endereços...");
            Endereco endereco1 = new Endereco("Rua das Flores, 123", "São Paulo", "01234-567");
            Endereco endereco2 = new Endereco("Av. Paulista, 1000", "São Paulo", "01311-100");
            Endereco endereco3 = new Endereco("Rua Central, 500", "Rio de Janeiro", "20040020");
            Endereco endereco4 = new Endereco("Rua da Praia, 100", "Salvador", "40010-020");

            // 3. Criar alunos
            LoggerUtil.info("Criando alunos...");
            Aluno aluno1 = new Aluno("João Silva", "joao.silva@email.com", "2026001");
            aluno1.setEndereco(endereco1);
            alunoDAO.salvar(aluno1);

            Aluno aluno2 = new Aluno("Maria Santos", "maria.santos@email.com", "2026002");
            aluno2.setEndereco(endereco2);
            alunoDAO.salvar(aluno2);

            Aluno aluno3 = new Aluno("Pedro Oliveira", "pedro.oliveira@email.com", "2026003");
            aluno3.setEndereco(endereco3);
            alunoDAO.salvar(aluno3);

            Aluno aluno4 = new Aluno("Ana Costa", "ana.costa@email.com", "2026004");
            aluno4.setEndereco(endereco4);
            alunoDAO.salvar(aluno4);

            // 4. Criar professores
            LoggerUtil.info("Criando professores...");
            Professor prof1 = new Professor("Dr. Carlos Mendes", "carlos@email.com", 5000.00);
            prof1.setEndereco(endereco1);
            professorDAO.salvar(prof1);

            Professor prof2 = new Professor("Dra. Jennifer Walsh", "jennifer@email.com", 5500.00);
            prof2.setEndereco(endereco2);
            professorDAO.salvar(prof2);

            Professor prof3 = new Professor("Prof. Lucas Ferreira", "lucas@email.com", 4800.00);
            prof3.setEndereco(endereco3);
            professorDAO.salvar(prof3);

            // 5. Criar matrículas
            LoggerUtil.info("Criando matrículas...");
            Matricula mat1 = new Matricula(new Date(), StatusMatricula.ATIVA);
            matriculaDAO.salvar(mat1, 1, "PROG001", 1);

            Matricula mat2 = new Matricula(new Date(), StatusMatricula.ATIVA);
            matriculaDAO.salvar(mat2, 2, "WEB001", 2);

            Matricula mat3 = new Matricula(new Date(), StatusMatricula.ATIVA);
            matriculaDAO.salvar(mat3, 3, "AI001", 3);

            Matricula mat4 = new Matricula(new Date(), StatusMatricula.ATIVA);
            matriculaDAO.salvar(mat4, 4, "PROG001", 1);

            Matricula mat5 = new Matricula(new Date(), StatusMatricula.TRANCADA);
            matriculaDAO.salvar(mat5, 1, "WEB001", 2);

            // 6. Criar pagamentos
            LoggerUtil.info("Criando pagamentos...");
            Pagamento pag1 = new PagamentoPix();
            pag1.setValor(500.00);
            pagamentoDAO.salvar(pag1, 1);

            Pagamento pag2 = new PagamentoCartao();
            pag2.setValor(500.00);
            pagamentoDAO.salvar(pag2, 2);

            Pagamento pag3 = new PagamentoPix();
            pag3.setValor(600.00);
            pagamentoDAO.salvar(pag3, 3);

            Pagamento pag4 = new PagamentoCartao();
            pag4.setValor(500.00);
            pagamentoDAO.salvar(pag4, 4);

            Pagamento pag5 = new PagamentoPix();
            pag5.setValor(500.00);
            pagamentoDAO.salvar(pag5, 5);

            LoggerUtil.separador();
            LoggerUtil.sucesso("População de dados concluída com sucesso!");
            LoggerUtil.info("✓ 4 alunos criados");
            LoggerUtil.info("✓ 3 professores criados");
            LoggerUtil.info("✓ 3 cursos criados");
            LoggerUtil.info("✓ 5 matrículas criadas");
            LoggerUtil.info("✓ 5 pagamentos criados");
            LoggerUtil.separador();

        } catch (Exception e) {
            LoggerUtil.erro("Erro ao popular dados de teste");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        popularBancoDados();
    }
}
