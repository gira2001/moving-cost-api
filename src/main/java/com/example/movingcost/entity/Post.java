package com.example.movingcost.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "from_prefecture", nullable = false, length = 10)
    private String fromPrefecture;

    @Column(name = "to_prefecture", nullable = false, length = 10)
    private String toPrefecture;

    @Column(nullable = false, length = 10)
    private String layout;

    @Column(name = "moving_date", nullable = false)
    private LocalDate movingDate;

    @Column(nullable = false)
    private Integer cost;

    @Column(nullable = false, length = 100)
    private String company;

    @Column(nullable = false)
    private Integer rating;

    @Column(nullable = false)
    private Integer quotes;

    @Column(length = 100)
    private String comment;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getFromPrefecture() { return fromPrefecture; }
    public void setFromPrefecture(String fromPrefecture) { this.fromPrefecture = fromPrefecture; }
    public String getToPrefecture() { return toPrefecture; }
    public void setToPrefecture(String toPrefecture) { this.toPrefecture = toPrefecture; }
    public String getLayout() { return layout; }
    public void setLayout(String layout) { this.layout = layout; }
    public LocalDate getMovingDate() { return movingDate; }
    public void setMovingDate(LocalDate movingDate) { this.movingDate = movingDate; }
    public Integer getCost() { return cost; }
    public void setCost(Integer cost) { this.cost = cost; }
    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }
    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }
    public Integer getQuotes() { return quotes; }
    public void setQuotes(Integer quotes) { this.quotes = quotes; }
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
