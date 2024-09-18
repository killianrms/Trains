package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public abstract class CarteVerte extends Carte {
    public CarteVerte(String nom, int valeur, int cout, String type) {
        super(nom, valeur, cout, type);
    }

    public void contreCoup(Joueur joueur){
        joueur.addPointRail(1);
        if (joueur.isPeutRecevoirFerraille()){
            joueur.getCartesRecues().add(joueur.getJeu().prendreDansLaReserve("Ferraille"));
        }
    }
}
