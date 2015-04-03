package it.univaq.f4i.iw.ex.dic.data;

public class Lingue {

    private int key;
    private String nome;
    private int livelloParlato;
    private int livelloScritto;

    //costruttore nuova lingua
    public Lingue(int key, String nome, int livelloParlato, int livelloScritto) {
        this.key = key;
        this.nome = nome;
        setLivelloParlato(livelloParlato);
        setLivelloScritto(livelloScritto);
        return;
    }

    //fun get
    public int getKey() {
        return this.key;
    }

    public String getNome() {
        return this.nome;
    }

    public int getLivelloParlato() {
        return this.livelloParlato;
    }

    public int getLivelloScritto() {
        return this.livelloScritto;
    }

//funzioni set
    public void setKey(int key) {
        this.key = key;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLivelloParlato(int liv) {
        if (liv < 1) {
            liv = 1;
        }
        if (liv > 5) {
            liv = 5;
        }
        this.livelloParlato = liv;
    }

    public void setLivelloScritto(int liv) {
        if (liv < 1) {
            liv = 1;
        }
        if (liv > 5) {
            liv = 5;
        }
        this.livelloScritto = liv;
    }
}
