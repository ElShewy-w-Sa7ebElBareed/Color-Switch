package application;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.StrokeType;

public class Circle {
	private Arc[] arcShapes;
	private Color[] arcColor = {Color.RED,Color.BLUE,Color.YELLOW,Color.GREEN};
	private int arcNums = 4;
	private double CenterX, CenterY, Radius,Angle = 0;
	
	public Circle(double CenterX,double CenterY,double Radius) {
		this.CenterX = CenterX;
		this.CenterY = CenterY;
		this.Radius = Radius;
		arcShapes = new Arc[arcNums];
		for (int i = 0 ; i < arcNums ; i++) {
			arcShapes[i] = new Arc();
			setArc(arcShapes[i],Angle,arcColor[i]);
			Angle += 360/arcNums; 
		}
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				Spin();
			}
		});
		t.start();
	}
	
	private void setArc(Arc arc,double Angle,Color color) {
		arc.setCenterX(CenterX); 
	    arc.setCenterY(CenterY); 
	    arc.setRadiusX(Radius); 
	    arc.setRadiusY(Radius); 
	    arc.setStartAngle(Angle); 
	    arc.setLength(90);
	    arc.setFill(Color.WHITE);
	    arc.setStroke(color);
	    arc.setStrokeType(StrokeType.INSIDE);
	    arc.setStrokeWidth(15);
	    arc.setType(ArcType.OPEN);
	}
	
	private synchronized void Spin() {
		while (true) {
			try {
				wait(100);
				Platform.runLater(()->{
					for (int i = 0 ; i < arcNums ; i++) {
						double tempAngle = arcShapes[i].getStartAngle()+5;
						arcShapes[i].setStartAngle(tempAngle);
					}
				});
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	private synchronized void Move(double Stage) {
		double start =CenterY;
		
	}
	
	public Arc[] getShape() {
		return arcShapes;
	}

}
