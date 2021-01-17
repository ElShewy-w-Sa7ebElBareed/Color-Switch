package application.Shapes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class ChangeColorBall extends AdditionalShapes {
	// double CenterX, CenterY, Radius;
	private Circle circle;

	public ChangeColorBall(double CenterX, double CenterY, double Radius) {
		/*
		 * this.CenterX = CenterX; this.CenterY = CenterY; this.Radius = Radius;
		 */
		circle = new Circle();
		circle.setCenterX(CenterX);
		circle.setCenterY(CenterY);
		circle.setRadius(Radius);
		circle.setFill(Color.RED);
	}

	@Override
	public void Move(double stageStep) {

	}

	@Override
	public Shape getShape() {
		return circle;
	}

}
