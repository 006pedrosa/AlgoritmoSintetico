/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author Pedro Guimarães Rosa
 */
public class ClienteConexao implements Runnable {

    int id;
    String ip;
    int PORTA;

    public ClienteConexao(String ip, int PORTA, int id) {
        this.id = id;
        this.PORTA = PORTA;
        this.ip = ip;
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket(this.ip, this.PORTA);
            if (socket.isConnected()) {
                System.out.println("CONEXAO COM O SERVIDOR ESTABELECIDA");
            }
            while (socket.isConnected()) {
                
            }
        } catch (IOException ex) {
            // do nothing
        }
    }
}


