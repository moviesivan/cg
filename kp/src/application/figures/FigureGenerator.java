package application.figures;

import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import math.Matrix;
import math.Vector;
import static java.lang.Math.sqrt;

public class FigureGenerator extends Figure {
	public FigureGenerator(Slider[] params, TextField[] points) {
		super();

		this.params = params;
		this.pointsText = points;
	}

	@Override
	public void generate() {
		int paramStep = (int)params[0].getValue();
		double paramAngle = params[1].getValue();
		double step = paramAngle / paramStep;
		Double p1X,p1Y,p1Z,p2X,p2Y,p2Z,p3X,p3Y,p3Z,p4X,p4Y,p4Z;
		try{
			p1X = Double.parseDouble(pointsText[0].getText());
		}catch (NumberFormatException e){
			pointsText[0].setText("1.0");
			p1X = Double.parseDouble(pointsText[0].getText());
		}
		try{
			 p1Y = Double.parseDouble(pointsText[1].getText());
		}catch (NumberFormatException e){
			pointsText[1].setText("1.0");
			 p1Y = Double.parseDouble(pointsText[1].getText());
		}
		try{
			 p1Z = Double.parseDouble(pointsText[2].getText());
		}catch (NumberFormatException e){
			pointsText[2].setText("1.0");
			 p1Z = Double.parseDouble(pointsText[2].getText());
		}
		try{
			 p2X = Double.parseDouble(pointsText[3].getText());
		}catch (NumberFormatException e){
			pointsText[3].setText("1.0");
			 p2X = Double.parseDouble(pointsText[3].getText());
		}
		try{
			 p2Y = Double.parseDouble(pointsText[4].getText());
		}catch (NumberFormatException e){
			pointsText[4].setText("1.0");
			 p2Y = Double.parseDouble(pointsText[4].getText());
		}
		try{
			 p2Z = Double.parseDouble(pointsText[5].getText());
		}catch (NumberFormatException e){
			pointsText[5].setText("1.0");
			 p2Z = Double.parseDouble(pointsText[5].getText());
		}
		try{
			 p3X = Double.parseDouble(pointsText[6].getText());
		}catch (NumberFormatException e){
			pointsText[6].setText("1.0");
			 p3X = Double.parseDouble(pointsText[6].getText());
		}
		try{
			 p3Y = Double.parseDouble(pointsText[7].getText());
		}catch (NumberFormatException e){
			pointsText[7].setText("1.0");
			 p3Y = Double.parseDouble(pointsText[7].getText());
		}
		try{
			 p3Z = Double.parseDouble(pointsText[8].getText());
		}catch (NumberFormatException e){
			pointsText[8].setText("1.0");
			 p3Z = Double.parseDouble(pointsText[8].getText());
		}
		try{
			 p4X = Double.parseDouble(pointsText[9].getText());
		}catch (NumberFormatException e){
			pointsText[9].setText("1.0");
			 p4X = Double.parseDouble(pointsText[9].getText());
		}
		try{
			 p4Y = Double.parseDouble(pointsText[10].getText());
		}catch (NumberFormatException e){
			pointsText[10].setText("1.0");
			 p4Y = Double.parseDouble(pointsText[10].getText());
		}
		try{
			 p4Z = Double.parseDouble(pointsText[11].getText());
		}catch (NumberFormatException e){
			pointsText[11].setText("1.0");
			 p4Z = Double.parseDouble(pointsText[11].getText());
		}

		Vector p0 = new Vector(p1X, p1Y, p1Z,1.0);
		Vector p1 = new Vector(p2X, p2Y, p2Z,1.0);
		Vector p2 = new Vector(p3X, p3Y, p3Z,1.0);
		Vector p3 = new Vector(p4X, p4Y, p4Z,1.0);

		points.clear();
		indexes.clear();

		for( int i = 0; i <= paramStep; ++i) {
			double t = i / (double) paramStep;
			Vector pixel = CalculateBezierPoint(t, p0, p1, p2, p3);
			for (int j = 0; j <= paramStep; ++j) {
				Matrix rotMat = new Matrix().initRotationY(j * step);
				Vector tmp = rotMat.transform(pixel);
				points.add(tmp);
			}
		}

        int pointsInRow = paramStep+1;

        for (int i = 0; i < paramStep; ++i) {
            for (int j = 0; j < paramStep; ++j) {
                indexes.add(i * pointsInRow + j);
                indexes.add((i + 1) * pointsInRow + j);
                indexes.add(i * pointsInRow + j + 1);
                indexes.add((i + 1) * pointsInRow + j);
                indexes.add((i + 1) * pointsInRow + j + 1);
                indexes.add(i * pointsInRow + j + 1);
            }
        }


	}

	private Vector CalculateBezierPoint(double t, Vector p0, Vector p1, Vector p2, Vector p3)
	{
		double u = 1 - t;
		double tt = t*t;
		double uu = u*u;
		double uuu = uu * u;
		double ttt = tt * t;

		Vector p = p0.mul(uuu);
		p = p.add(p1.mul(uu*t*3));
		p = p.add(p2.mul(3 * u * tt));
		p = p.add(p3.mul(ttt));

		return p;
	}
}
