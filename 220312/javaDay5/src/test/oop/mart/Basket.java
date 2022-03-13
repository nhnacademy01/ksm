package test.oop.mart;

import java.util.ArrayList;

public class Basket {
    ArrayList<Food> foods = new ArrayList<>();

    public Basket() {
    }

    public void add(Food food) {
        foods.add(food);
    }

    public void print() {
        System.out.println("[장바구니목록]");
        for (Food food : this.foods) {
            if (food != null) System.out.println(food.toString());
        }
        System.out.println("----------------");
    }
}
