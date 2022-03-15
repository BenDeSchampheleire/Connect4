package launchPattern;

import client.Automate;
import client.ClientTCP;
import client.GameApplication;
import javafx.application.Application;

public class MainClient {

	public static void main(String[] args) {
		ClientTCP myClt = new ClientTCP("localhost", 6666 );
		
//		if ( myClt.connecterAuServeur() ) {
//			myClt.transmettreChaine("PING");
//			myClt.deconnecterDuServeur();
//		}
		// Instancie l'interface graphique du client
		Application.launch(GameApplication.class, args);
		System.out.println("attente interface graphique");
	
	}

}
