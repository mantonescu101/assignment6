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
        Map<String, Integer> offices = new HashMap<String, Integer>() {{
            put("BX1", 101);
            put("BX2", 100);
        }};

        Theater t = new Theater(100, 2, show);
        BookingClient bc = new BookingClient(offices, t);
        joinAllThreads(bc.simulate());
    }
}
