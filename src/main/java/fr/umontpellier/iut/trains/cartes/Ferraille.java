package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class Ferraille extends Carte {
    public Ferraille() {
        super("Ferraille", 0, 0, "gris");
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.addArgent(getValeur());
    }
}
