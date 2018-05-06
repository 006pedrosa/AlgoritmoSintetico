/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pedro Guimarães Rosa
 */
public class GerenciadorDeConexao implements Runnable {

    Socket conexao;

    GerenciadorDeConexao(Socket novaConexao) {
        this.conexao = novaConexao;
    }

    @Override
    public void run() {
        String ipCliente = this.conexao.getInetAddress().getHostAddress();
        System.out.println("Nova conexão com o cliente " + ipCliente);

        Scanner mensagem;

        try {
            mensagem = new Scanner(this.conexao.getInputStream());
            int tamanhoVetor = mensagem.nextInt();
            int[] vetor = new int[tamanhoVetor];

            for (int i = 0; i < tamanhoVetor; i++) {
                vetor[i] = mensagem.nextInt();
            }
            QuickSort.quickSort(vetor, 0, tamanhoVetor-1);

            for (int i = 0; i < tamanhoVetor; i++) {
                new PrintStream(conexao.getOutputStream()).println(vetor[i]);
            }

            System.out.println("FIM DO ENVIO PARA O CLIENTE :" + ipCliente);
        } catch (IOException ex) {
            Logger.getLogger(GerenciadorDeConexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
