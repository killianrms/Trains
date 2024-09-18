package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class SalleDeControle extends CarteRouge {
    public SalleDeControle() {
        super("Salle de contrôle", 0, 7, "rouge");
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.addCarteMain(joueur.piocher(3));
    }
}
