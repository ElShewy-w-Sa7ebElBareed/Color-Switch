package application;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball {
	public Circle buildCircle(Group group,double CenterX,double centerY,double raduis) {
		Circle circle = new Circle();
		circle.setCenterX(CenterX);
		circle.setCenterY(centerY);
		circle.setRadius(raduis);
		circle.setFill(Color.RED);
		return circle;
	}
	
	
	public void  Jump(int upDistance , Scene scene , Circle circle){
		EventHandler<MouseEvent> MouseClick = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				circle.setCenterY(circle.getCenterY()-upDistance);
				System.out.println(circle.getCenterY());
			}
		};
		scene.addEventFilter(MouseEvent.MOUSE_CLICKED, MouseClick);
	}
	
	
	public void fall(int downDistance , Circle circle) {
        Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				Down(downDistance , circle);
			}
		});
		t.start();
	}
	
	private synchronized Object Down(int downDistance , Circle circle) {
		while(true) {
			try {
				System.out.println("Ahmed");
				if(circle.getCenterY()<600) {
				wait(100);
				circle.setCenterY(circle.getCenterY()+downDistance);
				System.out.println("Thread");}
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
	}
}
