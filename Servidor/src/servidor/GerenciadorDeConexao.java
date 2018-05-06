/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.IOException;
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
        while (this.conexao.isConnected()) {
            
            Scanner mensagem;

            try {
                mensagem = new Scanner(this.conexao.getInputStream());
                if(mensagem.hasNextLine()){
                
                }else{
                    System.out.println("CLIENTE: " + ipCliente + " SE DESCONECTOU DA REDE");
                    break;
                }
            } catch (IOException ex) {
                Logger.getLogger(GerenciadorDeConexao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
