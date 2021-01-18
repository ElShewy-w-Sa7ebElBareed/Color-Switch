package application;
	
import java.util.ArrayList;

import application.Shapes.*;
import application.Shapes.Additional.ChangeColorBall;
import application.Shapes.Additional.StarShape;
<<<<<<< HEAD
import application.Shapes.Basic.*;
=======
import application.Shapes.Basic.BasicShapes;
import application.Shapes.Basic.CircleShape;
import application.Shapes.Basic.RectangleShapes;
>>>>>>> branch 'master' of https://github.com/ElShewy-w-Sa7ebElBareed/Color-Switch.git
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;


public class App extends Application {
	final double length = 800, width = 400;
	public static Level gameLevel = Level.EASY;
	ArrayList<BasicShapes> levelShapes = new ArrayList<BasicShapes>();
	public static Ball AppBall = new Ball();
	public static RectangleShapes r = new RectangleShapes();
	//should be removed we need class Ball to do every thing with no need to declare circle here 
	//in Addition jump and fall
	public static Circle circle;
	@Override
	public void start(Stage primaryStage) {
		try {
			Group root = new Group();  
			Scene scene = new Scene(root,width,length);
			
			circle = AppBall.buildCircle(root, 200, 600, 20);
		    //root.getChildren().addAll(new CircleShape(width/2,150,90).getShape());
			levelShapes.add(new CircleShape(width/2,150,90));
			levelShapes.add(new LineShape(200));
			levelShapes.add(new StairsShape(400));
			//levelShapes.add(new StarShape(width/2,150,20));
		   /* for (BasicShapes shape : levelShapes) {
		    	root.getChildren().addAll(shape.getShape());
		    }*/
		   // root.getChildren().addAll(circle,new StarShape(width/2,150,20).getShape(),new ChangeColorBall(width/2, 400, 20).getShape());
			root.getChildren().addAll(circle);
			root.getChildren().addAll(r.getShape());
		    AppBall.Jump(90, scene, circle);
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
