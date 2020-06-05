package com.modestack.ahmed.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "article_table")
public class ArticleDto implements Serializable {

	@Id
	@GenericGenerator(name = "article", strategy = "increment")
	@GeneratedValue(generator = "article")
	@Column(name = "article_id")
	private int articleId;
	@Column(name = "article_title")
	private String title;
	@Column(name = "article_body")
	private String body;
	@Column(name = "article_author")
	private String author;
	
	@OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="user_id")
	private UserDto user;

	public ArticleDto() {
		System.out.println(this.getClass().getName() + " Constructor Called...");
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "ArticleDto [articleId=" + articleId + ", title=" + title + ", body=" + body + ", author=" + author
				+ "]";
	}

}
