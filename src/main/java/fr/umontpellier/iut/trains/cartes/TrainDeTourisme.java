package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class TrainDeTourisme extends CarteBleu {
    public TrainDeTourisme() {
        super("Train de tourisme", 1, 4, "dbtype");
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.addArgent(getValeur());
        joueur.addPv(1);
    }
}
