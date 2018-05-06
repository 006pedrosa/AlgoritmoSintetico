/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.util.Scanner;

/**
 *
 * @author Pedro Guimarães Rosa
 */
public class Cliente {
     public static String ip;
     public static int PORTA=3535;
     public static ClienteConexao clienteConnection;
     
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("INSIRA O IP DO SERVIDOR PARA CONEXAO - PARA CONEXAO LOCAL DIGITE localhost ou 127.0.0.1");
        Scanner in = new Scanner(System.in);
        ip = in.nextLine();
        
        System.out.println("DIGITE UMA QUANTIDADE DE CONEXOES PARA SIMULAR");
        int quantidadeThreads = in.nextInt();
        
        for(int i=0;i<quantidadeThreads;i++){
            clienteConnection = new ClienteConexao(ip, PORTA, i);
            new Thread(clienteConnection).start();
        }
          
    }
    
}
