package gestaobiblioteca.model;
import java.util.List;
public class Disciplina {
    private long id;
    private String nome;
    private Professor professor;
    private Curso curso;
    private List <Trabalho> trabalhos;  
}
