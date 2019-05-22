/**
 * Worker.java - cada conexao vai ser trabata por um worker
*/

import java.io.*;
import java.net.*;

public class Worker extends Thread
{
   private static final String ANSI_RESET  = "\u001B[0m";

   private Socket socket;
   private int iterations;
   private String color;

   public Worker(Socket socket, int iterations, String color) { // recebe o socket ativo no construtor
     this.socket = socket;
     this.iterations = iterations;
     this.color = color;
   }

   public void run() {
      
      System.out.println("   " + color + "Worker - nova thread iniciando ..." + ANSI_RESET);

      try {
         // obtem os fluxos que fazem parte do Socket TCP
         // e "encapsula" os mesmos em fluxos de objeto

         ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
         ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

         for (int i=0; i<iterations; i++){
            // Agora eh facil: le o objeto, calsse Object, do fluxo de entrada 
            // ... e faz o casting para a classe Struct. Depois exibe o conteudo.
            // esse eh o "processamento da requisicao"
            Struct str = (Struct) in.readObject ();
            System.out.println("");
            System.out.println("   " + color + "Mensagem recebida de " + socket.getRemoteSocketAddress().toString() + ANSI_RESET);
            System.out.println("   " + color + str + ANSI_RESET);

            // Agora, a resposta. Monta uma string e simplesmente joga o objeto
            // no fluxo de saida de objetos ... vai direto para o "outro lado"
            // ... ou seja, para o cliente, que esta conectado
            //out.writeObject("Recebido Ok!" + new java.util.Date().toString());
            //ToDo: Substituir por um echo
            out.writeObject(str);
         }

         // fecha o socket e acabou o servico
         // se precisar de muitas interacoes, tem que fazer um loop...
         socket.close();
      }
      catch (Exception e) {
         System.out.println("   " + color + e + ANSI_RESET);
      }
   }
}

