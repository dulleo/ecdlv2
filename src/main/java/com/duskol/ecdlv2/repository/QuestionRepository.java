package com.duskol.ecdlv2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duskol.ecdlv2.entity.Question;

/**
 * 
 * Cretaed Jan 15, 2019 by duskol
 *
 */
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>{

}
