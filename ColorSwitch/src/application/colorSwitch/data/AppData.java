package application.colorSwitch.data;

import application.colorSwitch.shapes.Ball;
import javafx.scene.paint.Color;

public abstract class AppData {
	
	protected final double ScreenLength = 800, ScreenWidth = 400;
	protected Level gameLevel = Level.EASY;
	protected Color[] AppColor = { Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN };
	protected static Ball AppBall;

}
