package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ParcDAttractions extends CarteRouge {
    public ParcDAttractions() {
        super("Parc d'attractions", 1, 4, "rouge");
    }

    @Override
    public void jouer(Joueur joueur) {
        List<String> choixPossibles = new ArrayList<>();
        for (Carte tmp: joueur.getCartesEnJeu()){
            if (Objects.equals("bleu", tmp.getType()) || Objects.equals("dbtype", tmp.getType())){
                choixPossibles.add(tmp.getNom());
            }
        }

        String entrer = joueur.choisir("Entrez le nom d'une carte Train du jeu", choixPossibles, null, true);

        for (Carte tmp : joueur.getCartesEnJeu()){
            if (Objects.equals(entrer, tmp.getNom())){
                joueur.addArgent(tmp.getValeur());
                break;
            }
        }
        joueur.addArgent(getValeur());

    }
}
