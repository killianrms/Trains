package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.Objects;

public class Decharge extends CarteRouge {
    public Decharge() {
        super("Décharge", 0, 2, "rouge");
    }

    @Override
    public void jouer(Joueur joueur) {
        ListeDeCartes LCarte = new ListeDeCartes();
        int count =0;
        for (Carte tmp : joueur.getMain()){
            if (Objects.equals(tmp.getNom(), "Ferraille")){
                count ++;
            }
        }

        for(Carte tmp : joueur.getJeu().getReserve().get("Ferraille")){
            LCarte.add(tmp);
        }
        for (int i = 0; i < count; i++) {
            LCarte.add(joueur.getMain().retirer("Ferraille"));
        }
        joueur.getJeu().getReserve().put("Ferraille",LCarte);
    }
}
