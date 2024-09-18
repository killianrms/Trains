package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class Cooperation extends CarteVerte {
    public Cooperation() {
        super("Coopération", 0 ,5, "verte");
    }

    @Override
    public void jouer(Joueur joueur) {
        contreCoup(joueur);
        joueur.setSurcoutAutre(false);
        joueur.setPeutRecevoirFerraille(false);
    }
}
