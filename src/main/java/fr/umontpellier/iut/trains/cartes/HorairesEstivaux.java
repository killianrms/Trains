package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HorairesEstivaux extends CarteRouge {
    public HorairesEstivaux() {
        super("Horaires estivaux", 0, 3, "rouge");
    }

    @Override
    public void jouer(Joueur joueur) {
        List<String> choixPossibles = new ArrayList<>();
        choixPossibles.add("oui");
        choixPossibles.add("non");

        List<Bouton> boutons = new ArrayList<>();
        for (String tmp : choixPossibles){
            boutons.add(new Bouton(tmp, tmp));
        }

        String entrer = joueur.choisir("Voulez ecartez cette carte : oui/non", choixPossibles, boutons, false);
        if (Objects.equals(entrer, "oui")){
            joueur.getJeu().getCartesEcartees().add(joueur.getCartesEnJeu().retirer(this.getNom()));
            joueur.addArgent(3);
        }
    }
}
