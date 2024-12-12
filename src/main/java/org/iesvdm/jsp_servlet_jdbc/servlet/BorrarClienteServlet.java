package org.iesvdm.jsp_servlet_jdbc.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesvdm.jsp_servlet_jdbc.dao.ClienteDAOImpl;


import java.io.IOException;

@WebServlet("/BorrarClienteServlet")
public class BorrarClienteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Obtener el parámetro "id" del cliente
        String idParam = request.getParameter("id");

        if (idParam != null && !idParam.isEmpty()) {
            try {
                // Convertir el id a un entero
                int id = Integer.parseInt(idParam);

                // Crear instancia del DAO
                ClienteDAOImpl clienteDAO = new ClienteDAOImpl();

                // Llamar al método de borrado
                clienteDAO.delete(id);

                // Redirigir al listado de clientes
                response.sendRedirect("ListarClientesServlet");

            } catch (NumberFormatException e) {
                // Manejar si el id no es un número válido
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "El ID proporcionado no es válido.");
            }
        } else {
            // Manejar si no se proporcionó un id
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No se proporcionó un ID válido.");
        }
    }
}
