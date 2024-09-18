package fr.umontpellier.iut.trains.plateau;

/**
 * Enumération des différents types de terrain des tuiles du plateau
 */
public enum TypeTerrain {
    PLAINE("plaine",0 ), MONTAGNE("montagne", 2), FLEUVE("fleuve", 1);

    private String nom;
    private int surcout;
    TypeTerrain(String nom, int surcout) {
        this.nom = nom;
        this.surcout = surcout;
    }

    public int getSurcout() {
        return surcout;
    }

    public String getNom() {
        return nom;
    }
}
