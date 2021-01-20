package application;
	
import java.io.File;
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
import javafx.scene.image.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class App extends Application {
	final double length = 800, width = 400;
	public static Level gameLevel = Level.EASY;
	ArrayList<BasicShapes> levelShapes = new ArrayList<BasicShapes>();
	@SuppressWarnings("exports")
	public static RectangleShapes r = new RectangleShapes();
	//should be removed we need class Ball to do every thing with no need to declare circle here 
	//in Addition jump and fall
	//public static Circle circle;
	@SuppressWarnings("exports")
	public static Ball ball;
	@SuppressWarnings("exports")
	@Override
	public void start(Stage primaryStage) {
		try {
			String path = "C:\\Users\\Ahmed Ashraf\\git\\Color-Switch\\Color Switch\\src\\sound.mp3";
	        Media pick = new Media(new File(path).toURI().toString()); // replace this with your own audio file
	        MediaPlayer player = new MediaPlayer(pick);

	        // Add a mediaView, to display the media. Its necessary !
	        // This mediaView is added to a Pane
	        MediaView mediaView = new MediaView(player);
			Group root = new Group(mediaView);  
			Scene scene = new Scene(root,width,length);
			ball = new Ball(scene, 200, 600, 10, Color.RED);
			Image background = new Image("https://orsted.com/-/media/WWW/Images/Corp/Campaign/SpaceSafari/space-safari-background.png");
			ImageView vm = new ImageView(background);
			root.getChildren().addAll(vm);
			//circle = (Circle) ball.getShape();
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
			primaryStage.setScene(scene);
		//	primaryStage.setResizable(false);
			primaryStage.show();
			// Play the media once the stage is shown
	        player.play();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
