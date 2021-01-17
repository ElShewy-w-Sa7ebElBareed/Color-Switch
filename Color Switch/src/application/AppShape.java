package application;

import javafx.scene.shape.Shape;

public abstract class AppShape extends Shape{
	
	public abstract void Move (double stageStep);
	
	public abstract Shape[] getShape();
	
}
