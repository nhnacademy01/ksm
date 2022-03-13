package test.oop.mart;

import java.util.ArrayList;

public class Wallet {
    private final ArrayList<Coupon> coupons = new ArrayList<>();
    private final Money money;

    public Wallet(int money) {
        this.money = new Money(money);
    }

    public void add(Coupon coupon) {
        coupons.add(coupon);
    }

    public void pay(Price price) {
        money.minus(price);
    }

    public int getMoney() {
        return money.getAmounts();
    }

    public Coupon getTenPercentCoupon() {
        for (Coupon coupon : this.coupons) {
            if (coupon instanceof DiscountTenPercent) {
                this.coupons.remove(coupon);
                return coupon;
            }
        }
        return null;
    }

    public Coupon getOneThousandWonCoupon() {
        for (Coupon coupon : this.coupons) {
            if (coupon instanceof DiscountOneThousandWon) {
                this.coupons.remove(coupon);
                return coupon;
            }
        }
        return null;
    }
}
