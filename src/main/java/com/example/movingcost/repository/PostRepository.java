package com.example.movingcost.repository;

import com.example.movingcost.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {

    @Query("SELECT AVG(p.cost) FROM Post p " +
           "WHERE (:fromPrefecture IS NULL OR p.fromPrefecture = :fromPrefecture) " +
           "AND (:toPrefecture IS NULL OR p.toPrefecture = :toPrefecture) " +
           "AND (:layout IS NULL OR p.layout = :layout) " +
           "AND (:maxCost IS NULL OR p.cost <= :maxCost)")
    Optional<Double> findAvgCost(
            @Param("fromPrefecture") String fromPrefecture,
            @Param("toPrefecture") String toPrefecture,
            @Param("layout") String layout,
            @Param("maxCost") Integer maxCost);

    @Query("SELECT MIN(p.cost) FROM Post p " +
           "WHERE (:fromPrefecture IS NULL OR p.fromPrefecture = :fromPrefecture) " +
           "AND (:toPrefecture IS NULL OR p.toPrefecture = :toPrefecture) " +
           "AND (:layout IS NULL OR p.layout = :layout) " +
           "AND (:maxCost IS NULL OR p.cost <= :maxCost)")
    Optional<Integer> findMinCost(
            @Param("fromPrefecture") String fromPrefecture,
            @Param("toPrefecture") String toPrefecture,
            @Param("layout") String layout,
            @Param("maxCost") Integer maxCost);

    @Query("SELECT MAX(p.cost) FROM Post p " +
           "WHERE (:fromPrefecture IS NULL OR p.fromPrefecture = :fromPrefecture) " +
           "AND (:toPrefecture IS NULL OR p.toPrefecture = :toPrefecture) " +
           "AND (:layout IS NULL OR p.layout = :layout) " +
           "AND (:maxCost IS NULL OR p.cost <= :maxCost)")
    Optional<Integer> findMaxCost(
            @Param("fromPrefecture") String fromPrefecture,
            @Param("toPrefecture") String toPrefecture,
            @Param("layout") String layout,
            @Param("maxCost") Integer maxCost);
}
