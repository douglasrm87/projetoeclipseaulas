package padroesdeprojeto.observer;

import java.util.Observable;
import java.util.Scanner;

class SubjectOrigemdoEvento extends Observable implements Runnable {
	Scanner sc = new Scanner(System.in);
    public void run() {
        while (true) {
        	System.out.println("Subject: Gerar evento:");
            String response = sc.next();
            setChanged();
            notifyObservers(response);
            if ("fim".equalsIgnoreCase(response)){
            	System.out.println("Encerrando programa");
            	System.exit(0);
            }
        }
    }
}
