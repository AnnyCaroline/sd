/**
 * ClientWorker.java - cada cliente é uma thread
*/

import java.io.*;
import java.net.*;

public class ClientWorker extends Thread
{
    private String host;
    private int port;
    private int iterations;
    public  long rtt[][];

    public ClientWorker(String host, int port, int iterations) {
        this.host = host;
        this.port = port;
        this.iterations = iterations;
        this.rtt  = new long[17][iterations];
    }

    public void run() {
        int kOut=-1;
        try {
            Socket socket = new Socket(host, port);

            DataInputStream in=new DataInputStream(socket.getInputStream());  
            DataOutputStream out=new DataOutputStream(socket.getOutputStream());
            
            for (int k=1, j=0; j<=16; j++, k=k*2){
                kOut=k;
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
                    rtt[j][i] = time;
                    //System.out.print("[k:"+k+"-"+time+"]");
                }

                //System.out.println("["+k+", "+media+", "+desvioPadrao+"]");
                //System.out.print("\n\n\n");
            }

            Thread.sleep(1000);
            socket.close(); // fecha socket
        }
        catch (Exception e) {
            System.out.println("k: "+kOut);
            e.printStackTrace();
        }
   }
}

