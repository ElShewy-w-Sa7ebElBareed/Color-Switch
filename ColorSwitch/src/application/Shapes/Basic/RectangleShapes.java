package application.Shapes.Basic;


import java.util.ArrayList;

import application.App;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;

public class RectangleShapes extends BasicShapes {
	Rectangle rect1,rect2,rect3,rect4;
	int Angel = 5 ;
	int CenterX = 200;
	int CenterY = 150;
	//private final int speedStage = 5 ;
	Rectangle[] Shapes = new Rectangle[4];
		public RectangleShapes() {
		this.rect1 = buildRectangle(130, 80, 125, 15,Color.RED)  ;
		Shapes[0]= rect1;
		this.rect2 = buildRectangle(130, 220, 140, 15,Color.BLUE)  ;
		Shapes[1]= rect2;
		this.rect3 = buildRectangle(255, 80, 15, 140,Color.GREEN)  ;
		Shapes[2]= rect3;
		this.rect4 = buildRectangle(130, 80, 15, 140,Color.YELLOW)  ;
		Shapes[3]= rect4;
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				Spin();
			}
		});
		t.start();
		MoveWithSpeed(100);
	} 
	
    Rotate rotate = new Rotate();  
      
	private synchronized void Spin() {
		while (true) {
			try {
				wait(50);
				Platform.runLater(()->{
					rotate.setAngle(Angel);  
				    rotate.setPivotX(CenterX);  
				    rotate.setPivotY(CenterY);
				    rect1.getTransforms().add(rotate);
					rect2.getTransforms().add(rotate);
				    rect3.getTransforms().add(rotate);
					rect4.getTransforms().add(rotate);
					if (CheckHit(App.ball.getShape(),getShapesWithOppositeColor(App.ball.getShape().getFill()))) {
						System.out.println("game Over inside the class");
					}
				});
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	
	public Rectangle buildRectangle(double CenterX,double CenterY,double Width, double Height,Color color) {
	    	 Rectangle rect=new Rectangle(); //instantiating Rectangle   
	    	    rect.setX(CenterX); //setting the X coordinate of upper left //corner of rectangle   
	    	    rect.setY(CenterY); //setting the Y coordinate of upper left //corner of rectangle   
	    	    rect.setWidth(Width); //setting the width of rectangle   
	    	    rect.setHeight(Height); // setting the height of rectangle
	    	    rect.setFill(color);
	    	return rect;
	    }

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
					for (int i = 0 ; i < 4 ; i++) {
						
						Shapes[i].setY(Shapes[i].getY()+1);
					}
				});
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public Shape[] getShape() {
		return Shapes;
	}

	@Override
	public void Move(double stageStep) {
		CenterY+=stageStep;
		Platform.runLater(()->{
			for (int i = 0 ; i < 4 ; i++) {
				Shapes[i].setY(Shapes[i].getY()+1);
			}
		});
		
	}


	@Override
	public ArrayList<Shape> getShapesWithOppositeColor(Paint paint) {
		ArrayList<Shape> shapes = new ArrayList<Shape>();
		for (Rectangle rect : Shapes) {
			if (!rect.getFill().equals(paint)) {
				shapes.add(rect);
			}
		}
		//System.out.println(shapes);
		return shapes;
	}

}
