package controllers;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;

import com.sun.media.jfxmediaimpl.platform.Platform;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.PointLight;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;
import noyau.Circuit;
import noyau.Composant;
import noyau.ComposantDeChronogramme;
import noyau.EtatLogique;
import noyau.Horloge;
import noyau.Pin;
import noyau.Sequentiels;
import sun.java2d.d3d.D3DSurfaceData;
import javafx.scene.input.MouseEvent;



public class ChronogrammeController implements Initializable {


	private static ImageView detectionBar=new ImageView();
	public static ArrayList<Sequentiels> composantDechrono = new ArrayList<Sequentiels>();

	@FXML
	private AnchorPane chronogrameField;
	private static Horloge horlogeDecHRONO;
	private static HashMap< Line, Integer[]> valeursDesuivis = new HashMap<Line, Integer[]>();
	@FXML
	private  TableView<ComposantDeChronogramme> tableView;

	private static TableView<ComposantDeChronogramme> extTableView;
	@FXML
	private  TableColumn<ComposantDeChronogramme, String> elementsColumn;

	@FXML
	private  TableColumn<ComposantDeChronogramme, String>  valeurQ;
	private static AnchorPane field;
	@FXML
	private  TableColumn<ComposantDeChronogramme, String>  horlogeColumn;
	@FXML
	private TableColumn<ComposantDeChronogramme, String> valeurQbar; 
	@FXML
	private Label label0;
	@FXML
	private AnchorPane lightBox;
	public static AnchorPane lightBoxH;

	@FXML
	private Label label1;

	@FXML
	private Label label2;

	@FXML
	private Label label3;

	@FXML
	private Label label4;

	@FXML
	private Label label5;

	@FXML
	private Label label6;

	@FXML
	private Label label7;

	@FXML
	private Label label8;

	@FXML
	private Label label9;

	@FXML
	private Label label10;
	@FXML
	private ImageView playButton;
	private static ArrayList<Line> frontList= new ArrayList<Line>();
	static int i=1;


	@FXML
	private ImageView stopBotton;
	@FXML
	private Button homebutton;

	@FXML
	private ImageView pauseBotton;
	public static boolean first=true;

	@FXML
	private  ScrollPane scrollPane;
	private static ScrollPane scrolDeChrono;
	@FXML
	private Line line;


	@FXML
	private PointLight p1;

	@FXML
	private PointLight p2;

	@FXML
	private PointLight p3;

	@FXML
	private PointLight p4;

	@FXML
	private PointLight p5;

	@FXML
	private PointLight p6;

	@FXML
	private PointLight p7;

	@FXML
	private PointLight p8;

	@FXML
	private PointLight p9;

	@FXML
	private PointLight p10;


	private static Label[] labels;

	double origineDeChronogramme; 
	double translationDeSouris;
	public static ArrayList<PointLight> pointsDeDepart=new ArrayList<PointLight>();

	@FXML
	void arreter(MouseEvent event) {

		playButton.setImage(new Image("/chronoIcones/CHRONO_START_OFF.png"));
		pauseBotton.setImage(new Image("/chronoIcones/PAUSE.png"));

		chronogrameField.getChildren().removeAll(frontList);
		frontList.clear();
		valeursDesuivis.clear();
		for(int i=0 ; i<composantDechrono.size();i++)
		{
			composantDechrono.get(i).setStartChronoX(1);
			composantDechrono.get(i).setStartChronoY(pointsDeDepart.get(i).getLayoutY());
		}
		Horloge.setStartX(1);
		Horloge.setStartY(76);
		first=true;
		scrolDeChrono.setHvalue(0);
		i=1;
		detectionBar.setLayoutX(0);
		HomeController.chrono=true;


	}
	@FXML
	void playEnter(MouseEvent event) {
		playButton.setCursor(Cursor.HAND);
	}

	@FXML
	void stopEnter(MouseEvent event) {
		pauseBotton.setCursor(Cursor.HAND);
	}
	@FXML
	void clickHome(MouseEvent event) {
		Stage s = (Stage) homebutton.getScene().getWindow();
		s.close();	
		composantDechrono.clear();
		valeursDesuivis.clear();
		extTableView.getItems().clear();    		
		first=true;
		i=1;
		Horloge.setStartX(1);
		Horloge.setStartY(76);
		HomeController.chrono=false;
		scrolDeChrono.setHvalue(0);

	}

	@FXML
	void homeEnter(MouseEvent event) {
		homebutton.setCursor(Cursor.HAND);
	}
	@FXML
	void arreterEnter(MouseEvent event) {
		stopBotton.setCursor(Cursor.HAND);
	}

	@FXML
	void stoper(MouseEvent event) {	    			  
		if( HomeController.chrono==true)
		{
			lightBoxH.setStyle("-fx-background-color:#303337");
			pauseBotton.setImage(new Image("/chronoIcones/CHRONO_STOP_OFF.png"));
			playButton.setImage(new Image("/chronoIcones/START.png"));
			HomeController.chrono=false;  
			for(int i=0 ; i<composantDechrono.size();i++)
			{
				composantDechrono.get(i).setStartChronoX(1);
				composantDechrono.get(i).setStartChronoY(pointsDeDepart.get(i).getLayoutY());
			}
			Horloge.setStartX(1);
			Horloge.setStartY(76);
			first=true;
			scrolDeChrono.setHvalue(0);
			i=1;
		}
	}

	@FXML
	void play(MouseEvent event) {
		if(HomeController.chrono ==false)
		{
			pauseBotton.setImage(new Image("/chronoIcones/PAUSE.png"));
			valeursDesuivis.clear();
			chronogrameField.getChildren().removeAll(frontList);	     
			detectionBar.setLayoutX(0);
			HomeController.chrono=true;
			playButton.setImage(new Image("/chronoIcones/CHRONO_START_OFF.png"));
		}
		Circuit.defaultValueSeq();
		for(Line line : valeursDesuivis.keySet())
		{
			if (line.getStartX() <= detectionBar.getLayoutX()+4 && detectionBar.getLayoutX()+4 <= line.getEndX())
			{
				labels[valeursDesuivis.get(line)[0]].setText(String.valueOf(valeursDesuivis.get(line)[1]));
			}
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lightBoxH=lightBox;
		pauseBotton.setImage(new Image("/chronoIcones/CHRONO_STOP_OFF.png"));
		labels=new Label[] {label0,label1,label2,label3,label4,label5,label6,label7,label8,label9,label10};
		tracerGrilleChrono();			
		i=1;
		scrolDeChrono=scrollPane;
		pointsDeDepart.addAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10));
		extTableView=tableView;
		extTableView.getItems().add(horlogeDecHRONO);
		field= chronogrameField;
		elementsColumn.setCellValueFactory(dat-> new SimpleStringProperty(dat.getValue().getNom()));
		valeurQ.setCellValueFactory(dat-> new SimpleStringProperty(String.valueOf(dat.getValue().getSortieAafficher().getNum())));
		valeurQbar.setCellValueFactory(dat-> new SimpleStringProperty(String.valueOf(dat.getValue().getSortieBar().getNum())));

		for(int i=0;i<composantDechrono.size();i++)
		{
			tableView.getItems().add(composantDechrono.get(i));
			composantDechrono.get(i).setStartChronoX(1);
			composantDechrono.get(i).setStartChronoY(pointsDeDepart.get(i).getLayoutY());
		}

		scrollPane.setPannable(false);
		detectionBar.setImage(new Image("/chronoIcones/SUIVIT.png"));

		chronogrameField.getChildren().add(detectionBar);
		ajouterDragX(detectionBar);
	}

	public static void refrecher() {

		extTableView.refresh();


	}

	public static void tracerFront(EtatLogique etatDeHorloge) {

		Line horHline=new Line();
		Line verHline=new Line();



		if(etatDeHorloge.getNum() == 0)
		{

			if(first) Horloge.setStartY(Horloge.getStartY()-22);
			verHline=new Line(
					Horloge.getStartX(),Horloge.getStartY(),
					Horloge.getStartX(),Horloge.getStartY()+22
					);
			Horloge.setStartY(Horloge.getStartY()+22);
			horHline=new Line(
					Horloge.getStartX(),Horloge.getStartY(),
					Horloge.getStartX()+25,Horloge.getStartY()
					);
			Horloge.setStartX(Horloge.getStartX()+25);
		}
		else
		{
			verHline=new Line(
					Horloge.getStartX(),Horloge.getStartY(),
					Horloge.getStartX(),Horloge.getStartY()-22
					);
			Horloge.setStartY(Horloge.getStartY()-22);
			horHline=new Line(
					Horloge.getStartX(),Horloge.getStartY(),
					Horloge.getStartX()+25,Horloge.getStartY()
					);
			Horloge.setStartX(Horloge.getStartX()+25);
		}

		for(int i=0;i<composantDechrono.size();i++)
		{


			EtatLogique etat= composantDechrono.get(i).getSortieAafficher();
			Line horline=new Line();
			Line verline=new Line();

			verline.setStroke(Color.web("90EE90"));

			verline.setStyle("-fx-stroke-width: 2px;");

			if(composantDechrono.get(i).getFront().getNum()==composantDechrono.get(i).getEntreeHorloge().getEtat().getNum()
					&&composantDechrono.get(i).getEntreeHorloge().getEtat().getNum()!= composantDechrono.get(i).getEtatPrecHorloge().getNum() )
			{

				if(composantDechrono.get(i).getEtatAvant().getNum() != etat.getNum())
				{

					if(etat.getNum()==1)
					{

						verline=new Line(
								composantDechrono.get(i).getStartChronoX(),composantDechrono.get(i).getStartChronoY(),
								composantDechrono.get(i).getStartChronoX(),composantDechrono.get(i).getStartChronoY()-22
								);
						composantDechrono.get(i).setStartChronoY( composantDechrono.get(i).getStartChronoY()-22);
						horline=new Line(
								composantDechrono.get(i).getStartChronoX(),composantDechrono.get(i).getStartChronoY(),
								composantDechrono.get(i).getStartChronoX()+25,composantDechrono.get(i).getStartChronoY()
								);
						composantDechrono.get(i).setStartChronoX( composantDechrono.get(i).getStartChronoX()+25);
					}
					else {
						if(first) composantDechrono.get(i).setStartChronoY( composantDechrono.get(i).getStartChronoY()-22);
						verline=new Line(
								composantDechrono.get(i).getStartChronoX(),composantDechrono.get(i).getStartChronoY(),
								composantDechrono.get(i).getStartChronoX(),composantDechrono.get(i).getStartChronoY()+22
								);
						composantDechrono.get(i).setStartChronoY( composantDechrono.get(i).getStartChronoY()+22);
						horline=new Line(
								composantDechrono.get(i).getStartChronoX(),composantDechrono.get(i).getStartChronoY(),
								composantDechrono.get(i).getStartChronoX()+25,composantDechrono.get(i).getStartChronoY()
								);
						composantDechrono.get(i).setStartChronoX( composantDechrono.get(i).getStartChronoX()+25);

					}
					verline.setStroke(Color.web("90EE90"));					
					verline.setStyle("-fx-stroke-width: 2px;");
					field.getChildren().add(verline);
					frontList.add(verline);
				}
				else {
					if(etat.getNum()==1 && first) composantDechrono.get(i).setStartChronoY( composantDechrono.get(i).getStartChronoY()-22);
					horline=new Line(
							composantDechrono.get(i).getStartChronoX(),composantDechrono.get(i).getStartChronoY(),
							composantDechrono.get(i).getStartChronoX()+25,composantDechrono.get(i).getStartChronoY()
							);
					composantDechrono.get(i).setStartChronoX( composantDechrono.get(i).getStartChronoX()+25);

				}
			}
			else {

				if(etat.getNum()==1 && first) composantDechrono.get(i).setStartChronoY( composantDechrono.get(i).getStartChronoY()-22);
				horline=new Line(
						composantDechrono.get(i).getStartChronoX(),composantDechrono.get(i).getStartChronoY(),
						composantDechrono.get(i).getStartChronoX()+25,composantDechrono.get(i).getStartChronoY()
						);
				composantDechrono.get(i).setStartChronoX( composantDechrono.get(i).getStartChronoX()+25);

			}
			horline.setStyle("-fx-stroke-width: 2px;");
			horline.setStroke(Color.web("90EE90"));    
			field.getChildren().add(horline);
			frontList.add(horline);
			valeursDesuivis.put(horline, new Integer[] {i+1,etat.getNum()});

		}	 
		if(Horloge.getStartX()>470*i) 
		{
			scrolDeChrono.setHvalue(scrolDeChrono.getHvalue()+0.1);
			i++;
		}
		horHline.setStyle("-fx-stroke-width: 2px;");
		verHline.setStyle("-fx-stroke-width: 2px;");
		horHline.setStroke(Color.web("90EE90"));
		verHline.setStroke(Color.web("90EE90"));
		field.getChildren().add(horHline);
		field.getChildren().add(verHline);
		valeursDesuivis.put(horHline, new Integer[] {0,etatDeHorloge.getNum()});
		frontList.add(horHline);
		frontList.add(verHline);
		first=false;
	}

	public static Horloge getHorlogeDecHRONO() {
		return horlogeDecHRONO;
	}

	public static void setHorlogeDecHRONO(Horloge horlogeDecHRONO) {
		ChronogrammeController.horlogeDecHRONO = horlogeDecHRONO;
	}
	public void tracerGrilleChrono() {
		for (int i = 0; i <= chronogrameField.getPrefWidth(); i += 25 ) {
			Line l1 = new Line();
			l1.toBack();
			l1.setStyle("-fx-stroke-width: 0.2px;");
			l1.setStroke(Color.web("ffffff"));
			l1.setOpacity(0.8);
			l1.setLayoutX(i);
			l1.setLayoutY(0);
			l1.setStartX(0);
			l1.setStartY(0);
			l1.setEndX(0);
			l1.setEndY(chronogrameField.getPrefHeight());
			l1.getStrokeDashArray().addAll(2.2d);
			chronogrameField.getChildren().add(l1);
		}
	}

	private void ajouterDragX(final ImageView node) {
		node.setOnDragDetected(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				SnapshotParameters snapParams = new SnapshotParameters();
				snapParams.setFill(Color.TRANSPARENT);           
				detectionBar.setImage(node.snapshot(snapParams, null));
				detectionBar.startFullDrag();
				e.consume();
			}
		});
		node.setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				Point2D localPoint = chronogrameField.sceneToLocal(new Point2D(e.getSceneX(), e.getSceneY()));

				detectionBar.relocate(
						(int)(localPoint.getX() - detectionBar.getBoundsInParent().getWidth()/2),
						detectionBar.getLayoutY()
						);
				valeurSuivi();
				e.consume();
			}
		});
		node.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				node.setCursor(Cursor.HAND);
			}
		});
		node.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {

				detectionBar.setMouseTransparent(true);
				node.setMouseTransparent(true);
				node.setCursor(Cursor.CLOSED_HAND);
			}
		});
		node.setOnMouseReleased(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {

				detectionBar.setMouseTransparent(false);
				node.setMouseTransparent(false);
				node.setCursor(Cursor.DEFAULT);

			}
		});

	}
	public static void valeurSuivi() {
		for(Line line : valeursDesuivis.keySet())
		{

			if (line.getStartX() <= detectionBar.getLayoutX()+4 && detectionBar.getLayoutX()+4 <= line.getEndX())
			{
				labels[valeursDesuivis.get(line)[0]].setText(String.valueOf(valeursDesuivis.get(line)[1]));

			}

		}
	}
}

