/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Pedro Guimarães Rosa
 */
public class Cliente {

    public static String ip;
    public static int PORTA = 3535;
    public static ClienteConexao clienteConnection;
    public static long tempoResposta;
    public static Thread[] vetorThreads;
    public static FileWriter csv;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("INSIRA O IP DO SERVIDOR PARA CONEXAO - PARA CONEXAO LOCAL DIGITE localhost ou 127.0.0.1");
        Scanner in = new Scanner(System.in);
        ip = in.nextLine();
        csv = new FileWriter("simulacao.csv");

        System.out.println("DIGITE UMA QUANTIDADE MAXIMA DE CONEXOES PARA SIMULAR");
        int quantidadeThreads = in.nextInt();

        vetorThreads = new Thread[quantidadeThreads];

        for (int j = 1; j < quantidadeThreads; j++) {
            int threadsFinalizadas = 0;
            long inicio = System.currentTimeMillis();

            for (int i = 0; i < j; i++) {
                clienteConnection = new ClienteConexao(ip, PORTA, i);
                vetorThreads[i] = new Thread(clienteConnection);
            }

            for (int i = 0; i < j; i++) {
                vetorThreads[i].start();
            }

            for (int i = 0; i < j; i++) {
                vetorThreads[i].join();
            }

            tempoResposta = System.currentTimeMillis() - inicio;
            csv.append(Integer.toString(j));
            csv.append(",");
            csv.append(Long.toString(tempoResposta));
            csv.append("\n");

        }

        csv.flush();
        csv.close();

    }

}
