package it.univaq.f4i.iw.ex.dic.data;

public class CurriculumRef {

    private String nome;
    private String cognome;
    private int key;

    public CurriculumRef(int key, String nome) {
        setNome(nome);
        setKey(key);
    }

    /**
     * @return the termine
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param termine the termine to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the key
     */
    public int getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(int key) {
        this.key = key;
    }
}
