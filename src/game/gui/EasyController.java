package game.gui;

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

public class EasyController{
	@FXML
	private Stage stage;
	@FXML
	private Scene scene;
	@FXML
	private Parent root;
	private static Battle battle;
	@FXML
    private Label score,turn,phase,wall1,wall2,wall3;
	@FXML
	private Label r2,showScore;
	@FXML
	private Button start,l1,l2,l3,w1,w2,w3,w4,pass,display;
	@FXML
	private TextArea text1,text2,text3,titanInfo;
	int laneChosen;
	@FXML
	private GridPane grid;

	public void returnToMenu(ActionEvent event) throws IOException{
		Parent root=FXMLLoader.load(getClass().getResource("Menu.fxml"));
		stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene= new Scene(root,1300,600);
		stage.setScene(scene);
		stage.show();
	}	public void startGame(ActionEvent event) throws IOException{
		battle=new Battle(1,0,60,3,250);
		lanesEnable();
		initializeWeapons();
		updateWalls();
		start.setDisable(true);
		updateGameInfo();
	}
	public void moveTitans(){
		grid.getChildren().clear();
		for(Lane lane:battle.getOriginalLanes()){
			for(Titan titan:lane.getTitans()){
				Circle circle=getCircle(titan);
				grid.add(circle,0,battle.getOriginalLanes().indexOf(lane));
				circle.setTranslateX(titan.getDistance()*12.5);
				if(titan instanceof PureTitan){
					circle.setTranslateY(35);
				}
				else if(titan instanceof AbnormalTitan){
					circle.setTranslateY(20);
				}
				else if(titan instanceof ArmoredTitan){
					
				}
				else{
					circle.setTranslateY(-30);
				}
		        circle.setOnMouseEntered(event -> {
		            titanInfo.setText(getTitan(titan));
		        });
		        circle.setOnMouseExited(event -> {
		        	 titanInfo.setText("Hover over titan to get Info");
		        });

			}
		}
	}
	public String getTitan(Titan titan){
		if(titan instanceof PureTitan){
			return"Type: PureTitan"+"\nHeight: "+titan.getHeightInMeters()
					+"\nSpeed: "+titan.getSpeed()+"\nHP: "+titan.getCurrentHealth();
		}
		else if(titan instanceof AbnormalTitan){
			return"Type: AbnormalTitan"+"\nHeight: "+titan.getHeightInMeters()
					+"\nSpeed: "+titan.getSpeed()+"\nHP: "+titan.getCurrentHealth();
		}
		else if(titan instanceof ArmoredTitan){
			return"Type: ArmoredTitan"+"\nHeight: "+titan.getHeightInMeters()
					+"\nSpeed: "+titan.getSpeed()+"\nHP: "+titan.getCurrentHealth();
		}
		else{
			return"Type: ColossalTitan"+"\nHeight: "+titan.getHeightInMeters()
					+"\nSpeed: "+titan.getSpeed()+"\nHP: "+titan.getCurrentHealth();
		}
	}
	public Circle getCircle(Titan titan){
		if(titan instanceof PureTitan){
			Circle circle=new Circle(10);
			circle.setFill(Color.CYAN);;
			return circle;
		}
		else if(titan instanceof AbnormalTitan){
			Circle circle=new Circle(5);
			circle.setFill(Color.DARKGOLDENROD);;
			return circle;
		}
		else if(titan instanceof ArmoredTitan){
			Circle circle=new Circle(10);
			circle.setFill(Color.GREEN);
			return circle;
		}
		else{
			Circle circle=new Circle(15);
			circle.setFill(Color.RED);
			return circle;
		}
	}
	public void lane1Choose(ActionEvent event) {
		weaponsEnable();
		initializeWeapons();
		laneChosen=0;
	}
	public void lane2Choose(ActionEvent event) {
		weaponsEnable();
		initializeWeapons();
		laneChosen=1;
	}
	public void lane3Choose(ActionEvent event) {
		weaponsEnable();
		initializeWeapons();
		laneChosen=2;
	}
	
	public void updateGameInfo(){
	score.setText("" + battle.getScore());
	turn.setText("" + battle.getNumberOfTurns());
	phase.setText("" + battle.getBattlePhase());
	r2.setText("" + battle.getResourcesGathered());
	}
	public void weaponsEnable(){
		w1.setDisable(false);
		w2.setDisable(false);
		w3.setDisable(false);
		w4.setDisable(false);
		pass.setDisable(false);
		l1.setDisable(true);
		l2.setDisable(true);
		l3.setDisable(true);
	}
	public void lanesEnable(){
		w1.setDisable(true);
		w2.setDisable(true);
		w3.setDisable(true);
		w4.setDisable(true);
		pass.setDisable(false);
		l1.setDisable(false);
		l2.setDisable(false);
		l3.setDisable(false);
	}
	public void initializeWeapons(){
		w1.setText("Name: "+battle.getWeaponFactory().getWeaponShop().get(1).getName()
				+ "\nPrice: "+battle.getWeaponFactory().getWeaponShop().get(1).getPrice()
				+"\nDamage: "+battle.getWeaponFactory().getWeaponShop().get(1).getDamage()
				+"\nType: Piercing Cannon");
		w2.setText("Name: "+battle.getWeaponFactory().getWeaponShop().get(2).getName()
				+ "\nPrice: "+battle.getWeaponFactory().getWeaponShop().get(2).getPrice()
				+"\nDamage: "+battle.getWeaponFactory().getWeaponShop().get(2).getDamage()
				+"\nType: Sniper Cannon");
		w3.setText("Name: "+battle.getWeaponFactory().getWeaponShop().get(3).getName()
				+ "\nPrice: "+battle.getWeaponFactory().getWeaponShop().get(3).getPrice()
				+"\nDamage: "+battle.getWeaponFactory().getWeaponShop().get(3).getDamage()
				+"\nType: Volley Spread Cannon");
		w4.setText("Name: "+battle.getWeaponFactory().getWeaponShop().get(4).getName()
				+ "\nPrice: "+battle.getWeaponFactory().getWeaponShop().get(4).getPrice()
				+"\nDamage: "+battle.getWeaponFactory().getWeaponShop().get(4).getDamage()
				+"\nType: Wall Trap");
	}
	public void updateWalls(){
		if(battle.getOriginalLanes().get(0).isLaneLost())
			wall1.setText("LOST!");
		else
			wall1.setText("HP: "+battle.getOriginalLanes().get(0).getLaneWall().getCurrentHealth()
				+"\nDanger Level: "+battle.getOriginalLanes().get(0).getDangerLevel());
		if(battle.getOriginalLanes().get(1).isLaneLost())
			wall2.setText("LOST!");
		else
			wall2.setText("HP: "+battle.getOriginalLanes().get(1).getLaneWall().getCurrentHealth()
				+"\nDanger Level: "+battle.getOriginalLanes().get(1).getDangerLevel());
		if(battle.getOriginalLanes().get(2).isLaneLost())
			wall3.setText("LOST!");
		else
			wall3.setText("HP: "+battle.getOriginalLanes().get(2).getLaneWall().getCurrentHealth()
				+"\nDanger Level: "+battle.getOriginalLanes().get(2).getDangerLevel());
	}
	public TextArea getTextArea(int num){
		if(num==0)
			return text1;
		else if(num==1)
			return text2;
		else 
			return text3;
	}
	public void weapon1Choose(ActionEvent event) throws IOException{
		try{
			battle.purchaseWeapon(1, battle.getOriginalLanes().get(laneChosen));
			updateWalls();
			lanesEnable();
			getTextArea(laneChosen).appendText("\n"+battle.getWeaponFactory().getWeaponShop().get(1).getName());
			updateGameInfo();
			moveTitans();
		}
		catch(InvalidLaneException e){
			displayAlert("Invalid Lane selection", "You cannot choose a lost lane");
			lanesEnable();
		}
		catch(InsufficientResourcesException e){
			displayAlert("Invalid Weapon selection", "Not sufficient resources to buy weapon");
		}
		catch(NullPointerException e){
			Parent root=FXMLLoader.load(getClass().getResource("EndGame.fxml"));
			stage=(Stage)((Node)event.getSource()).getScene().getWindow();
			Scene scene= new Scene(root,1300,600);
			stage.setScene(scene);
			stage.show();

		}
	}
	public void weapon2Choose(ActionEvent event) throws IOException{
		try{
			battle.purchaseWeapon(2, battle.getOriginalLanes().get(laneChosen));
			updateWalls();
			lanesEnable();
			getTextArea(laneChosen).appendText("\n"+battle.getWeaponFactory().getWeaponShop().get(2).getName());
			updateGameInfo();
			moveTitans();

		}
		catch(InvalidLaneException e){
			displayAlert("Invalid Lane selection", "You cannot choose a lost lane");
			lanesEnable();
		}
		catch(InsufficientResourcesException e){
			displayAlert("Invalid Weapon selection", "Not sufficient resources to buy weapon");
		}
		catch(NullPointerException e){
			Parent root=FXMLLoader.load(getClass().getResource("EndGame.fxml"));
			stage=(Stage)((Node)event.getSource()).getScene().getWindow();
			Scene scene= new Scene(root,1300,600);
			stage.setScene(scene);
			stage.show();

		}
	}
	public void weapon3Choose(ActionEvent event) throws IOException{
		try{
			battle.purchaseWeapon(3, battle.getOriginalLanes().get(laneChosen));
			updateWalls();
			lanesEnable();
			getTextArea(laneChosen).appendText("\n"+battle.getWeaponFactory().getWeaponShop().get(3).getName());
			updateGameInfo();
			moveTitans();
		}
		catch(InvalidLaneException e){
			displayAlert("Invalid Lane selection", "You cannot choose a lost lane");
			lanesEnable();
		}
		catch(InsufficientResourcesException e){
			displayAlert("Invalid Weapon selection", "Not sufficient resources to buy weapon");
		}
		catch(NullPointerException e){
			Parent root=FXMLLoader.load(getClass().getResource("EndGame.fxml"));
			stage=(Stage)((Node)event.getSource()).getScene().getWindow();
			Scene scene= new Scene(root,1300,600);
			stage.setScene(scene);
			stage.show();

		}
	}
	public void weapon4Choose(ActionEvent event) throws IOException{
		try{
			battle.purchaseWeapon(4, battle.getOriginalLanes().get(laneChosen));
			updateWalls();
			lanesEnable();
			getTextArea(laneChosen).appendText("\n"+battle.getWeaponFactory().getWeaponShop().get(4).getName());
			updateGameInfo();
			moveTitans();
		}
		catch(InvalidLaneException e){
			displayAlert("Invalid Lane selection", "You cannot choose a lost lane");
			lanesEnable();
		}
		catch(InsufficientResourcesException e){
			displayAlert("Invalid Weapon selection", "Not sufficient resources to buy weapon");
		}
		catch(NullPointerException e){
			Parent root=FXMLLoader.load(getClass().getResource("EndGame.fxml"));
			stage=(Stage)((Node)event.getSource()).getScene().getWindow();
			Scene scene= new Scene(root,1300,600);
			stage.setScene(scene);
			stage.show();

		}
	}
	public void passTurn(ActionEvent event) throws IOException{
		try{
			battle.passTurn();
		updateWalls();
		lanesEnable();
		updateGameInfo();
		moveTitans();
		}
		catch(NullPointerException e){
			Parent root=FXMLLoader.load(getClass().getResource("EndGame.fxml"));
			stage=(Stage)((Node)event.getSource()).getScene().getWindow();
			Scene scene= new Scene(root,1300,600);
			stage.setScene(scene);
			stage.show();
			showScore.setText("Score: "+battle.getScore());
		}
		}
	public void displayScore(ActionEvent event){
		display.setVisible(false);
		showScore.setVisible(true);
		showScore.setText("Score: "+ battle.getScore());
	}
	 private void displayAlert(String title, String message) {
	        Stage alertStage = new Stage();
	        alertStage.setTitle(title);

	        Label label = new Label(message);
	        Button closeButton = new Button("Continue Playing");
	        //closing is predefined
	        closeButton.setOnAction(event -> alertStage.close());

	        BorderPane pane = new BorderPane();
	        pane.setTop(label);
	        pane.setCenter(closeButton);

	        Scene scene = new Scene(pane, 500, 100);
	        alertStage.setScene(scene);
	        alertStage.show();
	    }
	
}
