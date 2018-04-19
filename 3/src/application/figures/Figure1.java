package application.figures;

import javafx.scene.control.Slider;
import math.Vector;

public class Figure1 extends Figure {
	public Figure1(Slider[] params) {
		super();

		this.params = params;
	}

	@Override
	public void generate() {
		double a = params[0].getValue();
		double c = params[1].getValue();
		int paramStep = (int)params[2].getValue();
		double stepX = 2.0 * Math.PI / paramStep;
		double stepY = 200/(paramStep*c);

		points.clear();
		indexes.clear();

		points.add(new Vector(0.0, c, 0.0, 1.0));
		points.add(new Vector(0.0, -c, 0.0, 1.0));
		for (int i = 1; i < paramStep; ++i) {
			double y = c * (1 + i*stepY);
			double rad = Math.sqrt(a*a*(-1+y*y/(c*c)));
			for (int j = 0; j < paramStep; ++j) {
				double x = Math.cos(j * stepX) * rad;
				double z = Math.sin(j * stepX) * rad;
				points.add(new Vector(x, y, z, 1.0));
				points.add(new Vector(-x, -y, -z, 1.0));
			}

		}

		double y = c * (1 + (paramStep-1)*stepY);
		double rad = Math.sqrt(a*a*(-1+y*y/(c*c)));
		for (int j = 0; j < paramStep; ++j) {
			double x = Math.cos(j * stepX) * rad;
			double z = Math.sin(j * stepX) * rad;
			points.add(new Vector(x, y, z, 1.0));
			points.add(new Vector(-x, -y, -z, 1.0));
		}
		points.add(new Vector(0.0, y, 0.0, 1.0));
		points.add(new Vector(0.0, -y, 0.0, 1.0));

        for (int i = 0; i < paramStep; ++i) {
            indexes.add(0);
			indexes.add(i * 2 + 2);
			int ind = (i * 2 + 4)%(paramStep * 2 + 2);
            indexes.add(ind > 0 ? ind : 2);
        }

        for (int i = 0; i < paramStep; ++i) {
            indexes.add(i * 2 + 3);
			indexes.add(1);
			int ind = (i * 2 + 5)%(paramStep * 2 + 3);
            indexes.add(ind > 0 ? ind : 3);
        }

		for (int i = 0; i < paramStep - 1 ; ++i) {
            for (int j = 0; j < paramStep; ++j) {
                int offsetPoint1 = (j + 1) * 2 + 2 * i * paramStep;

                indexes.add(offsetPoint1);
                indexes.add(offsetPoint1 + 2*paramStep);
                int ind1 = (offsetPoint1 + 2) % (paramStep * 2 + 2 + 2 * i * paramStep);
                indexes.add(ind1 > 0 ? ind1 : (2 + 2 * i * paramStep));
                indexes.add(offsetPoint1 + 2*paramStep);
                int ind2 = (offsetPoint1 + 2 + 2*paramStep) % (paramStep * 2 + 2 + 2 * (i + 1) * paramStep);
                indexes.add(ind2 > 0 ? ind2 : (2 + 2 * (i + 1) * paramStep));
                indexes.add(ind1 > 0 ? ind1 : (2 + 2 * i * paramStep));

                int offsetPoint2= (j + 1) * 2 + 1 + 2 * i * paramStep;

                indexes.add(offsetPoint2);
                int ind3 = (offsetPoint2 + 2) % (paramStep * 2 + 3 + 2 * i * paramStep);
                indexes.add(ind3 > 0 ? ind3 : (3 + 2 * i * paramStep));
                indexes.add(offsetPoint2 + 2*paramStep);
                indexes.add(offsetPoint2 + 2*paramStep);
                indexes.add(ind3 > 0 ? ind3 : (3 + 2 * i * paramStep));
                int ind4 = (offsetPoint2 + 2 + 2*paramStep) % (paramStep * 2 + 3 + 2 * (i + 1) * paramStep);
                indexes.add(ind4 > 0 ? ind4 : (3 + 2 * (i + 1) * paramStep));
            }
		}

       	for (int i = 0; i < paramStep; ++i) {
            indexes.add(i * 2 + 2 + 2*paramStep*(paramStep-1));
            indexes.add(points.size() - 2);
            int ind = (i * 2 + 4 + 2*paramStep*(paramStep-1))%(2 + 2*paramStep*paramStep);
            indexes.add(ind > 0 ? ind : 2 + 2*paramStep*(paramStep-1));
        }

		for (int i = 0; i < paramStep; ++i) {
			indexes.add(points.size() - 1);
			indexes.add(i * 2 + 3 + 2*paramStep*(paramStep-1));
            int ind = (i * 2 + 5 + 2*paramStep*(paramStep-1))%(3 + 2*paramStep*paramStep);
			indexes.add(ind > 0 ? ind : 3 + 2*paramStep*(paramStep-1));
		}
	}
}
