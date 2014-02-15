package it.univaq.f4i.iw.ex.dic.data;
public class Esperienze {

    private int key;
    private String datore;
    private String incarico;
    private String periodo;

    public Esperienze(int key, String datore, String incarico, String periodo) {
        this.key = key;
        this.datore = datore;
        this.incarico = incarico;
        this.periodo = periodo;

    }

    // Funzioni GET
    public int getKey() {
        return this.key;
    }

    public String getDatore() {
        return this.datore;
    }

    public String getIncarico() {
        return this.incarico;
    }

    public String getPeriodo() {
        return this.periodo;
    }

    //FUNZIONI SET
    public void setKey(int key) {
        this.key = key;
    }

    public void setDatore(String datore) {
        this.datore = datore;
    }

    public void setIncarico(String incarico) {
        this.incarico = incarico;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
}
