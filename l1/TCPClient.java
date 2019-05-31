import java.io.*;
import java.net.*;

public class TCPClient
{
   private static boolean txt = false; //false:csv true:txt

   public static void main(String args[]) throws Exception{

      String host=host = args[0];
      int port=port = Integer.parseInt(args[1]);
      int iterations=iterations = Integer.parseInt(args[2]);

      Socket socket = new Socket(host, port);
      
      DataInputStream in=new DataInputStream(socket.getInputStream());  
      DataOutputStream out=new DataOutputStream(socket.getOutputStream());

      String l1;
      String lMed;
      String lDvp;
      if (txt){
         l1 = String.format("%-12s ", "TCP/2/100");
         for (int k=1, j=0; j<=16; j++, k=k*2){
            l1 = l1 + String.format("%-12d ", k);
         }

         lMed = String.format("%-12s ", "MED");
         lDvp = String.format("%-12s ", "DVP");         
      }else{
         l1 = new String();
         for (int k=1, j=0; j<=16; j++, k=k*2){
            l1 = l1 + String.format("%d;", k);
         }

         lMed = new String();
         lDvp = new String();
      }

      for (int k=1, j=0; j<=16; j++, k=k*2){
         long rtt[] = new long[iterations];
         double media = 0;
		   double desvioPadrao = 0;

         //System.out.print("k: "+ k+"-> ");
         for (int i=0; i<iterations; i++){
            byte[] dataSend = new byte[k];

            long time = System.nanoTime();
         
            out.write(dataSend,0,dataSend.length);
            out.flush();

            /////////////////////////

            byte[] data  = new byte[k];
            int count = in.read(data);
            byte[] real = new byte[count+1];
            for(int ii=0;ii<count;ii++)
               real[ii]=data[ii];

            // Calcular o RTT
            time = System.nanoTime()-time;
            rtt[i] = time;
            //System.out.print(time+",");
         }

         // Media - descartando as 10 primeiras medidas
         for (int i=10; i<iterations; i++)
            media += rtt[i];
         media = ((double)media)/(iterations-10);
         if (txt) 
            lMed = lMed + String.format("%-12.2f ", media);
         else
            lMed = lMed + String.format("%.2f;", media);

         // Desvio Padrao - descartando as 10 primeiras medidas
         for (int i=10; i<iterations; i++)
            desvioPadrao += Math.pow(rtt[i] - media,2);
         desvioPadrao = desvioPadrao/(iterations-10);
         desvioPadrao = Math.sqrt(desvioPadrao);
         if (txt) 
            lDvp = lDvp + String.format("%-12.2f ", desvioPadrao);
         else
            lDvp = lDvp + String.format("%.2f;", desvioPadrao);

         //System.out.println("["+k+", "+media+", "+desvioPadrao+"]");
      }

      socket.close(); // fecha socket

      System.out.println(l1);
      System.out.println(lMed);
      System.out.println(lDvp);
   }
}

