package application.colorSwitch.shapes.basic;

import java.util.ArrayList;
import java.util.Arrays;

import application.colorSwitch.shapes.additional.StarShape;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;

public class LineShape extends BasicShapes{
	private final double StrokeWidth = 15,lineLength = 150;
	private final int SpeedStage = 4;
	private int SpinSpeed;
	private Line[] lineShapes ;
	private StarShape star;
	private double y ;
	
	public LineShape (double y) {
		this.y = y;
		lineShapes = new Line[ShapeNums];
		this.SpinSpeed = SpeedStage * speedLevel();
		star = new StarShape(y-2*StrokeWidth);
		initialize(y);
	}
	
	private void initialize(double y ){
		for (int i = 0 ; i < ShapeNums;i++) {
			lineShapes[i] = new Line();
			setLine(lineShapes[i],ScreenWidth-(i+1)*lineLength,AppColor[i]);
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
	
	private void setLine(Line line,double startX,Color color ) {
		line.setStartX(startX+StrokeWidth/2);
		line.setStartY(y);
		line.setEndX(startX+lineLength-StrokeWidth/2);
		line.setEndY(y);
		line.setStroke(color);
	    line.setStrokeType(StrokeType.CENTERED);
	    line.setStrokeWidth(StrokeWidth);
	    
	}
	
	private synchronized void Spin() {
		while (true) {
			try {
				wait(50);
				Platform.runLater(()->{
					for (int i = 0 ; i < ShapeNums ; i++) {
						double tempStartX = lineShapes[i].getStartX()+SpinSpeed;
						if (tempStartX >=  ScreenWidth) {
							tempStartX = ScreenWidth-ShapeNums*lineLength+StrokeWidth/2;
						}
						lineShapes[i].setStartX(tempStartX+StrokeWidth/2);
						lineShapes[i].setEndX(tempStartX+lineLength-StrokeWidth/2);
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
		double start = y;
		while ((y-start)<stageStep) {
			try {
				wait(5);
				y++;
				Platform.runLater(()->{
					for (Line temp : lineShapes ) {
						temp.setStartY(y);
						temp.setEndY(y);
					}
				});
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public ArrayList<Shape> getShapesWithOppositeColor(Paint paint) {
		return shapesVsColor(paint, lineShapes);
	}

	@Override
	public Shape[] getShape() {
		ArrayList<Shape> shapes = new ArrayList<>(Arrays.asList(lineShapes));
		shapes.add(star.getShape());
		return shapes.toArray(new Shape[0]);
	}

}
