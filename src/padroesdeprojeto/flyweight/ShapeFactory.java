package padroesdeprojeto.flyweight;

import java.util.HashMap;

public class ShapeFactory {
	private static final HashMap<String, Shape> circleMap = new HashMap<>();
	private static final HashMap<String, Shape> quadMap = new HashMap<>();

	public static Shape getCircle(String color) {
		Circle circle = (Circle) circleMap.get(color);

		if (circle == null) {
			circle = new Circle(color);
			circleMap.put(color, circle);
			System.out.println("Creating circle of color : " + color);
		}
		return circle;
	}
	public static Shape getQuadrado(String color) {
		Quadrado quad = (Quadrado) quadMap.get(color);

		if (quad == null) {
			quad = new Quadrado(color);
			quadMap.put(color, quad);
			System.out.println("Criando quadrado da cor : " + color);
		}
		return quad;
	}
}
