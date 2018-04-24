package assignment6;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private static String show = "Ouija";

    private static void joinAllThreads(List<Thread> threads) throws InterruptedException {
        for (Thread t: threads) {
            t.join();
        }
    }


    public static void main(String[] args) throws InterruptedException{
        //Test1
        Map<String, Integer> offices = new HashMap<String, Integer>() {{
            put("BX1", 50);
            put("BX2", 100);
        }};
        Theater t = new Theater(100, 1, show);

        //Test2
//        Map<String, Integer> offices = new HashMap<String, Integer>() {{
//            put("BX1", 400);
//            put("BX2", 600);
//        }};
//        Theater t = new Theater(500, 2, show);

        BookingClient bc = new BookingClient(offices, t);
        joinAllThreads(bc.simulate());
    }
}
