package Input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class Input {

    /**
     * Este método permite que o utilizador faça input de um inteiro na consola,
     * fazendo catch das exceções possíveis
     *
     * @return inteiro inserido pelo utilizador, no caso de haver alguma
     *         exceção: 0
     */
    public static int inputInt() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        int opt;
        try {
            str = br.readLine();
            opt = Integer.parseInt(str);
        } catch (IOException ex) {
            opt = 0;
            System.out.println("Erro de input.");
        } catch (NumberFormatException ex) {
            opt = 0;
            System.out.println("Valor inválido.");
        }
        return opt;
    }
}
