package bancodadossupabse.model;

 

public abstract class Pagamento implements Pagavel {

    protected int id;
    protected double valor;
    protected TipoPagamento tipo;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public TipoPagamento getTipo() { return tipo; }
    public void setTipo(TipoPagamento tipo) { this.tipo = tipo; }
}
