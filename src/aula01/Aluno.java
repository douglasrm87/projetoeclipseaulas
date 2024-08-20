package aula01;
// Nome da classe. Também no do programa.
public class Aluno {
	private String nome;
	public void inserirNome(String n) {
		// código fonte para gravar o nome no BD.
		this.nome = n;
	}
	public String recuperarNome() {
		// código fonte para ler o nome no BD.
		return this.nome;
	}
	// Tela gráfica simulando a interface do usuário.
	public static void main(String[] args) {
		Aluno obj1 = new Aluno();
		obj1.inserirNome("Douglas");
		System.out.println("Saida: "+obj1.recuperarNome());
	}
}
