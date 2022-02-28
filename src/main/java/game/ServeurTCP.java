package game;

import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Représente un serveur TCP, qui écoute sur un numéro de port
 *
 */
public class ServeurTCP implements Serializable{

    private final int numeroPort;
    public IGrid grid;
    private final PropertyChangeSupport notifier;
    int nbConnectionMax;
    int nbConnection;

    ServeurTCP(int unNumeroPort) {
        notifier = new PropertyChangeSupport(this);
        numeroPort = unNumeroPort;
        nbConnectionMax = 2;
        nbConnection = 0;
    }

    public PropertyChangeSupport getNotifier() {return notifier;}

    public void setGrid(IGrid grid) {this.grid = grid;}

    public IGrid getGrid() {return grid;}

    public void go() {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        try {
            serverSocket = new ServerSocket(numeroPort);
        } catch (IOException e) {
            System.out.println("Could not listen on port: " + numeroPort + ", " + e);
            System.exit(1);
        }

        // Attention: on décide arbitrairement de ne servir que 4 clients, puis de
        // fermer la connexion
        while (nbConnection <= nbConnectionMax) {
            try {
                System.out.println(" Attente du serveur pour la communication d'un client ");
                System.out.println(" Nombre de clients actuellement " + nbConnection);
                clientSocket = serverSocket.accept();
                this.nbConnectionMax ++;
            } catch (IOException e) {
                System.out.println("Accept failed: " + 6666 + ", " + e);
                System.exit(1);
            }
            ServeurSpecifique st = new ServeurSpecifique(clientSocket, this);
            st.start();
        }

        try {
            serverSocket.close();
            this.nbConnectionMax ++;
        } catch (IOException e) {
            System.out.println("Could not close");
        }
    }



}

