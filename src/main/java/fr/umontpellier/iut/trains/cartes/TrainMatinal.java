package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TrainMatinal extends CarteBleu {
    public TrainMatinal() {
        super("Train matinal", 2, 5, "dbtype");
    }

    @Override
    public void jouer(Joueur joueur) {

        joueur.addArgent(getValeur());
        joueur.setAddDessus(true);


    }
}
