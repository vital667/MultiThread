package zadanieRestauracja;

public class Main {
    public static void main(String[] args) {

        Restaurant restaurant = new Restaurant(10);
//        HungryCrowd hungryCrowd = new HungryCrowd(((dish, timesOrdered) -> restaurant.order(dish, timesOrdered)));
        HungryCrowd hungryCrowd = new HungryCrowd((restaurant::order));

        restaurant.startCooking();
        hungryCrowd.startOrdering();
    }
}
