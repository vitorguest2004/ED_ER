package CTFEntities;

import java.util.Iterator;

import Collections.EmptyCollectionException;
import Collections.LinkedQueue;

public class Algorithm {
    private LinkedQueue<Integer> path;
    private AlgorithmEnum algorithm;

    public Algorithm() {
        this.path = new LinkedQueue<Integer>();
    }

    /**
     * Define o caminho do algoritmo de Dijkstra entre dois vértices no mapa e
     * armazena-o numa queue.
     *
     * @param map         O mapa a ser usado
     * @param startVertex Vértice de início.
     * @param endVertex   Vértice de destino.
     */
    protected void setPathAsDisjkstra(Map map, int startVertex, int endVertex) {
        Iterator it = map.iteratorShortestPath(startVertex, endVertex);
        it.next();
        while (it.hasNext()) {
            this.path.enqueue((Integer) it.next());
        }
        this.algorithm = AlgorithmEnum.DJIKSTRA;
    }

    /**
     * Define o caminho de custo mínimo entre dois vértices e armazena-o numa
     * queue.
     *
     * @param startVertex Vértice de início.
     * @param endVertex   Vértice de destino.
     * @param map         O mapa a ser usado.
     */
    protected void setPathAsMCP(Map map, int startVertex, int endVertex) {
        Iterator it = map.iteratorMinimumCostPath(startVertex, startVertex);
        it.next();
        while (it.hasNext()) {
            this.path.enqueue((Integer) it.next());
        }
        this.algorithm = AlgorithmEnum.MCP;
    }

    /**
     * Traça um caminho entre um vértice inicial e um vértice aleatório e
     * armazena-o numa queue.
     *
     * @param map         Mapa a ser usado.
     * @param startVertex Vértice de início.
     */
    protected void setPathAsRandom(Map map, int startVertex) {
        this.path = map.getRandomPath(startVertex);
        this.algorithm = AlgorithmEnum.RANDOM;
    }

    protected boolean isEmpty() {
        if (this.path.isEmpty() == true) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Obtém o próximo vértice no caminho armazenado na queue de caminho.
     *
     * @return O próximo vértice no caminho.
     * @throws EmptyCollectionException Lançada se houver uma coleção vazia.
     */
    protected Integer getNext() throws EmptyCollectionException {
        return this.path.first();
    }

    /**
     * Remove e desenfila (dequeue) o próximo vértice do caminho armazenado na
     * fila de caminho.
     *
     * @return Próximo vértice na queue do caminho.
     * @throws EmptyCollectionException Lançada se houver uma coleção vazia.
     */
    protected Integer dequeueNext() throws EmptyCollectionException {
        return this.path.dequeue();
    }

    /**
     * Devolve a enumeração do algoritmo usado na movimentação
     *
     * @return Enumeração do algoritmo usado.
     * @throws EmptyCollectionException Lançada se houver uma coleção vazia.
     */
    protected AlgorithmEnum getAlgorithm() throws EmptyCollectionException {
        return this.algorithm;
    }
}
