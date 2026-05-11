package com.example.movingcost.service;

import com.example.movingcost.dto.PostRequest;
import com.example.movingcost.dto.PostResponse;
import com.example.movingcost.dto.StatsResponse;
import com.example.movingcost.entity.Post;
import com.example.movingcost.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // 投稿作成
    public PostResponse create(PostRequest request) {
        Post post = new Post();
        post.setFromPrefecture(request.getFromPrefecture());
        post.setToPrefecture(request.getToPrefecture());
        post.setLayout(request.getLayout());
        post.setMovingDate(LocalDate.parse(request.getMovingDate() + "-01"));
        post.setCost(request.getCost());
        post.setCompany(request.getCompany());
        post.setRating(request.getRating());
        post.setQuotes(request.getQuotes());
        post.setComment(request.getComment());
        return new PostResponse(postRepository.save(post));
    }

    // 一覧取得
    public Page<PostResponse> findAll(
            String from, String to, String layout,
            Integer maxCost, String sort, int page, int limit) {

        Specification<Post> spec = buildSpec(from, to, layout, maxCost);
        Pageable pageable = PageRequest.of(page - 1, limit, buildSort(sort));
        return postRepository.findAll(spec, pageable).map(PostResponse::new);
    }

    // 1件取得
    public PostResponse findById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("投稿が見つかりません: " + id));
        return new PostResponse(post);
    }

    // 統計取得
    public StatsResponse getStats(String from, String to, String layout, Integer maxCost) {
        Specification<Post> spec = buildSpec(from, to, layout, maxCost);
        long count = postRepository.count(spec);
        long avg = postRepository.findAvgCost(from, to, layout, maxCost).map(Double::longValue).orElse(0L);
        long min = postRepository.findMinCost(from, to, layout, maxCost).map(Integer::longValue).orElse(0L);
        long max = postRepository.findMaxCost(from, to, layout, maxCost).map(Integer::longValue).orElse(0L);
        return new StatsResponse(count, avg, min, max);
    }

    // 絞り込み条件
    private Specification<Post> buildSpec(String from, String to, String layout, Integer maxCost) {
        Specification<Post> spec = Specification.where(null);
        if (from != null)     spec = spec.and((r, q, cb) -> cb.equal(r.get("fromPrefecture"), from));
        if (to != null)       spec = spec.and((r, q, cb) -> cb.equal(r.get("toPrefecture"), to));
        if (layout != null)   spec = spec.and((r, q, cb) -> cb.equal(r.get("layout"), layout));
        if (maxCost != null)  spec = spec.and((r, q, cb) -> cb.lessThanOrEqualTo(r.get("cost"), maxCost));
        return spec;
    }

    // ソート
    private Sort buildSort(String sort) {
        if (sort == null) return Sort.by(Sort.Direction.DESC, "createdAt");
        return switch (sort) {
            case "cost_asc"  -> Sort.by(Sort.Direction.ASC,  "cost");
            case "cost_desc" -> Sort.by(Sort.Direction.DESC, "cost");
            case "rating"    -> Sort.by(Sort.Direction.DESC, "rating");
            default          -> Sort.by(Sort.Direction.DESC, "createdAt");
        };
    }
}
