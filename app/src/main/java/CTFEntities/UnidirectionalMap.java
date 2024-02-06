package CTFEntities;

import java.util.Random;

public class UnidirectionalMap extends Map {
    /**
     * Cria um novo mapa unidirecional com o número de localizações
     * e densidade de arestas específicos.
     *
     * @param numLoc O número de localizações no mapa.
     * @param density A densidade de arestas no mapa.
     */
    public UnidirectionalMap(int numLoc, int density) {
        super(numLoc, density);
    }

    /**
     * Adiciona uma aresta entre dois vértices com um peso
     * específico.
     *
     * @param vertex1 O primeiro vértice da aresta.
     * @param vertex2 O segundo vértice da aresta.
     * @param weight  O peso da aresta.
     */
    @Override
    public void addEdge(Integer vertex1, Integer vertex2, double weight) {
        this.addUniEdge(vertex1 - 1, vertex2 - 1, weight);
    }

    /**
     * Adiciona uma aresta unidirecional entre dois índices com um peso
     * específico.
     *
     * @param index1 O índice do primeiro vértice da aresta.
     * @param index2 O índice do segundo vértice da aresta.
     * @param weight O peso da aresta.
     */
    private void addUniEdge(int index1, int index2, double weight) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = (int) weight;
        }
    }

    /**
     * Define as arestas do mapa unidirecional com base no número de
     * localizações e densidade específicos.
     *
     * @param numLoc  O número de localizações no mapa.
     * @param density A densidade de arestas no mapa.
     */
    @Override
    protected void createEdges(int numLoc, int density) {
        Random random = new Random();
        int vert1;
        int vert2;
        int numArestas = (int) ((numLoc * (numLoc - 1)) * density / 100);

        for (int i = 0; i < numArestas; i++) {
            do {
                vert1 = random.nextInt(1, numLoc + 1);
                do {
                    vert2 = random.nextInt(1, numLoc + 1);
                } while (vert2 == vert1);
            } while (edgeExists(vert1, vert2));

            addEdge(vert1, vert2, random.nextInt(1, 16));
        }
    }

    /**
     * Remove a aresta entre dois vértices específicos no mapa unidirecional.
     *
     * @param vertex1 O primeiro vértice da aresta a ser removida.
     * @param vertex2 O segundo vértice da aresta a ser removida.
     */
    @Override
    public void removeEdge(Integer vertex1, Integer vertex2) {
        this.adjMatrix[vertex1 - 1][vertex2 - 1] = (int) INFINITY;
    }
}
