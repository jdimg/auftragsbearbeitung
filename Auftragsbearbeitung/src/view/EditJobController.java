package view;

import app.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Job;

public class EditJobController {

	private Main main;
	private Job job;
	private int oldJobNumber;
	private Stage stage;
	@FXML private Button okButton;
	@FXML private Button escButton;
	@FXML private TextField customerNumber;
	@FXML private TextField customerName;
	@FXML private TextArea customerNotice;
	
	
	public EditJobController() {

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
		job.setCustomerNumber(Integer.parseInt(customerNumber.getText()));
		main.getController().refreshJobNumber(job, oldJobNumber);
		stage.close();
	}
	
	@FXML private void escButtonHandle() {
		stage.close();
	}
	
	public void setJob (Job job) {
		this.job = job;
		oldJobNumber = this.job.getCustomerNumber();
		customerNumber.setText(String.valueOf(this.job.getCustomerNumber()));
		customerName.setText(this.job.getCustomerName());
		customerNotice.setText(this.job.getCustomerNotice());
	}
	
	public void setMain(Main main) {
		this.main = main;	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
}
