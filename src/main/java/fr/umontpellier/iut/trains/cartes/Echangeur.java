package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Echangeur extends CarteRouge {
    public Echangeur() {
        super("Échangeur", 1, 3, "rouge");
    }

    @Override
    public void jouer(Joueur joueur) {
        List<String> choixPossibles = new ArrayList<>();
        for (Carte tmp : joueur.getCartesEnJeu()){
            if ((Objects.equals("bleu", tmp.getType()) || Objects.equals("dbtype", tmp.getType()))){
                choixPossibles.add(tmp.getNom());
            }
        }
        String entrer = joueur.choisir("Entrez le nom d'une carte", choixPossibles, null, true);
        for (Carte tmp : joueur.getCartesEnJeu()){
            if (Objects.equals(entrer, tmp.getNom()) ){
                joueur.getPioche().add(0, joueur.getCartesEnJeu().retirer(entrer));
            }
        }
        joueur.addArgent(getValeur());
    }
}
