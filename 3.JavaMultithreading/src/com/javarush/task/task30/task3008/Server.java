package com.javarush.task.task30.task3008;



import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static class Handler extends Thread {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }



        public void run (){
            ConsoleHelper.writeMessage("New connection with" + socket.getRemoteSocketAddress());
            try (Connection connection = new Connection(socket)){

                String userName = serverHandshake(connection);
                connectionMap.put(userName,connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                sendListOfUsers(connection,userName);
                serverMainLoop(connection,userName);
                connectionMap.remove(userName);
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED,userName));
                ConsoleHelper.writeMessage("Connection close right");

            } catch (IOException | ClassNotFoundException e) {
                ConsoleHelper.writeMessage("Error in run");
            }


        }
        private  String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message answer = connection.receive();
                if (answer.getType().equals(MessageType.USER_NAME) && !answer.getData().equals("") && !connectionMap.containsKey(answer.getData())) {
                    connectionMap.put(answer.getData(), connection);
                    connection.send(new Message(MessageType.NAME_ACCEPTED));
                    return answer.getData();
                }
            }
        }
        private void sendListOfUsers(Connection connection, String userName) throws IOException{
            for (Map.Entry<String, Connection> pair : connectionMap.entrySet()) {
                if (!pair.getKey().equals(userName)) {
                    connection.send(new Message(MessageType.USER_ADDED, pair.getKey()));
                }

            }
        }
        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            while (true) {
                Message message = connection.receive();
                if (message != null && message.getType() == MessageType.TEXT) {
                    sendBroadcastMessage(new Message(MessageType.TEXT, userName + ": " + message.getData()));
                } else {
                    ConsoleHelper.writeMessage("Something wrong in serverMainLoop");
                }
            }
        }

    }


    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void sendBroadcastMessage(Message message){
        for (Map.Entry <String, Connection> pair : connectionMap.entrySet()){
            try {
                pair.getValue().send(message);
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Cant send to - " + pair.getKey());
            }
        }
    }




    public static void main(String[] args) {
        
        ConsoleHelper.writeMessage("input server port:");
        try ( ServerSocket serverSocket = new ServerSocket(ConsoleHelper.readInt())){
           ConsoleHelper.writeMessage("Server on!");
           while(true) {
               Socket socket = serverSocket.accept();
               Handler handler = new Handler(socket);
               handler.start();
           }

        } catch (IOException e) {
            ConsoleHelper.writeMessage("Mistake !");
        }
        }
    }

