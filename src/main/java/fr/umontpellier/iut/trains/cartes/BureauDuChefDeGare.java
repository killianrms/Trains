package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.List;
import java.util.Objects;

public class BureauDuChefDeGare extends CarteRouge {
    public BureauDuChefDeGare() {
        super("Bureau du chef de gare", 0, 4, "rouge");
    }

    @Override
    public void jouer(Joueur joueur) {
        boolean necontientAction = true;
        int nbRouge = 0;

        for (Carte tmp : joueur.getMain()){
            if (Objects.equals(tmp.getType(), "rouge") || Objects.equals(tmp.getType(), "dbtype")){
                necontientAction = false;
                nbRouge++;
                if (Objects.equals(getNom(), tmp.getNom()) && nbRouge == 2){
                    necontientAction = true;
                }
            }
        }


        List<String> choixPossibles = listeChoixCarteMain(joueur, "rouge");
        choixPossibles.addAll(listeChoixCarteMain(joueur, "dbtype"));
        String entrer = joueur.choisir("Entrez le nom d'une carte Action de votre main", choixPossibles, null, necontientAction);
        for (Carte tmp : joueur.getMain()){
            if (Objects.equals(tmp.getNom(), entrer) && (Objects.equals("rouge", tmp.getType()) || Objects.equals("dbtype", tmp.getType()))){
                tmp.jouer(joueur);
                joueur.setArgent(joueur.getArgent()- tmp.getValeur());
                break;
            }
        }
    }
}
