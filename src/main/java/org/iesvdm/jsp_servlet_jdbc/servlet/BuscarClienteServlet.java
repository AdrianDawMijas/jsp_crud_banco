package org.iesvdm.jsp_servlet_jdbc.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesvdm.jsp_servlet_jdbc.dao.ClienteDAOImpl;
import org.iesvdm.jsp_servlet_jdbc.model.Cliente;


import java.io.IOException;
import java.util.List;

@WebServlet("/BuscarClienteServlet")
public class BuscarClienteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el parámetro "nombre" desde la solicitud
        String nombre = request.getParameter("nombre");

        if (nombre != null && !nombre.trim().isEmpty()) {
            // Crear instancia del DAO
            ClienteDAOImpl clienteDAO = new ClienteDAOImpl();

            // Buscar clientes por nombre
            List<Cliente> clientes = clienteDAO.buscarPorNombre(nombre);
            if (clientes.size() > 1) {
                // Pasar la lista de clientes como atributo
                request.setAttribute("clientes", clientes);

                // Redirigir al JSP de resultados (listaClientes.jsp)
                request.getRequestDispatcher("/WEB-INF/jsp/listaClientes.jsp").forward(request, response);
            } else if (clientes.size()==1) {
                Cliente cliente = clientes.getFirst();
                request.setAttribute("cliente", cliente);
                request.getRequestDispatcher("/WEB-INF/jsp/detalleCliente.jsp").forward(request, response);
            }
        } else {
            // Si no se proporciona un nombre válido, redirigir al listado general
            response.sendRedirect("ListarClientesServlet");
        }
    }
}
