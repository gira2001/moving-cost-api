package com.example.movingcost.controller;

import com.example.movingcost.dto.PostRequest;
import com.example.movingcost.dto.PostResponse;
import com.example.movingcost.dto.StatsResponse;
import com.example.movingcost.service.PostService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 投稿作成
    @PostMapping("/posts")
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody PostRequest request) {
        PostResponse response = postService.create(request);
        return ResponseEntity.ok(Map.of(
                "id", response.getId(),
                "message", "投稿しました"
        ));
    }

    // 投稿一覧取得
    @GetMapping("/posts")
    public ResponseEntity<Map<String, Object>> findAll(
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String to,
            @RequestParam(required = false) String layout,
            @RequestParam(required = false) Integer maxCost,
            @RequestParam(required = false) String sort,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "6") int limit) {

        Page<PostResponse> result = postService.findAll(from, to, layout, maxCost, sort, page, limit);
        return ResponseEntity.ok(Map.of(
                "total", result.getTotalElements(),
                "page", page,
                "limit", limit,
                "posts", result.getContent()
        ));
    }

    // 投稿1件取得
    @GetMapping("/posts/{id}")
    public ResponseEntity<PostResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.findById(id));
    }

    // 統計取得
    @GetMapping("/stats")
    public ResponseEntity<StatsResponse> getStats(
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String to,
            @RequestParam(required = false) String layout,
            @RequestParam(required = false) Integer maxCost) {

        return ResponseEntity.ok(postService.getStats(from, to, layout, maxCost));
    }
}
