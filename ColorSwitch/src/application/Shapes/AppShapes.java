package application.Shapes;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public abstract class AppShapes {

	public Color[] ShapeColor = { Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN };

	public abstract void Move(double stageStep);

	public boolean CheckHit(Shape s1, ArrayList<Shape> s2) {
		for (Shape s : s2) {
			Shape intersect = Shape.intersect(s1, s);
			if (intersect.getBoundsInLocal().getWidth() != -1) {
				System.out.println("Game Over");
				return true;
			}	
		}
		return false;
	}
}
