package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class Depot extends CarteRouge {
    public Depot() {
        super("Dépôt", 1, 3, "rouge");
    }

    @Override
    public void jouer(Joueur joueur) {
        String entrer;
        joueur.addCarteMain(joueur.piocher(2));
        for (int i = 0; i < 2; i++) {
            List<String> choixPossibles = listeChoixCarteMain(joueur);
            entrer = joueur.choisir("Entrez le nom d'une carte", choixPossibles, null, false);
            joueur.getDefausse().add(joueur.getMain().retirer(entrer));
            choixPossibles.remove(entrer);
        }
        joueur.addArgent(getValeur());
    }
}
