package application.colorSwitch.shapes.basic;

import java.util.ArrayList;
import application.colorSwitch.data.Level;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;

public class StairsShape extends BasicShapes{
	private final double StrokeWidth = 20,lineLength = 150;
	private final int SpeedStage = 4;
	private int SpinSpeed;
	private Line[] lineShapes ;
	private double y;
	
	public StairsShape (double y) {
		this.y = y;
		lineShapes = new Line[ShapeNums];
		this.SpinSpeed = SpeedStage * speedLevel(gameLevel);
		initialize();
	}
	
	private int speedLevel(Level level) {
		switch(level) {
		case EASY : return 1;
		case MEDIUM : return 2;
		case HARD : return 3;
		default : return 0;
		}
	}
	
	private void initialize(){
		for (int i = 0 ; i < ShapeNums;i++) {
			lineShapes[i] = new Line();
			setLine(lineShapes[i],ScreenWidth-(i+1)*lineLength,y+StrokeWidth*i,AppColor[i]);
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
	
	private void setLine(Line line,double startX,double y,Color color ) {
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
		t2.start();
	}
	
	private synchronized void MoveSpeed(double stageStep) {
		double start = y;
		while ((y-start)<stageStep) {
			try {
				wait(5);
				y++;
				Platform.runLater(()->{
					for (int i = 0 ; i < ShapeNums ; i++) {
						Line temp = lineShapes[i];
						temp.setStartY(y+StrokeWidth*i);
						temp.setEndY(y+StrokeWidth*i);
					}
				});
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public ArrayList<Shape> getShapesWithOppositeColor(Paint paint) {
		return shapesVsColor(paint,lineShapes);
	}

	@Override
	public Shape[] getShape() {
		return lineShapes;
	}

}