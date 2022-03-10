import java.util.Random;

public class Lotto {
    public static void main(String[] args) {
        Lotto lotto = new Lotto();
        lotto.lottoMachine();
    }

    public void lottoMachine() {
        Random random = new Random();

        int i = 5;
        int[] lottoNum = new int[6];

        while (i >= 0) {
            boolean returnWhile = false;
            lottoNum[i] = random.nextInt(45) + 1;
            for (int j = 5; j > i; j--) {
                if (lottoNum[i] == lottoNum[j]) {
                    returnWhile = true;
                    break;
                    //이부분 i-- 로 제어하는거 확인
                }
            }
            if (returnWhile) {
                continue;
            }
            System.out.println(lottoNum[i]);
            i--;
        }
    }
}
