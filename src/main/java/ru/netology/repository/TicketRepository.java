package ru.netology.repository;

import ru.netology.domain.Ticket;
import ru.netology.exception.AlreadyExistsException;
import ru.netology.exception.NotFoundException;

public class TicketRepository {
    private Ticket[] tickets = new Ticket[]{};

    public void save(Ticket ticket) {

        if (findById(ticket.getId()) != null) {
            throw new AlreadyExistsException("Ticket with id: " + ticket.getId() + " already exists");
        }

        int length = tickets.length + 1;
        Ticket[] tmp = new Ticket[length];
        System.arraycopy(tickets, 0, tmp, 0, tickets.length);
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = ticket;
        tickets = tmp;
    }

    public Ticket[] findAll() {
        return tickets;
    }

    public Ticket findById(int id) {
        for (Ticket ticket : tickets) {
            if (id == ticket.getId()) {
                return ticket;
            }
        }

        return null;
    }

    public Ticket[] findAllByFromAndTo(String from, String to) {
        Ticket[] tickets = new Ticket[]{};

        for (Ticket ticket : this.tickets) {
            if (from.equals(ticket.getFrom()) && to.equals(ticket.getTo())) {
                int length = tickets.length + 1;
                Ticket[] tmp = new Ticket[length];
                System.arraycopy(tickets, 0, tmp, 0, tickets.length);
                int lastIndex = tmp.length - 1;
                tmp[lastIndex] = ticket;
                tickets = tmp;
            }
        }

        return tickets;
    }

    public void removeById(int id) {

        if (findById(id) == null) {
            throw new NotFoundException("Ticket with id: " + id + " not found");
        }

        int length = tickets.length - 1;
        Ticket[] tmp = new Ticket[length];
        int index = 0;
        for (Ticket item : tickets) {
            if (item.getId() != id) {
                tmp[index] = item;
                index++;
            }
        }
        tickets = tmp;
    }
}
