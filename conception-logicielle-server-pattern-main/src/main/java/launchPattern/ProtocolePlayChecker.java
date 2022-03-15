package launchPattern;

import servPattern.IContext;
import servPattern.IProtocole;
import servPattern.Grid;
import java.io.*;

public class ProtocolePlayChecker implements IProtocole {

    public void execute( IContext c , InputStream unInput , OutputStream unOutput ) {

        Grid grid = (Grid) c;
        String inputReq;
        BufferedReader is = new BufferedReader(new InputStreamReader(
                unInput));
        PrintStream os = new PrintStream(unOutput);
        try {
            String valeurExpediee = "";
            if ((inputReq = is.readLine()) != null) {
                System.out.println(" Ordre Recu " + inputReq);
                String chaines[] = inputReq.split(" ");
                if (chaines[0].contentEquals("red")) {
                    int columnNumber = Integer.parseInt(chaines[1]);
                    grid.demandePlay(columnNumber, "red");
                    System.out.println(" Play red checker in column " + columnNumber);
//                    int valeurRetrait = b.demandeRetrait(valeur);
//                    valeurExpediee = String.valueOf(valeurRetrait);
//                    System.out.println(" Retrait dans serveur "	+ valeurExpediee);
//
//
//                    monServeur.getGrid().play_checker(columnNumber+1, "red");
//
//                    //os.println(valeurExpediee);
//
//                    System.out.println(monServeur);
                }
                if (chaines[0].contentEquals("yellow")) {
                    int columnNumber = Integer.parseInt(chaines[1]);
                    grid.demandePlay(columnNumber, "yellow");
                    System.out.println(" Play yellow checker in column " + columnNumber);
                }
                os.println(valeurExpediee);
            }
        } catch ( Exception e) {
            System.out.println(" Pb d'exception ");
        }
    }
}
