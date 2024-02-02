package CTFEntities;
import java.io.Serializable;

public class Map extends Network<Integer> implements Serializable{
    protected static final double INFINITY = Double.POSITIVE_INFINITY;

    /**
     * Cria um novo mapa com o número de localizações especificado e densidade
     * de arestas.
     *
     * @param numLoc O número de localizações no mapa.
     * @param density A densidade de arestas no mapa.
     */
    public Map(int numLoc, int density) {
        super();
        this.defineVertex(numLoc);
        this.defineEdges(numLoc, density);
    }

    /**
     * Adiciona vértices ao mapa com base no número especificado de
     * localizações.
     *
     * @param numLoc O número de localizações no mapa.
     */
    private void defineVertex(int numLoc) {
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
     * especificados.
     *
     * @param numLoc O número de localizações no mapa.
     * @param density A densidade de arestas no mapa.
     */
    protected void defineEdges(int numLoc, int density) {
        Random random = new Random();
        int vert1;
        int vert2;
        int numArestas = 0;
        numArestas = (int) (((numLoc * (numLoc - 1)) / 2) * density / 100);

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
     * Retorna uma representação em string da matriz de adjacência do mapa.
     *
     * @return Uma representação em string da matriz de adjacência.
     */
    public String adjPrint1() {
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
                    s.append("  Inf");
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
     * aleatório.
     * @return Uma fila contendo o caminho aleatório gerado.
     */
    protected LinkedQueue<Integer> getRandomPath(int startVertex) {
        ArrayUnorderedList<Integer> visited = new ArrayUnorderedList<Integer>();
        Random random = new Random();
        int currentVertex = startVertex;
        int nextVertex = -1;
        int noPlay = 0;
        int tamanho = 0;
        ArrayUnorderedList<Integer> listvertex;
        LinkedQueue<Integer> path = new LinkedQueue<Integer>();
        do {
            listvertex = this.checkSurroundings(currentVertex);
            tamanho = random.nextInt(listvertex.size() - 1) + 1;

            for (int i = 0; i < tamanho; i++) {
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
     * Retorna uma lista de vértices circundantes a um vértice específico.
     *
     * @param start O vértice para o qual verificar os vértices circundantes.
     * @return Uma lista de vértices circundantes.
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
