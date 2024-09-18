package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Carte {
    private final String nom;
    private int valeur;
    private int cout;
    private String type;

    /**
     * Constructeur simple
     * <p>
     * Important : La classe Carte est actuellement très incomplète. Vous devrez
     * ajouter des attributs et des méthodes et donc probablement définir au moins
     * un autre constructeur plus complet. Les sous-classes de Cartes qui vous sont
     * fournies font appel à ce constructeur simple mais au fur et à mesure que vous
     * les compléterez, elles devront utiliser les autres constructeurs de Carte. Si
     * vous n'utilisez plus ce constructeur, vous pouvez le supprimer.
     * 
     * @param nom
     */
    public Carte(String nom) {
        this.nom = nom;
    }

    public Carte(String nom, int valeur, int cout, String type) {
        this.nom = nom;
        this.cout = cout;
        this.valeur = valeur;
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public int getValeur() {
        return valeur;
    }

    public String getType() {
        return type;
    }

    public int getCout() {
        return cout;
    }

    /**
     * Cette fonction est exécutée lorsqu'un joueur joue la carte pendant son tour.
     * Toutes les cartes ont une méthode jouer, mais elle ne fait rien par défaut.
     * 
     * @param joueur le joueur qui joue la carte
     */
    public void jouer(Joueur joueur) {

    }

    public List<String> listeChoixCarteMain(Joueur joueur){
        List<String> choixPossibles = new ArrayList<>();
        for (Carte c : joueur.getMain()) {
            // ajoute les noms de toutes les cartes en main
            choixPossibles.add(c.getNom());
        }
        return choixPossibles;
    }

    public List<String> listeChoixCarteMain(Joueur joueur, String type){
        List<String> choixPossibles = new ArrayList<>();
        for (Carte c : joueur.getMain()) {
            if (Objects.equals(type, c.getType())){
                choixPossibles.add(c.getNom());
            }
        }
        return choixPossibles;
    }


    @Override
    public String toString() {
        return nom;
    }

    public int getPointVictoire() {
        return 0;
    }
}
