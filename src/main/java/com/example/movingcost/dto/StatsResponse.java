package com.example.movingcost.dto;

public class StatsResponse {
    private final long totalCount;
    private final long avgCost;
    private final long minCost;
    private final long maxCost;

    public StatsResponse(long totalCount, long avgCost, long minCost, long maxCost) {
        this.totalCount = totalCount;
        this.avgCost = avgCost;
        this.minCost = minCost;
        this.maxCost = maxCost;
    }

    public long getTotalCount() { return totalCount; }
    public long getAvgCost() { return avgCost; }
    public long getMinCost() { return minCost; }
    public long getMaxCost() { return maxCost; }
}
