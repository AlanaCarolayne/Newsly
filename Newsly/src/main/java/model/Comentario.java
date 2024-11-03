package model;

public class Comentario {
private int id;
private Noticia noticia;
private Usuario usuario;
private String comentario;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}

public String getComentario() {
	return comentario;
}
public void setComentario(String comentario) {
	this.comentario = comentario;
}

public Noticia getNoticia() {
	return noticia;
}
public void setNoticia(Noticia noticia) {
	this.noticia = noticia;
}
public Usuario getUsuario() {
	return usuario;
}
public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
}
public Comentario(int id, Noticia noticia, Usuario usuario, String comentario) {
	this.id = id;
	this.noticia = noticia;
	this.usuario = usuario;
	this.comentario = comentario;
}
public Comentario() {
}
public Comentario(Noticia noticia, Usuario usuario, String comentario) {
	this.noticia = noticia;
	this.usuario = usuario;
	this.comentario = comentario;
}


}
