package application.colorSwitch.shapes.basic;

import java.util.ArrayList;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;

public class LineShape extends BasicShapes{
	private final double StrokeWidth = 15,lineLength = 150;
	private final int lineNums = 4,SpeedStage = 4;
	private int SpinSpeed;
	private Line[] lineShapes ;
	private double y ;
	
	public LineShape (double y) {
		this.y = y;
		lineShapes = new Line[lineNums];
		this.SpinSpeed = SpeedStage * speedLevel();
		initialize(y);
	}
	
	private void initialize(double y ){
		for (int i = 0 ; i < lineNums;i++) {
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
					for (int i = 0 ; i < lineNums ; i++) {
						double tempStartX = lineShapes[i].getStartX()+SpinSpeed;
						if (tempStartX >=  ScreenWidth) {
							tempStartX = ScreenWidth-lineNums*lineLength+StrokeWidth/2;
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
	public ArrayList<Shape> getShapesWithOppositeColor(Paint paint) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Shape[] getShape() {
		return lineShapes;
	}

	@Override
	public void Move(double stageStep) {
		// TODO Auto-generated method stub
		
	}

}