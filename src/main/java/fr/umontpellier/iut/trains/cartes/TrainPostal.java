package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TrainPostal extends CarteBleu {
    public TrainPostal() {
        super("Train postal", 1, 4, "dbtype");
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.addArgent(getValeur());
        String entrer;
        do {
            List<String> choixPossibles = listeChoixCarteMain(joueur);
            entrer = joueur.choisir("Entrez le nom d'une carte Ferraille de votre main", choixPossibles, null, true);
            for (Carte tmp : joueur.getMain()){
                if (Objects.equals(tmp.getNom(), entrer)){
                    joueur.getDefausse().add(joueur.getMain().retirer(entrer));
                    joueur.addArgent(1);
                    break;
                }
            }

        } while (!Objects.equals(entrer, ""));
    }
}
