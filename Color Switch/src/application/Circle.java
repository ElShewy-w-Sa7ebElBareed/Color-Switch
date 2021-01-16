package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.StrokeType;

public class Circle {
	Arc[] arcShapes;
	Color[] arcColor = {Color.RED,Color.BLUE,Color.YELLOW,Color.GREEN};
	int arcNums = 4;
	double Angle = 0; 
	
	public Circle(double CenterX,double CenterY,double Radius) {
		arcShapes = new Arc[arcNums];
		for (int i = 0 ; i < arcNums ; i++) {
			arcShapes[i] = new Arc();
			setArc(arcShapes[i],CenterX,CenterY,Radius,Angle,arcColor[i]);
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
	
	private void setArc(Arc arc,double CenterX,double CenterY,double Radius,double Angle,Color color) {
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
				for (int i = 0 ; i < arcNums ; i++) {
					double tempAngle = arcShapes[i].getStartAngle()+5;
					arcShapes[i].setStartAngle(tempAngle);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Arc[] getShape() {
		return arcShapes;
	}

}
