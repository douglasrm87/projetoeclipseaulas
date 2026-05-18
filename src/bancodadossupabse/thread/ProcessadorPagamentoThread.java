package bancodadossupabse.thread;

import bancodadossupabse.model.Pagamento;
import bancodadossupabse.util.LoggerUtil;

public class ProcessadorPagamentoThread implements Runnable {

    private Pagamento pagamento;
    private int idPagamento;
    private boolean processado = false;

    public ProcessadorPagamentoThread(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public ProcessadorPagamentoThread(Pagamento pagamento, int idPagamento) {
        this.pagamento = pagamento;
        this.idPagamento = idPagamento;
    }

    @Override
    public void run() {
        LoggerUtil.info("Iniciando processamento de pagamento [ID: " + idPagamento + ", Tipo: " +
                       pagamento.getTipo() + ", Valor: R$ " + pagamento.getValor() + "]");
        LoggerUtil.info("Thread: " + Thread.currentThread().getName());

        try {
            simularVerificacaoPagamento();
            boolean resultado = pagamento.realizarPagamento(pagamento.getValor());

            if (resultado) {
                simularProcessamentoAdicional();
                processado = true;
                LoggerUtil.sucesso("Pagamento [ID: " + idPagamento + "] processado com sucesso!");
            } else {
                LoggerUtil.aviso("Falha ao processar pagamento [ID: " + idPagamento + "]");
            }
        } catch (InterruptedException e) {
            LoggerUtil.erro("Processamento de pagamento [ID: " + idPagamento + "] foi interrompido");
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            LoggerUtil.erro("Erro ao processar pagamento [ID: " + idPagamento + "]: " + e.getMessage());
        }
    }

    private void simularVerificacaoPagamento() throws InterruptedException {
        LoggerUtil.debug("Verificando dados do pagamento...");
        Thread.sleep(500);
    }

    private void simularProcessamentoAdicional() throws InterruptedException {
        LoggerUtil.debug("Enviando confirmação para servidor...");
        Thread.sleep(800);
    }

    public boolean isProcessado() {
        return processado;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public int getIdPagamento() {
        return idPagamento;
    }
}
