package application.colorSwitch.data;

import application.colorSwitch.shapes.basic.BasicShapes;
import application.colorSwitch.shapes.basic.CircleShape;
import application.colorSwitch.shapes.basic.LineShape;
import application.colorSwitch.shapes.basic.RectangleShapes;
import application.colorSwitch.shapes.basic.StairsShape;

public class Factory {
	public static BasicShapes getShape(int a,double y) {
		switch(a) {
		case 0:
		    return new CircleShape(y);
		case 1:
		    return new LineShape(y);
		case 2:
			 return  new RectangleShapes(y);
		case 3:
			return new StairsShape(y);
		default:
		    return null;
		}
	}
}
