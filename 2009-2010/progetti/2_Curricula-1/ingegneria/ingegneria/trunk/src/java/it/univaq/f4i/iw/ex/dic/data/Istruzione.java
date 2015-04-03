package it.univaq.f4i.iw.ex.dic.data;

public class Istruzione {

    private int key;
    private int tipologia; //0=medie 1=superiori 2=laurea base 3=specialistica 4=master 5=altro
    private String titolo;
    private String data;
    private String istituzione;
    private int voto;

    public Istruzione(int key, String titolo, String data, String istituzione, int voto, int tipologia) {
        this.data = data;
        this.tipologia = tipologia;
        this.istituzione = istituzione;
        this.key = key;
        this.titolo = titolo;
        this.voto = voto;
    }

    //Funzioni set
    public void setKey(int key) {
        this.key = key;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setIstituzione(String istituzione) {
        this.istituzione = istituzione;
    }

    public void setVoto(int voto) {
        this.voto = voto;
    }

    public void setTipologia(int tipologia) {
        if (tipologia < 0 || tipologia > 5) {
            tipologia = 5;
        }
        this.tipologia = tipologia;
    }

    //Funzioni GET
    public int getKey() {
        return this.key;
    }

    public String getTitolo() {
        return this.titolo;
    }

    public String getData() {
        return this.data;
    }

    public String getIstituzione() {
        return this.istituzione;
    }

    public int getVoto() {
        return this.voto;
    }

    public int getTipologia() {
        return this.tipologia;
    }
}
