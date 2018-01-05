package org.lwjglb.app;

import org.lwjglb.engine.Engine;
import org.lwjglb.engine.ILogic;
 
public class Main {
 
    public static void main(String[] args) {
        try {
            boolean vSync = true;
            ILogic logic = new AppLogic();
            Engine eng = new Engine("Лабораторная работа 6 Трубачев", 600, 480, vSync, logic);
            eng.start();

        } catch (Exception excp) {
            excp.printStackTrace();
            System.exit(-1);
        }
    }
}