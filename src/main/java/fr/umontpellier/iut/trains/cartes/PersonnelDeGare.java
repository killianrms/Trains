package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PersonnelDeGare extends CarteRouge {
    public PersonnelDeGare() {
        super("Personnel de gare", 0, 2, "rouge");
    }

    @Override
    public void jouer(Joueur joueur) {
        List<String> choixPossibles = new ArrayList<>();
        choixPossibles.add("piocher");
        choixPossibles.add("ferraille");
        choixPossibles.add("argent");

        List<Bouton> boutons = new ArrayList<>();
        for (String tmp : choixPossibles){
            boutons.add(new Bouton(tmp, tmp));
        }

        String entrer = joueur.choisir("Entrez le nom d'une carte Train de votre main", choixPossibles, boutons, false);

        if (Objects.equals(entrer, "piocher")){
            joueur.getMain().add(joueur.piocher());
        } else if (Objects.equals(entrer, "ferraille")) {
            ListeDeCartes LCarte = new ListeDeCartes();
            for(Carte tmp : joueur.getJeu().getReserve().get("Ferraille")){
                LCarte.add(tmp);
            }
            LCarte.add(joueur.getMain().retirer("Ferraille"));

            joueur.getJeu().getReserve().put("Ferraille",LCarte);
        } else if (Objects.equals(entrer, "argent")){
            joueur.addArgent(1);
        }
    }
}
