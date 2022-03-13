package test.oop.mart;

import java.util.Scanner;

class NhnMartService {
    public static void main(String[] args) {
        NhnMart mart = new NhnMart();
        mart.prepareMart();

        Customer semi = new Customer(20_000, inputBuyListFromShell());
        semi.take(mart.offerTenPercentCoupon());
        semi.take(mart.offerOneThousandWonCoupon());
        semi.bring(mart.provideBasket());
        semi.addToBasket(mart.getFoodStand());

        semi.payTo(mart.getCounter(), mart.noticeTotalPrice(semi.getBasket()));
    }

    private static BuyList inputBuyListFromShell() {
        BuyList buyList = new BuyList();
        System.out.println("Nhn 마트에 오신 것을 환영합니다. 사고 싶은 물건을 골라주세요 ex)계란한판 2");
        System.out.println("(선택을 마치려면 '완료'를 입력하세요)");
        inputForm(buyList);

        return buyList;
    }

    private static void inputForm(BuyList buyList) {
        Scanner scanner = new Scanner(System.in);
        String name;
        int amount;

        do {
            System.out.print("> ");
            name = scanner.next();
            if (name.equals("완료")) break;
            amount = scanner.nextInt();
            buyList.add(new BuyList.Item(name, amount));
        } while (true);
    }
}

public class NhnMart {
    private final FoodStand foodStand = new FoodStand();
    private final Counter counter = new Counter();

    public void prepareMart() {
        fillFoodStand("양파", 1_000, 2);
        fillFoodStand("계란한판", 5_000, 5);
        fillFoodStand("파", 500, 10);
        fillFoodStand("사과", 2_000, 20);
        //foodStand.print();
    }

    private void fillFoodStand(String name, int price, int quantity) {
        for (int i = 0; i < quantity; i++) {
            foodStand.add(new Food(name, price));
        }
    }

    public Coupon offerTenPercentCoupon() {
        return new DiscountTenPercent();
    }

    public Coupon offerOneThousandWonCoupon() {
        return new DiscountOneThousandWon();
    }

    public Basket provideBasket() {
        return new Basket();
    }

    public Price noticeTotalPrice(Basket basket) {
        Price totalPrice = Counter.calculateTotalPrice(basket);
        System.out.printf("총 가격은 %d 원 입니다. %n", totalPrice.value);
        System.out.println("---------------------------");
        return totalPrice;
    }

    public FoodStand getFoodStand() {
        return foodStand;
    }

    public Counter getCounter() {
        return this.counter;
    }
}

