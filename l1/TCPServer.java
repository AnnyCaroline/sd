import java.io.*;
import java.net.*;

public class TCPServer {
   public static final String ANSI_BLACK = "\u001B[30m";
   public static final String ANSI_RED = "\u001B[31m";
   public static final String ANSI_GREEN = "\u001B[32m";
   public static final String ANSI_YELLOW = "\u001B[33m";
   public static final String ANSI_BLUE = "\u001B[34m";
   public static final String ANSI_PURPLE = "\u001B[35m";
   public static final String ANSI_CYAN = "\u001B[36m";
   public static final String ANSI_WHITE = "\u001B[37m";   

   // ToDo: tratar excecoes
   public static void main(String args[]) throws Exception {
      String[] colors = {ANSI_YELLOW, ANSI_GREEN, ANSI_RED, ANSI_BLUE, ANSI_PURPLE, ANSI_CYAN, ANSI_WHITE, ANSI_BLACK};

      int port = Integer.parseInt(args[0]); //args[0]: porta
      int iterations = Integer.parseInt(args[1]); //args[1]: numero de iteracoes

      int clients;
      try{
         clients = Integer.parseInt(args[2]);
      }catch(Exception e){
         clients = 2;
      }

      // criando o socket passivo, para receber conexoes
      // e jah coloca o mesmo para "listen" (poderia fazer isso explicitmente)
      ServerSocket ss;
      ss = new ServerSocket(port);
      
      System.out.println("Server is listening ...");

      // loop: aceita conexao...
      //       cria ServerWorker pasasndo o socket ativo
      //       inicia a thread do ServerWorker
      //       volta para aguardar nova conexao
      ServerWorker w[]  = new ServerWorker[clients];

      try{
         for (int i=1; i<=clients; i++){
            Socket as = ss.accept(); //socket ativo eh criado
            System.out.printf("Nova conexao estabelecida. %d conexões restantes.\n", clients-i);
            
            //só para testar com mais de 8 clientes sem me preocupar com a cor
            int colorIndex = 0;
            if (i<=8)
               colorIndex = i-1;
   
            w[i-1] = new ServerWorker(as, iterations, colors[colorIndex]);
            w[i-1].start();
   
            if (i<clients)
               System.out.println("Server aguardando nova conexao"); 
         }
   
         for (int i=0; i<clients; i++){
            w[i].join();
         }
      }catch(Exception e){
         e.printStackTrace();
      }
      
      ss.close();
  }
}

