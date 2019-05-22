/**
 * Server.java
*/

import java.io.*;
import java.net.*;


public class TCPServer {

   // para deixer mais claro, estamos nos "livrando" das exceções
   public static void main(String args[]) throws Exception {

     int port = Integer.parseInt(args[0]); // porta passada

     // criando o socket passivo, para receber conexções
     // e já coloca o mesmo para "listen" (poderia fazer isso explicitmente)
     ServerSocket ss; ss = new ServerSocket(port);

     System.out.println("Server is listening ...");

     // loop: aceita conexão...
     //       cria Worker pasasndo o socket ativo
     //       inicia a thread do Worker
     //       volta para aguardar nova conexão
     while (true) {
        Socket as = ss.accept(); // socket ativo é criado
        System.out.println("Nova conexão estabelecida ...");
			
	  Worker w = new Worker(as);
	  w.start();

        System.out.println("Server aguardando nova conaxao ...."); 
     }
  }
}

