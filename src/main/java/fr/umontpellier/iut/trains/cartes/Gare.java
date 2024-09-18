package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;
import fr.umontpellier.iut.trains.plateau.Tuile;
import fr.umontpellier.iut.trains.plateau.TuileMer;
import fr.umontpellier.iut.trains.plateau.TuileVille;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Gare extends CarteBleu {
    public Gare() {
        super("Gare", 0, 3, "violette");
    }


    public void jouer(Joueur joueur) {
        List<String> choixPossibles = new ArrayList<>();
        for (int i = 0; i<joueur.getJeu().getTuiles().size(); i++){
            if (Objects.equals(joueur.getJeu().getTuile(i).getNom(), "ville")){
                if (joueur.getJeu().getTuile(i).ajoutGarePossible()){
                    choixPossibles.add("TUILE:"+i);
                }
            }
        }

        String entrer = joueur.choisir("Entrer le num d'une tuile", choixPossibles, null, false);

        String[] tab = entrer.split(":");
        joueur.getJeu().getTuile(Integer.parseInt(tab[1])).ajouterGare();
        joueur.ajouterFerraille();
//        if (tuile.estVide()) {
//            tuile.ajouterGare();
//            joueur.ajouterFerraille();
//        } else {
//            System.out.println("La tuile sélectionnée n'est pas valide pour construire une gare.");
//        }

    }
}