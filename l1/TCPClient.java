import java.io.*;
import java.net.*;

public class TCPClient
{
   // ToDo: tratar excecoes
   public static void main(String args[]) throws Exception{
      String host = args[0];
      int port = Integer.parseInt(args[1]);
      String stringParm = args[2];
      int iterations = Integer.parseInt(args[3]); //args[3]: numero de iteracoes
 
      // verificar se apenas passando a String do host o
      // socket consegue resolver o nome o se precisamos de
      // InetAddress IPAddress = InetAddress.getByName(args[0]);
      // e passar o objeto da classe InetAddress... 
      Socket sock = new Socket(host, port); // cria socket
      
      // pega os fluxos out e in do socket TCP e encapusla em
      // ... fluxos de objeto
      ObjectOutputStream out = new ObjectOutputStream (sock.getOutputStream()); 
      ObjectInputStream in = new ObjectInputStream(sock.getInputStream());      

      for (int i=0; i<iterations; i++){
         // cria a estrutura de dados a ser enviada         
         Struct str = new Struct(stringParm); 

         //ToDo: salvar o momento em que foi enviado
         long time = System.currentTimeMillis();
         
         // envia objeto para o servidor (Worker)
         out.writeObject(str); 

         Struct resp = (Struct)in.readObject(); // recebe objeto de resposta

         //ToDo: calcular o RTT
         time = System.currentTimeMillis() -time;
         System.out.println(time + "millisegundos");

         System.out.println ("Resposta = " + resp);
      }

      sock.close(); // fecha socket
   }
}
