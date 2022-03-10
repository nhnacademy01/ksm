import java.util.Scanner;

public class Accumulator {
    public static void main(String[] args) {
        Accumulator accumulator = new Accumulator();
        accumulator.cal();
    }

    public void cal() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("어떤 연산을 하실건가요? (+,-,*,/)");
        String input = scanner.next();
        calculation(input.toCharArray());
    }

    public void calculation(char[] op) {
        Scanner scanner = new Scanner(System.in);
        int result = 0;

        System.out.println("피연산자 2수를 입력하세요.");
        int x = scanner.nextInt();
        int y = scanner.nextInt();

        result = calResult(x, op, y);

        accumulation(result);
    }

    public void accumulation(int firstR) {
        int r = firstR;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("어떤 연산을 계속 하실건가요? (+,-,*,/)");
            String op = scanner.next();
            System.out.println("수를 입력하세요.");
            int z = scanner.nextInt();
            r = calResult(r, op.toCharArray(), z);
        } while (true);
    }

    public int calResult(int a, char[] op, int b) {
        int result = 0;
        switch (op[0]) {
            case '+':
                result = a + b;
                break;
            case '-':
                result = a - b;
                break;
            case '*':
                result = a * b;
                break;
            case '/':
                result = a / b;
                break;
            default:
                System.out.println("연산자 입력이 잘못됨.");
        }

        System.out.printf("%d %c %d = %d 입니다.%n", a, op[0], b, result);
        return result;
    }
}
