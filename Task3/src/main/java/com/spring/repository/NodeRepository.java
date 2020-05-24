package com.spring.repository;

import com.spring.entity.NodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
public interface NodeRepository extends JpaRepository<NodeEntity, Long> {
    List<NodeEntity> findAllByUser(String user);
    @Query(value = "SELECT * " + "FROM NODES " + "WHERE earth_box(ll_to_earth(?1,?2), ?3) " + "@> " + "ll_to_earth(lat, lon) ORDER by earth_distance(ll_to_earth(?1,?2), ll_to_earth(lat, lon));", nativeQuery = true )
    List<NodeEntity> getAllNodesByRadius (Double lat, Double lon, Long radius);
}
