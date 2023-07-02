package ru.netology.domain;

public class Ticket implements Comparable<Ticket> {
    private int id;
    private String from;
    private String to;
    private int price;
    private int time;

    public Ticket() {
    }

    public Ticket(int id, String from, String to, int price, int time) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.price = price;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public int compareTo(Ticket t) {
        if (this.price < t.price) {
            return -1;
        } else if (this.price > t.price) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", price=" + price +
                ", time=" + time +
                '}';
    }
}
