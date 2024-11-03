package test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import dao.CategoriaDAO;
import dao.ComentarioDAO;
import dao.NoticiaDAO;
import dao.UsuarioDAO;
import model.Categoria;
import model.Comentario;
import model.Noticia;
import model.Usuario;

public class Teste {

	public static void main(String[] args) {

        LocalDate localDate = LocalDate.now();
        Date data = Date.valueOf(localDate);
        
     
        CategoriaDAO catDAO = new CategoriaDAO();
        List<Categoria> listaCat = catDAO.listarCategoria();
        Categoria categoriaSelecionada = listaCat.get(0); 
   
//        Usuario u = new Usuario("Eva", "eva@email.com", "123");
//        Usuario u2 = new Usuario("Ivo", "ivo@email.com", "123");
        UsuarioDAO uDAO = new UsuarioDAO();
//        uDAO.inserir(u);
//        uDAO.inserir(u2);
        List<Usuario> listaU = uDAO.listar();
        Usuario u2 = listaU.get(1);
       
        
//        Noticia n = new Noticia(u2, categoriaSelecionada, "Cruzeiro x CAP: jogo", "Cruzeiro perde jogo!", data);
        NoticiaDAO nDAO = new NoticiaDAO();
//        nDAO.inserir(n); 
        List<Noticia> listaN = nDAO.listar(); 
        Noticia n = listaN.get(0);
//        Comentario com = new Comentario(n, u2, "Péssimo jogo");
        ComentarioDAO comDAO = new ComentarioDAO();
//        comDAO.inserir(com);
        List<Comentario> listaCom = comDAO.listar();
        
        
        System.out.println("Usuarios:");
        for (Usuario usuario : listaU) {
            System.out.println(usuario.getId() + " - " + usuario.getNome());
        }
        
        System.out.println("\nCategorias:");
        for (Categoria categoria : listaCat) {
            System.out.println(categoria.getId() + " - " + categoria.getCategoria());
        }
        
        System.out.println("\nNoticias:");
        for (Noticia noticia : listaN) {
            System.out.println(noticia.getId() + " - " + noticia.getTitulo() + " Categoria: " + noticia.getCategoria().getCategoria());
        }
        System.out.println("\n Comentários");
        for (Comentario comentario : listaCom) {
			System.out.println(comentario.getId() + " - " + comentario.getComentario() + " Comentario da noticia: " + comentario.getNoticia().getDesc());
		}
    }
}
