/**
 * Worker.java - cada conexao vai ser trabata por um worker
*/

import java.io.*;
import java.net.*;

public class ServerWorker extends Thread
{
   private static final String ANSI_RESET  = "\u001B[0m";

   private Socket socket;
   private int iterations;
   private String color;

   public ServerWorker(Socket socket, int iterations, String color) { // recebe o socket ativo no construtor
     this.socket = socket;
     this.iterations = iterations;
     this.color = color;
   }

   public void run() {
      
      System.out.println("   " + color + "Worker - nova thread iniciando ..." + ANSI_RESET);

      try {

         DataInputStream in=new DataInputStream(socket.getInputStream());  
         DataOutputStream out=new DataOutputStream(socket.getOutputStream());

         for (int k=1, j=0; j<=16; j++, k=k*2){

            for (int i=0; i<iterations; i++){
               byte[] data  = new byte[k];
               int count = in.read(data);
               
               byte[] real = new byte[count+1];
               for(int ii=0;ii<count;ii++)
                  real[ii]=data[ii];

               System.out.println("\n   " + color +k+"-"+j+"-"+i+"Mensagem recebida"+ ANSI_RESET);

               out.write(data);
               out.flush();
            }
         }
      }
      catch (Exception e) {
         System.out.println("   " + color + e + ANSI_RESET);
      }
   }
}

