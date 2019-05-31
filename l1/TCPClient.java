import java.io.*;
import java.net.*;

public class TCPClient
{
   public static void main(String args[]) throws Exception{

      String host = args[0];
      int port = Integer.parseInt(args[1]);
      int iterations = Integer.parseInt(args[2]);
      boolean txt = Boolean.parseBoolean(args[3]); //false:csv true:txt

      ClientWorker cw1 = new ClientWorker(host, port, iterations);
      ClientWorker cw2 = new ClientWorker(host, port, iterations);
      cw1.start();
      cw2.start();

      cw1.join();
      cw2.join();

      String l1;
      String lMed;
      String lDvp;

      String lMed2;
      String lLower;
      String lUpper;

      if (txt){
         l1 = String.format("%-12s ", "TCP/2/100");
         for (int k=1, j=0; j<=16; j++, k=k*2){
            l1 = l1 + String.format("%-12d ", k);
         }

         lMed = String.format("%-12s ", "MED");
         lDvp = String.format("%-12s ", "DVP");     
         lMed2 = String.format("%-12s ", "MED");
         lLower = String.format("%-12s ", "LOW");
         lUpper = String.format("%-12s ", "UPP");
      }else{
         l1 = new String();
         for (int k=1, j=0; j<=16; j++, k=k*2){
            l1 = l1 + String.format("%d;", k);
         }

         lMed = new String();
         lDvp = new String();
         lMed2 = new String();
         lLower = new String();
         lUpper = new String();
      }

      for (int k=1, j=0; j<=16; j++, k=k*2){
         // Media
         double media = 0;      
         
         for (int i=0; i<iterations; i++){
            media += cw1.rtt[j][i];
         }
         for (int i=0; i<iterations; i++){
            media += cw2.rtt[j][i];
         }
         media = ((double)media)/(2*(iterations));

         // Media - Print 
         if (txt) 
            lMed = lMed + String.format("%-12.2f ", media);
         else
            lMed = lMed + String.format("%.2f;", media);


         // Desvio Padrao
         double desvioPadrao = 0;
         for (int i=0; i<iterations; i++){
            desvioPadrao += Math.pow(cw1.rtt[j][i] - media,2);
         }
         for (int i=0; i<iterations; i++){
            desvioPadrao += Math.pow(cw2.rtt[j][i] - media,2);
         }
         desvioPadrao = desvioPadrao/(2*(iterations));
         desvioPadrao = Math.sqrt(desvioPadrao);

         // Desvio Padrao - print
         if (txt) 
            lDvp = lDvp + String.format("%-12.2f ", desvioPadrao);
         else
            lDvp = lDvp + String.format("%.2f;", desvioPadrao);

         //Método 2
         //juntando os arrays
         long rtt[] = new long[iterations*2];
         for (int i=0; i<iterations; i++){
            rtt[i] = cw1.rtt[j][i];
         }
         for (int i=0, ii=iterations; i<iterations; i++, ii++){
            rtt[ii] = cw2.rtt[j][i];
         }
         
         //calculando
         ConfidenceInterval ci = new ConfidenceInterval(rtt, 0.98);
         //média
         if (txt) 
            lMed2 = lMed2 + String.format("%-12.2f ", ci.getMean());
         else
            lMed2 = lMed2 + String.format("%.2f;", ci.getMean());
         //erros
         if (txt){
            lLower = lLower + String.format("%-12.2f ", ci.getLower());
            lUpper = lUpper + String.format("%-12.2f ", ci.getUpper());
         }else{
            lLower = lLower + String.format("%.2f;", ci.getLower());
            lUpper = lUpper + String.format("%.2f;", ci.getUpper());
         }
      }     

      System.out.println(l1);
      System.out.println(lMed);
      System.out.println(lDvp);
      System.out.println(lMed2);
      System.out.println(lLower);
      System.out.println(lUpper);
   }
}


