/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Pedro Guimarães Rosa
 */
public class ClienteConexao implements Runnable {

    int id;
    String ip;
    int PORTA;
    int tamanhoVetor;
    int [] vetor;
    Random gerador = new Random();
    int [] tempo;
    int threadsFinalizadas;

    public ClienteConexao(String ip, int PORTA, int id, int finalizadas) {
        this.id = id;
        this.PORTA = PORTA;
        this.ip = ip;
        this.tamanhoVetor = gerador.nextInt(100000);
        vetor = new int[tamanhoVetor];
        this.threadsFinalizadas = finalizadas;
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket(this.ip, this.PORTA);
            if (socket.isConnected()) {
                System.out.println("CONEXAO COM O SERVIDOR ESTABELECIDA");
                System.out.println("TAMANHO DO VETOR: " + tamanhoVetor);
            }
            new PrintStream(socket.getOutputStream()).println(tamanhoVetor);
            
            for(int i=0;i<tamanhoVetor;i++){
                new PrintStream(socket.getOutputStream()).println(gerador.nextInt(1000000));
            }
            Scanner mensagem = new Scanner(socket.getInputStream());
            for(int i=0;i<tamanhoVetor;i++){
                vetor[i]= mensagem.nextInt();
            }
            this.threadsFinalizadas+=1;
        } catch (IOException ex) {
            // do nothing
        }
    }
}

