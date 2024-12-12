package ies.vgm.jsp_crud_gestibank.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesvdm.jsp_servlet_jdbc.dao.UsuarioDAO;
import org.iesvdm.jsp_servlet_jdbc.dao.UsuarioDAOImpl;

import java.io.IOException;

@WebServlet(name = "PiramideServlet", value = "/PiramideServlet")
public class PiramideServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO = new UsuarioDAOImpl();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/piramide.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = null;
        int altura = Integer.parseInt(request.getParameter("altura"));
        if (altura > 0) {
            request.setAttribute("altura", altura);
        }
        else{
                request.setAttribute("altura", 0);
            }
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/resultado.jsp");
            dispatcher.forward(request, response);


    }
}
