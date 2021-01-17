package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;


public class Main extends Application {
	final double length = 800, width = 400;
	public static Level gameLevel = Level.EASY;
	@Override
	public void start(Stage primaryStage) {
		try {
			Group root = new Group();  
			Scene scene = new Scene(root,width,length);
		    root.getChildren().addAll(new CircleShape(width/2,150,90).getShape());
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
