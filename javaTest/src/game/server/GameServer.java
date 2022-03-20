package game.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ConcurrentHashMap;

public class GameServer {
    private final ConcurrentHashMap<String, DataOutputStream> clientOutMap = new ConcurrentHashMap();
    private final Sender sender = new Sender(clientOutMap);

    public static void main(String[] args) throws IOException {
        GameServer server = new GameServer();
        server.start();
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(8081)) {
            System.out.println(getTime() + " Start server " + serverSocket.getLocalSocketAddress());
            while (true) {
                try {
                    Socket socket = serverSocket.accept(); // serverSocket.accept() : 클라이언트와 연결할 수 있는 socket 생성
                    ClientSession client = new ClientSession(socket);
                    client.start();
                } catch (IOException e) {
                    System.out.println("[클라이언트 접속 실패]");
                    System.out.println("message : " + e.getMessage());
                }
            }
        }
    }

    private String getTime() {
        return LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss"));
    }

    // joinGame() : 사용자가 접속했을때 초기화면
    private void joinGame(ClientSession clientSession) {
        clientOutMap.put(clientSession.id, clientSession.out);
        sender.sendToClient("                 ___====-_  _-====___\n" +
                "           _--^^^#####//      \\\\#####^^^--_\n" +
                "        _-^##########// (    ) \\\\##########^-_\n" +
                "       -############//  |\\^^/|  \\\\############-\n" +
                "     _/############//   (@::@)   \\\\############\\_\n" +
                "    /#############((     \\\\//     ))#############\\\n" +
                "   -###############\\\\    (oo)    //###############-\n" +
                "  -#################\\\\  / VV \\  //#################-\n" +
                " -###################\\\\/      \\//###################-\n" +
                "_#/|##########/\\######(   /\\   )######/\\##########|\\#_\n" +
                "|/ |#/\\#/\\#/\\/  \\#/\\##\\  |  |  /##/\\#/  \\/\\#/\\#/\\#| \\|\n" +
                "`  |/  V  V  `   V  \\#\\| |  | |/#/  V   '  V  V  \\|  '\n" +
                "   `   `  `      `   / | |  | | \\   '      '  '   '\n" +
                "                    (  | |  | |  )\n" +
                "                   __\\ | |  | | /__\n" +
                "                  (vvv(VVV)(VVV)vvv)\n" +
                "\n" +
                "-- 계속 하려면 엔터를 입력해주세요. --", clientSession.out);
        sender.sendToAllExceptOne("[System] 용사 " + clientSession.id + "님이 입장했습니다.", clientSession.out);

        System.out.println(getTime() + " " + clientSession.id + " is joined: " + clientSession.socket.getInetAddress());
        loggingCurrentClientCount();
    }

    // 현재 접속자 수 출력(서버)
    private void loggingCurrentClientCount() {
        System.out.println(getTime() + " Currently " + clientOutMap.size() + " clients are connected.");
    }

    private void leaveGame(ClientSession clientSession) {
        clientOutMap.remove(clientSession.id);
        sender.sendToAll("[System] " + clientSession.id + "님이 접속 종료 하셨습니다.");
        System.out.println(getTime() + " " + clientSession.id + " is leaved: " + clientSession.socket.getInetAddress());
        loggingCurrentClientCount();
    }

    class ClientSession extends Thread {
        private final Socket socket;
        private final DataInputStream in; // 입력
        private final DataOutputStream out; // 출력
        private String id; // 접속 클라이언트 id

        ClientSession(Socket socket) throws IOException {
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
        }

        @Override
        public void run() {
            initialize();
            connect();
        }

        private void initialize() {
            try {
                this.id = in.readUTF();
                joinGame(this);
            } catch (IOException cause) {
                System.out.println("[최초 통신 실패 - id 받기 실패]");
                System.out.println("message : " + cause.getMessage());
            }
        }

        private void connect() {
            if (isConnect()) {
                Game game = new Game(clientOutMap, this);
                game.start();
            }
            disconnect();
        }

        private void disconnect() {
            leaveGame(this);
        }

        private boolean isConnect() {
            return this.in != null;
        }

        public DataInputStream getIn() {
            return in;
        }

        public String getClientId() {
            return id;
        }

        public DataOutputStream getOut() {
            return this.out;
        }
    }
}
