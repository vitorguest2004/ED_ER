package Interfaces;

import Collections.LinkedQueue;

public interface MapInterface {

    /**
     * Verifica se um índice é válido no contexto do mapa.
     *
     * @param index O índice a ser verificado.
     * @return true se o índice for válido, false caso contrário.
     */
    public boolean indexIsValid(int index);

    /**
     * Define as arestas do mapa com base no número de localizações e densidade
     * específicos.
     *
     * @param numLoc  O número de localizações no mapa.
     * @param density A densidade de arestas no mapa.
     */
    public void createEdges(int numLoc, int density);

    /**
     * Verifica se uma aresta existe entre dois vértices específicos no mapa.
     *
     * @param vertex1 O primeiro vértice da aresta.
     * @param vertex2 O segundo vértice da aresta.
     * @return true se a aresta existe, false caso contrário.
     */
    public boolean edgeExists(int vertex1, int vertex2);

    /**
     * Devolve uma representação genérica em string da matriz de adjacência do mapa.
     *
     * @return Uma representação em string da matriz de adjacência.
     */
    public String adjacenciesPrint();

    /**
     * Retorna um caminho aleatório gerado a partir de um vértice inicial.
     *
     * @param startVertex O vértice de início para a geração do caminho
     *                    aleatório.
     * @return Uma fila contendo o caminho aleatório gerado.
     */
    public LinkedQueue<Integer> getRandomPath(int startVertex);
}
