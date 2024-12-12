package org.iesvdm.jsp_servlet_jdbc.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesvdm.jsp_servlet_jdbc.dao.ClienteDAO;
import org.iesvdm.jsp_servlet_jdbc.dao.ClienteDAOImpl;
import org.iesvdm.jsp_servlet_jdbc.model.Cliente;

import java.io.IOException;
import java.util.List;

@WebServlet("/DetalleClienteServlet")
public class DetalleClienteServlet extends HttpServlet {

    private ClienteDAO clienteDAO = new ClienteDAOImpl();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Obtener el parámetro "id" del cliente
        String idParam = request.getParameter("id");

        if (idParam != null && !idParam.isEmpty()) {
            try {
                // Convertir el id a un entero
                int id = Integer.parseInt(idParam);

                // Llamar al método de borrado
                Cliente cliente = clienteDAO.find(id).get();
                if (cliente != null) {

                    request.setAttribute("cliente", cliente);
                    request.getRequestDispatcher("/WEB-INF/jsp/detalleCliente.jsp").forward(request, response);
                }
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

