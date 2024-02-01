package CTFEntities;

import Collections.ArrayUnorderedList;
import Collections.EmptyCollectionException;
import Collections.LinkedQueue;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Player {

    private static int playerCount = 1;
    private int id;
    private Flag flag;
    private Base base;
    private LinkedQueue<Bot> bots;
    private int numBots;

    /**
     * Cria um novo jogador com uma fila de robôs associada.
     *
     * @param bots A fila de robôs do jogador.
     */
    public Player(LinkedQueue<Bot> bots) {
        this.id = playerCount++;
        this.bots = bots;
    }

    /**
     * Cria um novo jogador sem associar uma fila de robôs inicial. O jogador
     * pode adicionar uma fila de robôs posteriormente usando o método addBots.
     */
    public Player() {
        this.id = playerCount++;
        this.bots = new LinkedQueue<Bot>();
    }

    /**
     * Obtém o identificador único atribuído a este jogador.
     *
     * @return O identificador único do jogador.
     */
    public int getId() {
        return id;
    }

    /**
     * Adiciona uma bandeira associada a este jogador.
     *
     * @param bandeira A bandeira a ser associada ao jogador.
     */
    public void addBandeira(Flag bandeira) {
        this.flag = bandeira;
    }

    /**
     * Adiciona uma base associada a este jogador.
     *
     * @param base A base a ser associada ao jogador.
     */
    public void addBase(Base base) {
        this.base = base;
    }

    /**
     * Obtém a fila de robôs associada a este jogador.
     *
     * @return A fila de robôs do jogador.
     */
    public LinkedQueue<Bot> getBots() {
        return bots;
    }

    /**
     * Adiciona um robô à fila de robôs associada a este jogador.
     *
     * @param bot O robô a ser adicionado à fila.
     */
    public void addBot(Bot bot) {
        bots.enqueue(bot);
        this.numBots++;
    }

    /**
     * Obtém a posição atual da bandeira associada a este jogador.
     *
     * @return A posição da bandeira no mapa.
     */
    public int getFlagPosition() {
        return this.flag.getLocation();
    }

    /**
     * Obtém a posição atual da base associada a este jogador.
     *
     * @return A posição da base no mapa.
     */
    public int getBasePosition() {
        return this.base.getLocation();
    }

    /**
     * Move o robô na frente da fila para uma nova posição, atualizando seu
     * estado.
     *
     * @throws EmptyCollectionException Se a fila de robôs estiver vazia.
     */
    protected void moveBot() throws EmptyCollectionException {
        Bot bAtual = this.bots.dequeue();
        bAtual.moveBot();
        this.bots.enqueue(bAtual);
    }

    /**
     * Retorna a próxima posição para o próximo movimento do robô na frente da
     * fila.
     *
     * @return A próxima posição para o movimento do robô.
     * @throws EmptyCollectionException Se a fila de robôs estiver vazia.
     */
    protected Integer nextMove() throws EmptyCollectionException {
        return this.bots.first().nextPosition();
    }

    /**
     * Define o estado do robô na frente da fila como bloqueado.
     *
     * @throws EmptyCollectionException Se a fila de robôs estiver vazia.
     */
    protected void setCurrentBlocked() throws EmptyCollectionException {
        Bot bAtual = this.bots.dequeue();
        bAtual.setBlocked();
        this.bots.enqueue(bAtual);
    }

    /**
     * Define o estado do robô na frente da fila como não bloqueado.
     *
     * @throws EmptyCollectionException Se a fila de robôs estiver vazia.
     */
    protected void setCurrentNotBlocked() throws EmptyCollectionException {
        Bot bAtual = this.bots.first();
        bAtual.setNotBlocked();
    }

    /**
     * Retorna o número total de robôs associados a este jogador.
     *
     * @return O número total de robôs.
     * @throws EmptyCollectionException Se a fila de robôs estiver vazia.
     */
    protected int numBlocked() throws EmptyCollectionException {
        LinkedQueue<Bot> savedBots = new LinkedQueue<Bot>();
        Bot currentBot = this.bots.first();
        int num = 0;
        for (int i = 0; i < this.numBots; i++) {
            currentBot = this.bots.dequeue();
            savedBots.enqueue(currentBot);
            if (currentBot.isBlocked()) {
                num++;
            }
        }
        this.bots = savedBots;
        return num;
    }

    /**
     * Retorna o número total de robôs associados a este jogador.
     *
     * @return O número total de robôs.
     */
    protected int getNumBots() {
        return this.numBots;
    }

    /**
     * Obtém um iterador das posições dos bots.
     *
     * @return Um iterador contendo as posições dos bots.
     */
    public Iterator getBotsPositions() {
        ArrayUnorderedList<Integer> positions = new ArrayUnorderedList<Integer>();
        LinkedQueue<Bot> savedBots = new LinkedQueue<Bot>();
        for (int i = 0; i < this.numBots; i++) {
            try {
                positions.addToRear(this.bots.first().getLocation());
                savedBots.enqueue(this.bots.dequeue());
            } catch (EmptyCollectionException ex) {
                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.bots = savedBots;
        return positions.iterator();
    }
}
