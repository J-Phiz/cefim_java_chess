package fr.leonie.jp.chess.enumeration;

public enum CouleurCarreau {
    BLANC("WHITE"), ROSE("PINK");

    String colorValue;
    CouleurCarreau(String colorValue) {
        this.colorValue = colorValue;
    }

    public String getColorValue() {
        return colorValue;
    }
}
