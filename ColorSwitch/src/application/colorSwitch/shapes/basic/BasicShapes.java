package application.colorSwitch.shapes.basic;

import java.util.ArrayList;
import application.colorSwitch.shapes.AppShapes;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

public abstract class BasicShapes extends AppShapes{
	
	public abstract ArrayList<Shape> getShapesWithOppositeColor(Paint paint);
	public abstract Shape[] getShape();
	
	protected int speedLevel() {
		switch(gameLevel) {
		case EASY : return 1;
		case MEDIUM : return 2;
		case HARD : return 3;
		default : return 0;
		}
	}
}
