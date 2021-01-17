package application.Shapes;

import application.Level;
import application.App;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;

public class CircleShape extends AppShape{
	private Arc[] arcShapes;
	private Color[] arcColor = {Color.RED,Color.BLUE,Color.YELLOW,Color.GREEN};
	private int arcNums = 4 , SpinSpeed;
	private double CenterX, CenterY, Radius,Angle = 0;
	private final int speedStage = 5 ;
	private final double StrokeWidth = 15;
	private final Color BackgroundColor = Color.WHITE;
	
	public CircleShape(double CenterX,double CenterY,double Radius) {
		this.CenterX = CenterX;
		this.CenterY = CenterY;
		this.Radius = Radius;
		this.SpinSpeed = speedStage * speedLevel(App.gameLevel);
		arcShapes = new Arc[arcNums];
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
	
	private void initialize() {
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
		MoveWithSpeed(100);
	}
	
	private void setArc(Arc arc,double Angle,Color color) {
		arc.setCenterX(CenterX); 
	    arc.setCenterY(CenterY); 
	    arc.setRadiusX(Radius); 
	    arc.setRadiusY(Radius); 
	    arc.setStartAngle(Angle); 
	    arc.setLength(360/arcNums);
	    arc.setFill(BackgroundColor);
	    arc.setStroke(color);
	    arc.setStrokeType(StrokeType.INSIDE);
	    arc.setStrokeWidth(StrokeWidth);
	    arc.setType(ArcType.OPEN);
	}
	
	private synchronized void Spin() {
		while (true) {
			try {
				wait(50);
				Platform.runLater(()->{
					for (int i = 0 ; i < arcNums ; i++) {
						double tempAngle = arcShapes[i].getStartAngle()+SpinSpeed;
						arcShapes[i].setStartAngle(tempAngle);
						if (CheckHit(arcShapes[i],App.circle)) {
							System.out.println("Hit Succesfully with Arc: "+ i);
						}
					}
				});
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	//******************** need modify ************************\\
	
	/*private boolean isBallHit() {
		double differenceY = Math.abs(App.circle.getCenterY()-CenterY);
		return ((differenceY<Radius+App.circle.getRadius())&&(differenceY>Radius-App.circle.getRadius()));
	}*/
	
	public void MoveWithSpeed(double stageStep) {
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
				CenterY++;
				Platform.runLater(()->{
					for (int i = 0 ; i < arcNums ; i++) {
						arcShapes[i].setCenterY(CenterY);
					}
				});
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void Move (double stageStep) {
		CenterY+=stageStep;
		Platform.runLater(()->{
			for (int i = 0 ; i < arcNums ; i++) {
				arcShapes[i].setCenterY(CenterY);
			}
		});
	}
	
	public Shape[] getShape() {
		return arcShapes;
	}

}
