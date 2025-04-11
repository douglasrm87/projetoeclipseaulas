package padroesdeprojeto.flyweight;

public class Quadrado implements Shape {
	private String color;
	private int x;

	public Quadrado(String color) {
		this.color = color;
	}

	public void setX(int x) {
		this.x = x;
	}

	@Override
	public void draw() {
		System.out.println("Quadrado: Draw() [Color : " + color + ", x : " + (x * x));
	}
}
