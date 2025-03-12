import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{

    public void run() throws IOException{
        int port=8080;
        ServerSocket socket = new ServerSocket(port);
        socket.setSoTimeout(1000);
        while(true){
            try {
                System.out.println("server is running");
                Socket acceptedConnection = socket.accept();
                System.out.println("connection accepted from client"+acceptedConnection.getRemoteSocketAddress());
                PrintWriter toClient=new PrintWriter(acceptedConnection.getOutputStream());
                // byte to normal data bufferReader (requires reader)
                //normal data to bytes printwriter (requires stream)
                BufferedReader fromClient=new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream()));
                toClient.println("hello from the server");//outstream 
                toClient.close();
                fromClient.close();
                acceptedConnection.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
           
        }

    }
    public static void main(String[] args){
        Server server = new Server();
        try{
            server.run();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}