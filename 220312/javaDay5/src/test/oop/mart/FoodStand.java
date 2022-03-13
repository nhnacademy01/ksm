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

    public void gaveFoodByBasket(BuyList buyList, Basket basket) {
        for (BuyList.Item item : buyList.items) {
            int addFoodCounts = 0;
            basket.addFoods(this.foods, item, addFoodCounts);
        }
    }
}
