package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CentreDeControle extends CarteRouge {
    public CentreDeControle() {
        super("Centre de contrôle", 0, 3, "rouge");
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.getMain().add(joueur.piocher());
        List<String> choixPossibles = new ArrayList<>();
        for (Carte c : joueur.getMain()) {
            // ajoute les noms de toutes les cartes en main
            if (!choixPossibles.contains(c)){
                choixPossibles.add(c.getNom());
            }
        }

        for (Carte c : joueur.getPioche()) {
            if (!choixPossibles.contains(c)){
                choixPossibles.add(c.getNom());
            }
        }

        for (Carte c : joueur.getDefausse()) {
            if (!choixPossibles.contains(c)){
                choixPossibles.add(c.getNom());
            }
        }

        for (Carte c : joueur.getCartesEnJeu()) {
            if (!choixPossibles.contains(c)){
                choixPossibles.add(c.getNom());
            }
        }

        List<Bouton> boutons = new ArrayList<>();
        for (String tmp : choixPossibles){
            boutons.add(new Bouton(tmp, tmp));
        }

        String entrer = joueur.choisir("Entrez le nom d'une carte", choixPossibles, boutons, true);
        joueur.log("Carte choisi : " + entrer);
        if (Objects.equals(entrer, joueur.getPioche().get(0).getNom())){
            joueur.getMain().add(joueur.piocher());
        }
    }
}
