package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.Objects;

public class HorairesTemporaires extends CarteRouge {
    public HorairesTemporaires() {
        super("Horaires temporaires", 0, 5, "rouge");
    }

    @Override
    public void jouer(Joueur joueur) {
        ListeDeCartes stock = new ListeDeCartes();
        int nb = 0;
        int nbtot = 0;
        nbtot += joueur.getPioche().size() + joueur.getDefausse().size();
        int i = 0;
        ListeDeCartes carteGarder = new ListeDeCartes();
        Carte tmp;
        while (nb < 2 && i < nbtot){
            tmp = joueur.piocher();
            if (Objects.equals("bleu", tmp.getType()) || Objects.equals("dbtype", tmp.getType())){
                carteGarder.add(tmp);
                nb++;
            } else {
                stock.add(tmp);
            }
            i++;
        }

        joueur.getDefausse().addAll(stock);
        joueur.addCarteMain(carteGarder);
    }
}
