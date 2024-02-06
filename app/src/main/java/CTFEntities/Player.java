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
     * Cria um novo jogador com uma queue de bots pré-definida.
     *
     * @param bots A fila de robôs do jogador.
     */
    public Player(LinkedQueue<Bot> bots) {
        this.id = playerCount++;
        this.bots = bots;
    }

    /**
     * Cria uma instância de jogador com uma queue vazia
     */
    public Player() {
        this.id = playerCount++;
        this.bots = new LinkedQueue<Bot>();
    }

    /**
     * Obtém o ID do jogador.
     *
     * @return O ID do jogador.
     */
    public int getId() {
        return id;
    }

    /**
     * Adiciona uma bandeira ao jogador.
     *
     * @param bandeira A bandeira a ser adicionada ao jogador.
     */
    public void addBandeira(Flag flag) {
        this.flag = flag;
    }

    /**
     * Adiciona uma base ao jogador.
     *
     * @param base A base a ser adicionada ao jogador.
     */
    public void addBase(Base base) {
        this.base = base;
    }

    /**
     * Devolve a queue de bots do jogador.
     *
     * @return A queue de robôs do jogador.
     */
    public LinkedQueue<Bot> getBots() {
        return bots;
    }

    /**
     * Adiciona um bot à queue de bots do jogador.
     *
     * @param bot O robô a ser adicionado à fila.
     */
    public void addBot(Bot bot) {
        bots.enqueue(bot);
        this.numBots++;
    }

    /**
     * Devolve a posição atual da bandeira do jogador.
     *
     * @return A posição da bandeira no mapa.
     */
    public int getFlagPosition() {
        return this.flag.getLocation();
    }

    /**
     * Define a posição atual da bandeira do jogador.
     *
     * @param location Localização da bandeira a ser definida
     */
    public void setFlagPosition(int location) {
        this.flag.setLocation(location);
    }

    /**
     * Devolve a posição atual da base do jogador.
     *
     * @return A posição da base no mapa.
     */
    public int getBasePosition() {
        return this.base.getLocation();
    }

    /**
     * Movimenta o bot para a frente da queue para uma nova posição, atualizando
     * o seu estado.
     *
     * @throws EmptyCollectionException Lançada se houver uma coleção vazia.
     */
    protected void moveBot() throws EmptyCollectionException {
        Bot currentBot = this.bots.dequeue();
        currentBot.moveBot();
        this.bots.enqueue(currentBot);
    }

    /**
     * Devolve a próxima posição para o próximo movimento do bot na frente da
     * queue.
     *
     * @return A próxima posição para o movimento do bot.
     * @throws EmptyCollectionException Lançada se houver uma coleção vazia.
     */
    protected Integer nextMove() throws EmptyCollectionException {
        return this.bots.first().nextPosition();
    }

    /**
     * Devolve o número total de bots do jogador.
     *
     * @return O número de bots atual.
     */
    protected int getNumBots() {
        return this.numBots;
    }

    /**
     * Devolve um iterador das posições dos bots.
     *
     * @return Um iterador com as posições dos bots.
     */
    public Iterator getBotsPositions() {
        ArrayUnorderedList<Integer> positions = new ArrayUnorderedList<Integer>();
        LinkedQueue<Bot> savedBots = new LinkedQueue<Bot>();
        while (!this.bots.isEmpty()) {
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
