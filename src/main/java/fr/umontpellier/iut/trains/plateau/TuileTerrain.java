package fr.umontpellier.iut.trains.plateau;

/**
 * Classe représentant une tuile plaine, fleuve ou montagne.
 */
public class TuileTerrain extends Tuile {
    /**
     * Type de terrain de la tuile ({@code PLAINE}, {@code FLEUVE} ou {@code MONTAGNE})
     */
    private TypeTerrain type;

    public TuileTerrain(TypeTerrain type) {
        super("terrain");
        this.type = type;
    }
    public TypeTerrain getType() {
        return type;
    }

    @Override
    public int getSurcout() {
        return type.getSurcout();
    }

    @Override
    public String getNom() {
        return type.getNom();
    }
}
