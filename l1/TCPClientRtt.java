import java.io.*;
import java.net.*;

public class TCPClientRtt
{
   public static void main(String args[]) throws Exception{

        String host = args[0];
        int port = Integer.parseInt(args[1]);
        int iterations = Integer.parseInt(args[2]);

        ClientWorker cw1 = new ClientWorker(host, port, iterations);
        ClientWorker cw2 = new ClientWorker(host, port, iterations);
        cw1.start();
        cw1.join();
        
        for (int k=1, j=0; j<=16; j++, k=k*2){
            System.out.print(k+";");
            for (int i=0; i<iterations; i++){
                System.out.print(cw1.rtt[j][i]+"; ");
            }
            System.out.println("");
        }
   }
}