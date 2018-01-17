package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;
import java.net.Socket;

public class Client  {
    public class SocketThread extends Thread {

        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
        }


        protected void informAboutAddingNewUser(String userName){
            ConsoleHelper.writeMessage("New user on chat, and his name is ... (John Cena!!!! tu turu tu =) ->" + userName);
        }
        protected void informAboutDeletingNewUser(String userName){
            ConsoleHelper.writeMessage(userName + "Good bye!");
        }
        protected void notifyConnectionStatusChanged(boolean clientConnected){
            synchronized (Client.this) {
                Client.this.clientConnected = clientConnected;
                Client.this.notify();
            }
        }

        protected void clientHandshake() throws IOException, ClassNotFoundException {
            Message message;
            while (!clientConnected) {
                try {
                    message = connection.receive();
                } catch (ClassNotFoundException e) {

                    throw new IOException("Unexpected MessageType");
                }
                if (message.getType() == MessageType.NAME_REQUEST) {
                    connection.send(new Message(MessageType.USER_NAME, getUserName()));
                } else {
                    if (message.getType() == MessageType.NAME_ACCEPTED) {notifyConnectionStatusChanged(true);}
                    else throw new IOException("Unexpected MessageType");}
            }

        }
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            while (true) {
                    Message inputMessage = connection.receive();
                if (inputMessage.getType() == MessageType.TEXT) processIncomingMessage(inputMessage.getData());
                else {
                    if (inputMessage.getType() == MessageType.USER_ADDED) informAboutAddingNewUser(inputMessage.getData());
                    else {
                        if (inputMessage.getType() == MessageType.USER_REMOVED) informAboutDeletingNewUser(inputMessage.getData());
                        else break;
                    }
                }
            }
            throw new IOException("Unexpected MessageType");
        }


        public void run (){
            try {
                Socket socket = new Socket(getServerAddress(),getServerPort());
                connection = new Connection(socket);
                clientHandshake();
                clientMainLoop();
            } catch (IOException | ClassNotFoundException e) {
               notifyConnectionStatusChanged(false);
            }
        }
    }


    protected Connection connection;
    private volatile boolean clientConnected = false;


    public void run (){
        SocketThread socketThread = getSocketThread();
        socketThread.setDaemon(true);
        socketThread.start();
        synchronized (this){
            try {
                wait();
            } catch (InterruptedException e) {
                ConsoleHelper.writeMessage("Something wrong with thread Client");
                System.exit(1);
            }
        }
        if (clientConnected){
            ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду 'exit'.");
        }else ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");
        while (clientConnected){
            String text;
            if ((text = ConsoleHelper.readString()).equalsIgnoreCase("exit"))break;
            if (shouldSendTextFromConsole()) sendTextMessage(text);
        }

    }


    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }

    protected String getServerAddress() {
        ConsoleHelper.writeMessage("Input server ip(string) address");
        return ConsoleHelper.readString();
    }

    protected int getServerPort(){
        ConsoleHelper.writeMessage("Input server port(int)");
        return ConsoleHelper.readInt();
    }

    protected String getUserName(){
        ConsoleHelper.writeMessage("Input your user name");
        return ConsoleHelper.readString();
    }

    protected boolean shouldSendTextFromConsole(){
        return true;
    }
    protected SocketThread getSocketThread(){
        return new SocketThread();
    }

    protected void sendTextMessage(String text){
        try {
            connection.send(new Message(MessageType.TEXT,text));
        } catch (IOException e) {
            ConsoleHelper.writeMessage("error in sendTextMessage");
            clientConnected = false;
        }
    }

}
