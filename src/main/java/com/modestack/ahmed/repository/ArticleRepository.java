package com.modestack.ahmed.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.modestack.ahmed.models.ArticleDto;

@Repository
public interface ArticleRepository extends PagingAndSortingRepository<ArticleDto, Integer> {

}
