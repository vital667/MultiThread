package zadanieRestauracja;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import static zadanieRestauracja.Dish.*;

public class Restaurant {
    private Timer dishGeneratorTimer;
    private HashMap<Dish, Integer> stock;
    private int moneyEarned;
    private int moneyLost;
    public static final int COOKING_EFFICIENCY = 1;
    public static final int COOKING_SPEED = 5;

    public Restaurant(int baseAmount) {
        initStock(baseAmount);
        this.moneyEarned = 0;
        this.moneyLost = 0;
    }

    private void initStock(int baseAmount) {
        stock = new HashMap<>();
        stock.put(ICE_CREAM, baseAmount);
        stock.put(GYROS, baseAmount);
        stock.put(PIZZA, baseAmount);
        stock.put(STEK, baseAmount);
        stock.put(TORTILLA, baseAmount);
    }


    public boolean order(Dish dishOrdered, int timesOrdered) {
        synchronized (stock) {
            int timesAvailable = stock.get(dishOrdered);
            int orderValue = dishOrdered.getPrice() * timesOrdered;
            if (timesAvailable >= timesOrdered) {
                stock.put(dishOrdered, timesAvailable - timesOrdered);
                moneyEarned += orderValue;
                return true;
            }
            moneyLost += orderValue;
            return false;
        }
    }


    public void startCooking() {
        ActionListener actionListener = e -> {
            synchronized (stock){
                System.out.println("Money earned|lost: "+ moneyEarned +"|"+moneyLost+" Current stock:");
                for(Map.Entry<Dish,Integer> entry : stock.entrySet()){
                    entry.setValue(entry.getValue()+COOKING_EFFICIENCY);
                    System.out.println(entry.getKey().name()+" "+entry.getValue());
                }
                System.out.println();
            }
        };
        dishGeneratorTimer = new Timer(COOKING_SPEED*1000, actionListener);
//        dishGeneratorTimer.setInitialDelay(COOKING_SPEED*1000);
        dishGeneratorTimer.start();
    }

}
