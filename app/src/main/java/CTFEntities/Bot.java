package CTFEntities;

import Collections.EmptyCollectionException;

public class Bot {

    private static int botCount = 1;
    private final int id = 0;
    private int location;
    private boolean hasFlag = false;
    private Algorithm algorithm;
    private boolean blocked = false;

    /**
     * Cria um novo objeto Bot com uma localização inicial e um algoritmo
     * específico.
     *
     * @param location A localização inicial do bot.
     * @param a O algoritmo associado ao bot.
     */
    public Bot(int location, Algorithm a) {
//        this.id = contadorBot++;
        this.location = location;
        this.algorithm = a;
        this.blocked = false;
        this.hasFlag = false;
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
     * @throws EmptyCollectionException Se a coleção estiver vazia e o próximo
     * local não puder ser obtido.
     */
    protected void moveBot() throws EmptyCollectionException {
        Integer next = this.algorithm.dequeueNext();

        this.location = next;
    }

    /**
     * Retorna a próxima posição calculada pelo algoritmo associado ao bot.
     *
     * @return A próxima posição calculada pelo algoritmo.
     * @throws EmptyCollectionException Se a coleção subjacente estiver vazia.
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
     * Define o estado do bot como bloqueado.
     */
    protected void setBlocked() {
        this.blocked = true;
    }

    /**
     * Define o estado do bot como não bloqueado.
     */
    protected void setNotBlocked() {
        this.blocked = false;
    }

    /**
     * Verifica se o bot está atualmente bloqueado.
     *
     * @return true se o bot estiver bloqueado, false caso contrário.
     */
    protected boolean isBlocked() {
        return this.blocked;
    }
}

