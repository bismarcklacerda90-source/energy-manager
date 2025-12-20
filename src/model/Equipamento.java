package model;

public class Equipamento {
    private String nome;
    private int consumo;
    private boolean ligado;

    public Equipamento(String nome, int consumo) {
        this.nome = nome;
        this.consumo = consumo;
        this.ligado = false;
    }

    public void ligar() {
        ligado = true;
    }

    public void desligar() {
        ligado = false;
    }

    public int getConsumo() {
        return ligado ? consumo : 0;
    }

    public String getNome() {
        return nome;
    }

    public boolean isLigado() {
        return ligado;
    }
}
