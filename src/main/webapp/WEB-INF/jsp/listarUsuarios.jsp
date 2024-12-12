<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.iesvdm.jsp_servlet_jdbc.model.Usuario" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Usuarios</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <div class="card p-4 shadow">
        <h3 class="text-center">Gestión de Usuarios</h3>
        <table class="table mt-3">
            <thead>
            <tr>
                <th>ID</th>
                <th>Usuario</th>
                <th>Contraseña</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
                if (usuarios != null && !usuarios.isEmpty()) {
                    for (Usuario usuario : usuarios) {
            %>
            <tr>
                <td><%= usuario.getId() %></td>
                <td><%= usuario.getUsername() %></td>
                <td><%= usuario.getPassword() %></td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="3" class="text-center">No hay usuarios registrados</td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
        <form method="get" action="GrabarUsuarioServlet">
            <button  class="btn btn-primary">Añadir Usuario</button>
        </form>

    </div>
</div>
</body>
</html>
