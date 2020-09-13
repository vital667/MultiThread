package akademia.basic;

public class Main {

    public static void main(String[] args) {

        System.out.println("Start main Thread");

        Thread anotherThread = new Thread( () ->{
            System.out.println("Start another anotherThread");
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("another Thread count: "+i+"\033[0m");
            }
    });
        anotherThread.start();

//        System.out.println("Start main Thread");
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Main Thread count: "+i+"\033[33m");
        }

        System.out.println("Main Application stop");

    }

}
