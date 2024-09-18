package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Remorquage extends CarteRouge {
    public Remorquage() {
        super("Remorquage", 0 ,3, "rouge");
    }

    @Override
    public void jouer(Joueur joueur) {
        boolean passer = false;
        List<String> choixPossibles = new ArrayList<>();
        for (Carte tmp : joueur.getDefausse()){
            if (Objects.equals("bleu", tmp.getType()) || Objects.equals("dbtype", tmp.getType())){
                choixPossibles.add(tmp.getNom());
            }
        }
        if (choixPossibles.isEmpty()){
            passer = true;
        }

        List<Bouton> boutons = new ArrayList<>();
        for (String tmp : choixPossibles){
            boutons.add(new Bouton(tmp, tmp));
        }


        String entrer = joueur.choisir("Entrez le nom d'une carte Train de votre main", choixPossibles, boutons, passer);

        for (Carte tmp : joueur.getDefausse()){
            if (Objects.equals(entrer, tmp.getNom())){
                joueur.getMain().add(joueur.getDefausse().retirer(entrer));
                break;
            }
        }
    }
}
