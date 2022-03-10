import java.util.Random;

public class CardGame {
    public static void main(String[] args) {
        CardGame cardGame = new CardGame();
        String[][] card = new String[6][14];
        card[0][0] = "하트";
        card[1][0] = "스페이스";
        card[2][0] = "클로버";
        card[3][0] = "다이아몬드";
        card[4][0] = "조커1";
        card[5][0] = "조커2";
        for (int i = 0; i < card.length; i++) {
            for (int j = 1; j < card[i].length; j++) {
                switch (j) {
                    case 1:
                        card[i][j] = "A";
                        break;
                    case 2:
                        card[i][j] = "2";
                        break;
                    case 3:
                        card[i][j] = "3";
                        break;
                    case 4:
                        card[i][j] = "4";
                        break;
                    case 5:
                        card[i][j] = "5";
                        break;
                    case 6:
                        card[i][j] = "6";
                        break;
                    case 7:
                        card[i][j] = "7";
                        break;
                    case 8:
                        card[i][j] = "8";
                        break;
                    case 9:
                        card[i][j] = "9";
                        break;
                    case 10:
                        card[i][j] = "10";
                        break;
                    case 11:
                        card[i][j] = "J";
                        break;
                    case 12:
                        card[i][j] = "Q";
                        break;
                    case 13:
                        card[i][j] = "K";
                        break;
                }
            }
        }
        cardGame.gameStart(card);
    }

    public void gameStart(String[][] card) {
        Random random = new Random();
        boolean returnWhile = true;
        int[][] userA = new int[2][2];
        int[][] userB = new int[2][2];

        while (returnWhile) {
            for (int j = 0; j < userA[0].length; j++) {
                userA[0][j] = random.nextInt(6);
                userA[1][j] = random.nextInt(13) + 1;
            }
            for (int j = 0; j < userB[0].length; j++) {
                userB[0][j] = random.nextInt(6);
                userB[1][j] = random.nextInt(13) + 1;
            }
            //중복 테스트
            for (int j = 0; j < userB[0].length; j++) {
                if (userA[0][j] == userB[0][j] && userA[1][j] == userB[1][j]) {
                    //System.out.println("중복발생");
                } else if (userA[0][0] == userB[0][1] && userA[1][0] == userB[1][1]) {
                    //System.out.println("중복발생");
                } else if (userA[0][1] == userB[0][0] && userA[1][1] == userB[1][0]) {
                    //System.out.println("중복발생");
                } else if (userA[0][0] == 4 && userB[0][0] == 4) {
                    //System.out.println("중복발생");
                } else if (userA[0][0] == 5 && userB[0][0] == 5) {
                    //System.out.println("중복발생");
                } else if (userA[0][1] == 4 && userB[0][1] == 4) {
                    //System.out.println("중복발생");
                } else if (userA[0][1] == 5 && userB[0][1] == 5) {
                    //System.out.println("중복발생");
                } else {
                    returnWhile = false;
                }
            }
        }
        cardBattle(userA, userB, card);
    }

    public void cardBattle(int[][] userA, int[][] userB, String[][] card) {
        Random random = new Random();
        int selectA = random.nextInt(2);
        int selectB = random.nextInt(2);

        System.out.println("userA 가 최종 선택한 1장의 카드 : " + card[userA[0][selectA]][0] + "-" + card[userA[0][selectA]][userA[1][selectA]]);
        System.out.println("userB 가 최종 선택한 1장의 카드 : " + card[userB[0][selectB]][0] + "-" + card[userB[0][selectA]][userB[1][selectA]]);

        if (userA[0][selectA] == 4 && userB[0][selectB] == 5) {
            System.out.println("무승부(조커)");
        } else if (userA[0][selectA] == 5 && userB[0][selectB] == 4) {
            System.out.println("무승부(조커)");
        } else if (userA[0][selectA] == 4 || userA[0][selectA] == 5) {
            System.out.println("userA 승리(조커)");
        } else if (userB[0][selectB] == 4 || userB[0][selectB] == 5) {
            System.out.println("userB 승리(조커)");
        } else if (userA[1][selectA] == userB[1][selectB]) {
            System.out.println("무승부");
        } else if (userA[1][selectA] > userB[1][selectB]) {
            System.out.println("userA 승리");
        } else {
            System.out.println("userB 승리");
        }
    }
}