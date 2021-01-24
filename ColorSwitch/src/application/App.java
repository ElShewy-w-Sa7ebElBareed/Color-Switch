package application;

import application.colorSwitch.ColorSwitchGame;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
	@SuppressWarnings("exports")
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setScene(new ColorSwitchGame().getGameScene());
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
