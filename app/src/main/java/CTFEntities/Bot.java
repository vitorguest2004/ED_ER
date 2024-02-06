package CTFEntities;

import Collections.EmptyCollectionException;

public class Bot {

    private final int id = 0;
    private int location;
    private boolean hasFlag;
    private boolean blocked;
    private Algorithm algorithm;

    /**
     * Cria um novo objeto Bot com uma localização inicial e um algoritmo
     * específico.
     *
     * @param location A localização inicial do bot.
     * @param a O algoritmo associado ao bot.
     */
    public Bot(int location, Algorithm a) {
        this.location = location;
        this.algorithm = a;
        this.hasFlag = false;
        this.blocked = false;
    }

    /**
     * Obtém a localização atual do bot.
     *
     * @return A localização atual do bot.
     */
    public int getLocation() {
        return location;
    }

    /**
     * Define a localização do bot para uma nova posição.
     *
     * @param location A nova localização do bot.
     */
    public void setLocation(int location) {
        this.location = location;
    }

    /**
     * Move o bot para o próximo local usando um algoritmo específico.
     *
     * @throws EmptyCollectionException Lançada se houver uma coleção vazia.
     */
    protected void moveBot() throws EmptyCollectionException {
        Integer nextPosition = this.algorithm.dequeueNext();

        this.location = nextPosition;
    }

    /**
     * Retorna a próxima posição do algoritmo.
     *
     * @return A próxima posição do algoritmo.
     * @throws EmptyCollectionException Lançada se houver uma coleção vazia.
     */
    protected Integer nextPosition() throws EmptyCollectionException {
        return this.algorithm.getNext();
    }

    /**
     * Define se o bot capturou uma bandeira como capturado.
     */
    protected void setCaptured() {
        this.hasFlag = true;
    }

    /**
     * Define se o bot capturou uma bandeira como não capturado.
     */
    protected void setNotCaptured() {
        this.hasFlag = false;
    }

    /**
     * Retorna se o bot capturou uma bandeira.
     *
     * @return true se o bot tiver capturado uma bandeira, false se não houve
     * captura
     */
    protected boolean getCapturedState() {
        return this.hasFlag;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    /**
     * Devolve o algoritmo usado para a movimentação.
     *
     * @return Algoritmo usado para movimentação
     * @throws EmptyCollectionException Lançada se houver uma coleção vazia.
     */
    protected Algorithm getAlgorithmInUse() throws EmptyCollectionException {
        return this.algorithm;
    }
}
