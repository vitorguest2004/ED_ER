package Interfaces;

import java.util.Iterator;

import CTFEntities.Base;
import CTFEntities.Bot;
import CTFEntities.Flag;
import Collections.EmptyCollectionException;
import Collections.LinkedQueue;

public interface PlayerInterface {
    /**
     * Obtém o ID do jogador.
     *
     * @return O ID do jogador.
     */
    public int getId();

    /**
     * Adiciona uma bandeira ao jogador.
     *
     * @param bandeira A bandeira a ser adicionada ao jogador.
     */
    public void addBandeira(Flag flag);

    /**
     * Adiciona uma base ao jogador.
     *
     * @param base A base a ser adicionada ao jogador.
     */
    public void addBase(Base base);

    /**
     * Devolve a queue de bots do jogador.
     *
     * @return A queue de robôs do jogador.
     */
    public LinkedQueue<Bot> getBots();

    /**
     * Adiciona um bot à queue de bots do jogador.
     *
     * @param bot O robô a ser adicionado à fila.
     */
    public void addBot(Bot bot);

    /**
     * Devolve a posição atual da bandeira do jogador.
     *
     * @return A posição da bandeira no mapa.
     */
    public int getFlagPosition();

    /**
     * Define a posição atual da bandeira do jogador.
     *
     * @param location Localização da bandeira a ser definida
     */
    public void setFlagPosition(int location);

    /**
     * Devolve a posição atual da base do jogador.
     *
     * @return A posição da base no mapa.
     */
    public int getBasePosition();

    /**
     * Movimenta o bot para a frente da queue para uma nova posição, atualizando
     * o seu estado.
     *
     * @throws EmptyCollectionException Lançada se houver uma coleção vazia.
     */
    public void moveBot() throws EmptyCollectionException;

    /**
     * Devolve a próxima posição para o próximo movimento do bot na frente da
     * queue.
     *
     * @return A próxima posição para o movimento do bot.
     * @throws EmptyCollectionException Lançada se houver uma coleção vazia.
     */
    public Integer nextMove() throws EmptyCollectionException;

    /**
     * Devolve o número total de bots do jogador.
     *
     * @return O número de bots atual.
     */
    public int getNumBots();

    /**
     * Devolve um iterador das posições dos bots.
     *
     * @return Um iterador com as posições dos bots.
     */
    public Iterator getBotsPositions();
}
