package application.Shapes;

import javafx.scene.shape.Shape;

public abstract class AppShape{
	
	public abstract void Move (double stageStep);
	
	public abstract Shape[] getShape();
	
	public boolean CheckHit (Shape s1 , Shape s2) {
		Shape intersect = Shape.intersect(s1, s2);
		if (intersect.getBoundsInLocal().getWidth() != -1)
			return true;
		return false;
	}
}
