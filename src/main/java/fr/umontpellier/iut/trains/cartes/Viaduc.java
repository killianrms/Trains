package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class Viaduc extends CarteVerte {
    public Viaduc() {
        super("Viaduc", 0, 5, "verte");
    }

    @Override
    public void jouer(Joueur joueur) {

        contreCoup(joueur);
        joueur.setSurcoutVille(false);
    }
}
