package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class PontEnAcier extends CarteVerte {
    public PontEnAcier() {
        super("Pont en acier", 0, 4, "verte");
    }

    @Override
    public void jouer(Joueur joueur) {
        contreCoup(joueur);
        joueur.setSurcoutRiviere(false);
    }
}
