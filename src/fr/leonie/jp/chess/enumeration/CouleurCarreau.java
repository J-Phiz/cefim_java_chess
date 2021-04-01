package fr.leonie.jp.chess.enumeration;

public enum CouleurCarreau {
    BLANC("#FFFFFF"), ROSE("#F0BBD1");

    String colorValue;
    CouleurCarreau(String colorValue) {
        this.colorValue = colorValue;
    }

    public String getColorValue() {
        return colorValue;
    }
}
