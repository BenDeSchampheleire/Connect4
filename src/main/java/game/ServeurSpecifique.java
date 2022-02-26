package game;

import java.io.*;
import java.net.Socket;

/**
 * Représente la gestion de la connexion d'un client avec le serveur. Cette
 * gestion repose sur une {@link Socket} et s'effectue dans un {@link Thread}
 *
 *
 */
class ServeurSpecifique extends Thread {

    private final Socket clientSocket;
    private final ServeurTCP monServeur;

    public ServeurSpecifique(Socket uneSocket, ServeurTCP unServeur) {
        super("ServeurThread");

        clientSocket = uneSocket;
        monServeur = unServeur;
    }

    @Override
    public void run() {
        String inputReq;

        try {
            // create an object output stream from the output stream so we can send an object through it
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());

            BufferedReader is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintStream os = new PrintStream(clientSocket.getOutputStream());
            System.out.println("Serveur avec  Client ");

            if ((inputReq = is.readLine()) != null) {
                System.out.println(" Msg 2 Recu " + inputReq);
                String[] chaines = inputReq.split(" ");
                System.out.println(" Ordre Recu " + chaines[0]);
                if (chaines[0].contentEquals("red")) {
                    int columnNumber = Integer.parseInt(chaines[1]);

                    monServeur.getGrid().play_checker(columnNumber+1, "red");

                    //os.println(valeurExpediee);

                    System.out.println(monServeur);
                }
                if (chaines[0].contentEquals("yellow")) {
                    int columnNumber = Integer.parseInt(chaines[1]);

                    monServeur.getGrid().play_checker(columnNumber+1, "yellow");

                    //os.println(valeurExpediee);

                    System.out.println(monServeur);
                }
                if (chaines[0].contentEquals("GiveMeAColor")) {
                    String colorAssigned;
                    colorAssigned = monServeur.getGrid().assignColor();


                    // send it to the client
                    os.println(colorAssigned);


                    System.out.println(monServeur);
                }
                if (chaines[0].contentEquals("MyTurnToPlay?")) {
                    String currentTurn;
                    currentTurn = monServeur.getGrid().playerTurn();

                    // send it to the client
                    os.println(currentTurn);
                    System.out.println("currentTurn: " + currentTurn);

                    System.out.println(monServeur);
                }
                if (chaines[0].contentEquals("GiveMeTheGrid")) {
                    System.out.println("Server received grid demand");
                    Grid grid;
                    grid = (Grid) monServeur.getGrid();

                    // send it to the client
                    objectOutputStream.writeObject(grid);

                    System.out.println(monServeur);
                }
            }
            clientSocket.close();
            objectOutputStream.close();
            os.close();
            is.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

