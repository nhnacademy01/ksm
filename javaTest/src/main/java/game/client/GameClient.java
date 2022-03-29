package game.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

class GameClient {
    private final String id;
    private static final String MESSAGE_TEMPLATE = "message : ";

    public GameClient(String id) {
        this.id = id;
    }

    public static void main(String[] args) {
        if (hasNotArgs(args)) {
            System.out.println("USAGE: java MyGameClient {id}");
            return;
        }
        String id = args[0];
        GameClient client = new GameClient(id);
        client.connect();
    }

    static boolean hasNotArgs(String[] args) {
        return args.length == 0;
    }

    private void connect() {
        try {
            Socket socket = new Socket("127.0.0.1", 8888);
            System.out.println("Connected to server " + "127.0.0.1" + ":" + 8888);

            Thread sender = new Sender(socket, id);
            Thread receiver = new Receiver(socket);

            sender.start();
            receiver.start();
        }  catch (IOException e) {
            System.out.println(MESSAGE_TEMPLATE + e.getMessage());
        }
    }

    private static class Sender extends Thread {
        private final String id;
        private final DataOutputStream out;

        private Sender(Socket socket, String id) throws IOException {
            this.id = id;
            this.out = new DataOutputStream(socket.getOutputStream());
        }

        @Override
        public void run() {
            try {
                initialize();
                sendMessage();
            } catch (IOException e) {
                System.out.println(MESSAGE_TEMPLATE + e.getMessage());
            }
        }

        private void initialize() throws IOException {
            if (isSend()) {
                this.out.writeUTF(id);
            }
        }

        private boolean isSend() {
            return this.out != null;
        }

        private void sendMessage() throws IOException {
            try (Scanner scanner = new Scanner(System.in)) {
                while (isSend()) {
                    this.out.writeUTF(scanner.nextLine());
                }
            }
        }
    }

    private static class Receiver extends Thread {
        private final DataInputStream in;

        private Receiver(Socket socket) throws IOException {
            this.in = new DataInputStream(socket.getInputStream());
        }

        @Override
        public void run() {
            while (isReceivable()) {
                receiveMessage();
            }
        }

        private boolean isReceivable() {
            return this.in != null;
        }

        private void receiveMessage() {
            try {
                System.out.println(in.readUTF());
            } catch (IOException e) {
                System.out.println(MESSAGE_TEMPLATE + e.getMessage());
            }
        }
    }
}
