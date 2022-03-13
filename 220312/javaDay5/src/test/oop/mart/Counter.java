package test.oop.mart;

public class Counter {
    public static Price calculateTotalPrice(Basket basket) {
        Price totalPrice = new Price();
        for (Food food : basket.foods) {
            totalPrice.value += food.getPrice();
        }
        return totalPrice;
    }

    public Price discountProcess(Coupon coupon, Price totalPrice) {
        Price discountPrice = coupon.discount(totalPrice);
        // 할인된 금액
        System.out.printf("할인 후 %d 원 입니다. 고맙습니다. %n", discountPrice.value);
        System.out.println("---------------------------");
        return discountPrice;
    }

    public void paymentProcess(Price price, Wallet wallet) {
        wallet.pay(price);
        // 결제 후 잔액
        System.out.printf("고객님 결제 후 잔액 : %d 원 %n", wallet.getMoney());
        System.out.println("---------------------------");
    }
}
