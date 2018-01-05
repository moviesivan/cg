package org.lwjglb.app;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjglb.engine.Item;
import org.lwjglb.engine.ILogic;
import org.lwjglb.engine.MouseInput;
import org.lwjglb.engine.Window;
import org.lwjglb.engine.graph.Camera;
import org.lwjglb.engine.graph.Material;
import org.lwjglb.engine.graph.Mesh;
import org.lwjglb.engine.graph.OBJLoader;
import org.lwjglb.engine.graph.PointLight;

public class AppLogic implements ILogic {

    private static final float MOUSE_SENSITIVITY = 1f;

    private final Vector3f cameraInc;

    private final Renderer renderer;

    private final Camera camera;

    private Item[] items;

    private Vector3f ambientLight;

    private PointLight pointLight;

    private static final float CAMERA_POS_STEP = 0.05f;

    private float parama = 5f;

    private float paramc = 5f;

    private int paramstep = 3;

    private float lightared = 0.2f;

    private float lightagreen = 0.2f;

    private float lightablue = 0.5f;

    private float lightdred = 0.2f;

    private float lightdgreen = 0.2f;

    private float lightdblue = 0.5f;

    private float lightsred = 0.2f;

    private float lightsgreen = 0.2f;

    private float lightsblue = 0.5f;

    private float reflectance;

    private Mesh mesh;

    private Material material;

    private Figure figure;

    private Text valuea;

    private Text valuec;

    private Text valueparam;

    private Text valuelightared;

    private Text valuelightagreen;

    private Text valuelightablue;

    private Text valuelightdred;

    private Text valuelightdgreen;

    private Text valuelightdblue;

    private Text valuelightsred;

    private Text valuelightsgreen;

    private Text valuelightsblue;

    public AppLogic() {
        renderer = new Renderer();
        camera = new Camera(new Vector3f(0, 0, 10),new Vector3f(0, 0, 0));
        cameraInc = new Vector3f(0.0f, 0.0f, 0.0f);
    }

    @Override
    public void init(Window window) throws Exception {
        renderer.init(window);
        Display display = new Display();
        final Shell shell = new Shell(display, SWT.TITLE);
        shell.setText("Управление");
        shell.setLayout(new GridLayout());

        Label labela = new Label(shell, SWT.NULL);
        labela.setText("a:");

        Slider slidera = new Slider(shell, SWT.HORIZONTAL);
        slidera.setBounds(115, 50, 25, 15);
        slidera.setMaximum(20);
        slidera.setMinimum(1);
        slidera.setIncrement(1);
        slidera.setPageIncrement(5);

        slidera.setThumb(4);

        slidera.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
                int perspectiveValue = slidera.getSelection();
                valuea.setText(String.valueOf(perspectiveValue));
                parama = perspectiveValue;
                figure = new Figure();
                figure.generate(parama,paramc,paramstep);
                try{
                    mesh = OBJLoader.loadMesh("target/generatedfigure.obj");
                    mesh.setMaterial(material);
                    Item item = new Item(mesh);
                    item.setScale(0.01f);
                    item.setPosition(0, 0, 0);
                    items = new Item[]{item};
                } catch (Exception e){
                    e.printStackTrace();
                    System.exit(-1);
                }
            }
        });

        valuea = new Text(shell,  SWT.SINGLE);

        valuea.setEditable(false);
        slidera.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));
        valuea.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));

        Label labelc = new Label(shell, SWT.NULL);
        labelc.setText("c:");

        Slider sliderc = new Slider(shell, SWT.HORIZONTAL);
        sliderc.setBounds(115, 50, 25, 15);
        sliderc.setMaximum(54);
        sliderc.setMinimum(5);
        sliderc.setIncrement(1);
        sliderc.setPageIncrement(5);

        sliderc.setThumb(4);

        sliderc.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
                int perspectiveValue = sliderc.getSelection();
                valuec.setText(String.valueOf(perspectiveValue));
                paramc = perspectiveValue;figure = new Figure();
                figure.generate(parama,paramc,paramstep);
                try{
                    mesh = OBJLoader.loadMesh("target/generatedfigure.obj");
                    mesh.setMaterial(material);
                    Item item = new Item(mesh);
                    item.setScale(0.01f);
                    item.setPosition(0, 0, 0);
                    items = new Item[]{item};
                } catch (Exception e){
                    e.printStackTrace();
                    System.exit(-1);
                }
            }
        });

        valuec = new Text(shell,  SWT.SINGLE);

        valuec.setEditable(false);
        sliderc.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));
        valuec.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));

        Label labelparam = new Label(shell, SWT.NULL);
        labelparam.setText("step:");

        Slider sliderparam = new Slider(shell, SWT.HORIZONTAL);
        sliderparam.setBounds(115, 50, 25, 15);
        sliderparam.setMaximum(54);
        sliderparam.setMinimum(3);
        sliderparam.setIncrement(1);
        sliderparam.setPageIncrement(5);

        sliderparam.setThumb(4);

        sliderparam.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
                int perspectiveValue = sliderparam.getSelection();
                valueparam.setText(String.valueOf(perspectiveValue));
                paramstep = perspectiveValue;figure = new Figure();
                figure.generate(parama,paramc,paramstep);
                try{
                    mesh = OBJLoader.loadMesh("target/generatedfigure.obj");
                    mesh.setMaterial(material);
                    Item item = new Item(mesh);
                    item.setScale(0.01f);
                    item.setPosition(0, 0, 0);
                    items = new Item[]{item};
                } catch (Exception e){
                    e.printStackTrace();
                    System.exit(-1);
                }
            }
        });

        valueparam = new Text(shell,  SWT.SINGLE);

        valueparam.setEditable(false);
        sliderparam.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));
        valueparam.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));

        Label labellightared = new Label(shell, SWT.NULL);
        labellightared.setText("ambient red:");

        Slider sliderlightared = new Slider(shell, SWT.HORIZONTAL);
        sliderlightared.setBounds(115, 50, 25, 15);
        sliderlightared.setMaximum(104);
        sliderlightared.setMinimum(0);
        sliderlightared.setIncrement(1);
        sliderlightared.setPageIncrement(5);

        sliderlightared.setThumb(4);

        sliderlightared.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
                float perspectiveValue = (float)sliderlightared.getSelection()/100;
                valuelightared.setText(String.valueOf(perspectiveValue));
                lightared = perspectiveValue;
                material.setAmbientColour(new Vector4f(lightared, lightagreen, lightablue, 1.0f));
            }
        });

        valuelightared = new Text(shell,  SWT.SINGLE);

        valuelightared.setEditable(false);
        sliderlightared.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));
        valuelightared.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));

        Label labellightagreen = new Label(shell, SWT.NULL);
        labellightagreen.setText("ambient green:");

        Slider sliderlightagreen = new Slider(shell, SWT.HORIZONTAL);
        sliderlightagreen.setBounds(115, 50, 25, 15);
        sliderlightagreen.setMaximum(104);
        sliderlightagreen.setMinimum(0);
        sliderlightagreen.setIncrement(1);
        sliderlightagreen.setPageIncrement(5);

        sliderlightagreen.setThumb(4);

        sliderlightagreen.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
                float perspectiveValue = (float)sliderlightagreen.getSelection()/100;
                valuelightagreen.setText(String.valueOf(perspectiveValue));
                lightagreen = perspectiveValue;
                material.setAmbientColour(new Vector4f(lightared, lightagreen, lightablue, 1.0f));
            }
        });

        valuelightagreen = new Text(shell,  SWT.SINGLE);

        valuelightagreen.setEditable(false);
        sliderlightagreen.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));
        valuelightagreen.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));


        Label labellightablue = new Label(shell, SWT.NULL);
        labellightablue.setText("ambient blue:");

        Slider sliderlightablue = new Slider(shell, SWT.HORIZONTAL);
        sliderlightablue.setBounds(115, 50, 25, 15);
        sliderlightablue.setMaximum(104);
        sliderlightablue.setMinimum(0);
        sliderlightablue.setIncrement(1);
        sliderlightablue.setPageIncrement(5);

        sliderlightablue.setThumb(4);

        sliderlightablue.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
                float perspectiveValue = (float)sliderlightablue.getSelection()/100;
                valuelightablue.setText(String.valueOf(perspectiveValue));
                lightablue = perspectiveValue;
                material.setAmbientColour(new Vector4f(lightared, lightagreen, lightablue, 1.0f));
            }
        });

        valuelightablue = new Text(shell,  SWT.SINGLE);

        valuelightablue.setEditable(false);
        sliderlightablue.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));
        valuelightablue.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));


        Label labellightdred = new Label(shell, SWT.NULL);
        labellightdred.setText("diffuse red:");

        Slider sliderlightdred = new Slider(shell, SWT.HORIZONTAL);
        sliderlightdred.setBounds(115, 50, 25, 15);
        sliderlightdred.setMaximum(104);
        sliderlightdred.setMinimum(0);
        sliderlightdred.setIncrement(1);
        sliderlightdred.setPageIncrement(5);

        sliderlightdred.setThumb(4);

        sliderlightdred.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
                float perspectiveValue = (float)sliderlightdred.getSelection()/100;
                valuelightdred.setText(String.valueOf(perspectiveValue));
                lightdred = perspectiveValue;
                material.setDiffuseColour(new Vector4f(lightdred, lightdgreen, lightdblue, 1.0f));
            }
        });

        valuelightdred = new Text(shell,  SWT.SINGLE);

        valuelightdred.setEditable(false);
        sliderlightdred.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));
        valuelightdred.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));

        Label labellightdgreen = new Label(shell, SWT.NULL);
        labellightdgreen.setText("diffuse green:");

        Slider sliderlightdgreen = new Slider(shell, SWT.HORIZONTAL);
        sliderlightdgreen.setBounds(115, 50, 25, 15);
        sliderlightdgreen.setMaximum(104);
        sliderlightdgreen.setMinimum(0);
        sliderlightdgreen.setIncrement(1);
        sliderlightdgreen.setPageIncrement(5);

        sliderlightdgreen.setThumb(4);

        sliderlightdgreen.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
                float perspectiveValue = (float)sliderlightdgreen.getSelection()/100;
                valuelightdgreen.setText(String.valueOf(perspectiveValue));
                lightdgreen = perspectiveValue;
                material.setDiffuseColour(new Vector4f(lightdred, lightdgreen, lightdblue, 1.0f));
            }
        });

        valuelightdgreen = new Text(shell,  SWT.SINGLE);

        valuelightdgreen.setEditable(false);
        sliderlightdgreen.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));
        valuelightdgreen.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));


        Label labellightdblue = new Label(shell, SWT.NULL);
        labellightdblue.setText("diffuse blue:");

        Slider sliderlightdblue = new Slider(shell, SWT.HORIZONTAL);
        sliderlightdblue.setBounds(115, 50, 25, 15);
        sliderlightdblue.setMaximum(104);
        sliderlightdblue.setMinimum(0);
        sliderlightdblue.setIncrement(1);
        sliderlightdblue.setPageIncrement(5);

        sliderlightdblue.setThumb(4);

        sliderlightdblue.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
                float perspectiveValue = (float)sliderlightdblue.getSelection()/100;
                valuelightdblue.setText(String.valueOf(perspectiveValue));
                lightdblue = perspectiveValue;
                material.setDiffuseColour(new Vector4f(lightdred, lightdgreen, lightdblue, 1.0f));
            }
        });

        valuelightdblue = new Text(shell,  SWT.SINGLE);

        valuelightdblue.setEditable(false);
        sliderlightdblue.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));
        valuelightdblue.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));



        Label labellightsred = new Label(shell, SWT.NULL);
        labellightsred.setText("specular red:");

        Slider sliderlightsred = new Slider(shell, SWT.HORIZONTAL);
        sliderlightsred.setBounds(115, 50, 25, 15);
        sliderlightsred.setMaximum(104);
        sliderlightsred.setMinimum(0);
        sliderlightsred.setIncrement(1);
        sliderlightsred.setPageIncrement(5);

        sliderlightsred.setThumb(4);

        sliderlightsred.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
                float perspectiveValue = (float)sliderlightsred.getSelection()/100;
                valuelightsred.setText(String.valueOf(perspectiveValue));
                lightsred = perspectiveValue;
                material.setSpecularColour(new Vector4f(lightsred, lightsgreen, lightsblue, 1.0f));
            }
        });

        valuelightsred = new Text(shell,  SWT.SINGLE);

        valuelightsred.setEditable(false);
        sliderlightsred.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));
        valuelightsred.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));

        Label labellightsgreen = new Label(shell, SWT.NULL);
        labellightsgreen.setText("specular green:");

        Slider sliderlightsgreen = new Slider(shell, SWT.HORIZONTAL);
        sliderlightsgreen.setBounds(115, 50, 25, 15);
        sliderlightsgreen.setMaximum(104);
        sliderlightsgreen.setMinimum(0);
        sliderlightsgreen.setIncrement(1);
        sliderlightsgreen.setPageIncrement(5);

        sliderlightsgreen.setThumb(4);

        sliderlightsgreen.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
                float perspectiveValue = (float)sliderlightsgreen.getSelection()/100;
                valuelightsgreen.setText(String.valueOf(perspectiveValue));
                lightsgreen = perspectiveValue;
                material.setSpecularColour(new Vector4f(lightsred, lightsgreen, lightsblue, 1.0f));
            }
        });

        valuelightsgreen = new Text(shell,  SWT.SINGLE);

        valuelightsgreen.setEditable(false);
        sliderlightsgreen.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));
        valuelightsgreen.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));


        Label labellightsblue = new Label(shell, SWT.NULL);
        labellightsblue.setText("specular blue:");

        Slider sliderlightsblue = new Slider(shell, SWT.HORIZONTAL);
        sliderlightsblue.setBounds(115, 50, 25, 15);
        sliderlightsblue.setMaximum(104);
        sliderlightsblue.setMinimum(0);
        sliderlightsblue.setIncrement(1);
        sliderlightsblue.setPageIncrement(5);

        sliderlightsblue.setThumb(4);

        sliderlightsblue.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
                float perspectiveValue = (float)sliderlightsblue.getSelection()/100;
                valuelightsblue.setText(String.valueOf(perspectiveValue));
                lightsblue = perspectiveValue;
                material.setSpecularColour(new Vector4f(lightsred, lightsgreen, lightsblue, 1.0f));
            }
        });

        valuelightsblue = new Text(shell,  SWT.SINGLE);

        valuelightsblue.setEditable(false);
        sliderlightsblue.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));
        valuelightsblue.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));

        shell.pack();
        shell.open();
        //textUser.forceFocus();

        figure = new Figure();
        figure.generate(parama,paramc,paramstep);

        reflectance = 100f;
        mesh = OBJLoader.loadMesh("target/generatedfigure.obj");
        material = new Material(new Vector4f(lightared, lightagreen, lightablue, 1.0f), reflectance);

        mesh.setMaterial(material);
        Item item = new Item(mesh);
        item.setScale(0.01f);
        item.setPosition(0, 0, 0);
        items = new Item[]{item};

        ambientLight = new Vector3f(0.3f, 0.3f, 0.3f);
        Vector3f lightColour = new Vector3f(1, 1, 1);
        Vector3f lightPosition = new Vector3f(0, 0, 10);
        float lightIntensity = 50.0f;
        pointLight = new PointLight(lightColour, lightPosition, lightIntensity);
        PointLight.Attenuation att = new PointLight.Attenuation(0.0f, 0.0f, 1.0f);
        pointLight.setAttenuation(att);
    }

    @Override
    public void input(Window window, MouseInput mouseInput) {
    }

    @Override
    public void update(float interval, MouseInput mouseInput) {
        camera.movePosition(cameraInc.x * CAMERA_POS_STEP, cameraInc.y * CAMERA_POS_STEP, cameraInc.z * CAMERA_POS_STEP);

        if (mouseInput.isLeftButtonPressed()) {
            Vector2f rotVec = mouseInput.getDisplVec();
            items[0].moveRotation(rotVec.x * MOUSE_SENSITIVITY, rotVec.y * MOUSE_SENSITIVITY, 0);
        }
    }

    @Override
    public void render(Window window) {
        renderer.render(window, camera, items, ambientLight, pointLight);
    }

    @Override
    public void cleanup() {
        renderer.cleanup();
        for (Item item : items) {
            item.getMesh().cleanUp();
        }
    }

}
