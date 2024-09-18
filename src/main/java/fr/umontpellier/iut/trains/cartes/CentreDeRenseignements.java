package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CentreDeRenseignements extends CarteRouge {
    public CentreDeRenseignements() {
        super("Centre de renseignements", 1, 4, "rouge");
    }

    @Override
    public void jouer(Joueur joueur) {
        String entrer;
        boolean passer = true;
        int nb = 4;
        ListeDeCartes CartesTmp = new ListeDeCartes();
        List<String> choixPossibles = new ArrayList<>();
        if (joueur.getPioche().size() >= 4) {
            for (int i = 0; i < 4; i++) {
                CartesTmp.add(joueur.getPioche().get(i));
                choixPossibles.add(CartesTmp.get(i).getNom());
            }
        } else {
            for (int i = 0; i < joueur.getPioche().size(); i++) {
                CartesTmp.add(joueur.getPioche().get(i));
                choixPossibles.add(CartesTmp.get(i).getNom());
            }
            nb = joueur.getPioche().size();
        }


        List<Bouton> boutons = new ArrayList<>();
        for (String tmp : choixPossibles){
            boutons.add(new Bouton(tmp, tmp));
        }

        ListeDeCartes listeDeCartes = new ListeDeCartes();

        for (int i = 0; i < nb; i++) {
            entrer = joueur.choisir("Entrez le nom d'une carte", choixPossibles, boutons, passer);
            for (Carte tmp : CartesTmp) {
                if (Objects.equals(entrer, tmp.getNom()) && i == 0){
                    joueur.getMain().add(joueur.getPioche().retirer(entrer));
                    break;
                } else if (Objects.equals(entrer, tmp.getNom())) {
                    listeDeCartes.add(0, joueur.getPioche().retirer(entrer));
                    break;
                } else if (Objects.equals(entrer, "") && i == 0) {
                    nb++;
                    break;
                }
            }
            if (!Objects.equals(entrer, "")){
                choixPossibles.remove(entrer);
                boutons.remove(new Bouton(entrer, entrer));
            }
            passer = false;
        }
        int nt = listeDeCartes.size();

        for (int i =0 ; i<nt; i++){
            joueur.getPioche().add(0,listeDeCartes.get(listeDeCartes.size()-1));
            listeDeCartes.remove(listeDeCartes.size()-1);
        }

        joueur.addArgent(getValeur());
    }
}
