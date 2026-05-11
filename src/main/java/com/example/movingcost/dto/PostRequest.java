package com.example.movingcost.dto;

import jakarta.validation.constraints.*;

public class PostRequest {

    @NotBlank(message = "出発地は必須です")
    private String fromPrefecture;

    @NotBlank(message = "目的地は必須です")
    private String toPrefecture;

    @NotBlank(message = "間取りは必須です")
    private String layout;

    @NotBlank(message = "引越し時期は必須です")
    private String movingDate;

    @NotNull(message = "費用は必須です")
    @Min(value = 0, message = "費用は0円以上で入力してください")
    private Integer cost;

    @NotBlank(message = "業者名は必須です")
    private String company;

    @NotNull(message = "満足度は必須です")
    @Min(value = 1, message = "満足度は1以上で入力してください")
    @Max(value = 5, message = "満足度は5以下で入力してください")
    private Integer rating;

    @NotNull(message = "見積もり社数は必須です")
    @Min(value = 1, message = "見積もり社数は1以上で入力してください")
    private Integer quotes;

    @Size(max = 100, message = "コメントは100文字以内で入力してください")
    private String comment;

    public String getFromPrefecture() { return fromPrefecture; }
    public void setFromPrefecture(String fromPrefecture) { this.fromPrefecture = fromPrefecture; }
    public String getToPrefecture() { return toPrefecture; }
    public void setToPrefecture(String toPrefecture) { this.toPrefecture = toPrefecture; }
    public String getLayout() { return layout; }
    public void setLayout(String layout) { this.layout = layout; }
    public String getMovingDate() { return movingDate; }
    public void setMovingDate(String movingDate) { this.movingDate = movingDate; }
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
}
