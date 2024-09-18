package fr.umontpellier.iut.trains.plateau;

/**
 * Classe représentant une tuile ville (où l'on peut poser des gares)
 */
public class TuileVille extends Tuile {
    /**
     * Nombre maximum de gares que l'on peut poser sur la tuile
     */
    private int nbGaresMax;
    /**
     * Nombre de gares posées sur la tuile
     */
    private int nbGaresPosees;

    public TuileVille(int taille) {
        super("ville");
        this.nbGaresMax = taille;
        this.nbGaresPosees = 0;
    }


    public void ajouterGare() {
        if (nbGaresPosees < nbGaresMax) {
            nbGaresPosees++;
        }
    }

    public boolean ajoutGarePossible(){
        return nbGaresPosees+1<= nbGaresMax;
    }

    @Override
    public int getNbGares() {
        return nbGaresPosees;
    }

    @Override
    public int getSurcout() {
        return 1+nbGaresPosees;
    }
}
