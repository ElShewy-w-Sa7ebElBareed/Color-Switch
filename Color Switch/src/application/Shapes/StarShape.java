package application.Shapes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class StarShape extends AppShape {
	
	private double CenterX = 200 ,CenterY = 150 ,TopLength = 20;
	private Polygon polygon; 
	
	public StarShape() {
		polygon = new Polygon(handlePoints(CenterX,CenterY,TopLength));	
		polygon.setFill(Color.GOLD);
	}
	
	
	private double[] handlePoints(double X,double Y ,double L) {
		double[] handledPoints = {  
		        X,(Y-L),
		        X-(L/2)*Math.cos(Math.toRadians(54)), Y - (L/2)*Math.sin(Math.toRadians(54)),
		        X - L * Math.cos(Math.toRadians(18)), Y - L*Math.sin(Math.toRadians(18)),
		        X-(L/2)*Math.cos(Math.toRadians(18)), Y + (L/2)*Math.sin(Math.toRadians(18)),
		        X - L * Math.cos(Math.toRadians(54)), Y + L*Math.sin(Math.toRadians(54)),
		        X , Y + (L/2),
		        X + L * Math.cos(Math.toRadians(54)), Y + L*Math.sin(Math.toRadians(54)),
		        X+(L/2)*Math.cos(Math.toRadians(18)), Y + (L/2)*Math.sin(Math.toRadians(18)),
		        X + L * Math.cos(Math.toRadians(18)), Y - L*Math.sin(Math.toRadians(18)),
		        X+(L/2)*Math.cos(Math.toRadians(54)), Y - (L/2)*Math.sin(Math.toRadians(54))} ;
		return handledPoints;
	}
	

	@Override
	public void Move(double stageStep) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Shape[] getShape() {
		Shape[] s = new Shape[1];
		s[0] = polygon;
		return s;
	}

}
