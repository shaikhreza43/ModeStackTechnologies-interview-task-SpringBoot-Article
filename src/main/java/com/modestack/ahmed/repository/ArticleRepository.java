package com.modestack.ahmed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.modestack.ahmed.models.ArticleDto;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleDto, Integer> {

}
