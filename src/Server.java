import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server implements Runnable {

    Socket st;
    Server(Socket st)
    {
        this.st = st;
    }

    static Queue<Socket> SockQueue = new ArrayDeque<Socket>();

    public static void main(String args[]) throws Exception
    {
        int client = 0;

        ServerSocket ss = new ServerSocket(7070);
        System.out.println("Server started time: " + new Date() + '\n');

        while (true)
        {
            if (client <=100) {
                Socket sock = ss.accept();
                client++;
                System.out.println(client + "  Client connected: " + "at " + new Date() + '\n');
                new Thread(new Server(sock)).start();
            }
            else
            {
                Socket sock = ss.accept();
                SockQueue.add(sock);
            }

        }
    }

    @Override
    public void run() {
        try
        {
            st.close();
        }
        catch (IOException e)
        {
          System.out.println(e);
        }
    }
}