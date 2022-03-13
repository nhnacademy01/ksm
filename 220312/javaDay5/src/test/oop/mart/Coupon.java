package test.oop.mart;

abstract public class Coupon {
    public Price discount(Price totalPrice){return totalPrice;}
}

class DiscountTenPercent extends Coupon{
    private final double TEN_PERCENT = 0.1;
    @Override
    public Price discount(Price totalPrice) {
        totalPrice.value -= totalPrice.value*TEN_PERCENT;
        System.out.println("10% 할인 쿠폰을 사용하셨습니다.");
        return totalPrice;
    }
}

class DiscountOneThousandWon extends Coupon{
    private final int ONE_THOUSAND_WON = 1_000;
    @Override
    public Price discount(Price totalPrice) {
        totalPrice.value -= ONE_THOUSAND_WON;
        System.out.println("1,000원 할인 쿠폰을 사용하셨습니다.");
        return totalPrice;
    }
}

class VIPDiscountTwentyPercent extends Coupon{
    private final double TWENTY_PERCENT = 0.2;
    @Override
    public Price discount(Price totalPrice) {
        totalPrice.value -= totalPrice.value*TWENTY_PERCENT;
        System.out.println("VIP 전용 상시 20% 할인 쿠폰을 사용하셨습니다.");
        return totalPrice;
    }
}