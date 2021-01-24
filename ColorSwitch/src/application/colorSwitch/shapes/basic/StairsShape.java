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
	private final int width = 400,lineNums = 4,SpeedStage = 4;
	private int SpinSpeed;
	private Line[] lineShapes ;
	
	public StairsShape (double y) {
		lineShapes = new Line[lineNums];
		this.SpinSpeed = SpeedStage * speedLevel(gameLevel);
		initialize(y);
	}
	
	private int speedLevel(Level level) {
		switch(level) {
		case EASY : return 1;
		case MEDIUM : return 2;
		case HARD : return 3;
		default : return 0;
		}
	}
	
	private void initialize(double y ){
		for (int i = 0 ; i < lineNums;i++) {
			lineShapes[i] = new Line();
			setLine(lineShapes[i],width-(i+1)*lineLength,y+StrokeWidth*i,AppColor[i]);
		}
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				Spin();
			}
		});
		t.start();
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
					for (int i = 0 ; i < lineNums ; i++) {
						double tempStartX = lineShapes[i].getStartX()+SpinSpeed;
						if (tempStartX >=  width) {
							tempStartX = -200+StrokeWidth/2;
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
