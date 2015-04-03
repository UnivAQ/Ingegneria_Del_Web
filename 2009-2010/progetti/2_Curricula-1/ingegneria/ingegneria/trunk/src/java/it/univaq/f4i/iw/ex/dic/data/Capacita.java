package it.univaq.f4i.iw.ex.dic.data;

public class Capacita {

    private String capacita;
    private int key;

    public Capacita(int key, String capacita) {
        setKey(key);
        setCapacita(capacita);
    }

    public String getCapacita() {
        return capacita;
    }

    public void setCapacita(String capacita) {
        this.capacita = capacita;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
