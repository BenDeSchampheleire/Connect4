package launchPattern;

import servPattern.DemandeManageGame;
import servPattern.DemandePlayChecker;
import servPattern.Grid;
import servPattern.ServeurTCP;

public class MainServeur {

	public static void main(String[] args) {
		Grid grid = new Grid(7, 6, new DemandePlayChecker(), new DemandeManageGame());
		//ServeurTCP myServ = new ServeurTCP(new UnContexte() , new ProtocolePingPong() , 6666 );
		//myServ.start();
		grid.startGame();
		
	}
}
