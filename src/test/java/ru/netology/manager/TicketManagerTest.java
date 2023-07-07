package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.comarator.TicketByTimeAscComparator;
import ru.netology.domain.Ticket;
import ru.netology.exception.AlreadyExistsException;
import ru.netology.exception.NotFoundException;
import ru.netology.repository.TicketRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TicketManagerTest {
    private Ticket[] tickets = {
            new Ticket(1, "VKO", "NSK", 10000, 20),
            new Ticket(2, "DME", "CSH", 60000, 30),
            new Ticket(3, "DME", "OMS", 30000, 50),
            new Ticket(4, "DME", "OMS", 40000, 20),
            new Ticket(5, "DME", "OMS", 20000, 30)
    };

    TicketManager ticketManager = new TicketManager(new TicketRepository());

    public TicketManagerTest() {
        for (Ticket ticket : tickets) {
            ticketManager.add(ticket);
        }
    }

    @Test
    public void shouldAddOneTicket() {
        Ticket ticket = new Ticket(1, "VKO", "NSK", 10000, 20);

        TicketManager ticketManager = new TicketManager(new TicketRepository());
        ticketManager.add(ticket);

        Ticket[] expected = new Ticket[]{ticket};
        Ticket[] actual = ticketManager.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldDeleteOneTicket() {
        ticketManager.removeById(1);

        Ticket[] expected = new Ticket[]{tickets[1], tickets[2], tickets[3], tickets[4]};
        Ticket[] actual = ticketManager.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldAddExistsTicketException() {
        assertThrows(AlreadyExistsException.class, () -> {
            ticketManager.add(tickets[0]);
        });
    }

    @Test
    public void shouldDeleteException() {
        assertThrows(NotFoundException.class, () -> {
            ticketManager.removeById(-1);
        });
    }

    @Test
    public void shouldFindOneTicket() {
        Ticket[] expected = new Ticket[]{tickets[1]};
        Ticket[] actual = ticketManager.findAll("DME", "CSH");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindTicketsAndSortByPriceAsc() {
        Ticket[] expected = new Ticket[]{tickets[4], tickets[2], tickets[3]};
        Ticket[] actual = ticketManager.findAll("DME", "OMS");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindTicketsAndSortByTimeAsc() {
        Ticket[] expected = new Ticket[]{tickets[3], tickets[4], tickets[2]};
        Ticket[] actual = ticketManager.findAll("DME", "OMS", new TicketByTimeAscComparator());
        assertArrayEquals(expected, actual);
    }
}
