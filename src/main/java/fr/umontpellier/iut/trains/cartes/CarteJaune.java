package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public abstract class CarteJaune extends Carte{

    private int pointVictoire;
    public CarteJaune(String nom, int pointVictoire, int valeur, int cout, String type) {
        super(nom, valeur, cout, type);
        this.pointVictoire = pointVictoire;
    }

    public int getPointVictoire() {
        return pointVictoire;
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.addArgent(getValeur());
    }
}
