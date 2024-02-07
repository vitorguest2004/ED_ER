package CTFEntities;

import Collections.EmptyCollectionException;
import Collections.LinkedQueue;
import Exceptions.InvalidOptionException;
import Exceptions.NoMorePositionsException;
import Exceptions.PositionAlreadySelectedException;
import Input.Input;

import java.util.Iterator;

public class Game {

    private Map map;
    private LinkedQueue<Player> players;

    /**
     * Cria uma instãncia de um jogo
     *
     * @param map Mapa para ser usado
     */
    public Game(Map map) {
        this.map = map;
        this.players = new LinkedQueue<Player>();
    }

    /**
     * Mostra em tempo real as movimentações dos bots e posição das bandeiras
     *
     * @return ID do jogador vencedor
     * @throws EmptyCollectionException Lançada se houver uma coleção vazia
     *                                  enquanto estiver em execução
     */
    public int playGame() throws EmptyCollectionException, NoMorePositionsException {

        while (true) {
            System.out.println("Mapa");
            this.map.adjacenciesPrint();

            Player currentPlayer = getCurrentPlayer();
            Player opponentPlayer = getOpponentPlayer(currentPlayer);

            System.out.println("Posicão da base do jogador " + currentPlayer.getId());
            System.out.println("Posição: " + currentPlayer.getBasePosition());

            System.out.println("Posicão da bandeira do jogador " + currentPlayer.getId());
            System.out.println("Posição: " + currentPlayer.getFlagPosition());

            System.out.println("Posicão da bandeira do jogador " + opponentPlayer.getId());
            System.out.println("Posição: " + opponentPlayer.getFlagPosition());

            System.out.println("Posicão dos bots do jogador " + currentPlayer.getId());
            Iterator pos1 = currentPlayer.getBotsPositions();
            while (pos1.hasNext()) {
                System.out.println("Posição: " + pos1.next());
            }

            moveBot();

            if (this.checkVictory() != 0) {
                return checkVictory();
            }

            System.out.println("Insira 1 para continuar");
            Input.inputInt();
        }
    }

    /**
     * Devolve o jogar atual
     *
     * @return Jogador atual
     * @throws EmptyCollectionException Lançada se houver uma coleção vazia
     */
    public Player getCurrentPlayer() throws EmptyCollectionException {
        return this.players.first();
    }

    /**
     * Cria uma nova base num vértice específico do mapa para o jogador atual.
     * Cria também a bandeira correspondente no mesmo vértice.
     *
     * @param vertex O vértice no qual criar a base.
     * @throws EmptyCollectionException  Lançada se houver uma coleção vazia.
     * @throws PositionOccupiedException Se a posição já estiver ocupada por
     *                                   outra base.
     */
    public void createBaseAndFlag(Integer vertex) throws EmptyCollectionException, PositionAlreadySelectedException {
        Player currentPlayer = players.dequeue();
        Player nextPlayer = null;
        Base base = null;
        Flag flag = null;
        if (map.indexIsValid(vertex - 1)) {
            if (players.isEmpty()) {
                base = new Base(vertex, currentPlayer);
                flag = new Flag(vertex, currentPlayer);
            } else {
                base = new Base(vertex, currentPlayer);
                flag = new Flag(vertex, currentPlayer);
                nextPlayer = players.first();
                try {
                    if (nextPlayer.getBasePosition() == vertex) {
                        players.dequeue();
                        players.enqueue(currentPlayer);
                        players.enqueue(nextPlayer);
                        throw new PositionAlreadySelectedException("Posição Ocupada");
                    }
                } catch (NullPointerException e) {
                }
            }
        }

        currentPlayer.addBase(base);
        currentPlayer.addBandeira(flag);
        players.enqueue(currentPlayer);
    }

    /**
     * Cria um novo jogador no jogo.
     */
    public void createPlayer() {
        try {
            Player existingPlayer = this.players.dequeue();
            this.players.enqueue(existingPlayer);
            if (this.players.first() == existingPlayer) {
                Player player = new Player();
                players.enqueue(player);
            }
        } catch (EmptyCollectionException e) {
            Player player = new Player();
            players.enqueue(player);
        }
    }

    /**
     * Adiciona um novo bot ao jogador especificado.
     *
     * @param opt         Opção de algoritmo
     * @param playerIndex Índice do jogador ao qual adicionar o bot
     * @throws EmptyCollectionException Lançada se houver uma coleção vazia.
     * @throws InvalidOptionException   Se a opção não for válida.
     */
    public void addBot(int opt, int playerIndex) throws EmptyCollectionException, InvalidOptionException {
        if (opt != 1 && opt != 2 && opt != 3) {
            throw new InvalidOptionException("Opção inválida");
        }

        Player currentPlayer;
        Player nextPlayer;

        while (this.players.first().getId() != playerIndex) {
            this.players.enqueue(this.players.dequeue());
        }

        currentPlayer = this.players.dequeue();
        nextPlayer = this.players.dequeue();

        Algorithm a = new Algorithm();
        switch (opt) {
            case 1:
                a.setPathAsDisjkstra(map, currentPlayer.getBasePosition(), nextPlayer.getBasePosition());
                break;
            case 2:
                a.setPathAsMCP(map, currentPlayer.getBasePosition(), nextPlayer.getBasePosition());
                break;
            case 3:
                a.setPathAsRandom(map, currentPlayer.getBasePosition());
                break;
        }

        Bot b = new Bot(currentPlayer.getBasePosition(), a);

        currentPlayer.addBot(b);
        this.players.enqueue(nextPlayer);
        this.players.enqueue(currentPlayer);
    }

    /**
     * Move the bot of the current player.
     *
     * @throws EmptyCollectionException If the players queue is empty.
     */
    public void moveBot() throws EmptyCollectionException, NoMorePositionsException {
        Player currentPlayer = this.players.first();

        if (!currentPlayer.getBots().isEmpty()
                && currentPlayer.getBots().first().getAlgorithmInUse().getNext() != null) {

            int nextPosition = currentPlayer.nextMove();

            if (currentPlayer.getBots().first().getCapturedState()) {
                getOpponentPlayer(currentPlayer).setFlagPosition(nextPosition);
            }

            if (isEnemyBase(currentPlayer, nextPosition) && getOpponentPlayer(currentPlayer)
                    .getFlagPosition() == getOpponentPlayer(currentPlayer).getBasePosition()) {
                currentPlayer.getBots().first().setCaptured();
                returnToBase(currentPlayer, nextPosition);
            }

            if (opponentBotInNextPosition(currentPlayer, nextPosition)) {
                if (currentPlayer.getBots().first().getCapturedState()
                        && opponentBotInNextPositionHasFlag(currentPlayer, nextPosition)) {
                    returnFlagsToBases(currentPlayer);
                    currentPlayer.getBots().first().setNotCaptured();
                    Bot opponentBot = getOpponentBotInNextPosition(currentPlayer, nextPosition);
                    opponentBot.setNotCaptured();
                } else if (!currentPlayer.getBots().first().getCapturedState()
                        && opponentBotInNextPositionHasFlag(currentPlayer, nextPosition)) {
                    returnFlagToBase(currentPlayer);
                    Bot opponentBot = getOpponentBotInNextPosition(currentPlayer, nextPosition);
                    opponentBot.setNotCaptured();
                }
            }

            currentPlayer.moveBot();
        }

        this.players.enqueue(this.players.dequeue());
    }

    /**
     * Método auxiliar para verificar se a próxima posição do bot está ocupada
     * por um bot do jogador adversário.
     *
     * @param currentPlayer Jogador atual.
     * @param nextPosition  Próxima posição do bot.
     * @return True se houver um bot adversário na próxima posição, false caso
     *         contrário.
     * @throws EmptyCollectionException Lançada se houver uma coleção vazia.
     */
    private boolean opponentBotInNextPosition(Player currentPlayer, Integer nextPosition)
            throws EmptyCollectionException {
        Player opponentPlayer = getOpponentPlayer(currentPlayer);
        LinkedQueue<Bot> savedBots = new LinkedQueue<Bot>();
        boolean result = false;
        while (!opponentPlayer.getBots().isEmpty()) {
            Bot bot = opponentPlayer.getBots().dequeue();
            savedBots.enqueue(bot);
            if (bot.getLocation() == nextPosition) {
                result = true;
            }
        }

        while (!savedBots.isEmpty()) {
            opponentPlayer.getBots().enqueue(savedBots.dequeue());
        }

        return result;
    }

    /**
     * Método auxiliar que verifica se a próxima posição do bot está ocupada por
     * um bot adversário e se o mesmo tem uma bandeira capturada.
     *
     * @param currentPlayer Jogador atual.
     * @param nextPosition  Próxima posição do bot.
     * @return True se a condição acontecer, false caso contrário.
     * @throws EmptyCollectionException Lançada se houver uma coleção vazia.
     */
    private boolean opponentBotInNextPositionHasFlag(Player currentPlayer, Integer nextPosition)
            throws EmptyCollectionException {
        Player opponentPlayer = getOpponentPlayer(currentPlayer);
        LinkedQueue<Bot> savedBots = new LinkedQueue<Bot>();
        boolean result = false;
        while (!opponentPlayer.getBots().isEmpty()) {
            Bot bot = opponentPlayer.getBots().dequeue();
            savedBots.enqueue(bot);
            if (bot.getLocation() == nextPosition) {
                if (bot.getCapturedState()) {
                    result = true;
                }
            }
        }

        while (!savedBots.isEmpty()) {
            opponentPlayer.getBots().enqueue(savedBots.dequeue());
        }
        return result;
    }

    /**
     * Verifica se a próxima posição do bot é a base adversária.
     *
     * @param currentPlayer Jogador atual.
     * @param nextPosition  Próxima posição do bot
     * @return True se a próxima posição for a base adversária, false caso
     *         contrário.
     * @throws EmptyCollectionException Lançada se houver uma coleção vazia.
     */
    private boolean isEnemyBase(Player currentPlayer, Integer nextPosition) throws EmptyCollectionException {
        Player opponentPlayer = getOpponentPlayer(currentPlayer);
        return nextPosition.equals(opponentPlayer.getBasePosition());
    }

    /**
     * Método auxiliar responsável por trazer o bot de volta á sua base após
     * capturar a bandeira adversária, usando o mesmo algoritmo que o levou lá.
     *
     * @param currentPlayer   Jogador atual.
     * @param botNextPosition Próxima posição do bot.
     * @throws EmptyCollectionException Lançada se houver uma coleção vazia.
     */
    private void returnToBase(Player currentPlayer, Integer botNextPosition) throws EmptyCollectionException {
        Bot botToReturn = null;
        AlgorithmEnum algorithmToUse = null;
        LinkedQueue<Bot> savedBots = new LinkedQueue<Bot>();
        while (!currentPlayer.getBots().isEmpty()) {
            botToReturn = currentPlayer.getBots().dequeue();
            savedBots.enqueue(botToReturn);

            if (botToReturn.nextPosition().equals(botNextPosition)) {
                algorithmToUse = botToReturn.getAlgorithmInUse().getAlgorithm();
                break;
            }
        }

        if (null != algorithmToUse) {
            switch (algorithmToUse) {
                case DJIKSTRA:
                    botToReturn.getAlgorithmInUse().setPathAsDisjkstra(map, botNextPosition,
                            currentPlayer.getBasePosition());
                    break;
                case RANDOM:
                    botToReturn.getAlgorithmInUse().setPathAsRandom(map, botNextPosition);
                    break;
                case MCP:
                    botToReturn.getAlgorithmInUse().setPathAsMCP(map, botNextPosition, currentPlayer.getBasePosition());
                    break;
                default:
                    break;
            }
        }

        while (!savedBots.isEmpty()) {
            currentPlayer.getBots().enqueue(savedBots.dequeue());
        }

    }

    /**
     * Devolve a bandeira de volta á base se na próxima posição do bot que
     * capturou a bandeira encontrar um bot adversário.
     *
     * @param currentPlayer Jogador atual.
     * @throws EmptyCollectionException Lançada se houver uma coleção vazia.
     */
    private void returnFlagToBase(Player currentPlayer) throws EmptyCollectionException {
        currentPlayer.setFlagPosition(currentPlayer.getBasePosition());
    }

    /**
     * Devolve a bandeira de volta á base se na próxima posição do bot que
     * capturou a bandeira encontrar um bot adversário que também capturou a
     * bandeira adversária.
     *
     * @param currentPlayer Jogador atual.
     * @throws EmptyCollectionException Lançada se houver uma coleção vazia.
     */
    private void returnFlagsToBases(Player currentPlayer) throws EmptyCollectionException {
        Player opponentPlayer = getOpponentPlayer(currentPlayer);
        currentPlayer.setFlagPosition(currentPlayer.getBasePosition());
        opponentPlayer.setFlagPosition(opponentPlayer.getBasePosition());
    }

    /**
     * Devolve o jogador adversário.
     *
     * @param currentPlayer Jogador atual.
     * @return Jogador adversário.
     * @throws EmptyCollectionException Lançada se houver uma coleção vazia.
     */
    private Player getOpponentPlayer(Player currentPlayer) throws EmptyCollectionException {
        Iterator<Player> playerIterator = players.iterator();

        while (playerIterator.hasNext()) {
            Player player = playerIterator.next();
            if (player != currentPlayer) {
                return player;
            }
        }

        throw new EmptyCollectionException("Não foi possível encontrar o jogador adversário.");
    }

    /**
     * Verifica se existe um bot adversário na próxima posição do bot atual,
     * devolvendo-o caso exista.
     *
     * @param currentPlayer Jogador atual
     * @param nextPosition  Próxima posição do bot.
     * @return Bot adversário.
     * @throws EmptyCollectionException Lançada se houver uma coleção vazia.
     */
    private Bot getOpponentBotInNextPosition(Player currentPlayer, Integer nextPosition)
            throws EmptyCollectionException {
        Player opponentPlayer = getOpponentPlayer(currentPlayer);
        LinkedQueue<Bot> savedBots = new LinkedQueue<Bot>();
        Bot b = null;
        while (!opponentPlayer.getBots().isEmpty()) {
            Bot bot = opponentPlayer.getBots().dequeue();
            savedBots.enqueue(bot);
            if (bot.getLocation() == nextPosition) {
                opponentPlayer.getBots().enqueue(bot);
                b = bot;
            }
        }

        while (!savedBots.isEmpty()) {
            opponentPlayer.getBots().enqueue(savedBots.dequeue());
        }
        if (b == null) {
            throw new EmptyCollectionException("Bot do jogador adversário não encontrado na posição especificada.");
        }

        return b;
    }

    /**
     * Verifica o vencedor do jogo e devolve o seu ID.
     *
     * @return ID do jogador vencedor ou 0 se não se verificar vitória.
     * @throws EmptyCollectionException Lançada se houver uma coleção vazia.
     */
    public int checkVictory() throws EmptyCollectionException {
        LinkedQueue<Player> savedPlayers = new LinkedQueue<Player>();
        boolean opponentFlagCaptured1 = false;
        Bot bot1 = null;
        int base1 = players.first().getBasePosition();
        Player player1 = players.first();
        LinkedQueue<Bot> savedBots1 = new LinkedQueue<Bot>();

        while (!player1.getBots().isEmpty()) {
            Bot bot = player1.getBots().dequeue();
            savedBots1.enqueue(bot);
            if (bot.getCapturedState()) {
                bot1 = bot;
                opponentFlagCaptured1 = true;
                break;
            }
        }

        while (!savedBots1.isEmpty()) {
            player1.getBots().enqueue(savedBots1.dequeue());
        }

        savedPlayers.enqueue(players.dequeue());

        boolean opponentFlagCaptured2 = false;
        Bot bot2 = null;
        int base2 = players.first().getBasePosition();
        Player player2 = players.first();
        LinkedQueue<Bot> savedBots2 = new LinkedQueue<Bot>();

        while (!player2.getBots().isEmpty()) {
            Bot bot = player2.getBots().dequeue();
            savedBots2.enqueue(bot);
            if (bot.getCapturedState()) {
                bot2 = bot;
                opponentFlagCaptured2 = true;
                break;
            }
        }

        while (!savedBots2.isEmpty()) {
            player2.getBots().enqueue(savedBots2.dequeue());
        }

        savedPlayers.enqueue(players.dequeue());
        this.players = savedPlayers;

        if (bot1 != null) {
            if (bot1.nextPosition() == base1 && opponentFlagCaptured1) {
                return player1.getId();
            }
        }

        if (bot2 != null) {
            if (bot2.nextPosition() == base2 && opponentFlagCaptured2) {
                return player2.getId();
            }
        }

        return 0;
    }
}
