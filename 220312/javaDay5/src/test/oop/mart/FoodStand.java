package test.oop.mart;

import java.util.ArrayList;

public class FoodStand {
    ArrayList<Food> foods = new ArrayList<>();

    public void add(Food food) {
        foods.add(food);
    }

    public void print() {
        System.out.println("[식품매대목록]");
        for (Food food : this.foods) {
            if (food != null) System.out.println(food.toString());
        }
        System.out.println("----------------");
    }

    public void getFoodToBasket(BuyList buyList, Basket basket) {
        for (BuyList.Item item : buyList.items) {
            int addFoodCounts = 0;
            for (Food food : foods) {
                if (food.isSame(item)) {
                    if (addFoodCounts >= item.getAmount()) {
                        break;
                    }
                    basket.add(food);
                    addFoodCounts++;
                }
            }
            if(addFoodCounts<item.getAmount()){
                System.out.printf("매장에 있는 %s 가 희망하는 구매 수량보다 적습니다. %d 개만 장바구니에 담습니다.%n",item.getName(),addFoodCounts);
            }
        }
    }
}
