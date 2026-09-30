public class Marca {
    private int id;
    private String nome;

    public Marca() {

    }

    /**
     * @param id
     * @param nome
     */
    public Marca(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * O combobox usa o toString para saber o texto que mostra na tela
     */
    @Override
    public String toString() {
        return nome;
    }

}
