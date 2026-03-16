package gestaobiblioteca.model;

import java.util.Date;
import java.util.List;

public class Trabalho {
    private long id;
    private Date dataSubmissao;
    private StatusTrabalho status;
    private String feedback;
    private String titulo;
    private Aluno aluno;
    private Disciplina disciplina;
    private Certificado certificado;
    private List<Arquivo> arquivos;

    public void atualizarStatus (StatusTrabalho novoStatus) {
        this.status = novoStatus;
    }
}
