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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Theater {
    /**
     * Represents a seat in the theater
     * A1, A2, A3, ... B1, B2, B3 ...
     */
    static class Seat {
        private int rowNum;
        private int seatNum;

        public Seat(int rowNum, int seatNum) {
            this.rowNum = rowNum;
            this.seatNum = seatNum;
        }

        public int getSeatNum() {
            return seatNum;
        }

        public int getRowNum() {
            return rowNum;
        }

        /**
         * @return a string that displays the row letter followed by the seat number
         */
        @Override
        public String toString() {
            int power = powerHelper();
            StringBuilder str = new StringBuilder();
            //char[] charA = new char[power];
            int i = 0;
            int temp = getRowNum();
            while(i < power){
                char c;
                int temp2 = (int)(temp / Math.pow(26, power - 1 - i));
                if(temp2 > 0){
                    c = (char)('A' + temp2 - 1);
                }
                else{
                    c = (char)('A' + (temp % Math.pow(26, power-1-i)));
                }
                str.append(c);
                temp = (int)(temp % Math.pow(26, power-1-i));
                i++;
            }

            return "" + str + getSeatNum();
        }

        /**
         * helper function for the toString
         */
        private int powerHelper(){
            int power = 1;
            while(getRowNum() > Math.pow(26, power)){
                power++;
            }
            return power;
        }
    }

    /**
     * Represents a ticket purchased by a client
     */
    static class Ticket {
        private String show;
        private String boxOfficeId;
        private Seat seat;
        private int client;

        public Ticket(String show, String boxOfficeId, Seat seat, int client) {
            this.show = show;
            this.boxOfficeId = boxOfficeId;
            this.seat = seat;
            this.client = client;
        }

        public Seat getSeat() {
            return seat;
        }

        public String getShow() {
            return show;
        }

        public String getBoxOfficeId() {
            return boxOfficeId;
        }

        public int getClient() {
            return client;
        }

        /**
         * @return the ticket string that displays the show, box office id, seat, and client
         */
        @Override
        public String toString() {
            String s = "-------------------------------\n";
            s += "| Show: " + getShow();
            for (int i = 0; i < 22 - getShow().length(); i++) {
                s += " ";
            }
            s += "|\n";
            s += "| Box Office ID: " + getBoxOfficeId();
            for (int i = 0; i < 13 - getBoxOfficeId().length(); i++) {
                s += " ";
            }
            s += "|\n";
            s += "| Seat: " + getSeat().toString();
            for (int i = 0; i < 22 - getSeat().toString().length(); i++) {
                s += " ";
            }
            s += "|\n";

            String clientStr = "" + getClient();

            s += "| Client: " + clientStr;
            for (int i = 0; i < 20 - clientStr.length(); i++) {
                s += " ";
            }
            s += "|\n";
            s += "-------------------------------";

            return s;
        }
    }

    private String show;
    private Seat[] seats;
    private int ticketsSold;
    private List<Ticket> ticketList = new ArrayList<>();

    /**
     *
     * @param numRows
     * @param seatsPerRow
     * @param show
     */
    public Theater(int numRows, int seatsPerRow, String show) {
        // TODO: Implement this constructor
        this.show = show;
        seats = new Seat[numRows * seatsPerRow];
        for (int i = 0; i < numRows ; i++) {
            for (int j = 0; j < seatsPerRow; j++) {
                Seat seat = new Seat( i + 1, j + 1);
                seats[(i * seatsPerRow) + j] = seat;
            }
        }
        ticketsSold = 0;
    }

    /**
     * Calculates the best seat not yet reserved
     *
     * @return the best seat or null if theater is full
     */
    public Seat bestAvailableSeat() {
        //TODO: Implement this method
        if(ticketsSold >= seats.length){
            return null;
        }
        else{
            Seat temp = seats[ticketsSold];
          //  int temp = ticketsSold;
            ticketsSold++;
            return temp;
        }
    }

    /**
     * Prints a ticket for the client after they reserve a seat
     * Also prints the ticket to the console
     *
     * @param seat a particular seat in the theater
     * @return a ticket or null if a box office failed to reserve the seat
     */
    public Ticket printTicket(String boxOfficeId, Seat seat, int client) {
        //TODO: Implement this method
        Ticket ticket = new Ticket(show, boxOfficeId, seat, client);
        ticketList.add(ticket);
        System.out.println(ticket);

        return ticket;
    }

    /**
     * Lists all tickets sold for this theater in order of purchase
     *
     * @return list of tickets sold
     */
    public List<Ticket> getTransactionLog() {
        //TODO: Implement this method
        return ticketList;
    }
}
