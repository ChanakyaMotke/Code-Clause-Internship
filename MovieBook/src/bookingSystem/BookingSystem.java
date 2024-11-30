package bookingSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class BookingSystem {
    private List<Movie> movies;

    public BookingSystem() {
        this.setMovies(new ArrayList<>());
    }

    public void addMovie(Movie movie) {
        getMovies().add(movie);
    }

    public void displayMovies() {
        System.out.println("Available Movies:");
        for (int i = 0; i < getMovies().size(); i++) {
            Movie movie = getMovies().get(i);
            System.out.println((i + 1) + ". " + movie.getName() + " - Show Time: " + movie.getShowTime() + " - Seats Available: " + movie.getAvailableSeats());
        }
    }

    public void bookTicket(int movieIndex, int seatNumber) {
        Movie movie = getMovies().get(movieIndex);
        if (movie.isSeatAvailable(seatNumber)) {
            movie.bookSeat(seatNumber);
            System.out.println("Ticket booked successfully for " + movie.getName() + " at seat " + seatNumber + ".");
        } else {
            System.out.println("Seat not available. Please choose another seat.");
        }
    }

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}
}
