package CTFEntities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveData {
     /**
     * Exporta um objeto do tipo Mapa para um arquivo.
     *
     * @param map O objeto Mapa a ser exportado.
     * @throws IOException Se ocorrer um erro durante a operação de escrita no
     * arquivo.
     */
    public static void exportMap(Map map) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("ExportedMap.txt"));

        out.writeObject(map);
        out.close();
    }

    /**
     * Importa um objeto do tipo Mapa de um arquivo.
     *
     * @return O objeto Mapa importado.
     * @throws IOException Se ocorrer um erro durante a operação de leitura do
     * arquivo.
     * @throws ClassNotFoundException Se a classe do objeto lido do arquivo não
     * for encontrada.
     */
    public static Map importMap() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("ExportedMap.txt"));
        Map map = (Map) in.readObject();

        return map;
    }
}
