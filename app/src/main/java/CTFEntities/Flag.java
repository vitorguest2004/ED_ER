package CTFEntities;

public class Flag {

    private final int location;
    private final Player player;
    
    /**
     * Cria um novo objeto Flag com uma localização inicial e associado a um
     * jogador específico.
     *
     * @param localizacao A localização inicial da bandeira.
     * @param jogador O jogador ao qual a bandeira está associada.
     */
    public Flag(int location, Player player) {
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
