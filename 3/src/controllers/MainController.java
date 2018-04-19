package controllers;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Slider;
import javafx.scene.control.TabPane;
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

		Slider[] paramsFig = new Slider[] {parama, paramc, paramStep};
		Slider[] paramsLight = new Slider[] {
				paramLightARed, paramLightAGreen, paramLightABlue,
				paramLightDRed, paramLightDGreen, paramLightDBlue,
				paramLightSRed, paramLightSGreen, paramLightSBlue
		};

		HandlerFigures handlerFigures = new HandlerFigures(canvas, paramsFig, paramsLight);

		for (int i = 0; i < paramsFig.length; ++i) {
			paramsFig[i].valueProperty().addListener(handlerFigures);
		}

		for (int i = 0; i < paramsLight.length; ++i) {
			paramsLight[i].valueProperty().addListener(handlerFigures);
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
	private Slider parama;
	@FXML
	private Slider paramc;
	@FXML
	private Slider paramStep;

	@FXML
	private Slider paramLightARed;
	@FXML
	private Slider paramLightAGreen;
	@FXML
	private Slider paramLightABlue;
	@FXML
	private Slider paramLightDRed;
	@FXML
	private Slider paramLightDGreen;
	@FXML
	private Slider paramLightDBlue;
	@FXML
	private Slider paramLightSRed;
	@FXML
	private Slider paramLightSGreen;
	@FXML
	private Slider paramLightSBlue;
}
