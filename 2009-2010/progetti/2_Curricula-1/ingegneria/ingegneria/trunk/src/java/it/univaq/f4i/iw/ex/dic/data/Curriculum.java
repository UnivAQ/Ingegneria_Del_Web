
package it.univaq.f4i.iw.ex.dic.data;

import java.util.ArrayList;
import java.util.List;

public class Curriculum {

    private int key;
    private String nome;
    private String cognome;
    private String dataNascita;
    private String email;
    private String indirizzo;
    private String telefono;
    private String sesso;
    private String provincia;
    private TipoImpiego tipoImpiego;//agg regione
    private List<Istruzione> istruzione;
    private List<Capacita> capacita;
    private List<Lingue> lingue;
    private List<Esperienze> esperienze;
    private int numLingue;
    private int numIstruzione;
    private int numCapacita;
    private int numEsperienze;

    public Curriculum(int key, String nome, String cognome, String sesso, String dataNascita, String email, String indirizzo, String provincia, String telefono) {
        setNome(nome);
        setKey(key);
        setCognome(cognome);
        setSesso(sesso);
        setDataNascita(dataNascita);
        setEmail(email);
        setIndirizzo(indirizzo);
        setProvincia(provincia);
        setTelefono(telefono);

        //pagina successiva

        istruzione = new ArrayList();
        numIstruzione = 0;

        capacita = new ArrayList();
        numCapacita = 0;

        lingue = new ArrayList();
        this.numLingue = 0;

        esperienze = new ArrayList();
        numEsperienze = 0;
    }

    // funzioni set di modifica dei parametri
    public void setKey(int key) {
        this.key = key;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public void setDataNascita(String dataNascita) {
        this.dataNascita = dataNascita;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setNumLingue(int numLingue) {
        this.numLingue = numLingue;
    }

    public void setTipoImpiego(TipoImpiego tipoImpiego) {
        this.tipoImpiego = tipoImpiego;
    }

    public void setIstruzione(List<Istruzione> istruzioni) {
        this.istruzione = istruzioni;
    }
    //fine funzioni set

    //funzioni di get dei valori
    public String getNome() {
        return this.nome;
    }

    public String getCognome() {
        return this.cognome;
    }

    public String getSesso() {
        return this.sesso;
    }

    public int getKey() {
        return this.key;
    }

    public int getNumLingue() {
        return this.numLingue;
    }

    public String getDataNascita() {
        return this.dataNascita;
    }

    public String getEmail() {
        return this.email;
    }

    public String getIndirizzo() {
        return this.indirizzo;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public String getProvincia() {
        return this.provincia;
    }

    public TipoImpiego getTipoImpiego() {
        return this.tipoImpiego;
    }

    public List<Istruzione> getIstruzione() {
        return this.istruzione;
    }

    public List<Capacita> getCapacita() {
        return this.capacita;
    }

    public List<Lingue> getLingue() {
        return this.lingue;
    }

    public List<Esperienze> getEsperienze() {
        return this.esperienze;
    }
    //fine funzioni get

    //aggiungi istruzione
    public void addIstruzione(Istruzione istruzione) {
        this.istruzione.add(istruzione);
    }

    public void addEsperienze(Esperienze esperienze) {
        this.esperienze.add(esperienze);
    }

    public void addLingue(Lingue lingue) {
        this.lingue.add(lingue);
    }

    public void addCapacita(Capacita capacita) {
        this.capacita.add(capacita);
    }

    /**
     * @return the numIstruzione
     */
    public int getNumIstruzione() {
        return numIstruzione;
    }

    /**
     * @param numIstruzione the numIstruzione to set
     */
    public void setNumIstruzione(int numIstruzione) {
        this.numIstruzione = numIstruzione;
    }

    /**
     * @return the numCapacita
     */
    public int getNumCapacita() {
        return numCapacita;
    }

    /**
     * @param numCapacita the numCapacita to set
     */
    public void setNumCapacita(int numCapacita) {
        this.numCapacita = numCapacita;
    }

    /**
     * @return the numEsperienze
     */
    public int getNumEsperienze() {
        return numEsperienze;
    }

    /**
     * @param numEsperienze the numEsperienze to set
     */
    public void setNumEsperienze(int numEsperienze) {
        this.numEsperienze = numEsperienze;
    }
}

