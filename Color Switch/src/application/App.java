package application;
	
import java.util.ArrayList;

import application.Shapes.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;


public class App extends Application {
	final double length = 800, width = 400;
	public static Level gameLevel = Level.EASY;
	ArrayList<AppShape> levelShapes = new ArrayList<AppShape>();
	public static Ball AppBall = new Ball();
	//should be removed we need class Ball to do every thing with no need to declare circle here 
	//in Addition jump and fall
	public static Circle circle;
	@Override
	public void start(Stage primaryStage) {
		try {
			Group root = new Group();  
			Scene scene = new Scene(root,width,length);
			circle = AppBall.buildCircle(root, 200, 600, 20);
		    root.getChildren().addAll(new CircleShape(width/2,150,90).getShape());
		    for (AppShape shape : levelShapes) {
		    	root.getChildren().addAll(shape.getShape());
		    }
		    root.getChildren().addAll(circle);
		    AppBall.Jump(50, scene, circle);
		    AppBall.fall(4, circle);
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
