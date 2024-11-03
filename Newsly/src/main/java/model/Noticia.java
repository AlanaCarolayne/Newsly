package model;

import java.sql.Date;

public class Noticia {
	private int id;
	private Usuario usuario;
	private Categoria categoria;
	private String desc, titulo;
	private Date data;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Noticia(int id, Usuario usuario, Categoria categoria, String desc, String titulo, Date data) {
		this.id = id;
		this.usuario = usuario;
		this.categoria = categoria;
		this.desc = desc;
		this.titulo = titulo;
		this.data = data;
	}
	public Noticia() {
	}
	public Noticia(Usuario usuario, Categoria categoria, String desc, String titulo, Date data) {
		this.usuario = usuario;
		this.categoria = categoria;
		this.desc = desc;
		this.titulo = titulo;
		this.data = data;
	}
	
	

}
