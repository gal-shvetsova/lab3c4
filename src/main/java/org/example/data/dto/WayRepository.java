package org.example.data.dto;

import java.math.BigInteger;

import org.example.data.Way;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WayRepository extends CrudRepository<Way, BigInteger> {
}
