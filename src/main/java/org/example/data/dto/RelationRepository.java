package org.example.data.dto;

import java.math.BigInteger;

import org.example.data.Relation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationRepository extends CrudRepository<Relation, BigInteger> {
}
