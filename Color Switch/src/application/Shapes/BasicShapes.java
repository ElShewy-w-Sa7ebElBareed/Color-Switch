package application.Shapes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public abstract class BasicShapes extends AppShapes{
	
	public abstract Shape getShapByColor(Color color);
	public abstract Shape[] getShape();
}
