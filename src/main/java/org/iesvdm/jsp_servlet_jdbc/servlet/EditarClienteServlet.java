package org.iesvdm.jsp_servlet_jdbc.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesvdm.jsp_servlet_jdbc.dao.ClienteDAOImpl;
import org.iesvdm.jsp_servlet_jdbc.model.Cliente;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/EditarClienteServlet")
public class EditarClienteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el parámetro "id" del cliente
        String idParam = request.getParameter("id");

        if (idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);

                // Instanciar el DAO y buscar el cliente por id
                ClienteDAOImpl clienteDAO = new ClienteDAOImpl();
                Optional<Cliente> clienteOpt = clienteDAO.find(id);

                if (clienteOpt.isPresent()) {
                    // Pasar el cliente al JSP de edición
                    request.setAttribute("cliente", clienteOpt.get());
                    request.getRequestDispatcher("/WEB-INF/jsp/editarCliente.jsp").forward(request, response);
                } else {
                    // Si no se encuentra el cliente, redirigir al listado con un mensaje
                    response.sendRedirect("ListadoClientesServlet?error=notfound");
                }
            } catch (NumberFormatException e) {
                // Manejar si el id no es válido
                response.sendRedirect("ListadoClientesServlet?error=invalidid");
            }
        } else {
            // Redirigir al listado si no se proporciona un id
            response.sendRedirect("ListadoClientesServlet?error=missingid");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los datos del formulario
        String idParam = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");
        String fechaNacimiento = request.getParameter("fechaNacimiento");

        if (idParam != null && !idParam.isEmpty() && nombre != null && direccion != null && telefono != null && fechaNacimiento != null) {
            try {
                int id = Integer.parseInt(idParam);

                // Crear un objeto Cliente con los datos actualizados
                Cliente cliente = new Cliente();
                cliente.setId(id);
                cliente.setNombre(nombre);
                cliente.setDireccion(direccion);
                cliente.setTelefono(telefono);
                cliente.setFechaNacimiento(fechaNacimiento);

                // Actualizar el cliente en la base de datos
                ClienteDAOImpl clienteDAO = new ClienteDAOImpl();
                clienteDAO.update(cliente);

                // Redirigir al listado de clientes
                response.sendRedirect("ListadoClientesServlet?success=updated");
            } catch (NumberFormatException e) {
                // Manejar errores en la conversión de id
                response.sendRedirect("ListadoClientesServlet?error=invalidid");
            }
        } else {
            // Redirigir al formulario con un mensaje de error si faltan datos
            response.sendRedirect("EditarClienteServlet?id=" + idParam + "&error=missingdata");
        }
    }
}
