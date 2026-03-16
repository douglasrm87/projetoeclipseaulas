package gestaobiblioteca.model;
import java.util.List; // Importação da classe Curso
public class Faculdade {
    private long id;
    private String nome;
    // Relacionamento com a classe Curso
    private List<Curso> cursos; 
}
