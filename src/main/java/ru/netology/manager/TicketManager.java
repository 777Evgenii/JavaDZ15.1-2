package ru.netology.manager;

import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

import java.util.Arrays;
import java.util.Comparator;

public class TicketManager {
    private TicketRepository repo;

    public TicketManager(TicketRepository repo) {
        this.repo = repo;
    }

    public void add(Ticket ticket) {
        repo.save(ticket);
    }

    public void removeById(int id) {
        repo.removeById(id);
    }

    public Ticket[] findAll() {
        return repo.findAll();
    }

    public Ticket[] findAll(String from, String to) {
        Ticket[] tickets = repo.findAllByFromAndTo(from, to);
        Arrays.sort(tickets);
        return tickets;
    }

    public Ticket[] findAll(String from, String to, Comparator<Ticket> comparator) {
        Ticket[] tickets = repo.findAllByFromAndTo(from, to);
        Arrays.sort(tickets, comparator);
        return tickets;
    }
}
