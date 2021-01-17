package application.Shapes.Additional;

import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class ChangeColorBall extends AdditionalShapes {
	// double CenterX, CenterY, Radius;
	private Circle circle;

	public ChangeColorBall(double CenterX, double CenterY, double Radius) {
		/*
		 * this.CenterX = CenterX; this.CenterY = CenterY; this.Radius = Radius;
		 */
		Stop[] stops = new Stop[] { new Stop(0, Color.GREEN), new Stop(0.35, Color.BLUE), new Stop(0.7, Color.YELLOW),
				new Stop(1, Color.RED) };
		LinearGradient linear = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
		/*
		 * RadialGradient gradient1 = new RadialGradient(0, .1, CenterX, CenterY,
		 * Radius, false, CycleMethod.NO_CYCLE, stops);
		 */
		circle = new Circle(CenterX, CenterY, Radius);
		circle.setFill(linear);
	}

	@Override
	public void Move(double stageStep) {

	}

	@Override
	public Shape getShape() {
		return circle;
	}

}
