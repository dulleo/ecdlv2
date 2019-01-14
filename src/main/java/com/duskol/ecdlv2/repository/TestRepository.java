package com.duskol.ecdlv2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duskol.ecdlv2.entity.Test;

@Repository
public interface TestRepository extends JpaRepository<Test, Long>{

}
