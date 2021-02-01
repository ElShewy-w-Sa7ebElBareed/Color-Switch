package application.colorSwitch.shapes.additional;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class ChangeColorBall extends AdditionalShapes {
	// double CenterX, CenterY, Radius;
	private Circle circle;
	private double CenterX = ScreenWidth/2,Radius = 15,CenterY;

	public ChangeColorBall(double CenterY) {
		this.CenterY = CenterY;
		Stop[] stops = new Stop[] { new Stop(0, Color.GREEN), new Stop(0.35, Color.BLUE), new Stop(0.7, Color.YELLOW),
				new Stop(1, Color.RED) };
		LinearGradient linear = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
		/*
		 * RadialGradient gradient1 = new RadialGradient(0, .1, CenterX, CenterY,
		 * Radius, false, CycleMethod.NO_CYCLE, stops);
		 */
		circle = new Circle(CenterX, CenterY, Radius);
		circle.setFill(linear);
	}

	@Override
	public void Move(double stageStep) {
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				MoveSpeed(stageStep);
			}
		});
		t2.start();
	}
	
	private synchronized void MoveSpeed(double stageStep) {
		double start = CenterY;
		while ((CenterY-start)<stageStep) {
			try {
				wait(10);
				CenterY+=2;
				Platform.runLater(()->{
					circle.setCenterY(CenterY);
				});
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Shape getShape() {
		return circle;
	}

}
