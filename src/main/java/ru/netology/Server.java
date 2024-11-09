package ru.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final Integer PORT = 8080;

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Сервер стартовал");
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


                ) {
                    System.out.printf("New connection accepted Port: %d. Write your name?\n",
                            clientSocket.getPort());
                    final String name = bufferedReader.readLine();
                    System.out.println(name);


                    printWriter.println(String.format("Hi %s, your port is %d. Are you child? (yes/no)",
                            name, clientSocket.getPort()));
                    final String answer = bufferedReader.readLine();
                    System.out.println(answer);
                    if (answer.equals("yes")) {
                        printWriter.println("Welcome to the kids area, " + name + "! Let's play!");
                    } else if (answer.equals("no")) {
                        printWriter.println("Welcome to the adult zone, " + name + "! Have a good rest, or a good working day!");
                    } else {
                        printWriter.println("The answer is wrong.");
                    }
                    final String bye = bufferedReader.readLine();
                    System.out.println(bye);
                    printWriter.println(bye);
                }


            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}