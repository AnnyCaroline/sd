import java.io.*;
import java.net.*;

public class TCPClient
{
    public static void main(String args[]) throws Exception{

        String host="";
        int port=0;
        int iterations=0;

        host = args[0];
        port = Integer.parseInt(args[1]);
        iterations = Integer.parseInt(args[2]);
      
        Socket sock = new Socket(host, port); // cria socket
        
        ObjectOutputStream out = new ObjectOutputStream (sock.getOutputStream()); 
        ObjectInputStream in = new ObjectInputStream(sock.getInputStream());      
         

        for (int i=0; i<iterations; i++){
            // Gera uma string de tamanho k/2
            String str = new String("kjjjjjjjjjjlkkkkkkkkkkkkkkkkkjjjjjjjjjjlkkkkkkkkkkkkkkkkkjjjjjjjjjjlkkkkkkkkkkkkkkkkkjjjjjjjjjjlkkkkkkkkkkkkkkkkkjjjjjjjjjjlkkkkkkkkkkkkkkkkkjjjjjjjjjjlkkkkkkkkkkkkkkkkkjjjjjjjjjjlkkkkkkkkkkkkkkkkkjjjjjjjjjjlkkkkkkkkkkkkkkkkkjjjjjjjjjjlkkkkkkkkkkkkkkkkkjjjjjjjjjjlkkkkkkkkkkkkkkkkkjjjjjjjjjjlkkkkkkkkkkkkkkkkkjjjjjjjjjjlkkkkkkkkkkkkkkkkkjjjjjjjjjjlkkkkkkkkkkkkkkkkkjjjjjjjjjjlkkkkkkkkkkkkkkkkkjjjjjjjjjjlkkkkkkkkkkkkkkkkkjjjjjjjjjjlkkkkkkkkkkkkkkkkkjjjjjjjjjjlkkkkkkkkkkkkkkkkkjjjjjjjjjjlkkkkkkkkkkkkkkkkkjjjjjjjjjjlkkkkkkkkkkkkkkkkkjjjjjjjjjjlkkkkkkkkkkkkkkkkkjjjjjjjjjjlkkkkkkkkkkkkkkkkkjjjjjjjjjjlkkkkkkkkkkkkkkkkkjjjjjjjjjjlkkkkkkkkkkkkkkkkkjjjjjjjjjjlkkkkkkkkkkkkkkkkkjjjjjjjjjjlkkkkkkkkkkkkkkkkkjjjjjjjjjjlkkkkkkkkkkkkkkkkkjjjjjjjjjjlkkkkkkkkkkkkkkkkkjjjjjjjjjjlkkkkkkkkkkkkkkkkkjjjjjjjjjjlkkkkkkkkkkkkkkkkkjjjjjjjjjjlkkkkkkkkkkkkkkkkkjjjjjjjjjjlkkkkkkkkkkkkkkkkkjjjjjjjjjjlkkkkkkkkkkkkkkkkkjjjjjjjjjjlkkkkkkkkkkkkkkkkkjjjjjjjjjjlkkkkkkkkkkkkkkkkkjjjjjjjjjjlkkkkkkkkkkkkkkkkkjjjjjjjjjjkkkkklkkkkkkkkkkkkkkkkkkkkkkkkkkk"); //1024 chars - referente a k=2048
            
            // Salvar o momento em que foi enviado
            long time = System.nanoTime();
            
            // Envia objeto para o servidor (Worker)
            out.writeObject(str); 
            out.flush();

            String resp = (String)in.readObject(); // recebe objeto de resposta

            //print resp
            System.out.println ("Resposta = " + resp);

            // Calcular o RTT
            time = System.nanoTime()-time;
            System.out.print(time+",");
            //add time na lista
            //rtt[i] = time;
         }

         System.out.print("\n\n");

      sock.close(); // fecha socket
   }
}

