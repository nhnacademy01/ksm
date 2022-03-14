package mart;

abstract public class Customer {
    protected final BuyList buyList;
    protected final Wallet wallet;
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
        foodStand.gaveFoodByBasket(this.buyList, this.basket);
        basket.print();
    }

    public void payTo(Counter counter, Price totalPrice) {
    }

    protected Coupon useTenPercentCoupon() {
        return this.wallet.getTenPercentCoupon();
    }

    protected Coupon useOneThousandWonCoupon() {
        return this.wallet.getOneThousandWonCoupon();
    }

    public Basket getBasket() {
        return this.basket;
    }
}

class NormalCustomer extends Customer{
    public NormalCustomer(int money, BuyList buyList) {
        super(money, buyList);
        System.out.println("일반 고객 입장");
    }

    @Override
    public void payTo(Counter counter, Price totalPrice) {
        if (totalPrice.value != 0) {
            totalPrice = counter.discountProcess(useTenPercentCoupon(), totalPrice);
            totalPrice = counter.discountProcess(useOneThousandWonCoupon(), totalPrice);
            counter.paymentProcess(totalPrice, wallet);
        } else {
            System.out.printf("결제 할 금액이 %d 로 없습니다. 감사합니다.%n", totalPrice.value);
        }
    }
}

class VIPCustomer extends Customer {
    public VIPCustomer(int money, BuyList buyList) {
        super(money, buyList);
        System.out.println("vip 고객 입장");
    }

    private Coupon useVIPTwentyPercentCoupon() {
        return wallet.getVIPTwentyPercentCoupon();
    }

    @Override
    public void payTo(Counter counter, Price totalPrice) {
        if (totalPrice.value != 0) {
            totalPrice = counter.discountProcess(useVIPTwentyPercentCoupon(), totalPrice);
            totalPrice = counter.discountProcess(useTenPercentCoupon(), totalPrice);
            totalPrice = counter.discountProcess(useOneThousandWonCoupon(), totalPrice);
            counter.paymentProcess(totalPrice, wallet);
        } else {
            System.out.printf("결제 할 금액이 %d 로 없습니다. 감사합니다.", totalPrice.value);
            System.out.println("---------------------------");
        }
    }
}