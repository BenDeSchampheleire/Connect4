package application;

import game.Grid;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Représente un client TCP : cette classe s'appuie principalement sur un objet
 * {@link Socket}, et s'initialise par un nom de serveur et un numéro de port
 */
public class ClientTCP {

    private final int numeroPort; // ## attribute numeroPort
    private final String nomServeur; // ## attribute nomServeur
    private BufferedReader socIn; // ## link socIn
    private PrintStream socOut; // ## link socOut
    private Socket socketServeur; // ## link socketServeur
    private InputStream inputStream; //
    private ObjectInputStream objectInputStream;

    /**
     * Création d'un nouveau {@link ClientTCP} avec un nom de serveur et un numéro
     * de port
     *
     * @param unNomServeur
     * @param unNumero
     */
    public ClientTCP(String unNomServeur, int unNumero) {
        numeroPort = unNumero;
        nomServeur = unNomServeur;
    }



    /**
     * Exécute la connexion au serveur, et crée la {@link Socket}
     *
     * Si une exception se produit, elle est traité en interne, et la méthode
     * renvoit false
     *
     * @return true si la connexion s'est bien déroulée
     */
    public boolean connexionServeur() {
        boolean ok = false;
        try {
            System.out.println("Tentative : " + nomServeur + " -- " + numeroPort);
            socketServeur = new Socket(nomServeur, numeroPort);
            socOut = new PrintStream(socketServeur.getOutputStream());
            socIn = new BufferedReader(new InputStreamReader(socketServeur.getInputStream()));
            inputStream = socketServeur.getInputStream();
            objectInputStream = new ObjectInputStream(inputStream);
            System.out.println("Connection faite");
            ok = true;
        }
        catch (UnknownHostException e) {
            System.err.println("Serveur inconnu : " + e);
        }
        catch (ConnectException e) {
            System.err.println("Serveur off : " + e);
            System.out.println("Il faut lancer le serveur");
        }
        catch (Exception e) {
            System.err.println("Exception:  " + e);
            }

        return ok;
    }

    /**
     * Commande la déconnexion au serveur
     */
    public void deconnexionServeur() {
        try {
            socOut.close();
            socIn.close();
            inputStream.close();
            objectInputStream.close();
            socketServeur.close();
        } catch (Exception e) {
            System.err.println("Exception:  " + e);
        }
    }

    /**
     * Transmet une chaine de caractères sur la Socket, et retourne la réponse sous
     * la forme d'une chaine.
     *
     * Cette méthode nécessite que la connexion soit effective
     *
     * @param uneChaine
     * @return
     */
    public String transmettreChaine(String uneChaine) {
        String msgServeur = null;
        try {

            System.out.println("Client " + uneChaine);

            socOut.println(uneChaine);
            socOut.flush();

            msgServeur = socIn.readLine();

            System.out.println("Client msgServeur " + msgServeur);
            return msgServeur;

        } catch (UnknownHostException e) {
            System.err.println("Serveur inconnu : " + e);
            return null;
        } catch (Exception e) {
            System.err.println("Exception:  " + e);
            return null;
        }
    }

    /**
     * Echaînement d'une connexion au serveur, de la transmission d'une chaine de
     * caractère, et de la déconnexion
     *
     * @param uneChaine
     * @return
     */
    public String connexionTransmettreChaine(String uneChaine) {
        String msgServeur = null;
        if (connexionServeur() == true) {
            try {

                System.out.println("Client " + uneChaine);
                socOut.println(uneChaine);
                socOut.flush();

                msgServeur = socIn.readLine();
                System.out.println("Client msgServeur " + msgServeur);
                deconnexionServeur();
            } catch (Exception e) {
                System.err.println("Exception:  " + e);
            }
        }

        return msgServeur;
    }

    /**
     * Transmet une chaine de caractères sur la Socket, et retourne la réponse sous
     * la forme d'une chaine.
     *
     * Cette méthode nécessite que la connexion soit effective
     *
     * @param uneChaine
     * @return la grille
     */
    public Grid transmettreChaineGrid(String uneChaine) {
        try {

            System.out.println("Client " + uneChaine);

            socOut.println(uneChaine);
            socOut.flush();

            Grid grid = (Grid) objectInputStream.readObject();

            System.out.println("Client msgServeur " + grid);
            return grid;

        } catch (UnknownHostException e) {
            System.err.println("Serveur inconnu : " + e);
            return null;
        } catch (Exception e) {
            System.err.println("Exception:  " + e);
            return null;
        }
    }

}

