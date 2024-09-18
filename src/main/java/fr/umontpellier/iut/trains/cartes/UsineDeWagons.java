package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UsineDeWagons extends CarteRouge {
    public UsineDeWagons() {
        super("Usine de wagons", 0, 5, "rouge");
    }

    @Override
    public void jouer(Joueur joueur) {
        List<String> choixPossibles = new ArrayList<>();
        for (Carte tmp : joueur.getMain()){
            if (Objects.equals("bleu", tmp.getType()) || Objects.equals("dbtype", tmp.getType())){
                choixPossibles.add(tmp.getNom());
            }
        }

        String entrer = joueur.choisir("Entrez le nom d'une carte Ferraille de votre main", choixPossibles, null, true);
        Carte c = joueur.getMain().retirer(entrer);
        joueur.getJeu().getCartesEcartees().add(c);

        choixPossibles = new ArrayList<>();

        for (String nomCarte : joueur.getJeu().getReserve().keySet()) {
            // ajoute les noms des cartes dans la réserve préfixés de "ACHAT:"
            choixPossibles.add("ACHAT:" + nomCarte);
        }
        entrer = joueur.choisir("Entrez le nom d'une carte Ferraille de votre main", choixPossibles, null, true);

        String nomCarte = entrer.split(":")[1];
        Carte carte = joueur.getJeu().prendreDansLaReserve(nomCarte);

        if (carte.getCout() <= c.getCout() + 3){
            joueur.getMain().add(carte);
        }


    }
}
