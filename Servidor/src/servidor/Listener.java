/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pedro Guimarães Rosa
 */
public class Listener implements Runnable {

    public int PORTA;
    ServerSocket socketListener;

    Listener(int porta) {
        this.PORTA = porta;
        try {
            this.socketListener = new ServerSocket(this.PORTA);
            System.out.println("SERVER ESCUTANDO NA PORTA: " + this.PORTA);
            new Thread(this).start();
        } catch (IOException ex) {
            Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket conexao = socketListener.accept();
                // QUANDO A CONEXÃO É ESTABELECIDA, ABRE UMA THREAD PARA FICAR ESCUTANDO E TRATANDO TUDO QUE FOR ENVIADO 
                if (conexao.isConnected()) {
                    GerenciadorDeConexao novaConexao = new GerenciadorDeConexao(conexao);
                    new Thread(novaConexao).start();
                }
            } catch (IOException ex) {
                Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
