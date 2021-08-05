package com.example.transformer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.transformer.model.Transformer;

@Repository
public interface Repo extends JpaRepository<Transformer, Long>{

}
