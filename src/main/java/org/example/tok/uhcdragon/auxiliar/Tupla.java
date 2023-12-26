package org.example.tok.uhcdragon.auxiliar;

public class Tupla {
    private String idJugador;
    private int cantVidas;

    public Tupla(String idJugador, int cantVidas) {
        this.idJugador = idJugador;
        this.cantVidas = cantVidas;
    }

    public String getIdJugador() {
        return idJugador;
    }

    public int getCantVidas() {
        return cantVidas;
    }

    public void setCantVidas(int nuevaCantidadVidas) {
        this.cantVidas = nuevaCantidadVidas;
    }
}
