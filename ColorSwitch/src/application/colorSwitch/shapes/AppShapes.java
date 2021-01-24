package application.colorSwitch.shapes;

import java.util.ArrayList;

import application.colorSwitch.data.AppData;
import javafx.scene.shape.Shape;

public abstract class AppShapes extends AppData {

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
