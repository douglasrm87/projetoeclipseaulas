package padroesdeprojeto.observer;

public class ObserverPrincipalObservador {
	public static void main(String[] args) {
		SubjectOrigemdoEvento eventSource = new SubjectOrigemdoEvento();
		new Thread(eventSource).start();
		ObserverThread01 obs01 = new ObserverThread01("OBServer 01:",eventSource);
		obs01.start();
		ObserverThread02 obs02 = new ObserverThread02("OBServer 02:",eventSource);
		obs02.start();
		ObserverThread03 obs03 = new ObserverThread03("OBServer 03:",eventSource);
		obs03.start();
	}
}
