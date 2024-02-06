package Interfaces;

import CTFEntities.AlgorithmEnum;
import CTFEntities.Map;
import Collections.EmptyCollectionException;

public interface AlgorithmInterface {

    /**
     * Define o caminho usando o algoritmo de Dijkstra entre dois vértices no
     * mapa.
     *
     * @param map         O mapa.
     * @param startVertex O vértice de início.
     * @param endVertex   O vértice de destino.
     */
    public void setPathAsDisjkstra(Map map, int startVertex, int endVertex);

    /**
     * Define o caminho como sendo gerado aleatoriamente usando o método
     * getRandomPath do mapa a partir de um vértice inicial.
     *
     * @param map         O mapa.
     * @param startVertex O vértice de início para a geração do caminho
     *                    aleatório.
     */
    public void setPathAsRandom(Map map, int startVertex);

    /**
     * Define o caminho de custo mínimo entre dois vértices e armazena-o numa
     * queue.
     *
     * @param startVertex Vértice de início.
     * @param endVertex   Vértice de destino.
     * @param map         O mapa a ser usado.
     */
    public void setPathAsMCP(Map map, int startVertex, int endVertex);

    /**
     * Obtém o próximo elemento na fila.
     *
     * @return O próximo elemento na fila.
     * @throws EmptyCollectionException Se a fila estiver vazia.
     */
    public Integer getNext() throws EmptyCollectionException;

    /**
     * Remove o próximo elemento na fila.
     *
     * @throws EmptyCollectionException Se a fila estiver vazia.
     */
    public Integer dequeueNext() throws EmptyCollectionException;

    /**
     * Devolve a enumeração do algoritmo usado na movimentação
     *
     * @return Enumeração do algoritmo usado.
     * @throws EmptyCollectionException Lançada se houver uma coleção vazia.
     */
    public AlgorithmEnum getAlgorithm() throws EmptyCollectionException;
}
