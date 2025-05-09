package agrupamento;

public class Principal {
    // Atributos
    private  Aluno aluno1,aluno2,aluno3,aluno4,aluno5,aluno6,aluno7,aluno8,aluno9;
    private  Escola escola;
    // Método main
    public static void main(String args[]) {
        new Principal().processar();
    }
    private void processar(){
        escola = new Escola("Escola Pedro Álvares Cabral", "42.336.174/0006-13");
        criarAlunos();
        matricularAlunos();
        escola.agruparAlunos();
    }
    //Métodos
    private void criarAlunos( ){
        aluno1 = new Aluno("Marco Antônio","Rio de Janeiro");
        aluno2 = new Aluno("Clara Silva","Rio de Janeiro");
        aluno3 = new Aluno("Marcos Cintra","Sorocaba");
        aluno4 = new Aluno("Ana Beatriz","Barra do Pirai");
        aluno5 = new Aluno("Marcio Gomes","São Paulo");
        aluno6 = new Aluno("João Carlos","Sorocaba");
        aluno7 = new Aluno("César Augusto","São Paulo");
        aluno8 = new Aluno("Alejandra Gomez","Madri");
        aluno9 = new Aluno("Castelo Branco","São Paulo");
    }
    private void matricularAlunos( ){
        escola.matricularAluno(aluno1);
        escola.matricularAluno(aluno2);
        escola.matricularAluno(aluno3);
        escola.matricularAluno(aluno4);
        escola.matricularAluno(aluno5);
        escola.matricularAluno(aluno6);
        escola.matricularAluno(aluno7);
        escola.matricularAluno(aluno8);
        escola.matricularAluno(aluno9);
    }
}
