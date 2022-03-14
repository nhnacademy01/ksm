package mart;

import java.util.Scanner;

class NhnMartService {
    public static void main(String[] args) {
        NhnMart mart = new NhnMart();
        mart.prepareMart();

        // 일반 고객
        Customer semi = new NormalCustomer(20_000, inputBuyListFromShell(mart));
        semi.take(mart.offerTenPercentCoupon());
        semi.take(mart.offerOneThousandWonCoupon());
        semi.bring(mart.provideBasket());
        semi.addToBasket(mart.getFoodStand());
        semi.payTo(mart.getCounter(), mart.noticeTotalPrice(semi.getBasket()));

        // VIP 고객
        Customer semiVip = new VIPCustomer(30_000, inputBuyListFromShell(mart));
        semiVip.take(mart.offerTenPercentCoupon());
        semiVip.take(mart.offerOneThousandWonCoupon());
        semiVip.take(mart.offerVipCoupon());
        semiVip.bring(mart.provideBasket());
        semiVip.addToBasket(mart.getFoodStand());
        semiVip.payTo(mart.getCounter(), mart.noticeTotalPrice(semiVip.getBasket()));
    }

    private static BuyList inputBuyListFromShell(NhnMart mart) {
        BuyList buyList = new BuyList();
        while (true) {
            System.out.println("Nhn 마트에 오신 것을 환영합니다. 사고 싶은 물건을 골라주세요 ex)계란한판 2");
            System.out.println("(선택을 마치려면 '완료'를 입력하세요)");
            if (inputForm(buyList, mart)) break;
        }

        return buyList;
    }

    private static boolean inputForm(BuyList buyList, NhnMart mart) {
        Scanner scanner = new Scanner(System.in);
        String name;
        int amount;

        do {
            System.out.print("> ");
            name = scanner.next();
            // 입력 종료
            if (name.equals("완료")) return true;
            // 구매목록에 추가하기 전에 식품 매대랑 비교하기
            if (!mart.checkFoodStand(name)) {
                return false;
            }
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
    }

    private void fillFoodStand(String name, int price, int quantity) {
        for (int i = 0; i < quantity; i++) {
            foodStand.add(new Food(name, price));
        }
    }

    public boolean checkFoodStand(String name) {
        for (Food food : foodStand.foods) {
            if (food.getName().equals(name)) return true;
        }
        System.out.println("저희 매장에서 판매하지 않는 식품입니다. 처음부터 다시 입력해 주세요.");
        System.out.println("---------------------------");
        return false;
    }

    public Coupon offerTenPercentCoupon() {
        return new DiscountTenPercent();
    }

    public Coupon offerOneThousandWonCoupon() {
        return new DiscountOneThousandWon();
    }

    public Coupon offerVipCoupon() {
        return new VIPDiscountTwentyPercent();
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
/*
총 가격은 16000 원 입니다.
---------------------------
VIP 전용 상시 20% 할인 쿠폰을 사용하셨습니다.
할인 후 12800 원 입니다.
---------------------------
10% 할인 쿠폰을 사용하셨습니다.
할인 후 11520 원 입니다.
---------------------------
1,000원 할인 쿠폰을 사용하셨습니다.
할인 후 10520 원 입니다.
---------------------------
고객님 결제 후 잔액 : 19480 원

[장바구니목록]
---------------------------
총 가격은 0 원 입니다.
---------------------------
결제 할 금액이 0 로 없습니다. 감사합니다.
 */