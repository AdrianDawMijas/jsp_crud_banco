package org.iesvdm.jsp_servlet_jdbc.dao;

import org.iesvdm.jsp_servlet_jdbc.model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteDAOImpl extends AbstractDAOImpl implements ClienteDAO {

    public void create(Cliente cliente) {
        try (Connection conn = connectDB();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO cliente (nombre, direccion, telefono, fecha_nacimiento) VALUES (?, ?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS)) {

            int idx = 1;
            ps.setString(idx++, cliente.getNombre());
            ps.setString(idx++, cliente.getDireccion());
            ps.setString(idx++, cliente.getTelefono());
            ps.setString(idx++, cliente.getFechaNacimiento());

            int rows = ps.executeUpdate();
            if (rows == 0) {
                System.out.println("INSERT de cliente con 0 filas insertadas.");
            }

            try (ResultSet rsGenKeys = ps.getGeneratedKeys()) {
                if (rsGenKeys.next()) {
                    cliente.setId(rsGenKeys.getInt(1));
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Cliente> getAll() {
        List<Cliente> clientes = new ArrayList<>();
        try (Connection conn = connectDB();
             Statement s = conn.createStatement();
             ResultSet rs = s.executeQuery("SELECT * FROM cliente")) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("fecha_nacimiento")
                );
                clientes.add(cliente);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public Optional<Cliente> find(int id) {
        try (Connection conn = connectDB();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM cliente WHERE id = ?")) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("direccion"),
                            rs.getString("telefono"),
                            rs.getString("fecha_nacimiento")
                    );
                    return Optional.of(cliente);
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    public Optional<Cliente> findByName(String nombre) {
        try (Connection conn = connectDB();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM cliente WHERE nombre = ?")) {

            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("direccion"),
                            rs.getString("telefono"),
                            rs.getString("fecha_nacimiento")
                    );
                    return Optional.of(cliente);
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    public List<Cliente> buscarPorNombre(String nombre) {
        List<Cliente> clientes = new ArrayList<>();

        try (Connection conn = connectDB();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM cliente WHERE nombre LIKE ?")) {

            // Usar el operador LIKE con comodines para coincidencias parciales
            ps.setString(1, "%" + nombre + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // Crear y llenar un objeto Cliente con los datos obtenidos
                    Cliente cliente = new Cliente();
                    cliente.setId(rs.getInt("id"));
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setDireccion(rs.getString("direccion"));
                    cliente.setTelefono(rs.getString("telefono"));
                    cliente.setFechaNacimiento(rs.getString("fecha_nacimiento"));
                    clientes.add(cliente);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return clientes;
    }


    public void update(Cliente cliente) {
        try (Connection conn = connectDB();
             PreparedStatement ps = conn.prepareStatement(
                     "UPDATE cliente SET nombre = ?, direccion = ?, telefono = ?, fecha_nacimiento = ? WHERE id = ?")) {

            int idx = 1;
            ps.setString(idx++, cliente.getNombre());
            ps.setString(idx++, cliente.getDireccion());
            ps.setString(idx++, cliente.getTelefono());
            ps.setString(idx++, cliente.getFechaNacimiento());
            ps.setInt(idx++, cliente.getId());

            int rows = ps.executeUpdate();
            if (rows == 0) {
                System.out.println("UPDATE de cliente con 0 filas actualizadas.");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection conn = connectDB();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM cliente WHERE id = ?")) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            if (rows == 0) {
                System.out.println("DELETE de cliente con 0 filas eliminadas.");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
