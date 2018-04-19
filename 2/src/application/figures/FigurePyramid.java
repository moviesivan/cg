package application.figures;

import javafx.scene.control.Slider;
import math.Vector;

public class FigurePyramid extends Figure {
	public FigurePyramid(Slider[] params) {
		super();

		this.params = params;
	}

	@Override
	public void generate() {
		double paramHeight = params[0].getValue();
		int paramSides = (int)params[1].getValue();
		double paramRadius = params[2].getValue();
		double step = 2.0 * Math.PI / (paramSides);

		points.clear();
		indexes.clear();

		for (int i = 0; i < paramSides; ++i) {
			double x = Math.cos(i * step) * paramRadius;
			double z = Math.sin(i * step) * paramRadius;

			points.add(new Vector(x, 0.0, z, 1.0));
		}
		points.add(new Vector(0.0, 0.0, 0.0, 1.0));
		for (int i = 0; i < paramSides - 1; ++i) {
			indexes.add(points.size()-1);
			indexes.add(i + 1);
			indexes.add(i);
		}
		indexes.add(points.size()-1);
		indexes.add(0);
		indexes.add(paramSides - 1);

		double paramRadius2 = (paramRadius / 400.0) * (400-paramHeight);
		for (int i = 0; i < paramSides; ++i) {
			double x = Math.cos(i * step) * paramRadius2;
			double z = Math.sin(i * step) * paramRadius2;

			points.add(new Vector(x, paramHeight, z, 1.0));
		}
		points.add(new Vector(0.0, paramHeight, 0.0, 1.0));
		for (int i = 0; i < paramSides - 1; ++i) {
			indexes.add(paramSides + 1 + i);
			indexes.add(paramSides + 1 + i + 1);
			indexes.add(points.size()-1);
		}
		indexes.add(2*paramSides);
		indexes.add(paramSides + 1);
		indexes.add(points.size()-1);

		for (int i = 0; i < paramSides - 1; ++i) {
			indexes.add(i);
			indexes.add(paramSides + 1 + i + 1);
			indexes.add(paramSides + 1 + i);
			indexes.add(i);
			indexes.add(i + 1);
			indexes.add(paramSides + 1 + i + 1);
		}
		indexes.add(paramSides - 1);
		indexes.add(paramSides + 1);
		indexes.add(2*paramSides);
		indexes.add(paramSides - 1);
		indexes.add(0);
		indexes.add(paramSides + 1);
	}
}
