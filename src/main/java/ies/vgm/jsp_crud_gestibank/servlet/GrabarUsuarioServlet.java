package ies.vgm.jsp_crud_gestibank.servlet;

import ies.vgm.jsp_crud_gestibank.dao.UsuarioDAO;
import ies.vgm.jsp_crud_gestibank.dao.UsuarioDAOImpl;
import ies.vgm.jsp_crud_gestibank.model.Usuario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

//PLANTILLA DE CÓDIGO PARA SERVLETs EN INTELLIJ
//https://www.jetbrains.com/help/idea/creating-and-configuring-web-application-elements.html

//1A APROX. PATRÓN MVC -> M(dao, model y bbdd), V(jsp) & C(servlet)

//                      v--NOMBRE DEL SERVLET           v--RUTAS QUE ATIENDE, PUEDE SER UN ARRAY {"/GrabarSociosServlet", "/grabar-socio"}
@WebServlet(name = "GrabarUsuarioServlet", value = "/GrabarUsuarioServlet")
public class GrabarUsuarioServlet extends HttpServlet {
    private UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/crearUsuario.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = null;
        Optional<Usuario> optionalUsuario = null;
        try {
            optionalUsuario = UtilServlet.validaLoginHash(request);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
            int id = optionalUsuario.get().getId();
        if (optionalUsuario.isPresent() && usuarioDAO.find(id).isEmpty()) {

            Usuario usuario = optionalUsuario.get();
            this.usuarioDAO.create(usuario);

            List<Usuario> listado = this.usuarioDAO.getAll();

            request.setAttribute("usuarios", listado);
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/listarUsuarios.jsp");
        } else {

            //El OPTIONAL ESTÁ VACÍO (EMPTY)
            //PREPARO MENSAJE DE ERROR EN EL ÁMBITO DEL REQUEST PARA LA VISTA JSP
            //                                |
            //                                V
            request.setAttribute("error", "Error de validación!");

            //POR ÚLTIMO, REDIRECCIÓN INTERNA PARA LA URL /GrabarSocioServlet A formularioSocio.jsp
            //                                                                      |
            //                                                                      V
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/crearUsuario.jsp");
        }


        //SIEMPRE PARA HACER EFECTIVA UNA REDIRECCIÓN INTERNA DEL SERVIDOR
        //TENEMOS QUE HACER FORWARD CON LOS OBJETOS request Y response
        dispatcher.forward(request,response);

    }

}