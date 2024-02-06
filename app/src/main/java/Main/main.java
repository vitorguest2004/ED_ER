package Main;

import Collections.EmptyCollectionException;
import Exceptions.InvalidOptionException;
import Exceptions.NoMorePositionsException;
import Input.Input;
import Exceptions.PositionOccupiedException;
import CTFEntities.Map;
import CTFEntities.Game;
import CTFEntities.UnidirectionalMap;
import CTFEntities.SaveData;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class main {
    /**
     * @param args the command line arguments
     * @throws Collections.EmptyCollectionException
     * @throws Exceptions.PositionOccupiedException
     * @throws Exceptions.NoMorePositionsException
     */
    public static void main(String[] args)
            throws EmptyCollectionException, PositionOccupiedException, NoMorePositionsException {
        int option = 0, vertexes, density, base, bot;

        System.out.println("Início do jogo!");
        do {
            System.out.println("1-Pretende configurar o mapa. \n 2-Pretende importar um mapa anterior. \n 3-Sair");
            option = Input.inputInt();
            switch (option) {
                case 1:
                    do {
                        System.out.println("Quantidade de vertices?");
                        vertexes = Input.inputInt();
                    } while (vertexes <= 0);

                    do {
                        System.out.println("Insira a densidade (em percentagem)");
                        density = Input.inputInt();
                    } while (density < 0 || density >= 100);

                    do {
                        System.out.println(
                                "Qual o tipo de mapa pretendido:\n 1-Caminhos bidirecionais não orientados\n  2-Caminhos unidirecionais orientados?");
                        option = Input.inputInt();
                        switch (option) {
                            case 1: // bidirecionais
                                Map map1 = new Map(vertexes, density);
                                System.out.println(map1.adjacenciesPrint());

                                Game game = new Game(map1);
                                game.createPlayer();
                                game.createPlayer();

                                do {
                                    System.out.println("Jogador" + game.getCurrentPlayer().getId()
                                            + " insira o vertice da sua base");
                                    base = Input.inputInt();
                                    if (base <= 0 || base > vertexes) {
                                        System.out.println("Base inválida");
                                    } else {
                                        try {
                                            game.createBaseAndFlag(base);
                                        } catch (PositionOccupiedException ex) {
                                            System.out.println(ex.getMessage());
                                            base = -1;
                                        }
                                    }
                                } while (base <= 0 || base > vertexes);

                                do {
                                    System.out.println("Jogador" + game.getCurrentPlayer().getId()
                                            + " insira o vertice da sua base");
                                    base = Input.inputInt();
                                    if (base <= 0 || base > vertexes) {
                                        System.out.println("Base inválida");
                                    } else {
                                        try {
                                            game.createBaseAndFlag(base);
                                        } catch (PositionOccupiedException ex) {
                                            System.out.println(ex.getMessage());
                                            base = -1;
                                        }
                                    }
                                } while (base <= 0 || base > vertexes);

                                int[] botNumbers = new int[2];

                                for (int playerIndex = 0; playerIndex < 2; playerIndex++) {
                                    do {
                                        System.out.println("Jogador " + (playerIndex + 1) + ": Quantos bots terá?");
                                        botNumbers[playerIndex] = Input.inputInt();
                                        if (botNumbers[playerIndex] <= 0) {
                                            System.out.println("Num de bots inválido!");
                                        }
                                    } while (botNumbers[playerIndex] <= 0);
                                }

                                for (int playerIndex = 0; playerIndex < 2; playerIndex++) {
                                    for (int i = 0; i < botNumbers[playerIndex]; i++) {
                                        System.out.println("Jogador " + (playerIndex + 1));
                                        System.out.println(
                                                "Qual o algoritmo pretendido: (1-Dijkstra 2-MCP(caminho de custo mínimo) 3-Aleatório)");
                                        bot = Input.inputInt();
                                        try {
                                            game.addBot(bot, playerIndex + 1);
                                        } catch (InvalidOptionException ex) {
                                            System.out.println(ex.getMessage());
                                            i--;
                                        }
                                    }
                                }

                                int winner = game.playGame();
                                System.out.println("Parabéns player " + winner + ", venceste");

                                do {
                                    System.out.println("Pretende guardar o mapa utilizado? (1-sim 2-nao");
                                    option = Input.inputInt();
                                    if (option == 1) {
                                        try {
                                            SaveData.exportMap(map1);
                                        } catch (IOException ex) {
                                            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                } while (option != 1 && option != 2);

                                break;

                            case 2: // unidirecionais
                                Map map2 = new UnidirectionalMap(vertexes, density);
                                System.out.println(map2.adjacenciesPrint());

                                Game game1 = new Game(map2);
                                game1.createPlayer();
                                game1.createPlayer();

                                do {
                                    System.out.println("Jogador" + game1.getCurrentPlayer().getId()
                                            + " insira o vertice da sua base");
                                    base = Input.inputInt();
                                    game1.createBaseAndFlag(base);
                                } while (base <= 0 || base > vertexes);

                                do {
                                    System.out.println("Jogador" + game1.getCurrentPlayer().getId()
                                            + " insira o vertice da sua base");
                                    base = Input.inputInt();
                                    game1.createBaseAndFlag(base);
                                } while (base <= 0 || base > vertexes);

                                int[] botNumbers1 = new int[2];

                                for (int playerIndex = 0; playerIndex < 2; playerIndex++) {
                                    do {
                                        System.out.println("Jogador " + (playerIndex + 1) + ": Quantos bots terá?");
                                        botNumbers1[playerIndex] = Input.inputInt();
                                        if (botNumbers1[playerIndex] <= 0) {
                                            System.out.println("Num de bots inválido!");
                                        }
                                    } while (botNumbers1[playerIndex] <= 0);
                                }

                                for (int playerIndex = 0; playerIndex < 2; playerIndex++) {
                                    for (int i = 0; i < botNumbers1[playerIndex]; i++) {
                                        System.out.println("Jogador " + (playerIndex + 1));
                                        System.out.println(
                                                "Qual o algoritmo pretendido: (1-Dijkstra 2-MCP(caminho de custo mínimo) 3-Aleatório)");
                                        bot = Input.inputInt();
                                        try {
                                            game1.addBot(bot, playerIndex + 1);
                                        } catch (InvalidOptionException ex) {
                                            System.out.println(ex.getMessage());
                                            i--;
                                        }
                                    }
                                }

                                int winner1 = game1.playGame();
                                System.out.println("Parabéns player " + winner1 + ", venceste");

                                do {
                                    System.out.println("Pretende guardar o mapa utilizado? (1-sim 2-nao");
                                    option = Input.inputInt();
                                    if (option == 1) {
                                        try {
                                            SaveData.exportMap(map2);
                                        } catch (IOException ex) {
                                            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                } while (option != 1 && option != 2);

                                break;
                            default:
                                System.out.println("Opção inválida");
                        }
                    } while (option != 1 && option != 2);
                    break;

                case 2:
                    // importar mapa
                    Map map = null;
                    try {
                        map = SaveData.importMap();
                        System.out.println(map.adjacenciesPrint());
                    } catch (IOException ex) {
                        System.out.println("Erro ao importar");
                    } catch (ClassNotFoundException ex) {
                        System.out.println("Erro ao importar");
                    }

                    vertexes = map.size();
                    Integer[] vertexesArray = new Integer[vertexes];

                    for (int i = 0; i < vertexes; i++) {
                        vertexesArray[i] = i + 1;
                    }

                    map.setVertices(vertexesArray);

                    Game game2 = new Game(map);
                    game2.createPlayer();
                    game2.createPlayer();

                    do {
                        System.out.println(
                                "Jogador" + game2.getCurrentPlayer().getId() + " insira o vertice da sua base");
                        base = Input.inputInt();
                        if (base <= 0 || base > vertexes) {
                            System.out.println("Base inválida");
                        } else {
                            try {
                                game2.createBaseAndFlag(base);
                            } catch (PositionOccupiedException ex) {
                                System.out.println(ex.getMessage());
                                base = -1;
                            }
                        }
                    } while (base <= 0 || base > vertexes);

                    do {
                        System.out.println(
                                "Jogador" + game2.getCurrentPlayer().getId() + " insira o vertice da sua base");
                        base = Input.inputInt();
                        if (base <= 0 || base > vertexes) {
                            System.out.println("Base inválida");
                        } else {
                            try {
                                game2.createBaseAndFlag(base);
                            } catch (PositionOccupiedException ex) {
                                System.out.println(ex.getMessage());
                                base = -1;
                            }
                        }
                    } while (base <= 0 || base > vertexes);

                    int[] botNumbers1 = new int[2];

                    for (int playerIndex = 0; playerIndex < 2; playerIndex++) {
                        do {
                            System.out.println("Jogador " + (playerIndex + 1) + ": Quantos bots terá?");
                            botNumbers1[playerIndex] = Input.inputInt();
                            if (botNumbers1[playerIndex] <= 0) {
                                System.out.println("Num de bots inválido!");
                            }
                        } while (botNumbers1[playerIndex] <= 0);
                    }

                    for (int playerIndex = 0; playerIndex < 2; playerIndex++) {
                        for (int i = 0; i < botNumbers1[playerIndex]; i++) {
                            System.out.println("Jogador " + (playerIndex + 1));
                            System.out.println(
                                    "Qual o algoritmo pretendido: (1-Dijkstra 2-MCP(caminho de custo mínimo) 3-Aleatório)");
                            bot = Input.inputInt();
                            try {
                                game2.addBot(bot, playerIndex + 1);
                            } catch (InvalidOptionException ex) {
                                System.out.println(ex.getMessage());
                                i--;
                            }
                        }
                    }

                    int winner = game2.playGame();
                    System.out.println("Parabéns player " + winner + ", venceste");

                    do {
                        System.out.println("Pretende guardar o mapa utilizado? (1-sim 2-nao");
                        option = Input.inputInt();
                        if (option == 1) {
                            try {
                                SaveData.exportMap(map);
                            } catch (IOException ex) {
                                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    } while (option != 1 && option != 2);

                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opcao invalida");
                    break;
            }
        } while (option != 1 && option != 2);
    }
}
