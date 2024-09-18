package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class PassageEnGare extends CarteRouge {
    public PassageEnGare() {
        super("Passage en gare", 1, 3, "rouge");
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.addArgent(getValeur());
        Carte c = joueur.piocher();
        if (c != null)
            joueur.getMain().add(c);
    }
}
