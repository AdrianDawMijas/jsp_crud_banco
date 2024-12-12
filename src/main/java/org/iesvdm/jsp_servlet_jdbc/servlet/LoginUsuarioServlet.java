package org.iesvdm.jsp_servlet_jdbc.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesvdm.jsp_servlet_jdbc.dao.UsuarioDAO;
import org.iesvdm.jsp_servlet_jdbc.dao.UsuarioDAOImpl;
import org.iesvdm.jsp_servlet_jdbc.model.Usuario;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "LoginUsuarioServlet", value = "/LoginUsuarioServlet")
public class LoginUsuarioServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

    //HTML5 SÓLO SOPORTA GET Y POST
    //FRENTE A API REST UTLIZANDO CÓDIGO DE CLIENTE JS HTTP: GET, POST, PUT, DELETE, PATCH

    //MÉTODO PARA RUTAS GET /GrabarSociosServlet
    //PARA LA RUTA /GrabarSociosServlet VA A MOSTRAR LA JSP DE formularioSocio.jsp
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //SE TRATA DE UNA REDIRECCIÓN INTERNA EN EL SERVIDOR
        //FIJÉMONOS QUE LA RUTA DE LA JSP HA CAMBIADO A DENTRO DE /WEB-INF/
        //POR LO TANTO NO ES ACCESIBLE DIRECTAMENTE, SÓLO A TRAVÉS DE SERVLET
        //MEDIANTE UN RequestDispatcher ----------------v
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");

        //SIEMPRE QUE HACEMOS UN RequestDispatcher DEBE MATERIALIZARSE EN UN forward
        //             --------------------------------------------------------|
        //            V      v---------v-----SE LE PASAN LOS OBJETOS request Y response PARA HACER EFECTIVA
        dispatcher.forward(request, response); // LA REDIRECCIÓN INTERNA EN EL SERVIDOR A UNA JSP O VISTA.

    }


    //MÉTODO PARA RUTAS POST /GrabarSociosServlet
    //PARA LA RUTA POST /GrabarSociosServlet HAY 2 OPCIONES DE REDIRECCIÓN INTERNA A JSP
    //1a CASO DE QUE SE VALIDE CORRECTAMENTE --> pideNumeroSocio.jsp
    //2o CASO DE QUE NO SE VALIDE CORRECTAMENTE --> formularioSocio.jsp CON INFORME DE ERROR
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = null;

        //CÓDIGO DE VALIDACIÓN ENCAPSULADO EN UN MÉTODO DE UTILERÍA
        // SI OK ==> OPTIONAL CON SOCIO                 |
        // SI FAIL ==> EMPTY OPTIONAL                   |
        //                                              V
        Optional<Usuario> optionalUsuario = UtilServlet.validaLogin(request);

        //SI OPTIONAL CON SOCIO PRESENTE <--> VALIDA OK
        if (optionalUsuario.isPresent() && usuarioDAO.find(optionalUsuario.get().getId()).isPresent()) {

            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/acceso.jsp");
        } else {

            //El OPTIONAL ESTÁ VACÍO (EMPTY)
            //PREPARO MENSAJE DE ERROR EN EL ÁMBITO DEL REQUEST PARA LA VISTA JSP
            //                                |
            //                                V
            request.setAttribute("error", "Error de validación!");

            //POR ÚLTIMO, REDIRECCIÓN INTERNA PARA LA URL /GrabarSocioServlet A formularioSocio.jsp
            //                                                                      |
            //                                                                      V
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/acceso.jsp");
        }


        //SIEMPRE PARA HACER EFECTIVA UNA REDIRECCIÓN INTERNA DEL SERVIDOR
        //TENEMOS QUE HACER FORWARD CON LOS OBJETOS request Y response
        dispatcher.forward(request,response);

    }
}
