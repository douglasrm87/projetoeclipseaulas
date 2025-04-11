package padroesdeprojeto.observer.net;

import java.util.Observable;
import java.util.Observer;

public class ObserverClasse01 {
    public static void main(String[] args) {
        System.out.println("Enter Text: ");
        SubjectEventSource eventSource = new SubjectEventSource();

        eventSource.addObserver(new Observer() {
            public void update(Observable obj, Object arg) {
                System.out.println("Received response: " + arg);
            }
        });

        new Thread(eventSource).start();
    }
}