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
				System.out.println("before");
				Thread t2 = new Thread(new Runnable() {

					@Override
					public void run() {
						//System.out.println("Inn");
						Up(upDistance, circle);
					}
				});
				t2.start();
				System.out.println("after");
			}
		};
		scene.addEventFilter(MouseEvent.MOUSE_CLICKED, MouseClick);
	}
	
	private synchronized void Up(int upDistance, Circle circle) {
		System.out.println("In");
		double start = circle.getCenterY() ;
		Top = true;
		while ((circle.getCenterY()-start) < upDistance) {
			try {
				wait(10);
				
				Platform.runLater(()->{
					circle.setCenterY(circle.getCenterY() + 1) ;
				});
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
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

	private synchronized void Down(int downDistance, Circle circle) {
		while (true) {
			try {
				System.out.print("");
				if (circle.getCenterY() < 600 && !Top) {
					wait(50);
					circle.setCenterY(circle.getCenterY() + downDistance);
					//System.out.println("Thread");
				}
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
	}
}