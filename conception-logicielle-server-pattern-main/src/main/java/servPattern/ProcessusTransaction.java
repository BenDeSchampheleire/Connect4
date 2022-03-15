package servPattern;
import java.io.IOException;
import java.net.Socket;

/**
 * Processus de d'action (anciennement ServeurSpecifique)
 */
class ProcessusTransaction extends Thread {

	private Socket clientSocket;
	private ServeurTCP monServeurTCP;

	public  ProcessusTransaction(Socket uneSocket, ServeurTCP unServeur) {        
		super("ServeurThread");
		clientSocket = uneSocket;
		System.out.println("[ProcessusAction] CLIENT : " + clientSocket);
		monServeurTCP = unServeur;
	} 

	public void run() {        
		try {

			monServeurTCP.getProtocole().execute( monServeurTCP.getContexte() , clientSocket.getInputStream() , clientSocket.getOutputStream() );

			System.out.println("Processus action fait");
		} catch (IOException e) {
			System.err.println("[ProcessusAction] Exception : " + e );
			e.printStackTrace();
		}
	} 
}
