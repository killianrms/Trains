package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.Objects;

public class TGV extends CarteBleu {
    public TGV() {
        super("TGV", 1, 2,"dbtype");
    }

    @Override
    public void jouer(Joueur joueur) {
        for (Carte tmp : joueur.getCartesEnJeu()){
            if (Objects.equals(tmp.getNom(), "Train omnibus")){
                joueur.addArgent(1);
                break;
            }
        }
        joueur.addArgent(getValeur());
    }
}
