package game;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;

/**
 * Represents the management of the connection between client and server. This is based on a {@link Socket} and uses a {@link Thread}
 *
 */
class SpecificServer extends Thread {

    private final Socket clientSocket;
    private final ServerTCP myServer;

    public SpecificServer(Socket aSocket, ServerTCP aServer) {
        super("ServerThread");

        clientSocket = aSocket;
        myServer = aServer;
    }

    @Override
    public void run() {
        String inputReq;

        try {
            // create an object output stream, so we can send an object through it
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());

            BufferedReader bufferReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintStream outputStream = new PrintStream(clientSocket.getOutputStream());

            if ((inputReq = bufferReader.readLine()) != null) {
                String[] messages = inputReq.split(" ");
                System.out.println("Message received: " + Arrays.toString(messages));
                if (messages[0].contentEquals("red")) {
                    int columnNumber = Integer.parseInt(messages[1]);

                    myServer.getGrid().play_checker(columnNumber+1, "red");
                }
                if (messages[0].contentEquals("yellow")) {
                    int columnNumber = Integer.parseInt(messages[1]);

                    myServer.getGrid().play_checker(columnNumber+1, "yellow");
                }
                if (messages[0].contentEquals("MyTurnToPlay?")) {
                    String currentTurn = myServer.getGrid().playerTurn();

                    // send it to the client
                    outputStream.println(currentTurn);
                    System.out.println("Current turn: " + currentTurn);
                }
                if (messages[0].contentEquals("GiveMeTheGrid")) {
                    Grid grid = myServer.getGrid();

                    // send it to the client
                    objectOutputStream.writeObject(grid);
                    grid.display_grid();
                }
            }
            clientSocket.close();
            objectOutputStream.close();
            outputStream.close();
            bufferReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

