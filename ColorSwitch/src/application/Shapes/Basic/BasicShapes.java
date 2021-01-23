package application.Shapes.Basic;

import java.util.ArrayList;

import application.Shapes.AppShapes;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

public abstract class BasicShapes extends AppShapes{
	
	public abstract ArrayList<Shape> getShapesWithOppositeColor(Paint paint);
	public abstract Shape[] getShape();
}
