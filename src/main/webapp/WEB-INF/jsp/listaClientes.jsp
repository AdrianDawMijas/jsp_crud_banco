<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.iesvdm.jsp_servlet_jdbc.model.Cliente" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestibank - Resultados de Búsqueda</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center">Gestibank</h1>
    <table class="table table-striped mt-4">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Acción</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientes");
            if (clientes != null && !clientes.isEmpty()) {
                for (Cliente cliente : clientes) {
        %>
        <tr>
            <td><%= cliente.getNombre() %></td>
            <td>
                <form method="get" action="DetalleClienteServlet">
                    <input type="hidden" name="id" value="<%= cliente.getId() %>">
                    <button class="btn btn-primary">Detalle</button>
                </form>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="2" class="text-center">No se encontraron clientes.</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>
</body>
</html>
