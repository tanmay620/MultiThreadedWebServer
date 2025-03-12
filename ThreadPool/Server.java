import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class Server {

    private final ExecutorService threadPool;

    public Server(int poolsize){
        this.threadPool=Executors.newFixedThreadPool(poolsize);
    }

    public void handleClient(Socket clientSocket){
        try {
            PrintWriter toClient=new PrintWriter(clientSocket.getOutputStream(),true);
            toClient.println("hello from server");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
    int port=8080;
    int poolsize=10;
    Server server=new Server(poolsize);
    try{
        ServerSocket socket=new ServerSocket(port);
        socket.setSoTimeout(10000);
        System.out.println("server is listening on port"+port);
        while(true){
            Socket acceptedSocket=socket.accept();
            server.threadPool.execute(()->server.handleClient(acceptedSocket));
        }
    }catch (Exception e) {
        e.printStackTrace();
    }finally{
        server.threadPool.shutdown();
    }
}
}


