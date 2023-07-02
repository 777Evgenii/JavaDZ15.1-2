package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.exception.AlreadyExistsException;
import ru.netology.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class TicketRepositoryTest {
    private TicketRepository repo = new TicketRepository();
    private Ticket ticket1 = new Ticket(1, "VKO", "NSK", 10000, 20);
    private Ticket ticket2 = new Ticket(2, "DME", "CSH", 60000, 30);
    private Ticket ticket3 = new Ticket(3, "VKO", "NSK", 80000, 30);

    public TicketRepositoryTest() {
        repo.save(ticket1);
        repo.save(ticket2);
        repo.save(ticket3);
    }

    @Test
    public void shouldSaveOneTicket() {
        TicketRepository repository = new TicketRepository();
        repository.save(ticket1);

        Ticket[] expected = new Ticket[]{ticket1};
        Ticket[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldDeleteOneTicket() {
        repo.removeById(1);

        Ticket[] expected = new Ticket[]{ticket2, ticket3};
        Ticket[] actual = repo.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldAddExistsTicketException() {
        assertThrows(AlreadyExistsException.class, () -> {
            repo.save(ticket1);
        });
    }

    @Test
    public void shouldDeleteException() {
        assertThrows(NotFoundException.class, () -> {
            repo.removeById(-1);
        });
    }

    @Test
    public void shouldFindTicketById() {
        Ticket expected = ticket1;
        Ticket actual = repo.findById(1);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindMultipleTickets() {
        Ticket[] expected = new Ticket[]{ticket1, ticket3};
        Ticket[] actual = repo.findAllByFromAndTo("VKO", "NSK");
        assertArrayEquals(expected, actual);
    }
}
