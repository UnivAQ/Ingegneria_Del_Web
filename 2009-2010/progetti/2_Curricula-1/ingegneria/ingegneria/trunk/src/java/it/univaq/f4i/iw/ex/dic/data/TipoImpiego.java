package it.univaq.f4i.iw.ex.dic.data;

public class TipoImpiego {

    private int key;
    private String provincia;
    private boolean partTime;
    private boolean fullTime;
    private boolean determinato;
    private boolean indeterminato;
    private boolean dirigente;
    private boolean subordinato;
    private boolean lavoroEstero;
    private boolean soggiornoEstero;
    // implementare settori

    public TipoImpiego(int key, String provincia, boolean partTime, boolean fullTime, boolean determinato, boolean indeterminato, boolean dirigente, boolean subordinato, boolean lavoroEstero, boolean soggiornoEstero) {

        setDeterminato(determinato);
        setDirigente(dirigente);
        setIndeterminato(indeterminato);
        setKey(key);
        setLavoroEstero(lavoroEstero);
        setSoggiornoEstero(soggiornoEstero);
        setSubordinato(subordinato);
        setPartTime(partTime);
        setFullTime(fullTime);
        setProvincia(provincia);
    }
    // Funzioni SET

    public void setKey(int key) {
        this.key = key;
    }

    public void setPartTime(boolean var) {
        this.partTime = var;
    }

    public void setFullTime(boolean var) {
        this.fullTime = var;
    }

    public void setDeterminato(boolean var) {
        this.determinato = var;
    }

    public void setIndeterminato(boolean var) {
        this.indeterminato = var;
    }

    public void setDirigente(boolean var) {
        this.dirigente = var;
    }

    public void setSubordinato(boolean var) {
        this.subordinato = var;
    }

    public void setLavoroEstero(boolean var) {
        this.lavoroEstero = var;
    }

    public void setSoggiornoEstero(boolean var) {
        this.soggiornoEstero = var;
    }

    // Funzioni GET
    public int getKey() {
        return this.key;
    }

    public boolean getPartTime() {
        return this.partTime;
    }

    public boolean getFullTime() {
        return this.fullTime;
    }

    public boolean getDeterminato() {
        return this.determinato;
    }

    public boolean getIndeterminato() {
        return this.indeterminato;
    }

    public boolean getDirigente() {
        return this.dirigente;
    }

    public boolean getSubordinato() {
        return this.subordinato;
    }

    public boolean getLavoroEstero() {
        return this.lavoroEstero;
    }

    public boolean getSoggiornoEstero() {
        return this.soggiornoEstero;
    }

    /**
     * @return the provincia
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * @param provincia the provincia to set
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}
