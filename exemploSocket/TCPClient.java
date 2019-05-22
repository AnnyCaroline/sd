import java.io.*;
import java.net.*;

public class TCPClient
{
   // observe que estamos nos "livrando" do tratamento das
   // excecoes...

   public static void main(String args[]) throws Exception{
      String host = args[0];
      int port = Integer.parseInt(args[1]);
      int intParm = Integer.parseInt(args[2]);
      String stringParm = args[3];
 
      // verificar se apenas passando a String do host o
      // socket consegue resolver o nome o se precisamos de
      // InetAddress IPAddress = InetAddress.getByName(args[0]);
      // e passar o objeto da classe InetAddress... 
      Socket sock = new Socket(host,port); // cria socket

      // pega os fluxos out e in do socket TCP e encapusla em
      // ... fluxos de objeto
      ObjectOutputStream out = new ObjectOutputStream (sock.getOutputStream()); 
      ObjectInputStream in = new ObjectInputStream(sock.getInputStream());

      // cria a estrutura de dados a ser enviada
      Struct str = new Struct (intParm, stringParm); 

      out.writeObject (str); // envia objeto para o servidor (Worker)

      String resp = (String)in.readObject(); // recebe objeto de resposta

      System.out.println ("Resposta = " + resp);

      sock.close(); // fecha socket
   }
}
