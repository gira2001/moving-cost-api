package com.example.movingcost.dto;

import com.example.movingcost.entity.Post;
import java.time.format.DateTimeFormatter;

public class PostResponse {

    private final Long id;
    private final String fromPrefecture;
    private final String toPrefecture;
    private final String layout;
    private final String movingDate;
    private final Integer cost;
    private final String company;
    private final Integer rating;
    private final Integer quotes;
    private final String comment;
    private final String createdAt;

    public PostResponse(Post post) {
        this.id = post.getId();
        this.fromPrefecture = post.getFromPrefecture();
        this.toPrefecture = post.getToPrefecture();
        this.layout = post.getLayout();
        this.movingDate = post.getMovingDate().format(DateTimeFormatter.ofPattern("yyyy-MM"));
        this.cost = post.getCost();
        this.company = post.getCompany();
        this.rating = post.getRating();
        this.quotes = post.getQuotes();
        this.comment = post.getComment();
        this.createdAt = post.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public Long getId() { return id; }
    public String getFromPrefecture() { return fromPrefecture; }
    public String getToPrefecture() { return toPrefecture; }
    public String getLayout() { return layout; }
    public String getMovingDate() { return movingDate; }
    public Integer getCost() { return cost; }
    public String getCompany() { return company; }
    public Integer getRating() { return rating; }
    public Integer getQuotes() { return quotes; }
    public String getComment() { return comment; }
    public String getCreatedAt() { return createdAt; }
}
