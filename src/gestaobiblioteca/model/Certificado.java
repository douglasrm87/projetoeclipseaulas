package gestaobiblioteca.model;

import java.time.LocalDateTime;

public class Certificado {
    private long id;
    private Trabalho trabalho;
    private LocalDateTime dataEmissao;
    private String codigoVerificacao;
    public Certificado(long id, Trabalho trabalho, LocalDateTime dataEmissao) {
        this.id = id;
        this.trabalho = trabalho;
        this.dataEmissao = dataEmissao;
    }
    public void gerarCodigoVerificacao() {
        this.codigoVerificacao = "CERT-" + id + "-" + System.currentTimeMillis();
    }
}
