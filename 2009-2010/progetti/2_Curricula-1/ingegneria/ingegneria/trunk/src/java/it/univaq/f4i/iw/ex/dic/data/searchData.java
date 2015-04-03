package it.univaq.f4i.iw.ex.dic.data;

public class searchData {

    // Anagrafica
    private String etaMin;
    private String etaMax;
    private String sesso;
    // Lingue
    private String lingua1;
    private String lingua2;
    private String lingua3;
    // Titoli di studio
    private String tipologia;
    private String denominazione;
    // Timpo impiego
    private String provincia;
    private String partTime;
    private String fullTime;
    private String determinato;
    private String indeterminato;
    private String dirigente;
    private String subordinato;
    private String lavoroEstero;
    private String soggiornoEstero;

    public searchData(String etaMin, String etaMax, String sesso, String lingua1, String lingua2, String lingua3, String tipologia, String denominazione, String provincia, String partTime, String fullTime, String determinato, String indeterminato, String dirigente, String subordinato, String lavoroEstero, String soggiornoEstero) {
        if (etaMin.equals("")) {
            this.etaMin = "0";
        } else {
            this.etaMin = etaMin;
        }

        if (etaMax.equals("")) {
            this.etaMax = "99";
        } else {
            this.etaMax = etaMax;
        }

        this.sesso = sesso;
        this.lingua1 = lingua1;
        this.lingua2 = lingua2;
        this.lingua3 = lingua3;
        this.tipologia = tipologia;
        this.denominazione = denominazione;
        this.provincia = provincia;
        this.partTime = checkBox(partTime);
        this.fullTime = checkBox(fullTime);
        this.determinato = checkBox(determinato);
        this.indeterminato = checkBox(indeterminato);
        this.dirigente = checkBox(dirigente);
        this.subordinato = checkBox(subordinato);
        this.lavoroEstero = checkBox(lavoroEstero);
        this.soggiornoEstero = checkBox(soggiornoEstero);
    }

    private String checkBox(String str) {
        if (str == null) {
            return "0";
        } else if (str.equals("0")) {
            return "0";
        } else {
            return "1";
        }
    }

    /**
     * @return the etaMin
     */
    public String getEtaMin() {
        return etaMin;
    }

    /**
     * @param etaMin the etaMin to set
     */
    public void setEtaMin(String etaMin) {
        if (etaMin.equals("")) {
            this.etaMin = "0";
        } else {
            this.etaMin = etaMin;
        }
    }

    /**
     * @return the etaMax
     */
    public String getEtaMax() {
        return etaMax;
    }

    /**
     * @param etaMax the etaMax to set
     */
    public void setEtaMax(String etaMax) {
        if (etaMax.equals("")) {
            this.etaMax = "0";
        } else {
            this.etaMax = etaMax;
        }
    }

    /**
     * @return the sesso
     */
    public String getSesso() {
        return sesso;
    }

    /**
     * @param sesso the sesso to set
     */
    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    /**
     * @return the tipologia
     */
    public String getTipologia() {
        return tipologia;
    }

    /**
     * @param tipologia the tipologia to set
     */
    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    /**
     * @return the denominazione
     */
    public String getDenominazione() {
        return denominazione;
    }

    /**
     * @param denominazione the denominazione to set
     */
    public void setDenominazione(String denominazione) {
        this.denominazione = denominazione;
    }

    /**
     * @return the lingua1
     */
    public String getLingua1() {
        return lingua1;
    }

    /**
     * @param lingua1 the lingua1 to set
     */
    public void setLingua1(String lingua1) {
        this.lingua1 = lingua1;
    }

    /**
     * @return the lingua2
     */
    public String getLingua2() {
        return lingua2;
    }

    /**
     * @param lingua2 the lingua2 to set
     */
    public void setLingua2(String lingua2) {
        this.lingua2 = lingua2;
    }

    /**
     * @return the lingua3
     */
    public String getLingua3() {
        return lingua3;
    }

    /**
     * @param lingua3 the lingua3 to set
     */
    public void setLingua3(String lingua3) {
        this.lingua3 = lingua3;
    }

    /**
     * @return the partTime
     */
    public String getPartTime() {
        return partTime;
    }

    /**
     * @param partTime the partTime to set
     */
    public void setPartTime(String partTime) {
        this.partTime = partTime;
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

    /**
     * @return the fullTime
     */
    public String getFullTime() {
        return fullTime;
    }

    /**
     * @param fullTime the fullTime to set
     */
    public void setFullTime(String fullTime) {
        this.fullTime = fullTime;
    }

    /**
     * @return the determinato
     */
    public String getDeterminato() {
        return determinato;
    }

    /**
     * @param determinato the determinato to set
     */
    public void setDeterminato(String determinato) {
        this.determinato = determinato;
    }

    /**
     * @return the indeterminato
     */
    public String getIndeterminato() {
        return indeterminato;
    }

    /**
     * @param indeterminato the indeterminato to set
     */
    public void setIndeterminato(String indeterminato) {
        this.indeterminato = indeterminato;
    }

    /**
     * @return the dirigente
     */
    public String getDirigente() {
        return dirigente;
    }

    /**
     * @param dirigente the dirigente to set
     */
    public void setDirigente(String dirigente) {
        this.dirigente = dirigente;
    }

    /**
     * @return the subordinato
     */
    public String getSubordinato() {
        return subordinato;
    }

    /**
     * @param subordinato the subordinato to set
     */
    public void setSubordinato(String subordinato) {
        this.subordinato = subordinato;
    }

    /**
     * @return the lavoroEstero
     */
    public String getLavoroEstero() {
        return lavoroEstero;
    }

    /**
     * @param lavoroEstero the lavoroEstero to set
     */
    public void setLavoroEstero(String lavoroEstero) {
        this.lavoroEstero = lavoroEstero;
    }

    /**
     * @return the soggiornoEstero
     */
    public String getSoggiornoEstero() {
        return soggiornoEstero;
    }

    /**
     * @param soggiornoEstero the soggiornoEstero to set
     */
    public void setSoggiornoEstero(String soggiornoEstero) {
        this.soggiornoEstero = soggiornoEstero;
    }
}
