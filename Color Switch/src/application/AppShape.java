package application;

import javafx.scene.shape.Shape;

public abstract class AppShape{
	
	public abstract void Move (double stageStep);
	
	public abstract Shape[] getShape();
	
	//public abstract boolean isBallHit();
}
