package game.gui;

import game.engine.Battle;
import game.engine.Battle;
import game.engine.exceptions.InsufficientResourcesException;
import game.engine.exceptions.InvalidLaneException;
import game.engine.lanes.Lane;
import game.engine.titans.AbnormalTitan;
import game.engine.titans.ArmoredTitan;
import game.engine.titans.PureTitan;
import game.engine.titans.Titan;
import game.engine.weapons.Weapon;

import java.io.IOException;
import java.util.ResourceBundle;





import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.paint.Color;


public class Start extends Application {
	@FXML
	private Stage stage;
	@FXML
	private Scene scene;
	@FXML
	private Parent root;
	private static Battle battle;
	@FXML
    private Label score,turn,phase,wall1,wall2,wall3,wall4,wall5;
	@FXML
	private Label r2,showScore;
	@FXML
	private Button start,l1,l2,l3,l4,l5,w1,w2,w3,w4,pass;
	@FXML
	private TextArea text1,text2,text3,text4,text5,titanInfo;
	int laneChosen;
	@FXML
	private GridPane grid;
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root=FXMLLoader.load(getClass().getResource("Menu.fxml"));
		Scene scene= new Scene(root,1300,600);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Attack On Titan");
		primaryStage.show();
		
	}
	public void showInstructions(ActionEvent event) throws IOException{
		Parent root=FXMLLoader.load(getClass().getResource("Instructions.fxml"));
		stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene= new Scene(root,1300,600);
		stage.setScene(scene);
		stage.show();
	}
	public void returnToMenu(ActionEvent event) throws IOException{
		Parent root=FXMLLoader.load(getClass().getResource("Menu.fxml"));
		stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene= new Scene(root,1300,600);
		stage.setScene(scene);
		stage.show();
	}
	public void showGameMode(ActionEvent event) throws IOException{
		Parent root=FXMLLoader.load(getClass().getResource("GameMode.fxml"));
		stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene= new Scene(root,1300,600);
		stage.setScene(scene);
		stage.show();
	}
	public void playHard(ActionEvent event) throws IOException{
		Parent root=FXMLLoader.load(getClass().getResource("HardGame.fxml"));
		stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene= new Scene(root,1300,600);
		stage.setScene(scene);
		stage.show();
	}
	public void playEasy(ActionEvent event) throws IOException{
		battle=new Battle(1,0,60,3,250);
		Parent root=FXMLLoader.load(getClass().getResource("EasyGame.fxml"));
		stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene= new Scene(root,1300,600);
		stage.setScene(scene);
		stage.show();
	}
	public static void main(String[]args){
		launch();
	}
}

