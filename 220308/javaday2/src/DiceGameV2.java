import java.util.Random;
import java.util.Scanner;

public class DiceGameV2 {
    public static void main(String[] args) {
        DiceGameV2 diceGame = new DiceGameV2();
        diceGame.dice();
    }

    public void dice() {
        Random random = new Random();

        Scanner scanner = new Scanner(System.in);
        System.out.print("참가자 수를 입력해주세요: ");
        int userN = scanner.nextInt();
        System.out.print("주사위 갯수를 지정해주세요: ");
        int diceN = scanner.nextInt();

        int[][] users = new int[userN][diceN];
        int[] sumResults = new int[userN];

        for (int i = 0; i < users.length; i++) {
            System.out.printf("%d 번 사용자의 주사위 결과는 ", i + 1);
            for (int j = 0; j < users[i].length; j++) {
                users[i][j] = random.nextInt(6) + 1;
                System.out.printf("/ %d", users[i][j]);
            }
            System.out.println("/ 입니다.");
        }

        for (int i = 0; i < users.length; i++) {
            for (int j = 0; j < users[i].length; j++) {
                sumResults[i] += users[i][j];
            }
        }
        whoIsWinner(sumResults);
    }

    public void whoIsWinner(int[] sumResults) {
        int maxR = 0;
        for (int i = 0; i < sumResults.length; i++) {
            if (maxR < sumResults[i]) {
                maxR = sumResults[i];
            }
        }
        System.out.println("-------------------");
        int winnerCount = 0;
        for (int i = 0; i < sumResults.length; i++) {
            if (maxR == sumResults[i]) {
                winnerCount++;
                System.out.printf("승자는 %d 번 사용자 입니다. (총 합 : %d)%n", i + 1, maxR);
            }
        }
        System.out.printf(" (승자는 총 %d 명 입니다.)", winnerCount);
    }
}

