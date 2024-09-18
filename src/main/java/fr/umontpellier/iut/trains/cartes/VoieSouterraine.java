package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class VoieSouterraine extends CarteVerte {
    public VoieSouterraine() {
        super("Voie souterraine", 0, 7, "verte");
    }

    @Override
    public void jouer(Joueur joueur) {
        contreCoup(joueur);
        joueur.setSurcoutGeneral(false);
    }
}
