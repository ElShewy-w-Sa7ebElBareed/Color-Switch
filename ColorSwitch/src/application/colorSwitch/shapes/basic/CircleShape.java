package application.colorSwitch.shapes.basic;

import java.util.ArrayList;
import java.util.Arrays;

import application.colorSwitch.shapes.additional.StarShape;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;

public class CircleShape extends BasicShapes{
	private Arc[] arcShapes;
	private StarShape star; 
	private int SpinSpeed;
	private double CenterX, CenterY, Radius,Angle = 0;
	private final int speedStage = 5 ;
	private final double StrokeWidth = 15;
	private final Color BackgroundColor = Color.BLACK;
	
	public CircleShape(double CenterX,double CenterY,double Radius) {
		this.CenterX = CenterX;
		this.CenterY = CenterY;
		this.Radius = Radius;
		this.SpinSpeed = speedStage * speedLevel();
		arcShapes = new Arc[ShapeNums];
		star = new StarShape(CenterY);
		initialize();
	}
	
	private void initialize() {
		for (int i = 0 ; i < ShapeNums ; i++) {
			arcShapes[i] = new Arc();
			setArc(arcShapes[i],Angle,AppColor[i]);
			Angle += 360/ShapeNums; 
		}
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				Spin();
			}
		});
		t.start();
		Move(100);
	}
	
	private void setArc(Arc arc,double Angle,Color color) {
		arc.setCenterX(CenterX); 
	    arc.setCenterY(CenterY); 
	    arc.setRadiusX(Radius); 
	    arc.setRadiusY(Radius); 
	    arc.setStartAngle(Angle); 
	    arc.setLength(360/ShapeNums);
	    arc.setFill(BackgroundColor);
	    arc.setStroke(color);
	    arc.setStrokeType(StrokeType.INSIDE);
	    arc.setStrokeWidth(StrokeWidth);
	    arc.setType(ArcType.OPEN);
	}
	
	private synchronized void Spin() {
		while (CenterY < 1200) {
			try {
				wait(50);
				Platform.runLater(()->{
					for (int i = 0 ; i < ShapeNums ; i++) {
						double tempAngle = arcShapes[i].getStartAngle()+SpinSpeed;
						arcShapes[i].setStartAngle(tempAngle);
					}
				});
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void Move(double stageStep) {
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				MoveSpeed(stageStep);
			}
		});
		star.Move(stageStep);
		t2.start();
	}
	
	private synchronized void MoveSpeed(double stageStep) {
		double start = CenterY;
		while ((CenterY-start)<stageStep) {
			try {
				wait(5);
				CenterY++;
				Platform.runLater(()->{
					for (Arc temp : arcShapes) {
						temp.setCenterY(CenterY);
					}
				});
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public ArrayList<Shape> getShapesWithOppositeColor(Paint paint) {
		return shapesVsColor(paint, arcShapes);
	}

	@Override
	public Shape[] getShape() {
		ArrayList<Shape> shapes = new ArrayList<>(Arrays.asList(arcShapes));
		shapes.add(star.getShape());
		return shapes.toArray(new Shape[0]);
	}

}
