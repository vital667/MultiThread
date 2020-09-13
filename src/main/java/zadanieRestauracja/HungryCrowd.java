package zadanieRestauracja;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiPredicate;

public class HungryCrowd {

    public static final int DELAY_MIN = 1;
    public static final int DELAY_MAX = 4;
    public static final int ORDER_MIN = 2;
    public static final int ORDER_MAX = 6;

    private Timer timer;
    private BiPredicate<Dish, Integer> orderAction;

    public HungryCrowd(BiPredicate<Dish, Integer> orderAction) {
        this.orderAction = orderAction;
        timer = new Timer();
    }


    public void startOrdering() {
        timer.schedule(new OrderTask(), 0);
    }


    public class OrderTask extends TimerTask {    // clasa redundantna, klasa odpala sama siebie
        @Override
        public void run() {         //metoda sama siebie odpala
            int nextDelay = ThreadLocalRandom.current().nextInt(DELAY_MIN, DELAY_MAX); // za ile startuje zamowienie (opoznienie)
            int timesOrdered = ThreadLocalRandom.current().nextInt(ORDER_MIN, ORDER_MAX); // ile ma byc zamowien na raz
            int CurrentDish = ThreadLocalRandom.current().nextInt(0, Dish.values().length); //jakie danie jest zamawiane naraz
            Dish dishToOrder = Dish.values()[CurrentDish]; //jakie danie

            if (orderAction.test(dishToOrder, timesOrdered))
                System.out.println("Successfully ordered: " + dishToOrder.name() + " - " + timesOrdered);
            else System.out.println("Couldn't order " + dishToOrder.name() + " - " + timesOrdered);
            timer.schedule(new OrderTask(), nextDelay * 1000);
        }
    }
}

