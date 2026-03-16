package gestaobiblioteca.model;
import java.util.List;// Importação da classe List para a associação com Disciplina
public class Curso {
    private long id;
    private String nome;
    // Associação com Faculdade
    private Faculdade faculdade;
    // Associação com Disciplina
    private List<Disciplina> disciplinas; 
}
