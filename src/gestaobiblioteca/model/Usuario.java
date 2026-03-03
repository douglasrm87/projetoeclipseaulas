package gestaobiblioteca.model;

public /*abstract*/ class Usuario {
    private int id;

    public void setId(int id) {
        if (id <= 0) {
            throw new 
            IllegalArgumentException("ID deve ser um número positivo.");
        }
        this.id = id;
    }

    public int getId() {
        return id;
    }

    
}
