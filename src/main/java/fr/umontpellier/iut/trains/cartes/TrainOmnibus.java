package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class TrainOmnibus extends CarteBleu {
    public TrainOmnibus() {
        super("Train omnibus", 1, 1, "bleu");
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.addArgent(getValeur());
    }
}

