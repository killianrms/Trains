package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class Depotoir extends CarteRouge {
    public Depotoir() {
        super("Dépotoir", 1, 5, "rouge");
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.setPeutRecevoirFerraille(false);
        joueur.addArgent(getValeur());
    }
}
