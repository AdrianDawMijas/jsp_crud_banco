package org.iesvdm.jsp_servlet_jdbc.dao;
import org.iesvdm.jsp_servlet_jdbc.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteDAO {
    public void create(Cliente usuario);

    public List<Cliente> getAll();
    public Optional<Cliente> find(int id);
    public Optional<Cliente> findByName(String username);
    public void update(Cliente usuario);
    public List<Cliente> buscarPorNombre(String nombre);

    public void delete(int id);
}
