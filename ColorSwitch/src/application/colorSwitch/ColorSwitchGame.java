package application.colorSwitch;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import application.colorSwitch.data.AppData;
import application.colorSwitch.data.Factory;
import application.colorSwitch.shapes.Ball;
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
	private static int pointer = 0 ;
	static //the next two lines must be removed
	ArrayList<BasicShapes> levelShapes = new ArrayList<BasicShapes>();
	//public static RectangleShapes r = new RectangleShapes(150);
	public ColorSwitchGame() {
		String path = "sound.mp3";
        Media pick = new Media(new File(path).toURI().toString()); // replace this with your own audio file
        MediaPlayer player = new MediaPlayer(pick);
        MediaView mediaView = new MediaView(player);
		Group root = new Group(mediaView);  
		gameScene = new Scene(root,ScreenWidth,ScreenLength);
		AppBall = new Ball(gameScene, 200, 625, 10, Color.RED);
		String path2 = "background.jpeg";
		Image background = new Image(new File(path2).toURI().toString());
		ImageView vm = new ImageView(background);
		root.getChildren().addAll(vm);
		levelShapes.add(new RectangleShapes(375));
		levelShapes.add(new CircleShape(125));
		//levelShapes.add(new StairsShape(125));
	    for (BasicShapes shape : levelShapes) {
	    	root.getChildren().addAll(shape.getShape());
	    }
		generate(root);
	    //root.getChildren().addAll(new StarShape(150).getShape(),new ChangeColorBall(ScreenWidth/2, 400, 20).getShape());
		root.getChildren().addAll(AppBall.getShape());
		//root.getChildren().addAll(r.getShape());
		// Play the media once the stage is shown
        player.play();
	}
	
	public static void move(double stageStep) {
		for(BasicShapes s : levelShapes) {
			s.Move(stageStep);
		}
	}
	
	
	public static void generate(Group root) {
		Random rand = new Random(); //instance of random class
	    int upperbound = 4;
	        //generate random values from 0-3
	    int int_random = rand.nextInt(upperbound);
	    double y = levelShapes.get(levelShapes.size()-1).getY();
	    y-=250;
		BasicShapes a = Factory.getShape(int_random,y);
		root.getChildren().addAll(a.getShape());
		levelShapes.add(a);
	}
	 
	public static void hit() {
		if (AppBall.CheckHit(levelShapes.get(pointer).getShapesWithOppositeColor(AppBall.getShape().getFill()))) {
			System.out.println("game Over inside the class");
		}
		if(AppBall.CheckHit(levelShapes.get(pointer).getStar())) {
			System.out.println("score ++");
		}
		if(AppBall.CheckHit(levelShapes.get(pointer).getBall())) {
			AppBall.changeColor();
		}
	}
	
	public Scene getGameScene() {
		return gameScene;
	}
}
