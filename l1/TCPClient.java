import java.io.*;
import java.net.*;

public class TCPClient
{
   public static void main(String args[]) throws Exception{

      String host = args[0];
      int port = Integer.parseInt(args[1]);
      int iterations = Integer.parseInt(args[2]);
      int clients = 2;
      boolean csv = false;

      try{
         clients = Integer.parseInt(args[3]);

         if (args.length > 4){
            if (args[4].equals("csv"))
               csv = true;
         }
      }catch(Exception e){
         if (args.length > 3){
            if (args[3].equals("csv"))
               csv = true;
         }
      }
      
      ClientWorker cw[]  = new ClientWorker[clients];

      for (int i=0; i<clients; i++){
         cw[i] = new ClientWorker(host, port, iterations);
         cw[i].start();
      }

      for (int i=0; i<clients; i++){
         cw[i].join();
      }

      String l1;
      String lMed;
      String lDvp;

      String lMed2;
      String lLower;
      String lUpper;

      if (csv){
         l1 = new String();
         for (int k=1, j=0; j<=16; j++, k=k*2){
            l1 = l1 + String.format("%d;", k);
         }

         lMed = new String();
         lDvp = new String();
         lMed2 = new String();
         lLower = new String();
         lUpper = new String();
      }else{
         l1 = String.format("%-12s ", "TCP/2/100");
         for (int k=1, j=0; j<=16; j++, k=k*2){
            l1 = l1 + String.format("%-12d ", k);
         }

         lMed = String.format("%-12s ", "MED");
         lDvp = String.format("%-12s ", "DVP");     
         lMed2 = String.format("%-12s ", "MED");
         lLower = String.format("%-12s ", "LOW");
         lUpper = String.format("%-12s ", "UPP");
      }

      for (int k=1, j=0; j<=16; j++, k=k*2){
         // Media
         double media = 0;      
         
         for (int y=0; y<clients; y++){
            for (int i=0; i<iterations; i++){
               media += cw[y].rtt[j][i];
            }
         }
         media = ((double)media)/(2*(iterations));

         // Media - Print 
         if (csv)
            lMed = lMed + String.format("%.2f;", media);
         else
            lMed = lMed + String.format("%-12.2f ", media);


         // Desvio Padrao
         double desvioPadrao = 0;
         for (int y=0; y<clients; y++){
            for (int i=0; i<iterations; i++){
               desvioPadrao += Math.pow(cw[y].rtt[j][i] - media,2);
            }
         }
         desvioPadrao = desvioPadrao/(2*(iterations));
         desvioPadrao = Math.sqrt(desvioPadrao);

         // Desvio Padrao - print
         if (csv) 
            lDvp = lDvp + String.format("%.2f;", desvioPadrao);            
         else
            lDvp = lDvp + String.format("%-12.2f ", desvioPadrao);

         //Método 2
         //juntando os arrays
         long rtt[] = new long[iterations*2];
         for (int i=0; i<iterations; i++){
            rtt[i] = cw[0].rtt[j][i];
         }
         for (int i=0, ii=iterations; i<iterations; i++, ii++){
            rtt[ii] = cw[1].rtt[j][i];
         }
         
         //calculando
         ConfidenceInterval ci = new ConfidenceInterval(rtt, 0.98);
         //média
         if (csv) 
            lMed2 = lMed2 + String.format("%.2f;", ci.getMean());            
         else
            lMed2 = lMed2 + String.format("%-12.2f ", ci.getMean());
         //erros
         if (csv){
            lLower = lLower + String.format("%.2f;", ci.getLower());
            lUpper = lUpper + String.format("%.2f;", ci.getUpper());
         }else{
            lLower = lLower + String.format("%-12.2f ", ci.getLower());
            lUpper = lUpper + String.format("%-12.2f ", ci.getUpper());            
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


