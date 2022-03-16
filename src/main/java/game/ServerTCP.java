package game;

import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Represents a ServerTCP, that listens a certain port number
 *
 */
public class ServerTCP implements Serializable{

    private final int numberPort;
    public Grid grid;
    private final PropertyChangeSupport notifier;
    int nbConnectionsMax;
    int nbConnections;

    ServerTCP(int numberPort) {
        notifier = new PropertyChangeSupport(this);
        this.numberPort = numberPort;
        nbConnectionsMax = 2;
        nbConnections = 0;
    }

    public PropertyChangeSupport getNotifier() {return notifier;}

    public void setGrid(Grid grid) {this.grid = grid;}

    public Grid getGrid() {return grid;}

    public void go() {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        try {
            serverSocket = new ServerSocket(numberPort);
        } catch (IOException e) {
            System.out.println("Could not listen on port: " + numberPort + ", " + e);
            System.exit(1);
        }

        while (true) {
            try {
                System.out.println("Server is waiting for a client");
                System.out.println("Current number of clients: " + nbConnections);
                clientSocket = serverSocket.accept();
                this.nbConnections++;
            } catch (IOException e) {
                System.out.println("Accept failed: " + numberPort + ", " + e);
                System.exit(1);
            }
            SpecificServer specificServer = new SpecificServer(clientSocket, this);
            specificServer.start();
        }

        /*try {
            serverSocket.close();
            this.nbConnections--;
        } catch (IOException e) {
            System.out.println("Could not close");
        }*/
    }

    public String giveColor() {
        if (this.nbConnections % 2 == 0) {
            return "red";
        } else {
            return "yellow";
            }
        }
}


