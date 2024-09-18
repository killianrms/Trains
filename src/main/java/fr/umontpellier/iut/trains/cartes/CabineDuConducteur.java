package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.List;
import java.util.Objects;

public class CabineDuConducteur extends CarteRouge {
    public CabineDuConducteur() {
        super("Cabine du conducteur", 0, 2, "rouge");
    }

    @Override
    public void jouer(Joueur joueur) {

        String entrer;
        int count = 0;
        do {
            List<String> choixPossibles = listeChoixCarteMain(joueur);
            entrer = joueur.choisir("Entrez le nom d'une carte de votre main", choixPossibles, null, true);
            for (Carte tmp : joueur.getMain()){
                if (Objects.equals(tmp.getNom(), entrer)){
                    joueur.getDefausse().add(joueur.getMain().retirer(entrer));
                    count++;
                    break;
                }
            }


        } while (!Objects.equals(entrer, ""));

        joueur.addCarteMain(joueur.piocher(count));

    }
}
