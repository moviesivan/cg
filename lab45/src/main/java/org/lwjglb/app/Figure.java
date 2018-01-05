package org.lwjglb.app;

import org.joml.Vector3d;
import java.util.ArrayList;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class Figure {
    public Figure() {
        points = new ArrayList<>();
        normals = new ArrayList<>();
        indexes = new ArrayList<>();
    }

    public void generate(float a, float c, int paramStep) {
        double stepX = 2.0 * Math.PI / paramStep;
        double stepZ = 200/(paramStep*c);

        points.clear();
        indexes.clear();

        points.add(new Vector3d(0.0, 0.0, c));
        points.add(new Vector3d(0.0, 0.0, -c));
        for (int i = 1; i < paramStep+1; ++i) {
            double z = c * (1 + i*stepZ);
            double rad = Math.sqrt(a*a*(-1+z*z/(c*c)));
            for (int j = 0; j < paramStep; ++j) {
                double x = Math.cos(j * stepX) * rad;
                double y = Math.sin(j * stepX) * rad;
                points.add(new Vector3d(x, y, z));
                points.add(new Vector3d(-x, -y, -z));
            }

        }

        double z = c * (1 + (paramStep)*stepZ);
        points.add(new Vector3d(0.0, 0.0, z));
        points.add(new Vector3d(0.0, 0.0, -z));

        for (int i = 0; i < paramStep; ++i) {
            indexes.add(0);
            indexes.add(i * 2 + 2);
            int ind = (i * 2 + 4)%(paramStep * 2 + 2);
            indexes.add(ind > 0 ? ind : 2);
            Vector3d v0 = new Vector3d(points.get(indexes.get(indexes.size()-3)));
            Vector3d v1 = new Vector3d(points.get(indexes.get(indexes.size()-2)));
            Vector3d v2 = new Vector3d(points.get(indexes.get(indexes.size()-1)));

            Vector3d u = v2.sub(v0);
            Vector3d w = v1.sub(v0);

            Vector3d n = u.cross(w);
            n.normalize();
            normals.add(n);
        }

        for (int i = 0; i < paramStep; ++i) {
            indexes.add(i * 2 + 3);
            indexes.add(1);
            int ind = (i * 2 + 5)%(paramStep * 2 + 3);
            indexes.add(ind > 0 ? ind : 3);
            Vector3d v0 = new Vector3d(points.get(indexes.get(indexes.size()-3)));
            Vector3d v1 = new Vector3d(points.get(indexes.get(indexes.size()-2)));
            Vector3d v2 = new Vector3d(points.get(indexes.get(indexes.size()-1)));

            Vector3d u = v2.sub(v0);
            Vector3d w = v1.sub(v0);

            Vector3d n = u.cross(w);
            n.normalize();
            normals.add(n);
        }

        for (int i = 0; i < paramStep-1; ++i) {
            for (int j = 0; j < paramStep; ++j) {
                int offsetPoint1 = (j + 1) * 2 + 2 * i * paramStep;

                indexes.add(offsetPoint1);
                indexes.add(offsetPoint1 + 2*paramStep);
                int ind1 = (offsetPoint1 + 2) % (paramStep * 2 + 2 + 2 * i * paramStep);
                indexes.add(ind1 > 0 ? ind1 : (2 + 2 * i * paramStep));


                Vector3d v0 = new Vector3d(points.get(indexes.get(indexes.size()-3)));
                Vector3d v1 = new Vector3d(points.get(indexes.get(indexes.size()-2)));
                Vector3d v2 = new Vector3d(points.get(indexes.get(indexes.size()-1)));

                Vector3d u = v2.sub(v0);
                Vector3d w = v1.sub(v0);

                Vector3d n = u.cross(w);
                n.normalize();
                normals.add(n);

                indexes.add(offsetPoint1 + 2*paramStep);
                int ind2 = (offsetPoint1 + 2 + 2*paramStep) % (paramStep * 2 + 2 + 2 * (i + 1) * paramStep);
                indexes.add(ind2 > 0 ? ind2 : (2 + 2 * (i + 1) * paramStep));
                indexes.add(ind1 > 0 ? ind1 : (2 + 2 * i * paramStep));

                v0 = new Vector3d(points.get(indexes.get(indexes.size()-3)));
                v1 = new Vector3d(points.get(indexes.get(indexes.size()-2)));
                v2 = new Vector3d(points.get(indexes.get(indexes.size()-1)));

                u = v2.sub(v0);
                w = v1.sub(v0);

                n = u.cross(w);
                n.normalize();
                normals.add(n);

                int offsetPoint2= (j + 1) * 2 + 1 + 2 * i * paramStep;

                indexes.add(offsetPoint2);
                int ind3 = (offsetPoint2 + 2) % (paramStep * 2 + 3 + 2 * i * paramStep);
                indexes.add(ind3 > 0 ? ind3 : (3 + 2 * i * paramStep));
                indexes.add(offsetPoint2 + 2*paramStep);

                v0 = new Vector3d(points.get(indexes.get(indexes.size()-3)));
                v1 = new Vector3d(points.get(indexes.get(indexes.size()-2)));
                v2 = new Vector3d(points.get(indexes.get(indexes.size()-1)));

                u = v2.sub(v0);
                w = v1.sub(v0);

                n = u.cross(w);
                n.normalize();
                normals.add(n);

                indexes.add(offsetPoint2 + 2*paramStep);
                indexes.add(ind3 > 0 ? ind3 : (3 + 2 * i * paramStep));
                int ind4 = (offsetPoint2 + 2 + 2*paramStep) % (paramStep * 2 + 3 + 2 * (i + 1) * paramStep);
                indexes.add(ind4 > 0 ? ind4 : (3 + 2 * (i + 1) * paramStep));

                v0 = new Vector3d(points.get(indexes.get(indexes.size()-3)));
                v1 = new Vector3d(points.get(indexes.get(indexes.size()-2)));
                v2 = new Vector3d(points.get(indexes.get(indexes.size()-1)));

                u = v2.sub(v0);
                w = v1.sub(v0);

                n = u.cross(w);
                n.normalize();
                normals.add(n);
            }
        }

        for (int i = 0; i < paramStep; ++i) {
            indexes.add(i * 2 + 2 + 2*paramStep*(paramStep-1));
            indexes.add(points.size() - 2);
            int ind = (i * 2 + 4 + 2*paramStep*(paramStep-1))%(2 + 2*paramStep*(paramStep));
            indexes.add(ind > 0 ? ind : 2 + 2*paramStep*(paramStep-1));

            Vector3d v0 = new Vector3d(points.get(indexes.get(indexes.size()-3)));
            Vector3d v1 = new Vector3d(points.get(indexes.get(indexes.size()-2)));
            Vector3d v2 = new Vector3d(points.get(indexes.get(indexes.size()-1)));

            Vector3d u = v2.sub(v0);
            Vector3d w = v1.sub(v0);

            Vector3d n = u.cross(w);
            n.normalize();
            normals.add(n);
        }

        for (int i = 0; i < paramStep; ++i) {
            indexes.add(points.size() - 1);
            indexes.add(i * 2 + 3 + 2*paramStep*(paramStep-1));
            int ind = (i * 2 + 5 + 2*paramStep*(paramStep-1))%(3 + 2*paramStep*(paramStep));
            indexes.add(ind > 0 ? ind : 3 + 2*paramStep*(paramStep-1));

            Vector3d v0 = new Vector3d(points.get(indexes.get(indexes.size()-3)));
            Vector3d v1 = new Vector3d(points.get(indexes.get(indexes.size()-2)));
            Vector3d v2 = new Vector3d(points.get(indexes.get(indexes.size()-1)));

            Vector3d u = v2.sub(v0);
            Vector3d w = v1.sub(v0);

            Vector3d n = u.cross(w);
            n.normalize();
            normals.add(n);
        }

        try{
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("target/generatedfigure.obj"), StandardCharsets.UTF_8);
            for (Vector3d point : points) {
                String output = String.format("v %f %f %f\n", point.x, point.y, point.z);
                writer.write(output);
            }
            for (Vector3d point : normals) {
                String output = String.format("vn %f %f %f\n", point.x, point.y, point.z);
                writer.write(output);
            }
            for (int i=0; i < indexes.size()/3; ++i) {
                String output = String.format("f %d//%d %d//%d %d//%d\n", indexes.get(3*i)+1, i+1, indexes.get(3*i+1)+1, i+1, indexes.get(3*i+2)+1, i+1);
                writer.write(output);
            }
            writer.close();
        } catch(Exception ie) {
            ie.printStackTrace();
            System.exit(-1);
        }

    }

    protected ArrayList<Vector3d> points;
    protected ArrayList<Integer> indexes;
    protected ArrayList<Vector3d> normals;
}

