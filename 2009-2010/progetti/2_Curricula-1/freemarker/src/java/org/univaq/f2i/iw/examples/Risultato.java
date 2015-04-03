/*
 * Risultato.java
 *
 * Un bean che contiene i dati di un risultato 
 *
 */

package org.univaq.f2i.iw.examples;

/**
 * @author Giuseppe Della Penna
 */
public class Risultato {
    
    private String nome,matricola;
    private int voto;
    
    public Risultato(String _nome, String _matricola, int _voto) {
        setNome(_nome);
        setMatricola(_matricola);
        setVoto(_voto);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricola() {
        return matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    public int getVoto() {
        return voto;
    }

    public void setVoto(int voto) {
        this.voto = voto;
    }
}
