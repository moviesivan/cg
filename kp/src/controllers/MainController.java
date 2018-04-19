package controllers;

import javafx.fxml.FXML;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import application.CustomCanvas;
import handlers.HandlerFigures;

public class MainController {
	@FXML
	private void initialize() {
		Canvas canvas = new CustomCanvas(canvasHolder.getPrefWidth(), canvasHolder.getPrefHeight());

		AnchorPane.setBottomAnchor(canvas, 0.0);
		AnchorPane.setTopAnchor(canvas, 0.0);
		AnchorPane.setLeftAnchor(canvas, 0.0);
		AnchorPane.setRightAnchor(canvas, 0.0);

		Slider[] paramsFig = new Slider[] {Step, Angle};
		CheckBox[] paramsAxis = new CheckBox[] {X,Y,Z};
		TextField[] paramsPoints = new TextField[] {p1X,p1Y,p1Z,p2X,p2Y,p2Z,p3X,p3Y,p3Z,p4X,p4Y,p4Z};
		HandlerFigures handlerFigures = new HandlerFigures(canvas, paramsFig, paramsAxis, paramsPoints);

		for (int i = 0; i < paramsFig.length; ++i) {
			paramsFig[i].valueProperty().addListener(handlerFigures);
		}

		for (int i = 0; i < paramsAxis.length; ++i) {
			paramsAxis[i].selectedProperty().addListener((observable, oldValue, newValue) -> {handlerFigures.generate();});
		}

        upd.setOnAction( e ->  handlerFigures.generate());


        canvas.setOnMousePressed(handlerFigures);
		canvas.setOnMouseDragged(handlerFigures);
		canvasHolder.getChildren().add(canvas);
		canvasHolder.widthProperty().addListener(handlerFigures);
		canvasHolder.heightProperty().addListener(handlerFigures);
	}

	@FXML
	private AnchorPane canvasHolder;

	@FXML
	private Slider Step;
    @FXML
    private Slider Angle;

	@FXML
	private CheckBox X;
	@FXML
	private CheckBox Y;
	@FXML
	private CheckBox Z;

	@FXML
    private TextField p1X;
	@FXML
    private TextField p1Y;
    @FXML
    private TextField p1Z;


    @FXML
    private TextField p2X;
    @FXML
    private TextField p2Y;
    @FXML
    private TextField p2Z;


    @FXML
    private TextField p3X;
    @FXML
    private TextField p3Y;
    @FXML
    private TextField p3Z;


    @FXML
    private TextField p4X;
    @FXML
    private TextField p4Y;
    @FXML
    private TextField p4Z;

    @FXML
    private Button upd;

}
