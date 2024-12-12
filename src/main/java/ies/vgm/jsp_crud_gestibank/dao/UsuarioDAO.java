package ies.vgm.jsp_crud_gestibank.dao;


import ies.vgm.jsp_crud_gestibank.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioDAO {
    public void create(Usuario usuario);

    public List<Usuario> getAll();
    public Optional<Usuario> find(int id);
    public Optional<Usuario> findByName(String username);
    public void update(Usuario usuario);

    public void delete(int id);
}
