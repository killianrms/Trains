package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FeuDeSignalisation extends CarteRouge {
    public FeuDeSignalisation() {
        super("Feu de signalisation",0, 2, "rouge");
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.getMain().add(joueur.piocher());

        if (!(joueur.getPioche().size() == 0 && joueur.getDefausse().size() == 0)){
            List<String> choixPossibles = new ArrayList<>();
            choixPossibles.add("oui");
            choixPossibles.add("non");

            List<Bouton> boutons = new ArrayList<>();
            for (String tmp : choixPossibles){
                boutons.add(new Bouton(tmp, tmp));
            }
            String entrer = joueur.choisir("Defaussez la carte : oui/non", choixPossibles, boutons, true);
            if (Objects.equals(entrer, "oui")){
                joueur.getDefausse().add(joueur.getPioche().remove(0));
            }
        }
    }
}
