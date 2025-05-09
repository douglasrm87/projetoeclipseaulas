package agrupamento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Escola{
    //Atributos
    private String nome,CNPJ;   
    private Endereco endereco;
    private List <Departamento> departamentos;
    private List <Aluno> discentes;
    //Métodos
    public Escola(String nome,String CNPJ){
        this.nome=nome;
        this.CNPJ=CNPJ;
        this.departamentos=new ArrayList<Departamento>();
        this.discentes=new ArrayList<Aluno>();
    }
    public void criarDepartamento(String nomeDepartamento){
        departamentos.add(new Departamento(nomeDepartamento));
    }
    public void fecharDepartamento(Departamento departamento){
        departamentos.remove(departamento);
    }
    public void matricularAluno(Aluno novoAluno){
        discentes.add(novoAluno);
    }
    public void trancarMatriculaAluno(Aluno aluno){
        discentes.remove(aluno);
    }
    public void agruparAlunos(){
        Map<String,List<Aluno>> agrupamento=new HashMap<>();
        for (Aluno alu: discentes){
            // Verifica se a naturalidade já existe no mapa
            // Se não existir, cria uma nova lista para essa naturalidade
            // e adiciona o aluno a essa lista
            // Se já existir, apenas adiciona o aluno à lista existente
            // Agrupando por naturalidade
            if(!agrupamento.containsKey(alu.recuperarNaturalidade())) {
                 agrupamento.put(alu.recuperarNaturalidade(),new ArrayList<>());
            }
            agrupamento.get(alu.recuperarNaturalidade()).add(alu);
        }
        // Imprime os alunos agrupados por naturalidade
        imprimirAlunosAgrupados(agrupamento);
    }
    public void imprimirAlunosAgrupados(Map<String,List<Aluno>> agrupamento){
        for (Map.Entry<String,List<Aluno>> entry: agrupamento.entrySet()){
            System.out.println("\nNaturalidade: "+entry.getKey());
            for (Aluno alu: entry.getValue()){
                System.out.println(" *** " + alu);
            }
        }
    }
}

