package CTFEntities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveData {
     /**
     * Exporta uma instância do tipo Map para um ficheiro.
     *
     * @param map A instância de Map a ser exportado.
     * @throws IOException Se ocorrer um erro durante a operação de escrita no
     * ficheiro.
     */
    public static void exportMap(Map map) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("ExportedMap.txt"));

        out.writeObject(map);
        out.close();
    }

    /**
     * Importa uma instância do tipo Map de um ficheiro.
     *
     * @return A instância de Map importada.
     * @throws IOException Se ocorrer um erro durante a operação de leitura do
     * ficheiro.
     * @throws ClassNotFoundException Se a classe do objeto lido do ficheiro não
     * for encontrada.
     */
    public static Map importMap() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("ExportedMap.txt"));
        Map map = (Map) in.readObject();

        return map;
    }
}
