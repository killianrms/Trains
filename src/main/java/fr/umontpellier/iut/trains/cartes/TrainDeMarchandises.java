package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TrainDeMarchandises extends CarteBleu {
    public TrainDeMarchandises() {
        super("Train de marchandises", 1, 4, "dbtype");
    }

    @Override
    public void jouer(Joueur joueur) {
        List<String> choixPossibles = new ArrayList<>();

        for (Carte carte : joueur.getMain()){
            if (Objects.equals(carte.getNom(), "Ferraille")){
                choixPossibles.add(carte.getNom());
            }
        }

        ListeDeCartes LCarte = new ListeDeCartes();
        String entrer;
        int nb = 0;
        do {
            entrer = joueur.choisir("Entrez une carte de carte Ferraille de votre main", choixPossibles, null, true);
            if (Objects.equals(entrer, "Ferraille")){
                nb++;
                choixPossibles.remove("Ferraille");
            }

        } while (!Objects.equals(entrer, ""));

        for(Carte tmp : joueur.getJeu().getReserve().get("Ferraille")){
            LCarte.add(tmp);
        }

        for (int i = 0; i < nb; i++) {
            joueur.addArgent(1);
            LCarte.add(joueur.getMain().retirer("Ferraille"));
        }
        joueur.getJeu().getReserve().put("Ferraille",LCarte);

        joueur.addArgent(getValeur());
    }
}
