package com.demo.livraria.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Livro implements Serializable {

	private String title;
	private Integer id;
	private String isbn;
	private Integer authorId;
	private Autor autor;

	public Livro() {
		super();
	}

	public Livro(String title, String isbn) {
		this.title = title;
		this.isbn = isbn;
	}

	public Livro(Integer id, String title, String isbn, Integer authorId) {
		this.title = title;
		this.isbn = isbn;
		this.authorId = authorId;
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

}
