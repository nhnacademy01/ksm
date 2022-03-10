import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe();
        int[] userXY;
        int[] computerXY;
        char[][] board = new char[3][3];
        boolean finish = true;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = ' ';
            }
        }

        do {
            userXY = ticTacToe.userTurn();
            while (!ticTacToe.overLapCheck(board, userXY)) {
                System.out.println("이미 입력이 되어 있습니다. 다시 입력하세요");
                userXY = ticTacToe.userTurn();
            }
            board = ticTacToe.drawGame(board, userXY, 'O');

            finish = ticTacToe.winCheck(board);
            if (!finish) break;
            computerXY = ticTacToe.computerTurn();
            while (!ticTacToe.overLapCheck(board, computerXY)) {
                System.out.println("이미 입력이 되어 있습니다. 다시 입력하세요");
                computerXY = ticTacToe.computerTurn();
            }
            board = ticTacToe.drawGame(board, computerXY, 'X');

            finish = ticTacToe.winCheck(board);
        } while (finish);
    }

    public char[][] drawGame(char[][] board, int[] xAndY, char c) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (i == xAndY[0] && j == xAndY[1]) {
                    board[i][j] = c;
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            System.out.println(" " + board[i][0] + " | " + board[i][1] + " | " + board[i][2]);
            if (i != 2) {
                System.out.println("---|---|---");
            }
        }
        return board;
    }

    public int[] userTurn() {
        Scanner scanner = new Scanner(System.in);
        int[] xAndY = new int[2];

        System.out.print("사용자 턴(x y): ");
        xAndY[0] = scanner.nextInt();
        xAndY[1] = scanner.nextInt();
        return xAndY;
    }

    public int[] computerTurn() {
        System.out.print("컴퓨터 턴: ");
        Random random = new Random();
        int[] xAndY = {random.nextInt(3), random.nextInt(3)};
        System.out.println(xAndY[0] + "," + xAndY[1]);
        return xAndY;
    }

    public boolean overLapCheck(char[][] board, int[] xAndY) {
        return board[xAndY[0]][xAndY[1]] == ' ';
    }

    public void winEnd(int x, int y, char[][] board) {
        if (board[x][y] == 'O') {
            System.out.println("사용자가 승리하였습니다!");
        } else if (board[x][y] == 'X') {
            System.out.println("컴퓨터가 승리하였습니다!");
        }
    }

    public boolean winCheck(char[][] board) {
        boolean r = true;
        //가로
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == ' ') {
                continue;
            }
            // \ 승리
            if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
                System.out.println("\\ 승리");
                winEnd(0, 0, board);
                r = false;
                break;
            }
            for (int j = 0; j < board[i].length - 1; j++) {
                if (board[i][j] == board[i][j + 1]) {
                    if (j != 1) continue;
                    System.out.println("가로승리");
                    winEnd(i, j, board);
                    r = false;
                } else break;
            }
        }
        //세로
        for (int i = 0; i < board.length; i++) {
            if (board[0][i] == ' ') {
                continue;
            }
            // / 대각선 승리
            if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
                System.out.println("/ 승리");
                winEnd(0, 2, board);
                r = false;
                break;
            }
            for (int j = 0; j < board[i].length - 1; j++) {
                if (board[j][i] == board[j + 1][i]) {
                    if (j != 1) continue;
                    System.out.println("세로승리");
                    winEnd(j, i, board);
                    r = false;
                } else break;
            }
        }
        return r;
    }
}