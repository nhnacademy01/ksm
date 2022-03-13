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
        //일단 동작 제대로 되는듯 그런데 입력한 수량이 매대의 수량보다 많을 경우랑 입력한 물건이 매대에 없는경우 예외 처리
        // 객체지향적으로 코드 정리하기(메서드 분리하기)
        for (BuyList.Item item : buyList.items) {
            int addFoodCounts = 0;
            for (Food food : foodStand.foods) {
                if (food.isSame(item)) {
                    if (addFoodCounts < item.getAmount()) {
                        this.basket.add(food);
                        addFoodCounts++;
                    }
                }
            }
        }
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