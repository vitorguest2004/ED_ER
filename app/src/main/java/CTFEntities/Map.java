package CTFEntities;

import java.io.Serializable;
import java.util.Random;

import Collections.ArrayUnorderedList;
import Collections.LinkedQueue;
import Collections.Network;

public class Map extends Network<Integer> implements Serializable {
    protected static final double INFINITY = Double.POSITIVE_INFINITY;

    /**
     * Cria um novo mapa com o número de localizações e densidade
     * de arestas específicos.
     *
     * @param numLoc  O número de localizações no mapa.
     * @param density A densidade de arestas no mapa.
     */
    public Map(int numLoc, int density) {
        super();
        this.createVertex(numLoc);
        this.createEdges(numLoc, density);
    }

    /**
     * Adiciona vértices ao mapa com base no número específico de
     * localizações.
     *
     * @param numLoc O número de localizações no mapa.
     */
    private void createVertex(int numLoc) {
        for (int i = 1; i < numLoc + 1; i++) {
            this.addVertex(i);
        }
    }

    /**
     * Verifica se um índice é válido no contexto do mapa.
     *
     * @param index O índice a ser verificado.
     * @return true se o índice for válido, false caso contrário.
     */
    protected boolean indexIsValid(int index) {
        return (index >= 0 && index < this.numVertices);
    }

    /**
     * Define as arestas do mapa com base no número de localizações e densidade
     * específicos.
     *
     * @param numLoc  O número de localizações no mapa.
     * @param density A densidade de arestas no mapa.
     */
    protected void createEdges(int numLoc, int density) {
        Random random = new Random();
        int vertex1;
        int vertex2;
        int numEdges = 0;
        numEdges = (int) (((numLoc * (numLoc - 1)) / 2) * density / 100);

        for (int i = 0; i < numEdges; i++) {
            do {
                vertex1 = random.nextInt(1, numLoc + 1);
                do {
                    vertex2 = random.nextInt(1, numLoc + 1);
                } while (vertex2 == vertex1);
            } while (edgeExists(vertex1, vertex2));

            addEdge(vertex1, vertex2, random.nextInt(1, 16));
        }
    }

    /**
     * Verifica se uma aresta existe entre dois vértices específicos no mapa.
     *
     * @param vertex1 O primeiro vértice da aresta.
     * @param vertex2 O segundo vértice da aresta.
     * @return true se a aresta existe, false caso contrário.
     */
    protected boolean edgeExists(int vertex1, int vertex2) {
        if (this.adjMatrix[vertex1 - 1][vertex2 - 1] != INFINITY) {
            return true;
        }
        return false;
    }

    /**
     * Devolve uma representação genérica em string da matriz de adjacência do mapa.
     *
     * @return Uma representação em string da matriz de adjacência.
     */
    public String adjacenciesPrint() {
        StringBuilder s = new StringBuilder();

        s.append("   |");
        for (int i = 0; i < this.numVertices; i++) {
            s.append(String.format("%5d", i + 1));//
        }
        s.append("\n");

        s.append("-----");
        for (int i = 0; i < this.numVertices * 6 - 1; i++) {
            s.append("-");
        }
        s.append("\n");

        for (int i = 0; i < this.numVertices; i++) {
            s.append(String.format("%2d |", i + 1));

            for (int j = 0; j < this.numVertices; j++) {
                int weight = (int) adjMatrix[i][j];
                if (weight == Integer.MAX_VALUE) {
                    s.append("  N.A");
                } else {
                    s.append(String.format("%5d", weight));
                }
            }
            s.append("\n");
        }
        return s.toString();
    }

    /**
     * Retorna um caminho aleatório gerado a partir de um vértice inicial.
     *
     * @param startVertex O vértice de início para a geração do caminho
     *                    aleatório.
     * @return Uma fila contendo o caminho aleatório gerado.
     */
    protected LinkedQueue<Integer> getRandomPath(int startVertex) {
        ArrayUnorderedList<Integer> visited = new ArrayUnorderedList<Integer>();
        Random random = new Random();
        int currentVertex = startVertex;
        int nextVertex = -1;
        int noPlay = 0;
        int size = 0;
        ArrayUnorderedList<Integer> listvertex;
        LinkedQueue<Integer> path = new LinkedQueue<Integer>();
        do {
            listvertex = this.checkSurroundings(currentVertex);
            size = random.nextInt(listvertex.size() - 1) + 1;

            for (int i = 0; i < size; i++) {
                nextVertex = (int) listvertex.iterator().next();
            }

            if (!visited.contains(nextVertex)) {
                path.enqueue(nextVertex);
                visited.addToRear(currentVertex);
                currentVertex = nextVertex;
            } else {
                noPlay++;
            }
        } while (listvertex.size() != 0 && noPlay < 4);
        return path;
    }

    /**
     * Devolve uma lista não ordenada de vértices vizinhos a um vértice específico.
     *
     * @param start Vértice usado para verificar.
     * @return Uma lista não ordenada de vértices vizinhos.
     */
    private ArrayUnorderedList<Integer> checkSurroundings(Integer start) {
        ArrayUnorderedList<Integer> list = new ArrayUnorderedList<Integer>();
        for (int i = 0; i < this.numVertices; i++) {
            if (this.adjMatrix[start - 1][i] != INFINITY && this.adjMatrix[start - 1][i] != 0) {
                list.addToFront(i);
            }
        }
        return list;
    }
}
