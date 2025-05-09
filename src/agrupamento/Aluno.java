package agrupamento;
public class Aluno {
    //Atributos    
    private String matricula,nome,naturalidade;
    //Métodos
    public Aluno(String nome,String naturalidade){
            this.nome=nome;
            this.naturalidade=naturalidade;
    }
    @Override
     public String toString(){
         //return String.format("%s (%s)",nome,naturalidade);
         return String.format("Aluno: %s",nome);
     }
    
    public String recuperarNaturalidade(){
        return naturalidade;
    }
    public String recuperarNome(){
        return nome;
    }
    public String recuperarMatricula(){
        return matricula;
    }
    public void atribuirMatricula(String matricula){
        this.matricula=matricula;
    }
    public void alterarNome(String nome){
        this.nome=nome;
    }
    public void alterarNaturalidade(String naturalidade){
        this.naturalidade=naturalidade;
    }
    public void alterarMatricula(String matricula){
        this.matricula=matricula;
    }
    public String getNome() {
        return nome;
    }
    public String getNaturalidade() {
        return naturalidade;
    }
    public String getMatricula() {
        return matricula;
    }                                                                       
    public void setNome(String nome) {
        this.nome = nome;
    }       
    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }                                                                           
    public Aluno(String nome, String naturalidade, String matricula) {
        this.nome = nome;
        this.naturalidade = naturalidade;
        this.matricula = matricula;
    }
 
    public Aluno(String nome) {
        this.nome = nome;
    }
}