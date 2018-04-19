package controllers;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Slider;
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

		Slider[] paramsFig = new Slider[] {Height, Sides, Radius};
		HandlerFigures handlerFigures = new HandlerFigures(canvas, paramsFig);

		for (int i = 0; i < paramsFig.length; ++i) {
			paramsFig[i].valueProperty().addListener(handlerFigures);
		}


		canvas.setOnMousePressed(handlerFigures);
		canvas.setOnMouseDragged(handlerFigures);
		canvasHolder.getChildren().add(canvas);
		canvasHolder.widthProperty().addListener(handlerFigures);
		canvasHolder.heightProperty().addListener(handlerFigures);
	}

	@FXML
	private AnchorPane canvasHolder;

	@FXML
	private Slider Height;
	@FXML
	private Slider Sides;
	@FXML
	private Slider Radius;

}
