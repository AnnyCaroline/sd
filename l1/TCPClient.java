import java.io.*;
import java.net.*;

public class TCPClient
{
   private static boolean txt = false; //false:csv true:txt

   public static void main(String args[]) throws Exception{

      String host=host = args[0];
      int port=port = Integer.parseInt(args[1]);
      int iterations=iterations = Integer.parseInt(args[2]);

      for (int i=1; i<=2; i++){
         ClientWorker w = new ClientWorker(host, port, iterations, txt);
         w.start();
      }
   }
}

