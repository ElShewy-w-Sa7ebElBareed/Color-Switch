package application.colorSwitch;

import java.io.File;
import java.util.ArrayList;
import application.colorSwitch.data.AppData;
import application.colorSwitch.shapes.Ball;
import application.colorSwitch.shapes.additional.ChangeColorBall;
import application.colorSwitch.shapes.additional.StarShape;
import application.colorSwitch.shapes.basic.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;

public class ColorSwitchGame extends AppData {

	private Scene gameScene;
	
	//the next two lines must be removed
	ArrayList<BasicShapes> levelShapes = new ArrayList<BasicShapes>();
	public static RectangleShapes r = new RectangleShapes();
	public ColorSwitchGame() {
		String path = "sound.mp3";
        Media pick = new Media(new File(path).toURI().toString()); // replace this with your own audio file
        MediaPlayer player = new MediaPlayer(pick);
        MediaView mediaView = new MediaView(player);
		Group root = new Group(mediaView);  
		gameScene = new Scene(root,ScreenWidth,ScreenLength);
		AppBall = new Ball(gameScene, 200, 600, 10, Color.RED);
		Image background = new Image("https://orsted.com/-/media/WWW/Images/Corp/Campaign/SpaceSafari/space-safari-background.png");
		ImageView vm = new ImageView(background);
		root.getChildren().addAll(vm);
		levelShapes.add(new CircleShape(ScreenWidth/2,150,90));
		levelShapes.add(new LineShape(250));
		levelShapes.add(new StairsShape(400));
	    for (BasicShapes shape : levelShapes) {
	    	root.getChildren().addAll(shape.getShape());
	    }
	    //root.getChildren().addAll(new StarShape(150).getShape(),new ChangeColorBall(ScreenWidth/2, 400, 20).getShape());
		root.getChildren().addAll(AppBall.getShape());
		root.getChildren().addAll(r.getShape());
		// Play the media once the stage is shown
        player.play();
	}

	public Scene getGameScene() {
		return gameScene;
	}
}
