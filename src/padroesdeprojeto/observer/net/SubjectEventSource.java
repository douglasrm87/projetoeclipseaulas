package padroesdeprojeto.observer.net;

import java.util.Observable;
import java.util.Scanner;

class SubjectEventSource extends Observable implements Runnable {
	Scanner sc = new Scanner(System.in);

    public void run() {
        while (true) {
            String response = sc.next();
            setChanged();
            notifyObservers(response);
        }
    }
}
