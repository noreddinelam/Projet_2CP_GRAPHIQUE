package application;

import java.util.ArrayList;
import java.util.Collections;

import controllers.HomeController;

import controllers.PremierePageController;
import javafx.animation.FadeTransition;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.image.Image;
import noyau.*;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
//			
						
						FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
						
						Parent root = (Parent)loader.load();
						
						Scene scene = new Scene(root);
						scene.getStylesheets().add(getClass().getResource("/styleFile/application.css").toExternalForm());
						
						HomeController controller = (HomeController)loader.getController();
						controller.setHomeControllerStage(primaryStage);
						controller.setHomeControllerScene(scene);
						controller.inisialiser();
						
						/*FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Home.fxml"));
		                Parent root = (Parent) fxmlLoader.load();
						HomeController controller = new HomeController(primaryStage);*/
						

//					 	FadeTransition fade = new FadeTransition();  
//			         	fade.setDuration(Duration.millis(1000)); 
//			         	fade.setDelay(Duration.millis(4000));
//				        fade.setFromValue(10);  
//				        fade.setToValue(0.1);    
//				        fade.setCycleCount(0);  
//				        fade.setAutoReverse(true);     
//				        fade.setNode(root);  
//				        fade.play(); 
						//Scene scene = new Scene(root);
						primaryStage.setScene(scene);
//						PRIMARYSTAGE.GETICONS().ADD(NEW IMAGE("PATH"));
						//primaryStage.initStyle(StageStyle.UNDECORATED);
						primaryStage.setResizable(false);
						primaryStage.show();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		
		///////////////////////////////////////////////////////////////////////////////
		//--> Test d'un deMultipluexeur
 	
//		Circuit circuit =new Circuit();
//		Demultiplexeur deMultiplexeur = new Demultiplexeur(2);
//		
//		Pin pin1 = new Pin(true);
// 		Pin pin2 = new Pin(true);
// 		Pin pin3= new Pin(true);
// 		Pin pin4 = new Pin(true);
// 		
// 		pin1.setEtat(EtatLogique.ONE);
// 		Pin c1=new Pin(true);
// 		Pin c2=new Pin(true);
// 		c1.setEtat(EtatLogique.ONE);
// 		c2.setEtat(EtatLogique.ZERO);
// 		circuit.relier(pin1, deMultiplexeur, 0, 0);
//		circuit.relierCommand(c1, deMultiplexeur, 0, 0);
//		circuit.relierCommand(c2, deMultiplexeur, 0, 1);
//		pin1.evaluer();
//		c1.evaluer();
//		c2.evaluer();
//		//deMultiplexeur.genererSorties();
//		System.out.println(deMultiplexeur.sorties[1].getEtatLogiqueFil());
 	
		//////////////////////////////////////////////////////////////////////////
		//--> Test d'un decodeur
		
//		Circuit circuit =new Circuit();
//		Decodeur decodeur = new Decodeur(2);
//		Encodeur encodeur = new Encodeur(4);
//		
//		Pin pin1 = new Pin(true);
// 		Pin pin2 = new Pin(true);
// 		
// 		pin1.setEtat(EtatLogique.ZERO);
// 		pin2.setEtat(EtatLogique.ZERO);
// 		circuit.relier(pin1, decodeur, 0, 0);
//		circuit.relier(pin2, decodeur, 0, 1);
//		circuit.relier(decodeur,encodeur, 0, 0);
//		circuit.relier(decodeur,encodeur, 1, 1);
//		circuit.relier(decodeur,encodeur, 2, 2);
//		circuit.relier(decodeur,encodeur, 3, 3);
//		pin1.evaluer();
//		pin2.evaluer();
//		for (int i = 0; i < decodeur.nombreSortie; i++) {
//			System.out.println(decodeur.sorties[i].getEtatLogiqueFil());
//		}
//		for (int i = 0; i < encodeur.nombreSortie; i++) {
//			System.out.println(encodeur.sorties[i].getEtatLogiqueFil());
//		}
 	
		///////////////////////////////////////////////////////////////////////
		//--> Simulation d'un demiAdditionneur
 	
//		DemiAdditionneur demiAdditionneur = new DemiAdditionneur(2);
//		Circuit circuit = new Circuit();
//		
//		Pin pin1 = new Pin(true);
//		Pin pin2 = new Pin(true);
//		Pin pin3 = new Pin(true);
//		Pin pin4 = new Pin(true);
//		
//		pin1.setEtat(EtatLogique.ONE);
//		pin2.setEtat(EtatLogique.ONE);
//		pin3.setEtat(EtatLogique.ONE);
//		pin4.setEtat(EtatLogique.ONE);
//		
//		circuit.relier(pin1,demiAdditionneur, 0, 0);
//		circuit.relier(pin2,demiAdditionneur, 0, 1);
//		circuit.relier(pin3,demiAdditionneur, 0, 2);
//		circuit.relier(pin4,demiAdditionneur, 0, 3);
//		
//		pin1.evaluer();
//		pin2.evaluer();
//		pin3.evaluer();
//		pin4.evaluer();
//		
//		for (int i = 0; i < demiAdditionneur.nombreSortie; i++) {
//			System.out.println(demiAdditionneur.sorties[i].getEtatLogiqueFil());
//		}
 	
		//////////////////////////////////////////////////////////////////////////
		//--> Test d'un additionneur
		
//		AdditionneurN_Bites additionneurN_Bites = new AdditionneurN_Bites(2);
//		Circuit circuit = new Circuit();
//		Pin pin1 = new Pin(true);
//		Pin pin2 = new Pin(true);
//		Pin pin3 = new Pin(true);
//		Pin pin4 = new Pin(true);
//		Pin pin5 = new Pin(true);
//		
//		pin1.setEtat(EtatLogique.ONE);
//		pin2.setEtat(EtatLogique.ONE);
//		pin3.setEtat(EtatLogique.ONE);
//		pin4.setEtat(EtatLogique.ONE);
//		pin5.setEtat(EtatLogique.ONE);
//		
//		circuit.relier(pin1,additionneurN_Bites, 0, 0);
//		circuit.relier(pin2,additionneurN_Bites, 0, 1);
//		
//		circuit.relier(pin3,additionneurN_Bites, 0, 2);
//		circuit.relier(pin4,additionneurN_Bites, 0, 3);
//		
//		circuit.relier(pin5,additionneurN_Bites, 0, 4);
//		
//		pin1.evaluer();
//		pin2.evaluer();
//		pin3.evaluer();
//		pin4.evaluer();
//		pin5.evaluer();
//		
//		for (int i = 0; i < additionneurN_Bites.nombreSortie; i++) {
//			System.out.println(additionneurN_Bites.sorties[i].getEtatLogiqueFil());
//		}
 	
 	//////////////////////////////////////////////////////////////////////////////////
		//--> Simulation d'un additionneur Complet a base des portes logiques
		
//		Circuit circuit = new Circuit();
//		Pin a0= new Pin(true,"a0");
//		a0.setEtat(EtatLogique.ONE);
//		Pin b0= new Pin(true,"b0");
//		b0.setEtat(EtatLogique.ONE);
//		Pin r0=new Pin(true,"r0");
//		
//		Xor xor1=new Xor(2,"xor1");
//		Xor xor2=new Xor(2,"xor2");
//		And and1=new And(2,"and1");
//		And and2=new And(2,"and2");
//		Or or=new Or(2,"or");
//		Not inverseur=new Not("inv");
//		Not inverseur2=new Not("inv2");
//		Pin pinS1 = new Pin(false,"pinS1");
//		Pin pinS2 = new Pin(false,"pinS2");
//		circuit.relier(a0, xor1, 0, 0);
//		circuit.relier(b0, xor1, 0, 1);
//		circuit.relier(a0, and1, 0, 0);
//		circuit.relier(b0, and1, 0, 1);
//		circuit.relier(xor1, xor2, 0, 0);
//		circuit.relier(r0, xor2, 0, 1);
//		circuit.relier(xor1, and2, 0, 0);
//		circuit.relier(r0, and2, 0, 1);
//		circuit.relier(and1, or, 0, 0);
//		circuit.relier(and2, or, 0, 1);
//		circuit.relier(xor2, inverseur, 0, 0);
//		circuit.relier(inverseur, inverseur2, 0, 0);
//		circuit.relier(or, pinS1, 0, 0);
//		circuit.relier(xor2, pinS2, 0, 0);
//		System.out.println("R  E1  E2  RS   S");
//		circuit.tableVerite();
//		circuit.afficher();
		/*a0.evaluer();
		b0.evaluer();
		r0.evaluer();
		System.out.println("Le resultat est :"+ inverseur2.sorties[0].getEtatLogiqueFil());
		System.out.println("La ret est : " + or.sorties[0].getEtatLogiqueFil());*/
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//--> TEST COMBINATOIRE
		
//		Circuit circuit = new Circuit();
//		Pin a4=new Pin(true,"P4");
//		Pin a3=new Pin(true,"P3");
//		Pin a2=new Pin(true,"P2");
//		Pin a1=new Pin(true,"P1");
//		Pin a0=new Pin(true,"P0");
//		Pin pinS1 = new Pin(false,"PS");
//		//Pin mass = new Pin(true);
//		SourceConstante mass = new SourceConstante(EtatLogique.ZERO,"Masse");
//		
//		a4.setEtat(EtatLogique.ZERO);	a3.setEtat(EtatLogique.ZERO);	a2.setEtat(EtatLogique.ONE);      a1.setEtat(EtatLogique.ZERO);    a0.setEtat(EtatLogique.ZERO);
//		
//		Not inverseur1 =new Not("N1");
//		Not inverseur2 =new Not("N2");
//		And and1=new And(2,"A1");
//		And and2=new And(2,"A2");
//		Multiplexeur multiplexeur=new Multiplexeur(8,"Mux");
//		circuit.relierCommand(a0, multiplexeur, 0, 0);
//		circuit.relierCommand(a1, multiplexeur, 0, 1);
//		circuit.relierCommand(a2, multiplexeur, 0, 2);
//		circuit.relier(a3, inverseur1, 0, 0);
//		circuit.relier(a4, inverseur2, 0, 0);
//		circuit.relier(inverseur1, and1, 0, 0);
//		circuit.relier(inverseur2, and1, 0, 1);
//		circuit.relier(and1, multiplexeur, 0, 0);
//		circuit.relier(mass, multiplexeur, 0, 1);
//		circuit.relier(a3, multiplexeur, 0, 2);
//		circuit.relier(mass, multiplexeur, 0, 3);
//		circuit.relier(a4, multiplexeur, 0, 4);
//		circuit.relier(inverseur1, and2, 0, 0);
//		circuit.relier(inverseur2, and2, 0, 1);
//		circuit.relier(and2, multiplexeur, 0, 5);
//		circuit.relier(mass, multiplexeur, 0, 6);
//		circuit.relier(a3, multiplexeur, 0, 7);
//		circuit.relier(multiplexeur, pinS1, 0, 0);
//		
//		for(Pin pin : circuit.getEntreesCircuit())
//			System.out.println();
//	
////		a0.evaluer();
////		a1.evaluer();
////		a2.evaluer();
////		a3.evaluer();
////		a4.evaluer();
////		mass.evaluer();
////		System.out.println(multiplexeur.sorties[0].getEtatLogiqueFil());
//		
//		circuit.tableVerite();
//		circuit.afficher();
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		///--> TEST REGISTRE A DECALGE //
//      Circuit circuit = new Circuit();
//		RegistreDecalage registreDecalage = new RegistreDecalage(4, "REG", false);
//		Pin a4=new Pin(true,"P4");
//		Pin a3=new Pin(true,"P3");
//		Pin a2=new Pin(true,"P2");
//		Pin a1=new Pin(true,"P1");
//		Pin a0=new Pin(true,"P0");
//		
//		System.out.println(a0.getEtat());
//		System.out.println(registreDecalage.valeur());
//		
//		a0.setEtat(EtatLogique.ONE);
//		
//		Pin ps	= new Pin(false, "PS");
//		
//		circuit.relier(a0, registreDecalage, 0, 0);
//		
//		circuit.relier(registreDecalage, ps, 0, 0);
//		
//		a0.evaluer();
//		System.out.println(a0.getEtat());
//		System.out.println(registreDecalage.valeur());
//		
//		a0.setEtat(EtatLogique.ZERO);
//		
//		a0.evaluer();
//		System.out.println(a0.getEtat());
//		System.out.println(registreDecalage.valeur());
//		
//		a0.evaluer();
//		System.out.println(a0.getEtat());
//		System.out.println(registreDecalage.valeur());
//		
//		a0.evaluer();
//		System.out.println(a0.getEtat());
//		System.out.println(registreDecalage.valeur());
		////////////////////////////////////////////////////////////////////////////////////////////////
//		Circuit circuit = new Circuit();
//		
//		Compteur compteur = new Compteur(3, "cpt");
//		
//		compteur.setCompter(false);
//		
//		Pin pin1 = new Pin(true,"pin");
//		Pin pin2 = new Pin(true,"pin");
//		Pin pin3 = new Pin(true,"pin");
//		
//		Pin horPin = new Pin(true, "pin");
//		
//		pin1.setEtat(EtatLogique.ONE);
//		pin2.setEtat(EtatLogique.ONE);
//		pin3.setEtat(EtatLogique.ONE);
//		
//		pin1.evaluer();
//		pin2.evaluer();
//		pin3.evaluer();
//		
//		horPin.setEtat(EtatLogique.ONE);
//		
//		Pin pin4 = new Pin(false,"pin");
//		Pin pin5 = new Pin(false,"pin");
//		Pin pin6 = new Pin(false,"pin");
//		
//		circuit.relier(pin1, compteur, 0, 0);
//		circuit.relier(pin2, compteur, 0, 1);
//		circuit.relier(pin3, compteur, 0, 2);
//		
//		circuit.relier(compteur, pin4, 0, 0);
//		circuit.relier(compteur, pin5, 1, 0);
//		circuit.relier(compteur, pin6, 2, 0);
//		
//		circuit.relierHorloge(compteur, horPin, 0);
//		
//		System.out.println("valeur : "+compteur.getValeur());
//		for (int i = 0; i < 16; i++) {
//			if (i % 2 == 0) {
//				horPin.setEtat(EtatLogique.ZERO);
//			}
//			else {
//				horPin.setEtat(EtatLogique.ONE);
//			}
//			horPin.evaluer();
//			System.out.println("tour : " + i + " valeur : "+compteur.getValeur());
//		}
		
		///////////////////////////////////////////////////////////////////////////////////////////////
//		Circuit circuit = new Circuit();
//		JK b1 = new JK("B1");
//		JK b2 = new JK("B2");
//		JK b3 = new JK("B3");
//		
//		//And and = new And(2, "AND");
//		
//		Pin p = new Pin(true,"pin");
//		Horloge h = new Horloge("H");
//		
//		p.setEtat(EtatLogique.ONE);
//		
//		//circuit.relier(b1, and, 0, 0);
//		//circuit.relier(b2, and, 0, 1);
//		
//		circuit.relier(p, b1, 0, 0);
//		circuit.relier(p, b1, 0, 1);
//		circuit.relier(b1, b2, 1, 0);
//		circuit.relier(b1, b2, 0, 1);
//		
//		circuit.relierHorloge(b1,h,0);
//		circuit.relierHorloge(b2,h,0);
//		//circuit.relierHorloge(b3, and, 0);
//		
//		circuit.initialiser();
//		h.evaluer();
//		
//		System.out.println("Q1 : " + b1.sorties[0].getEtatLogiqueFil() + " Q1BAR : " + b1.sorties[1].getEtatLogiqueFil());
//		System.out.println("Q2 : " + b2.sorties[0].getEtatLogiqueFil() + " Q2BAR : " + b2.sorties[1].getEtatLogiqueFil());
//		
//		h.evaluer();
//		
//		System.out.println("Q1 : " + b1.sorties[0].getEtatLogiqueFil() + " Q1BAR : " + b1.sorties[1].getEtatLogiqueFil());
//		System.out.println("Q2 : " + b2.sorties[0].getEtatLogiqueFil() + " Q2BAR : " + b2.sorties[1].getEtatLogiqueFil());
//		
//		h.evaluer();
//		
//		System.out.println("Q1 : " + b1.sorties[0].getEtatLogiqueFil() + " Q1BAR : " + b1.sorties[1].getEtatLogiqueFil());
//		System.out.println("Q2 : " + b2.sorties[0].getEtatLogiqueFil() + " Q2BAR : " + b2.sorties[1].getEtatLogiqueFil());
//		//System.out.println(b3.getEtages());
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
//		Circuit circuit = new Circuit();
//		JK b0 = new JK("B1");
//		JK b1 = new JK("B2");
//		JK b2 = new JK("B3");
//		
//		Pin pin = new Pin(true, "p");
//		pin.setEtat(EtatLogique.ONE);
//		Horloge horloge = new Horloge("h");
//		
//		circuit.relier(pin, b0, 0, 0);
//		circuit.relier(pin, b0, 0, 1);
//		circuit.relier(pin, b1, 0, 0);
//		circuit.relier(pin, b1, 0, 1);
//		circuit.relier(pin, b2, 0, 0);
//		circuit.relier(pin, b2, 0, 1);
//		
//		circuit.relierHorloge(b0, horloge, 0);
//		circuit.relierHorloge(b1, b0, 0);
//		circuit.relierHorloge(b2, b1, 0);
//		
//		circuit.initialiser();
//		for (int i = 0; i < 8; i++) {
//			horloge.evaluer();
//			horloge.evaluer();
//			System.out.println("Q0 : " + b0.sorties[0].getEtatLogiqueFil() + " Q1 : " + b1.sorties[0].getEtatLogiqueFil() + " Q2 : "+ b2.sorties[0].getEtatLogiqueFil());
//		}
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//--> Test de bascule D sur un circuit compteur 
	
//		Circuit circuit = new Circuit();
//		D b0 = new D("D1",Front.Front_Descendant);
//		D b1 = new D("D2",Front.Front_Descendant);
//		D b2 = new D("D3",Front.Front_Descendant);
//		
//		circuit.relier(b0, b0, 1, 0);
//		circuit.relier(b1, b1, 1, 0);
//		circuit.relier(b2, b2, 1, 0);
//		
//		Horloge horloge = new Horloge("h",1000);
//		circuit.relierHorloge(b0, horloge, 0);
//		circuit.relierHorloge(b1, b0, 0);
//		circuit.relierHorloge(b2, b1, 0);
//		
//		Thread thread=new Thread(horloge);
//		thread.start();
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//--> Test des bascules JK
		
//		Circuit circuit=new Circuit();
//		JK jk1= new JK("jk1",Front.Front_Descendant);
//		JK jk2= new JK("jk2",Front.Front_Descendant);
//		JK jk3= new JK("jk3",Front.Front_Descendant);
//		Xor xor1=new Xor(2, "xor1");
//		Xor xor2=new Xor(2, "xor2");
//      	Pin p1=new Pin(true, "pin1");
//      	Pin p2=new Pin(true, "pin2");	
//		Pin X=new Pin(true, "X");
//		Horloge horloge=new Horloge("horloge",1000);
//		p1.setEtat(EtatLogique.ONE);
//		p2.setEtat(EtatLogique.ONE);
//		X.setEtat(EtatLogique.ONE);
//		circuit.relier(p1, jk1, 0, 0);
//		circuit.relier(p2, jk1, 0, 1);
//		circuit.relier(p1, jk2, 0, 0);
//		circuit.relier(p2, jk2, 0, 1);
//		circuit.relier(p1, jk3, 0, 0);
//		circuit.relier(p2, jk3, 0, 1);
//		circuit.relierHorloge(jk1, horloge, 0);
//		circuit.relier(jk1, xor1, 0, 0);
//		circuit.relier(X, xor1, 0, 1);
//		circuit.relierHorloge(jk2, xor1, 0);
//		circuit.relier(jk2, xor2, 0, 0);
//		circuit.relier(X, xor2, 0, 1);
//		circuit.relierHorloge(jk3, xor2, 0);
//		Thread thread=new Thread(horloge);
// 		thread.start();
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//--> Test des bascules D avec un registre a descalage 
	
//		Circuit circuit = new Circuit();
//		Circuit circuit2 = new Circuit();
//		D b0 = new D("D1",Front.Front_Montant);
//		D b1 = new D("D2",Front.Front_Montant);
//		D b2 = new D("D3",Front.Front_Montant);
//		Pin pin=new Pin(true, "pin");
//		pin.setEtat(EtatLogique.ONE);
//		circuit.relier(pin, b0, 0, 0);
//		circuit.relier(b0, b1, 0, 0);
//		circuit.relier(b1, b2, 0, 0);
//		Horloge horloge = new Horloge("h",1000);
//		circuit.relierHorloge(b0, horloge, 0);
//		circuit.relierHorloge(b1, horloge, 0);
//		circuit.relierHorloge(b2, horloge, 0);
//		Thread thread=new Thread(horloge);
//		thread.start();
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//--> Test du cpt :
//		Circuit circuit = new Circuit();
//		Compteur cpt= new Compteur(3, "cpt", Front.Front_Montant);
//		Horloge horloge = new Horloge("hrlg", 1000);
//		Pin clear= new Pin(true, "pin");
//		Pin pin = new Pin(true, "nom");
//		pin.setEtat(EtatLogique.ONE);
//		clear.setEtat(EtatLogique.ZERO);
//		circuit.relierHorloge(cpt, horloge, 0);
//		circuit.relierLoad(cpt, clear, 0);
//		circuit.relier(pin, cpt, 0, 0);
//		circuit.relier(pin, cpt, 0, 1);
//		circuit.relier(pin, cpt, 0, 2);
//		Thread thread=new Thread(horloge);
//		thread.start();
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//--> Test du cpt avec bascule JK
//		Circuit circuit = new Circuit();
//		Compteur cpt= new Compteur(3, "cpt", Front.Front_Montant);
//		JK jk = new JK("jk", Front.Front_Montant);
//		Horloge horloge = new Horloge("hrlg", 1000);
//		Pin pin = new Pin(true, "nom");
//		pin.setEtat(EtatLogique.ONE);
//		circuit.relier(pin, jk, 0, 0);
//		circuit.relier(pin, jk, 0, 1);
//		circuit.relierHorloge(jk, horloge, 0);
//		circuit.relierHorloge(cpt, jk, 0);
//		Thread thread=new Thread(horloge);
//		thread.start();
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//--> Test du cpt avec bascule JK et load à 0
//		Circuit circuit = new Circuit();
//		Compteur cpt= new Compteur(3, "cpt", Front.Front_Montant);
//		JK jk = new JK("jk", Front.Front_Montant);
//		Horloge horloge = new Horloge("hrlg", 1000);
//		Pin pin = new Pin(true, "nom");
//		Pin load= new Pin(true, "load");
//		Pin clear= new Pin(true, "clr");
//		pin.setEtat(EtatLogique.ONE);
//		clear.setEtat(EtatLogique.ONE);
//		circuit.relier(load, cpt, 0, 0);
//		circuit.relier(load, cpt, 0, 1);
//		circuit.relier(jk, cpt, 0, 2);
//		circuit.relier(pin, jk, 0, 0);
//		circuit.relier(pin, jk, 0, 1);
//		circuit.relierClear(cpt, clear, 0);
//		circuit.relierHorloge(jk, horloge, 0);
//		circuit.relierHorloge(cpt, horloge, 0);
//		circuit.relierLoad(cpt, load, 0);
//		Thread thread=new Thread(horloge);
//		thread.start();
		//horloge.run();
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//--> Test du reg à decalage avec load :
//		Circuit circuit = new Circuit();
//		RegistreDecalage reg = new RegistreDecalage(4, "reg", true, Front.Front_Montant);
//		Pin pin = new Pin(true, "nom");
//		pin.setEtat(EtatLogique.ONE);
//		Pin pin2 = new Pin(true, "nom");
//		pin2.setEtat(EtatLogique.ONE);
//		Pin load= new Pin(true, "clr");
//		Horloge horloge = new Horloge("hrlg", 1000);
//		circuit.relier(pin, reg, 0, 0);
//		circuit.relier(pin2, reg, 0, 1);
//		circuit.relier(pin2, reg, 0, 2);
//		circuit.relier(pin2, reg, 0, 3);
//		circuit.relier(pin2, reg, 0, 4);
//		circuit.relierLoad(reg,load, 0);
//		circuit.relierHorloge(reg, horloge, 0);
//		Thread thread=new Thread(horloge);
////		thread.start();
//		horloge.run();
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//--> Test du reg à decalage avec bascule T et porte not :
//		Circuit circuit = new Circuit();
//		RegistreDecalage reg = new RegistreDecalage(4, "reg", true, Front.Front_Montant);
//		//Compteur reg= new Compteur(3, "n", Front.Front_Montant);
//		Not not = new Not("not");
//		Or or = new Or(2, "or");
//		T t = new T("T", Front.Front_Montant);
//		Pin pin = new Pin(true, "nom");
//		pin.setEtat(EtatLogique.ONE);
//		Pin pin2 = new Pin(true, "nom");
//		pin2.setEtat(EtatLogique.ONE);
//		Horloge horloge = new Horloge("hrlg", 1000);
//		circuit.relier(pin,t, 0, 0);
//		circuit.relier(pin2, reg, 0, 0);
//		circuit.relier(t, or, 0, 0);
//		circuit.relier(t, not, 1, 0);
//		circuit.relier(not, or, 0, 1);
//		circuit.relierHorloge(t, horloge, 0);
//		circuit.relierHorloge(reg, or, 0);
//		Thread thread=new Thread(horloge);
//		thread.start();
		//horloge.run();
	}
}

/*package application;
import java.util.ArrayList;
import java.util.Collection;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Main extends Application{

    public static void main(String[] args){
        launch (args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Collection<String[]> list = new ArrayList<>();
        list.add("String1");
        list.add("String2");
        list.add("String3");
        list.add("String4");
        list.add("String5");
        list.add("String6");

        ObservableList<String[]> details = FXCollections.observableArrayList(list);

        TableView<String[]> tableView = new TableView<>();
        TableColumn<String[], String> col1 = new TableColumn<>();
        tableView.getColumns().addAll(col1);

        col1.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[0]));
        tableView.setItems(details);

        StackPane sp = new StackPane(tableView);
        Scene scene = new Scene(sp);
        primaryStage.setScene(scene);
        primaryStage.show();
   }*/
