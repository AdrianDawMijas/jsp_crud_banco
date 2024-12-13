package org.iesvdm.jsp_servlet_jdbc.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesvdm.jsp_servlet_jdbc.dao.ClienteDAO;
import org.iesvdm.jsp_servlet_jdbc.dao.ClienteDAOImpl;
import org.iesvdm.jsp_servlet_jdbc.model.Cliente;


import java.io.IOException;

@WebServlet(name = "AgregarClienteServlet", value = "/AgregarClienteServlet")
public class AgregarClienteServlet extends HttpServlet {

    private ClienteDAO clienteDAO = new ClienteDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recibir parámetros del formulario
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");
        String fechaNacimiento = request.getParameter("fechaNacimiento");

        // Validar los datos
        if (nombre == null || direccion == null || telefono == null || fechaNacimiento == null ||
                nombre.isEmpty() || direccion.isEmpty() || telefono.isEmpty() || fechaNacimiento.isEmpty()) {
            request.setAttribute("error", "Todos los campos son obligatorios.");
            return;
        }

        // Crear objeto Cliente y pasar los datos al DAO
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setDireccion(direccion);
        cliente.setTelefono(telefono);
        cliente.setFechaNacimiento(fechaNacimiento);

        try {
            clienteDAO.create(cliente);
            request.setAttribute("success", "Cliente agregado exitosamente con ID: " + cliente.getId());
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al agregar el cliente.");
        }

        // Redirigir al formulario
        response.sendRedirect("ListadoClientesServlet");
    }
}
