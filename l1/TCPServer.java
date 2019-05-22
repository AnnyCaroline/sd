import java.io.*;
import java.net.*;

public class TCPServer {

   private static final int N_CLIENTS = 2;

   private static final String ANSI_YELLOW = "\u001B[33m";
   private static final String ANSI_GREEN  = "\u001B[32m";

   // ToDo: tratar excecoes
   public static void main(String args[]) throws Exception {
      String[] colors = {ANSI_YELLOW, ANSI_GREEN};

      int port = Integer.parseInt(args[0]); //args[0]: porta
      int iterations = Integer.parseInt(args[1]); //args[1]: numero de iteracoes

      // criando o socket passivo, para receber conexoes
      // e jah coloca o mesmo para "listen" (poderia fazer isso explicitmente)
      ServerSocket ss;
      ss = new ServerSocket(port);
      
      System.out.println("Server is listening ...");

      // loop: aceita conexao...
      //       cria Worker pasasndo o socket ativo
      //       inicia a thread do Worker
      //       volta para aguardar nova conexao
      for (int i=1; i<=2; i++){
         Socket as = ss.accept(); //socket ativo eh criado
         System.out.printf("Nova conexao estabelecida. %d conexões restantes.\n", 2-i);
         
         Worker w = new Worker(as, iterations, colors[i-1]);
         w.start();

         if (i<=2)
            System.out.println("Server aguardando nova conexao"); 
      }
  }
}

