package application.Shapes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public abstract class AppShapes {
	public Color[] ShapeColor = { Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN };

	public abstract void Move(double stageStep);

	public boolean CheckHit(Shape s1, Shape s2) {
		Shape intersect = Shape.intersect(s1, s2);
		if (intersect.getBoundsInLocal().getWidth() != -1)
			return true;
		return false;
	}
}
