package ru.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try (Socket clientSocket = new Socket("netology.homework", Server.PORT);
             PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))

        ) {

            writer.println("netology.ru");
            final String resp = reader.readLine();
            System.out.println(resp);
            writer.println("yes");
            final String resp2 = reader.readLine();
            System.out.println(resp2);
            writer.println("bye");
            final String resp3 = reader.readLine();
            System.out.println(resp3);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
