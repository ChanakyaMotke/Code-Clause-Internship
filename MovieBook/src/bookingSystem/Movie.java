package bookingSystem;

import java.util.ArrayList;
import java.util.List;

class Movie {
    private String name;
    private String showTime;
    private int availableSeats;
    private List<Integer> bookedSeats;

    public Movie(String name, String showTime, int availableSeats) {
        this.name = name;
        this.showTime = showTime;
        this.availableSeats = availableSeats;
        this.bookedSeats = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getShowTime() {
        return showTime;
    }

    public int getAvailableSeats() {
        return availableSeats - bookedSeats.size();
    }

    public void bookSeat(int seatNumber) {
        if (!bookedSeats.contains(seatNumber)) {
            bookedSeats.add(seatNumber);
        }
    }

    public boolean isSeatAvailable(int seatNumber) {
        return !bookedSeats.contains(seatNumber) && seatNumber <= availableSeats && seatNumber > 0;
    }

    public List<Integer> getBookedSeats() {
        return bookedSeats;
    }
}
