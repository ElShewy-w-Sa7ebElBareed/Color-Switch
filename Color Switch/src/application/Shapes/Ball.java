package application.Shapes;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball {
	boolean Top = false;
	int clicks=0;
	Object LOCK = new Object();
	public Circle buildCircle(Group group, double CenterX, double centerY, double raduis) {
		Circle circle = new Circle();
		circle.setCenterX(CenterX);
		circle.setCenterY(centerY);
		circle.setRadius(raduis);
		circle.setFill(Color.RED);
		return circle;
	}

	public void Jump(int upDistance, Scene scene, Circle circle) {
		EventHandler<MouseEvent> MouseClick = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				//System.out.println("before");
				clicks++;
				Thread t2 = new Thread(new Runnable() {

					@Override
					public void run() {
						//System.out.println("Inn");
						Up(upDistance, circle);
					}
				});
				t2.start();
				//System.out.println("after");
			}
		};
		scene.addEventFilter(MouseEvent.MOUSE_CLICKED, MouseClick);
	}
	
	private void Up(int upDistance, Circle circle) {
		synchronized (LOCK) 
        { 
		//System.out.println("In");
		Top = true;
		double start = circle.getCenterY() ;
		while (start-(circle.getCenterY()) < upDistance) {
			try {
				LOCK.wait(20);
				
				Platform.runLater(()->{
					circle.setCenterY(circle.getCenterY() - 2) ;
				});
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//LOCK.notify();
		Top = false;
		clicks--;
		if(clicks==0) {
		fall(2, circle);}
        }
	}
	
	public void fall(int downDistance, Circle circle) {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				Down(downDistance, circle);
			}
		});
		t.start();
	}
/*
	private void Down(int downDistance, Circle circle) {
		
		while (true) {
			synchronized (LOCK) 
	        { 
			try {
				System.out.print("");
				if (circle.getCenterY() < 600 && !Top ) {
					LOCK.wait(20);
					circle.setCenterY(circle.getCenterY() + downDistance);
					//System.out.println("Thread");
				}/*else {
					LOCK.wait();
				}*/
		/*	} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
	}
		}*/
	private void Down(int downDistance, Circle circle) {
		synchronized (LOCK) 
        { 
		while (circle.getCenterY() < 600 && !Top) {
			try {
				LOCK.wait(20);
				
				Platform.runLater(()->{
					circle.setCenterY(circle.getCenterY() + downDistance) ;
				});
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
        }
	}
	
}