package mart;

import java.util.ArrayList;

public class Basket {
    ArrayList<Food> foods = new ArrayList<>();

    public Basket() {
    }

    public void add(Food food) {
        foods.add(food);
    }

    public void addFoods(ArrayList<Food> foodStandFoods, BuyList.Item item, int addCounts) {
        for (Food food : foodStandFoods) {
            if (food.isSame(item)) {
                if (addCounts >= item.getAmount()) {
                    break;
                }
                add(food);
                addCounts++;
            }
        }
        if (addCounts < item.getAmount()) {
            System.out.printf("매장에 있는 %s 가 희망하는 구매 수량보다 적습니다. %d 개만 장바구니에 담습니다.%n", item.getName(), addCounts);
        }
    }

    public void print() {
        System.out.println("[장바구니목록]");
        for (Food food : this.foods) {
            if (food != null) System.out.println(food.toString());
        }
        System.out.println("---------------------------");
    }
}