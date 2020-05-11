package view;

import app.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.Job;

public class Controller {

	private Main main;
	@FXML private Button buttonNew;
	@FXML private Button done;
	@FXML private Button undone;
	@FXML private Button edit;
	@FXML private ListView<Integer> jobNumberList;
	@FXML private Text customerNumberAndName;
	@FXML private TextArea customerNotice;
	@FXML private AnchorPane pane;
	private Job markedJob;
	private String marketJobStatus;
	private String nothingMarked = "Nichts markiert";
	private int circleCenterY = 12;
	private int circleCenterYMultiplier = 23;

	public Controller() {
		jobNumberList = new ListView<>();

		customerNumberAndName = new Text("");
		customerNotice = new TextArea();
	}

	/**
	 * Initialisierung der Controller-Klasse. Diese Methode wird automatisch
	 * aufgerufen, nachdem die fxml-Datei geladen wurde.
	 */
	@FXML
	private void initialize() {
		
	}

	@FXML
	private void showData() {
		markedJob = null;
		marketJobStatus = null;
		setJobStatusAndSetText();
	}

	private void setJobStatusAndSetText() {
		Integer i = jobNumberList.getSelectionModel().getSelectedItem();
		for(Job job : main.getJobList()) {
			marketJobStatus = new String();
			if(job.isStatus()) {
				marketJobStatus = "Fertig";
			} else {
				marketJobStatus = "Nicht fertig";
			}
			if(job.getCustomerNumber() == i) {
				customerNumberAndName.setText(i.toString() + " " + job.getCustomerName() + " - " + marketJobStatus);
				customerNotice.setText(job.getCustomerNotice());
				markedJob = job;
			}
		}
	}

	public void setMain(Main main) {
		this.main = main;
	}

	@FXML
	private void buttonDeleteHandle() {
		System.out.println("Löschen-Button gedrückt");
	}
	
	@FXML
	private void buttonEditHandle() {
		if(markedJob != null) {
			try {
				main.openEditView(markedJob);
			} catch (NullPointerException e) {
				customerNumberAndName.setText(nothingMarked);
			}	
		} else {
			customerNumberAndName.setText(nothingMarked);
		}
	}
	
	@FXML
	private void buttonDoneHandle() {
		try {
			markedJob.setStatus(true);
			markedJob.getCircle().setFill(Color.FORESTGREEN);
			setJobStatusAndSetText();	
		} catch(NullPointerException e) {
			customerNumberAndName.setText(nothingMarked);
		}
	}

	@FXML
	private void buttonUndoneHandle() {
		try {
			markedJob.setStatus(false);
			markedJob.getCircle().setFill(Color.YELLOW);
			setJobStatusAndSetText();	
		} catch(NullPointerException e) {
			customerNumberAndName.setText(nothingMarked);
		}
	}
	
	@FXML
	private void buttonProblemHandle() {
		try {
			markedJob.setStatus(false);
			markedJob.getCircle().setFill(Color.RED);
			setJobStatusAndSetText();	
		} catch(NullPointerException e) {
			customerNumberAndName.setText(nothingMarked);
		}
	}

	@FXML
	private void buttonNewHandle() {
		main.openNewView();
	}

	public void refreshJobNumber(Job job, int i) {
		int index = jobNumberList.getItems().indexOf(i);
		jobNumberList.getItems().remove(index);
		pane.getChildren().remove(job.getCircle());
		addToJobNumberListAndAddCircle(job);
		setCircleNewCenterY(job, false);
	}
	
	public void setNewJobVisibleInListView(Job job) {
		addToJobNumberListAndAddCircle(job);
		setCircleNewCenterY(job, true);
	}
	
	private void addToJobNumberListAndAddCircle(Job job) {
		jobNumberList.getItems().add(job.getCustomerNumber());
		pane.getChildren().add(job.getCircle());
	}
	
	//true = new, false = refresh
	private void setCircleNewCenterY(Job job, boolean refreshOrNew) {
		Integer number = job.getCustomerNumber();
		if(refreshOrNew) {
			job.getCircle().setCenterY(circleCenterY + (jobNumberList.getItems().indexOf(number) * circleCenterYMultiplier));
		} else {
			for(Job tempJob : main.getJobList()) {
				Integer tempNumber = tempJob.getCustomerNumber();
				tempJob.getCircle().setCenterY(circleCenterY + (jobNumberList.getItems().indexOf(tempNumber) * circleCenterYMultiplier));
			}
		}
	}
}
