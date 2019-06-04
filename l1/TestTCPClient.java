import java.io.*;
import java.net.*;

public class TestTCPClient
{
   public static void main(String args[]) throws Exception{

      String host = args[0];
      int port = Integer.parseInt(args[1]);
      
      Socket socket = new Socket(host, port);

      DataInputStream in=new DataInputStream(socket.getInputStream());  
      DataOutputStream out=new DataOutputStream(socket.getOutputStream());

      int k = 32768;

      byte[] data = new byte[k];
      out.write(data,0,data.length);
      out.flush();
   }
}
