package bancodadossupabse.model;
 

import java.util.Date;

public class Matricula {

    private int id;
    private Date data;
    private StatusMatricula status;

    public Matricula() {
    }

    public Matricula(Date data, StatusMatricula status) {
        this.data = data;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Date getData() { return data; }
    public void setData(Date data) { this.data = data; }

    public StatusMatricula getStatus() { return status; }
    public void setStatus(StatusMatricula status) { this.status = status; }
}
 
