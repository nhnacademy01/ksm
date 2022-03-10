public class Hw4 {
    public static void main(String[] args) {
        Hw4 hw4 = new Hw4();
        hw4.swap();
    }

    public void swap(){
        int a = 10, b = 20;
        System.out.println("a: " + a + ", b: " + b);
        a = a+b;
        b = a-b;
        a = a-b;
        System.out.println("After swap. a: " + a + ", b: " + b);
    }
}
