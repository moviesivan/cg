package application.figures;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.paint.Color;
import math.Vector;

public class CoordsSystemFigure extends Figure {
	public CoordsSystemFigure(CheckBox[] params) {
		super();

		this.paramsCheck = params;
	}

	@Override
	public void generate() {

	}

	@Override
	public void draw(Canvas canvas) {
		final double AXIS_SIZE = 600;
		GraphicsContext gc = canvas.getGraphicsContext2D();

		Vector axisCenter = new Vector(0.0, 0.0, 0.0, 1.0);
		Vector axisPointX = new Vector(AXIS_SIZE, 0.0, 0.0, 1.0);
		Vector axisPointY = new Vector(0.0, AXIS_SIZE, 0.0, 1.0);
		Vector axisPointZ = new Vector(0.0, 0.0, AXIS_SIZE, 1.0);

		axisCenter = mat.transform(axisCenter).perspectiveDivide();
		axisPointX = mat.transform(axisPointX).perspectiveDivide();
		axisPointY = mat.transform(axisPointY).perspectiveDivide();
		axisPointZ = mat.transform(axisPointZ).perspectiveDivide();

		gc.setStroke(Color.RED);
		gc.setLineWidth(1.0);
		if(paramsCheck[0].isSelected()) {
			gc.strokeLine(axisCenter.getX(), axisCenter.getY(), axisPointX.getX(), axisPointX.getY());
		}
		gc.setStroke(Color.GREEN);
		if(paramsCheck[1].isSelected()) {
			gc.strokeLine(axisCenter.getX(), axisCenter.getY(), axisPointY.getX(), axisPointY.getY());
		}
		gc.setStroke(Color.BLUE);
		if(paramsCheck[2].isSelected()) {
			gc.strokeLine(axisCenter.getX(), axisCenter.getY(), axisPointZ.getX(), axisPointZ.getY());
		}
	}
}
