import java.io.*;
import java.net.*;

public class TestTCPServer {
   public static final String ANSI_YELLOW = "\u001B[33m";

   public static void main(String args[]) throws Exception {
      int port = Integer.parseInt(args[0]);

      ServerSocket ss;
      ss = new ServerSocket(port);
      
      System.out.println("Server is listening ...");

      ServerWorker w;

      try{
         Socket socket = ss.accept(); //socket ativo eh criado
         System.out.println("Conexao estabelecida");
          
         DataInputStream in=new DataInputStream(socket.getInputStream());  
         DataOutputStream out=new DataOutputStream(socket.getOutputStream());

         byte[] data  = new byte[32768];
         int count = in.read(data);

         byte[] real = new byte[count+1];
         for(int ii=0;ii<count;ii++)
            real[ii]=data[ii];

         System.out.println("Mensagem recebida");
      }finally{
         ss.close();
      }
  }
}

