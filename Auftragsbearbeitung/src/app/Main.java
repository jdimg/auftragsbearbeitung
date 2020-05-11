package app;
	
import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Job;
import view.Controller;
import view.EditJobController;
import view.NewJobController;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	private Controller controller;
	private NewJobController newJobController;
	private EditJobController editJobController;
	private VBox gui;
	private VBox newJob;
	private VBox editJob;
	private Scene scene;
	private Stage primaryStage;
	private ObservableList<Job> jobList;
	
	@Override
	public void start(Stage homeStage) {
		try {
			jobList = FXCollections.<Job>observableArrayList();
			
			this.primaryStage = homeStage;
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/view/Gui.fxml"));
			gui = (VBox)loader.load();
			
			scene = new Scene(gui);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			homeStage.setScene(scene);
			controller = loader.getController();
			homeStage.setTitle("Auftragsbearbeitung");
			homeStage.show();
			
			controller.setMain(this);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void openNewView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/view/NewJob.fxml"));
			newJob = (VBox)loader.load();
			scene = new Scene(newJob);
			Stage stage = new Stage();
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(primaryStage);
			stage.setScene(scene);
			stage.setTitle("Neuer Auftrag");
			newJobController = loader.getController();
			stage.show();
			newJobController.setMain(this);
			newJobController.setStage(stage);
			
			scene.setOnKeyPressed(e -> {
				switch(e.getCode()) { 
				case CONTROL:
					if(newJobController.getCustomerNotice().isFocusTraversable() ) {
						newJobController.getOkButton().requestFocus();	}
				break;
				case ENTER:
					if(newJobController.getCustomerNumber().isFocused()) {
						newJobController.getCustomerName().requestFocus();
						break; }
					if(newJobController.getCustomerName().isFocused()) {
						newJobController.getCustomerNotice().requestFocus(); }
					break;
				default:
					break;
				}
			});
		} catch(IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void openEditView(Job job) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/view/EditJob.fxml"));
			editJob = (VBox)loader.load();
			scene = new Scene(editJob);
			Stage stage = new Stage();
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(primaryStage);
			stage.setScene(scene);
			stage.setTitle("Auftrag bearbeiten");
			editJobController = loader.getController();
			editJobController.setMain(this);
			editJobController.setStage(stage);
			editJobController.setJob(job);
			stage.show();
		} catch(Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Controller getController() {
		return controller;
	}
	
	public void addToJobList(Job job) {
		jobList.add(job);
		controller.setNewJobVisibleInListView(job);
	}
	
	public ObservableList<Job> getJobList() {
		return jobList;
	}
	
	public VBox getGui() {
		return gui;
	}
}
