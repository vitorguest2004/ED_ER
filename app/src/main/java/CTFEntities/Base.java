package CTFEntities;

public class Base {
    
    private final int location;
    private final Player player;
    
    /**
     * Cria um novo objeto Flag com uma localização inicial e associado a um
     * jogador específico.
     *
     * @param location A localização inicial da bandeira.
     * @param player O jogador ao qual a bandeira está associada.
     */
    public Base(int location, Player player) {
        this.location = location;
        this.player = player;
    }

    /**
     * Obtém a localização atual da bandeira.
     *
     * @return A localização atual da bandeira.
     */
    public int getLocation() {
        return location;
    }
}
