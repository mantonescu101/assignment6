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

import java.util.concurrent.locks.ReentrantLock;

public class BoxOfficeThread implements Runnable {
    private final ReentrantLock lock = new ReentrantLock();
    private int[] IDs;
    private Theater theater;
    private String BoxOfficeID;
    private static boolean isSoldOut = false;
    private int index;

    /**
     * creates a box office thread
     * @param ids an array that contains the IDs of the people in line at that box office
     * @param thtr the theater that the box offices belong to
     * @param OfficeID the ID of this thread's box office
     */
    public BoxOfficeThread(int[] ids, Theater thtr, int OfficeID){
        IDs = ids;
        theater = thtr;
        BoxOfficeID = "BX" + (OfficeID + 1);
        index = 0;
    }

    /**
     * runs through the int of IDs and prints a ticket for each
     */
    @Override
    public void run() {
      //  int index = 0;
        while(!isSoldOut && (index < IDs.length)){
            lock.lock();
            Theater.Seat seat = theater.bestAvailableSeat();
            if(seat == null){
                isSoldOut = true;
                System.out.println("Sorry, we are sold out!");
            }
            else{
                if(index < IDs.length){
                    Theater.Ticket tick = theater.printTicket(BoxOfficeID, seat, IDs[index]);
                }
                index++;
            }
            lock.unlock();
        }
    }
}
