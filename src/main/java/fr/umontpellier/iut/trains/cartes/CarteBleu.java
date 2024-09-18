package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public abstract class CarteBleu extends Carte{
    public CarteBleu(String nom, int valeur, int cout, String type) {
        super(nom, valeur, cout, type);
    }
    @Override
    public void jouer(Joueur joueur) {
        joueur.addArgent(getValeur());
    }
}
