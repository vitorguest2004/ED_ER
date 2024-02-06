package Interfaces;

import CTFEntities.Algorithm;
import Collections.EmptyCollectionException;

public interface BotInterface {

    /**
     * Move o bot de acordo com a lógica específica implementada.
     *
     * @throws EmptyCollectionException Se a coleção estiver vazia e o próximo
     *                                  movimento não puder ser realizado.
     */
    public void moveBot() throws EmptyCollectionException;

    /**
     * Retorna a próxima posição calculada pelo algoritmo associado ao bot.
     *
     * @return A próxima posição calculada pelo algoritmo.
     * @throws EmptyCollectionException Se a coleção subjacente estiver vazia.
     */
    public Integer nextPosition() throws EmptyCollectionException;

    /**
     * Obtém a localização atual do bot.
     *
     * @return A localização atual do bot.
     */
    public int getLocation();

    /**
     * Define a localização do bot para uma nova posição.
     *
     * @param location A nova localização do bot.
     */
    public void setLocation(int location);

    /**
     * Define se o bot capturou uma bandeira como capturado.
     */
    public void setCaptured();

    /**
     * Define se o bot capturou uma bandeira como não capturado.
     */
    public void setNotCaptured();

    /**
     * Retorna se o bot capturou uma bandeira.
     *
     * @return true se o bot tiver capturado uma bandeira, false se não houve
     *         captura
     */
    public boolean getCapturedState();

    /**
     * Devolve o algoritmo usado para a movimentação.
     *
     * @return Algoritmo usado para movimentação
     * @throws EmptyCollectionException Lançada se houver uma coleção vazia.
     */
    public Algorithm getAlgorithmInUse() throws EmptyCollectionException;
}
