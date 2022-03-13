package test.oop.mart;

public class Customer {
    private final Wallet wallet;
    private final BuyList buyList;
    private Basket basket;

    public Customer(int money, BuyList buyList) {
        this.wallet = new Wallet(money);
        this.buyList = buyList;
    }

    public void take(Coupon coupon) {
        this.wallet.add(coupon);
    }

    public void bring(Basket basket) {
        this.basket = basket;
    }

    public void addToBasket(FoodStand foodStand) {
        foodStand.getFoodToBasket(this.buyList,this.basket);
        basket.print();
    }

    public void payTo(Counter counter, Price totalPrice) {
        totalPrice = counter.discountProcess(useTenPercentCoupon(), totalPrice);
        totalPrice = counter.discountProcess(useOneThousandWonCoupon(), totalPrice);
        counter.paymentProcess(totalPrice, this.wallet);
    }

    private Coupon useTenPercentCoupon() {
        return this.wallet.getTenPercentCoupon();
    }

    private Coupon useOneThousandWonCoupon() {
        return this.wallet.getOneThousandWonCoupon();
    }

    public Basket getBasket() {
        return this.basket;
    }
}