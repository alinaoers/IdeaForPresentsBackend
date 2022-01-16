package com.example.fullstack.model;

public class PresentCard {
    private int id;
    private int clientId;
    private String description;
    private int price;
    private String link;

    @Override
    public String toString() {
        return "PresentCard{" +
                "id=" + id +
                ", client id=" + clientId +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", link='" + link + '\'' +
                '}';
    }

    public PresentCard() {
    }

    public PresentCard(int clientId, String description, int price, String link) {
        this.clientId = clientId;
        this.description = description;
        this.price = price;
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
