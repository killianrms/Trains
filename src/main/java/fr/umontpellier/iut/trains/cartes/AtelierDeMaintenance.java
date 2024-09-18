package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class AtelierDeMaintenance extends CarteRouge {
    public AtelierDeMaintenance() {
        super("Atelier de maintenance", 0, 5, "rouge");
    }

    @Override
    public void jouer(Joueur joueur) {
        boolean necontientTrain = true;
        for (Carte tmp : joueur.getMain()){
            if (Objects.equals(tmp.getType(), "bleu") || Objects.equals(tmp.getType(), "dbtype")){
                necontientTrain = false;
            }
        }
        List<String> choixPossibles = listeChoixCarteMain(joueur, "bleu");
        choixPossibles.addAll(listeChoixCarteMain(joueur, "dbtype"));
        String entrer = joueur.choisir("Entrez le nom d'une carte Train de votre main", choixPossibles, null, necontientTrain);
        for (Carte tmp : joueur.getMain()){
            if (Objects.equals(tmp.getNom(), entrer)){
                Carte c = joueur.getJeu().prendreDansLaReserve(entrer);
                if (c!= null )
                    joueur.getCartesRecues().add(c);
                break;
            }
        }
    }
}
