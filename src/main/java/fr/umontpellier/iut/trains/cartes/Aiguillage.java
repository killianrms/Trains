package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;

public class Aiguillage extends CarteRouge {
    public Aiguillage() {
        super("Aiguillage", 0, 5, "rouge");
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.addCarteMain(joueur.piocher(2));

    }

}
