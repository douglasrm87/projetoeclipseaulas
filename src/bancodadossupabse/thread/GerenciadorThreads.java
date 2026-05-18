package bancodadossupabse.thread;

import java.util.ArrayList;
import java.util.List;
import bancodadossupabse.util.LoggerUtil;

public class GerenciadorThreads {

    private List<Thread> threads;
    private static final int TIMEOUT_SEGUNDOS = 30;

    public GerenciadorThreads() {
        this.threads = new ArrayList<>();
    }

    public void adicionarTarefa(Runnable tarefa) {
        Thread thread = new Thread(tarefa);
        threads.add(thread);
    }

    public void executarTodas() {
        LoggerUtil.info("Executando " + threads.size() + " thread(s)...");
        for (Thread thread : threads) {
            thread.start();
        }
    }

    public void aguardarConclusao() throws InterruptedException {
        LoggerUtil.info("Aguardando conclusão de todas as threads...");
        for (Thread thread : threads) {
            thread.join(TIMEOUT_SEGUNDOS * 1000);
            if (thread.isAlive()) {
                LoggerUtil.aviso("Thread " + thread.getName() + " excedeu timeout");
            }
        }
        LoggerUtil.sucesso("Todas as threads foram concluídas");
    }

    public int getTotalThreads() {
        return threads.size();
    }

    public int getThreadsAtivasAtualmente() {
        int ativas = 0;
        for (Thread thread : threads) {
            if (thread.isAlive()) {
                ativas++;
            }
        }
        return ativas;
    }

    public void interromperTodas() {
        LoggerUtil.aviso("Interrompendo " + threads.size() + " thread(s)...");
        for (Thread thread : threads) {
            if (thread.isAlive()) {
                thread.interrupt();
            }
        }
    }

    public void limparThreads() {
        threads.clear();
        LoggerUtil.info("Threads limpas do gerenciador");
    }
}
