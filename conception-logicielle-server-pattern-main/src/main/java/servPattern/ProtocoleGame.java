package servPattern;

import java.io.*;

public class ProtocoleGame implements IProtocole{

    public void execute(IContext c , InputStream unInput , OutputStream unOutput ) {
        Grid grid = (Grid) c;
        String inputReq;

        try {
            // create an object output stream from the output stream so we can send an object through it
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(unOutput);

            BufferedReader is = new BufferedReader(new InputStreamReader(unInput));
            PrintStream os = new PrintStream(unOutput);
            System.out.println("Serveur avec  Client ");

            if ((inputReq = is.readLine()) != null) {
                System.out.println(" Msg 2 Recu " + inputReq);
                String[] chaines = inputReq.split(" ");
                System.out.println(" Ordre Recu " + chaines[0]);
                if (chaines[0].contentEquals("red")) {
                    int columnNumber = Integer.parseInt(chaines[1]);

                    grid.demandePlay(columnNumber+1, "red");

                    //os.println(valeurExpediee);

                    //System.out.println(monServeur);
                }
                if (chaines[0].contentEquals("yellow")) {
                    int columnNumber = Integer.parseInt(chaines[1]);

                    grid.demandePlay(columnNumber+1, "yellow");
                    //os.println(valeurExpediee);

                    //System.out.println(monServeur);
                }
                if (chaines[0].contentEquals("GiveMeAColor")) {
                    String colorAssigned;
                    colorAssigned = grid.demandeColor();


                    // send it to the client
                    os.println(colorAssigned);


                    //System.out.println(monServeur);
                }
                if (chaines[0].contentEquals("MyTurnToPlay?")) {
                    String currentTurn;
                    currentTurn = grid.demandeTurn();

                    // send it to the client
                    os.println(currentTurn);
                    System.out.println("currentTurn: " + currentTurn);

                    //System.out.println(monServeur);
                }
                if (chaines[0].contentEquals("GiveMeTheGrid")) {
                    System.out.println("Server received grid demand");
//                    Grid grid;
//                    grid = (Grid) monServeur.getGrid();

                    // send it to the client
                    objectOutputStream.writeObject(grid);

                    //System.out.println(monServeur);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

