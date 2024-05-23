import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static Socket clientSocket;
    private static PrintWriter out;
    private static BufferedReader in;

    public void start() throws IOException {
        clientSocket = new Socket("127.0.0.1",777);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        System.out.println("out yes");
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        System.out.println("in yes");
    }

    public static void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

    public static void main(String[] args) {
        Client client = new Client();
        try {
            while (true){
                client.start();
                Scanner scanner = new Scanner(System.in);
                //System.out.println("hello, stop: ");
                String choice = scanner.nextLine();
                out.println(choice);
                String response = in.readLine();
                System.out.println(response);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
