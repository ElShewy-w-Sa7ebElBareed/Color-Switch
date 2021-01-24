package application.colorSwitch.shapes.additional;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class StarShape extends AdditionalShapes {

	private double y, Length = 20 ;
	private Polygon polygon;

	public StarShape(double y) {
		this.y = y;
		polygon = new Polygon();
		polygon.getPoints().addAll(handlePoints(ScreenWidth / 2, y, Length));
		polygon.setFill(Color.GOLD);
	}

	private Double[] handlePoints(double X, double Y, double L) {
		Double[] handledPoints = { X, (Y - L), X - (L / 2) * Math.cos(Math.toRadians(54)),
				Y - (L / 2) * Math.sin(Math.toRadians(54)), X - L * Math.cos(Math.toRadians(18)),
				Y - L * Math.sin(Math.toRadians(18)), X - (L / 2) * Math.cos(Math.toRadians(18)),
				Y + (L / 2) * Math.sin(Math.toRadians(18)), X - L * Math.cos(Math.toRadians(54)),
				Y + L * Math.sin(Math.toRadians(54)), X, Y + (L / 2), X + L * Math.cos(Math.toRadians(54)),
				Y + L * Math.sin(Math.toRadians(54)), X + (L / 2) * Math.cos(Math.toRadians(18)),
				Y + (L / 2) * Math.sin(Math.toRadians(18)), X + L * Math.cos(Math.toRadians(18)),
				Y - L * Math.sin(Math.toRadians(18)), X + (L / 2) * Math.cos(Math.toRadians(54)),
				Y - (L / 2) * Math.sin(Math.toRadians(54)) };
		return handledPoints;
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
		double start = y;
		while ((y-start)<stageStep) {
			try {
				wait(5);
				y++;
				Platform.runLater(()->{
					polygon.getPoints().clear();
					polygon.getPoints().addAll(handlePoints(ScreenWidth / 2, y, Length));
				});
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public Shape getShape() {
		return polygon;
	}

}
