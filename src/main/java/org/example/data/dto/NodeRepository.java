package org.example.data.dto;

import java.math.BigInteger;
import java.util.List;

import org.example.data.Node;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodeRepository extends CrudRepository<Node, BigInteger> {
    @Query(value = "select node from Node node where earth_distance(ll_to_earth(node.lat, node.lon), ll_to_earth(?1, ?2)) < ?3 order by earth_distance(ll_to_earth(node.lat, node.lon), ll_to_earth(?1, ?2)) asc")
    List<Node> findInRegion(Double latitude, Double longitude, Double radius);
}
