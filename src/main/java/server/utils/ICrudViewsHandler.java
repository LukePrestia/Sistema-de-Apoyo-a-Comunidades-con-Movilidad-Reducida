package server.utils;

import domain.persona.Rol;
import domain.persona.Usuario;
import domain.repositorios.UsuarioRepositorio;
import io.javalin.http.Context;

public interface ICrudViewsHandler {
    void index(Context context);
    void show(Context context);
    void create(Context context);
    void save(Context context);
    void edit(Context context);
    void update(Context context);
    void delete(Context context);

    default boolean esAdmin(Context contexto){
        if(contexto.sessionAttribute("usuario_id")!=null){
            long id = contexto.sessionAttribute("usuario_id");
            UsuarioRepositorio usuarioRepositorio = UsuarioRepositorio.getInstancia();
            Usuario usuario = usuarioRepositorio.buscarPorId(Usuario.class,id);
            return usuario.getRol().equals(Rol.ADMIN);
        }
       return false;

    }
}
