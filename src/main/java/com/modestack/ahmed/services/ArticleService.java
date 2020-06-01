package com.modestack.ahmed.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.modestack.ahmed.models.ArticleDto;
import com.modestack.ahmed.repository.ArticleRepository;

@Service
public class ArticleService {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	public ArticleDto createArticle(ArticleDto article){
		ArticleDto saved = articleRepository.save(article);
		return saved;
	}
	
	public List<ArticleDto> fetchAllArticles(){
		List<ArticleDto> findAll = (List<ArticleDto>) articleRepository.findAll();
		return findAll;
	}
	
	public List<ArticleDto> fetchPaginatedArticles(int pageNo,int pageSize){
		Pageable paging = PageRequest.of(pageNo, pageSize);
		Page<ArticleDto> findAll = articleRepository.findAll(paging);
		return findAll.toList();
	}
}
