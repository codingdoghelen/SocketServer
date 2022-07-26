import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

    public static void main(String[] args) throws IOException {

        // Start a server socket
        ServerSocket serverSocket = new ServerSocket(1234);
        // Accept the client socket
        Socket socket= serverSocket.accept();
        System.out.println("Connected to client successfully!");

        // An OutputStreamWriter is a bridge from character streams to byte streams
        // Change to byte
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
        // An InputStreamReader is a bridge from byte streams to character streams
        // Change to human char
        InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());

        //  simplifies reading text from a character input stream
        // From weird stream to real char
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        // Writes text to a character-output stream
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

        while (true){
            // Keep looping to readLine
            String messageFromClient = bufferedReader.readLine();
            System.out.println("Client: " + messageFromClient);

            bufferedWriter.write("What are you waiting for?");
            bufferedWriter.newLine();
            bufferedWriter.flush();

            if (messageFromClient.equals("bye")){
                break;
            }
        }
        // After all, close all the things
        socket.close();
        inputStreamReader.close();
        outputStreamWriter.close();
        bufferedReader.close();
        bufferedWriter.close();
    }
}
