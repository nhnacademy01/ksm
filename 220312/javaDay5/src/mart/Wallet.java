package mart;

import java.util.ArrayList;

public class Wallet {
    protected final ArrayList<Coupon> coupons = new ArrayList<>();
    protected final Money money;

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

    public Coupon getVIPTwentyPercentCoupon(){
        for (Coupon coupon : coupons) {
            if (coupon instanceof VIPDiscountTwentyPercent) {
                this.coupons.remove(coupon);
                return coupon;
            }
        }
        return null;
    }

    public boolean has(Price price){
        if (money.getAmounts() < price.value) {
            System.out.printf("[결제 오류] 잔액이 부족합니다. (현재 잔액 : %d)%n", money.getAmounts());
            System.out.println("---------------------------");
            return false;
        }else{
            return true;
        }
    }
}