package application.colorSwitch.data;

import application.colorSwitch.shapes.basic.BasicShapes;
import application.colorSwitch.shapes.basic.CircleShape;
import application.colorSwitch.shapes.basic.LineShape;
import application.colorSwitch.shapes.basic.RectangleShapes;
import application.colorSwitch.shapes.basic.StairsShape;

public class Factory {
	public static BasicShapes getShape(int a) {
		switch(a) {
		case 0:
		    return new CircleShape(200,-90,90);
		case 1:
		    return new LineShape(-50);
		case 2:
			 return  new RectangleShapes(200,-150);
		case 3:
			return new StairsShape(-125);
		default:
		    return null;
		}
	}
}
