package application;
	
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			/*Thread T1 = new Thread (new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					BorderPane root1 = new BorderPane();
					Scene scene1 = new Scene(root,400,400);
					Stage x = new Stage();
					x.setScene(scene);
					x.show();
				}
				
			});
			T1.start();*/
			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					BorderPane root1 = new BorderPane();
					Scene scene1 = new Scene(root1,40,40);
					Stage x = new Stage();
					x.setScene(scene1);
					x.show();
					while (true) {
						System.out.println("1");
					}
				}
				
			});
			/*while (true) {
				System.out.println("2");
			}*/
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
