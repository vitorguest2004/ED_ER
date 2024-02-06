package CTFEntities;

public class Flag {

    private int location;
    private final Player player;

    /**
     * Cria uma instância de uma bandeira.
     *
     * @param location Localização da bandeira.
     * @param player   Jogador respetivo.
     */
    public Flag(int location, Player player) {
        this.location = location;
        this.player = player;
    }

    /**
     * Define a localização da bandeira.
     *
     * @param location Localização da bandeira.
     */
    public void setLocation(int location) {
        this.location = location;
    }

    /**
     * Devolve a localização da bandeira.
     *
     * @return Localização da bandeira.
     */
    public int getLocation() {
        return location;
    }
}
