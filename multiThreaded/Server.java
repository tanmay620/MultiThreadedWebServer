import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class Server {

    public Consumer<Socket> getConsumer(){
        // return new Consumer<Socket>() {
        //     @Override
        //     public void accept(Socket cliSocket){
        //         try {
        //             PrintWriter toClient =new PrintWriter(clientSocket.getOutputStream());
        //             toClient.println("hello from server");
        //             toClient.close();
        //             clientSocket.close();
        //         } catch (Exception e) {
        //             e.printStackTrace();
        //         }
        //     }
        // };
        // we can use anyone 
        return (clientSocket)->{
            try {
                PrintWriter toClient =new PrintWriter(clientSocket.getOutputStream());
                toClient.println("hello from server");
                //BufferedReader fromClient=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                //String line=fromClient.readLine();
                //System.out.println("message from client"+fromClient.readLine());
                toClient.close();
                //fromClient.close();
                clientSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
    public static void main(String[] args){
    int port=8080;
    Server server=new Server();
    try{
        ServerSocket socket=new ServerSocket(port);
        socket.setSoTimeout(10000);
        System.out.println("server is listening on port"+port);
        while(true){
            Socket acceptedSocket=socket.accept();
            Thread thread =new Thread(()->server.getConsumer().accept(acceptedSocket));
            thread.start();
        }
    }catch (Exception e) {
        e.printStackTrace();
    }
}
}


