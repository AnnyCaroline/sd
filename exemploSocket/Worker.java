/**
 * Worker.java - cada conex�o vai ser trabata por um worker
*/

import java.io.*;
import java.net.*;

public class Worker extends Thread
{
   private Socket sock;

   public Worker(Socket s) { // recebe o socket ativo no construtor
     sock = s;
   }

   public void run() {
      
      System.out.println("Worker - nova thread iniciando ...");

      try {

         // obtem os fluxos que fazem parte do Socket TCP
         // e "encapsula" os mesmos em fluxos de objeto
         ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
         ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());

         // Agora � f�cil: le o objeto, calsse Object, do fluxo de entrada 
         // ... e faz o casting para a classe Struct. Depois exibe o conte�do.
         // esse � o "processamento da requisicao"
         Struct str = (Struct) in.readObject ();
         System.out.println ("Recebemos uma mensagem ...");
         System.out.println (str);

         // Agora, a resposta. Monta uma string e simplesmente joga o objeto
         // no fluxo de sa�da de objetos ... vai direto para o "outro lado"
         // ... ou seja, para o cliente, que est� conectado
         out.writeObject("Recebido Ok!" + new java.util.Date().toString());

         // fecha o socket e acabou o servi�o
         // se precisar de muitas intera��es, tem que fazer um loop...
         sock.close();
      }
      catch (Exception e) {
         System.out.println(e);
      }
   }
}

