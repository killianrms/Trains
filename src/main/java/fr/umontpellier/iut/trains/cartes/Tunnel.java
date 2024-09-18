package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class Tunnel extends CarteVerte {
    public Tunnel() {
        super("Tunnel", 0, 5, "verte");
    }

    @Override
    public void jouer(Joueur joueur) {
        contreCoup(joueur);
        joueur.setSurcoutMontagne(false);
    }
}
