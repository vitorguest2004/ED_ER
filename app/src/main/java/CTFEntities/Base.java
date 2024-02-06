package CTFEntities;

public class Base {

    private final int location;
    private final Player player;

    /**
     * Cria uma instância de uma base.
     *
     * @param location Localização da base.
     * @param player Jogador associado.
     */
    public Base(int location, Player player) {
        this.location = location;
        this.player = player;
    }

    /**
     * Obtém a localização da base.
     *
     * @return Localização da base.
     */
    public int getLocation() {
        return location;
    }
}