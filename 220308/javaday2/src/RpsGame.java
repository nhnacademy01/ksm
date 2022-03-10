import java.util.Random;
import java.util.Scanner;

public class RpsGame {
    public static void main(String[] args) {
        RpsGame rpsGame = new RpsGame();
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        while (true) {
            System.out.print("가위(1) 바위(2) 보(3): ");
            int userInput = scanner.nextInt();
            int computerInput = random.nextInt(3) + 1;

            rpsGame.inputCheck(userInput, computerInput);

            if ((userInput > computerInput && (userInput - computerInput) == 1)) {
                System.out.println("이겼습니다!");
                break;
            } else if (userInput > computerInput && (userInput - computerInput == 2)) {
                System.out.println("졌습니다!");
                break;
            } else if (userInput == computerInput) {
                System.out.println("비겼습니다. 다시 합니다.");
            } else if (userInput < computerInput && (computerInput - userInput == 2)) {
                System.out.println("이겼습니다!");
                break;
            } else {
                System.out.println("졌습니다!");
                break;
            }
        }
    }

    public void inputCheck(int u, int c) {
        String user = null;
        String computer = null;
        switch (u) {
            case 1:
                user = "가위(1)";
                break;
            case 2:
                user = "바위(2)";
                break;
            case 3:
                user = "보(3)";
                break;
            default:
                System.out.println("잘못 입력");
        }
        switch (c) {
            case 1:
                computer = "가위(1)";
                break;
            case 2:
                computer = "바위(2)";
                break;
            case 3:
                computer = "보(3)";
                break;
            default:
                System.out.println("잘못 입력");
        }
        System.out.printf("당신은 %s입니다.%n", user);
        System.out.printf("컴퓨터는 %s입니다.%n", computer);
    }
}
