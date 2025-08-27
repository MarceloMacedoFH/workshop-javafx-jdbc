package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.DepartmentService;

public class MainViewController implements Initializable {

	// Atributos
	@FXML
	private MenuItem menuItemSaller;
	@FXML
	private MenuItem menuItemDepartment;
	@FXML
	private MenuItem menuItemAbout;

	// Metodos
	@FXML
	public void onMenuItemSellerAction() {
		System.out.println("onMenuItemSallerAction");
	}

	@FXML
	public void onMenuItemDepartmentAction() {
		loadView2("/gui/DepartmentList.fxml");
	}

	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml");
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

	// Abrir nova tela
	private synchronized void loadView(String absoluteName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			//Pegando a referência da Scene do MainView
			Scene mainScene = Main.getMainScene();
			//Pegando a referência do Vbox do MainView
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			//Incluindo nos filhos do VBox Main os filhos do Vbox About
			//Primeira passo guardar a referência para o menu.
			Node mainMenu = mainVBox.getChildren().get(0);
			//Limpar todos os vilhos do MainVbox 
			mainVBox.getChildren().clear();
			//Adicionar no Vbox os filhos do Vbox About
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
		}
		catch(IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}

	private synchronized void loadView2(String absoluteName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			//Pegando a referência da Scene do MainView
			Scene mainScene = Main.getMainScene();
			//Pegando a referência do Vbox do MainView
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			//Incluindo nos filhos do VBox Main os filhos do Vbox About
			//Primeira passo guardar a referência para o menu.
			Node mainMenu = mainVBox.getChildren().get(0);
			//Limpar todos os vilhos do MainVbox 
			mainVBox.getChildren().clear();
			//Adicionar no Vbox os filhos do Vbox About
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
			DepartmentListController controller = loader.getController();
			controller.setDepartmentService(new DepartmentService());
			controller.updateTableView();
		}
		catch(IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
}
