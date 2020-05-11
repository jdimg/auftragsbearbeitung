package view;

import app.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.Job;

public class NewJobController {

	private Main main;
	private Stage stage;
	private Job newJob;
	@FXML private Button okButton;
	@FXML private Button escButton;
	@FXML private TextField customerNumber;
	@FXML private TextField customerName;
	@FXML private TextArea customerNotice;
	private Circle circle;
	
	
	
	public NewJobController() {
		circle = new Circle();
		circle.setCenterX(160);
		circle.setCenterY(12);
		circle.setRadius(6);
		circle.fillProperty().set(Color.ORANGE);
	}
	
	/**
	 * Initialisierung der Controller-Klasse. Diese Methode wird automatisch aufgerufen,
	 * nachdem die fxml-Datei geladen wurde.
	 */
	@FXML 
	private void initialize() {
		okButton.setDisable(true);
		customerNumber.textProperty().addListener((observable, oldValue, newValue) -> {
			customerNumberChanging(observable.getValue());
		});
	}
	
	private void customerNumberChanging(String str) {
		try {
			int i = Integer.parseInt(str);
			if(i < 999999) {
				okButton.setDisable(false);
			} else {
				okButton.setDisable(true);
			}
		} catch (NumberFormatException nfe) {
			// TODO: handle exception
			okButton.setDisable(true);
		}
	}
	
	@FXML private void okButtonHandle() {
		newJob = new Job(Integer.parseInt(customerNumber.getText()), customerName.getText(), customerNotice.getText(), false, circle);
		main.addToJobList(newJob);
		stage.close();
	}
	
	@FXML private void escButtonHandle() {
		stage.close();
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public void setMain(Main main) {
		this.main = main;	}

	public Button getOkButton() {
		return okButton;
	}

	public TextField getCustomerNumber() {
		return customerNumber;
	}

	public TextField getCustomerName() {
		return customerName;
	}

	public TextArea getCustomerNotice() {
		return customerNotice;
	}
}
