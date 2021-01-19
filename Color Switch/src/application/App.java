package application;
	
import java.util.ArrayList;

import application.Shapes.*;
import application.Shapes.Additional.ChangeColorBall;
import application.Shapes.Additional.StarShape;
import application.Shapes.Basic.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class App extends Application {
	final double length = 800, width = 400;
	public static Level gameLevel = Level.EASY;
	ArrayList<BasicShapes> levelShapes = new ArrayList<BasicShapes>();
	public static RectangleShapes r = new RectangleShapes();
	//should be removed we need class Ball to do every thing with no need to declare circle here 
	//in Addition jump and fall
	public static Circle circle;
	@Override
	public void start(Stage primaryStage) {
		try {
			Group root = new Group();  
			Scene scene = new Scene(root,width,length);
			Ball ball = new Ball(scene, 200, 600, 5, Color.RED);
			circle = (Circle) ball.getShape();
		    //root.getChildren().addAll(new CircleShape(width/2,150,90).getShape());
			levelShapes.add(new CircleShape(width/2,150,90));
			levelShapes.add(new LineShape(200));
			levelShapes.add(new StairsShape(400));
			//levelShapes.add(new StarShape(width/2,150,20));
		    for (BasicShapes shape : levelShapes) {
		    	root.getChildren().addAll(shape.getShape());
		    }
		    root.getChildren().addAll(new StarShape(width/2,150,20).getShape(),new ChangeColorBall(width/2, 400, 20).getShape());
			root.getChildren().addAll(ball.getShape());
			root.getChildren().addAll(r.getShape());
		    //AppBall.Jump(50, scene, circle);
		    //AppBall.fall(2, circle);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
