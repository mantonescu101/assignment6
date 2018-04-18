/* MULTITHREADING <MyClass.java>
 * EE422C Project 6 submission by
 * Replace <...> with your actual data.
 * Mircea Antonescu
 * mca2357
 * 15455
 * Slip days used: 0
 * Fall 2017
 */

package assignment6;

import java.util.*;
import java.lang.Thread;

public class BookingClient {
    int numBoxOffices;
    private Theater theater;
    private Queue<int[]> boxOffices = new LinkedList<>();

    /**
     * @param office maps box office id to number of customers in line
     * @param theater the theater where the show is playing
     */
    public BookingClient(Map<String, Integer> office, Theater theater) {
        // TODO: Implement this constructor
        this.theater = theater;
        numBoxOffices = office.size();
        int ID = 1;
        Set<Map.Entry<String, Integer>> set = office.entrySet();
        for (Map.Entry<String, Integer> entry : set){
            int[] people = new int[entry.getValue()];
            for (int i = 0; i < people.length; i++) {
                people[i] = ID;
                ID++;
            }
            boxOffices.add(people);
        }
    }

    /**
     * Starts the box office simulation by creating (and starting) threads
     * for each box office to sell tickets for the given theater
     *
     * @return list of threads used in the simulation,
     *         should have as many threads as there are box offices
     */
    public List<Thread> simulate() {
        //TODO: Implement this method
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < numBoxOffices; i++) {
            Thread thread = new Thread(new BoxOfficeThread(boxOffices.poll(), theater, i));
            thread.start();
            threadList.add(thread);
        }

        return threadList;
    }
}
