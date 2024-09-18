package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class Ferronnerie extends CarteVerte {
    public Ferronnerie() {
        super("Ferronnerie", 1, 4, "rouge");
    }

    @Override
    public void jouer(Joueur joueur){
        joueur.addEffetFerronerie();
        joueur.addArgent(getValeur());
    }
}
