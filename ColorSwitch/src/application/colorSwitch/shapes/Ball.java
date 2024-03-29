package application.colorSwitch.shapes;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import application.colorSwitch.ColorSwitchGame;
import application.colorSwitch.shapes.additional.AdditionalShapes;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class Ball extends AdditionalShapes {
	boolean Top = false;
	int clicks=0;
	Object LOCK = new Object();
	//Object LOCK2 = new Object();
	private Circle circle;
	public Ball(Scene scene,double CenterX, double centerY, double raduis,Color color) {
		circle=buildCircle(CenterX, centerY, raduis, color);
		Jump(50, scene, circle);		
	}
	public Circle buildCircle(double CenterX, double centerY, double raduis,Color color) {
		Circle circle = new Circle();
		circle.setCenterX(CenterX);
		circle.setCenterY(centerY);
		circle.setRadius(raduis);
		circle.setFill(color);
		return circle;
	}

	public void Jump(int upDistance, Scene scene, Circle circle) {
		EventHandler<MouseEvent> MouseClick = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				//System.out.println("before");
				String path = "Click.mp3";
				AudioClip ALERT_AUDIOCLIP = new AudioClip(new File(path).toURI().toString());
				ALERT_AUDIOCLIP.play();
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
		while (circle.getCenterY() > 500 && start-(circle.getCenterY()) < upDistance) {
			try {
				LOCK.wait(10);
				
				Platform.runLater(()->{
					circle.setCenterY(circle.getCenterY() - 2) ;
					ColorSwitchGame.hit();
				});
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(circle.getCenterY() <= 500) {
			double d = upDistance - (start-(circle.getCenterY()));
			while(d>0) {
				try {
					ColorSwitchGame.move(d);
					for (int i = 0 ; i< d/2 ; i++) {
						LOCK.wait(10);
						Platform.runLater(()->{
						ColorSwitchGame.hit();
						});
					}
					//LOCK.wait((long) (10*d/2));
					//move
					d-=d;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
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
		while (circle.getCenterY() < 750 && !Top) {
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

	@Override
	public Shape getShape() {
		return circle;
	}
	
	@Override
	public void Move(double stageStep) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean CheckHit( ArrayList<Shape> s2) {
		for (Shape s : s2) {
			Shape intersect = Shape.intersect(circle, s);
			if (intersect.getBoundsInLocal().getWidth() != -1) {
				System.out.println("Game Over");
				return true;
			}	
		}
		return false;
	}
	
	
	public void changeColor () {
		circle.setFill(generateRandomColor());
	}
	
	
	private Color generateRandomColor() {
		Random rand = new Random(); //instance of random class
	    int upperbound = 4;
	        //generate random values from 0-3
	    int int_random = rand.nextInt(upperbound);
		Color c = AppColor[int_random];
		while(c.equals(circle.getFill())) {
			 int_random = rand.nextInt(upperbound);
			 c = AppColor[int_random];
		}
		return c;
	}
	
	
}