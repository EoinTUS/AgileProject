package cinemaBookingSystem;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CinemaTest {

    @Test
    public void testCinemaConstructor() {
        Cinema cinema = new Cinema("Vue Liffey Valley");

        assertNotNull(cinema);
    }

    @Test
    public void testGetLocation() {
        Cinema cinema = new Cinema("Omniplex Cinema");

        assertEquals("Omniplex Cinema", cinema.getLocation());
    }

    @Test
    public void testDifferentLocation() {
        Cinema cinema = new Cinema("IMC Cinema");

        assertEquals("IMC Cinema", cinema.getLocation());
    }
}