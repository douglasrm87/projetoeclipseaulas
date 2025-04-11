package padroesdeprojeto.observer;

import java.util.Observable;
import java.util.Observer;

public class ObserverThread02 extends Thread {
	String frase;
	SubjectOrigemdoEvento eventSource;
	public ObserverThread02(String frase, SubjectOrigemdoEvento eventSource) {
		this.frase = frase;
		this.eventSource = eventSource;
	}
	public void run() {
		System.out.println(frase);
		eventSource.addObserver(new Observer() {
			public void update(Observable obj, Object arg) {
				System.out.println(frase + " - " + arg);
			}
		});

	}
}
