package application;
	
import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;


public class App extends Application {
	final double length = 800, width = 400;
	public static Level gameLevel = Level.EASY;
	ArrayList<AppShape> levelShapes = new ArrayList<AppShape>();
	Ball b = new Ball();
	@Override
	public void start(Stage primaryStage) {
		try {
			Group root = new Group();  
			Scene scene = new Scene(root,width,length);
			Circle circle = b.buildCircle(root, 200, 600, 20);
		    root.getChildren().addAll(new CircleShape(width/2,150,90).getShape());
		    for (AppShape shape : levelShapes) {
		    	root.getChildren().addAll(shape.getShape());
		    }
		    root.getChildren().addAll(circle);
		    b.Jump(40, scene, circle);
			b.fall(2, circle);
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
