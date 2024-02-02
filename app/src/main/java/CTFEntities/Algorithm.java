package CTFEntities;

import Collections.LinkedQueue;

public class Algorithm {
    private LinkedQueue<Integer> path;

    public Algorithm() {
        this.path = new LinkedQueue<Integer>();
    }

    /**
     * Define o caminho do algoritmo de Dijkstra entre dois vértices no mapa e o
     * armazena na fila de caminho.
     *
     * @param map O mapa (grafo) no qual calcular o caminho.
     * @param startVertex O vértice de início do caminho.
     * @param endVertex O vértice de destino do caminho.
     */
    protected void setPathAsDisjkstra(Mapa map, int startVertex, int endVertex) {
        Iterator it = map.iteratorShortestPath(startVertex, endVertex);
        it.next();
        while (it.hasNext()) {
            this.path.enqueue((Integer) it.next());
        }
    }

    /**
     * Define o caminho do algoritmo de prim e o armazena na fila de caminho.
     *
     * @param map O mapa (grafo) no qual calcular o caminho.
     */
    protected void setPathAsPrim(Mapa map) {
        this.path = map.mstNetwork();
    }

    /**
     * Define o caminho como sendo gerado aleatoriamente usando o método
     * getRandomPath do mapa a partir de um vértice inicial.
     *
     * @param map O mapa.
     * @param startVertex O vértice de início para a geração do caminho
     * aleatório.
     */
    protected void setPathAsRandom(Mapa map, int startVertex) {
        this.path = map.getRandomPath(startVertex);
    }

    /**
     * Obtém o próximo vértice no caminho armazenado na fila de caminho.
     *
     * @return O próximo vértice no caminho.
     * @throws EmptyCollectionException Se a fila de caminho estiver vazia.
     */
    protected Integer getNext() throws EmptyCollectionException {
        return this.path.first();
    }

    /**
     * Remove e dequeue o próximo vértice do caminho armazenado na fila de
     * caminho.
     *
     * @throws EmptyCollectionException Se a fila de caminho estiver vazia.
     */
    protected Integer dequeueNext() throws EmptyCollectionException {
        return this.path.dequeue();
    }
}
