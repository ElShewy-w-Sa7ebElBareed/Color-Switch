package application.colorSwitch.shapes.basic;

import java.util.ArrayList;
import application.colorSwitch.shapes.AppShapes;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

public abstract class BasicShapes extends AppShapes{
	
	protected final int ShapeNums = 4;
	public abstract ArrayList<Shape> getShapesWithOppositeColor(Paint paint);
	public abstract Shape[] getShape();
	public abstract double getY();
	
	protected int speedLevel() {
		switch(gameLevel) {
		case EASY : return 1;
		case MEDIUM : return 2;
		case HARD : return 3;
		default : return 0;
		}
	}
	
	protected ArrayList<Shape> shapesVsColor(Paint paint,Shape[] allShapes){
		ArrayList<Shape> shapes = new ArrayList<Shape>();
		for (Shape shape : allShapes) {
			if (!shape.getStroke().equals(paint)) {
				shapes.add(shape);
			}
		}
		return shapes;
	}
	
}
