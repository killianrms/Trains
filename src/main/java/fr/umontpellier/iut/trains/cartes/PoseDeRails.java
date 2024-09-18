package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class PoseDeRails extends CarteVerte {
    public PoseDeRails() {
        super("Pose de rails", 0, 3, "verte");
    }

    @Override
    public void jouer(Joueur joueur) {
        contreCoup(joueur);
    }
}
