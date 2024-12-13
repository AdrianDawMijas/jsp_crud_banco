<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.iesvdm.jsp_servlet_jdbc.model.Cliente" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestibank - Detalle del Cliente</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <div class="card p-4 shadow">
        <%
            Cliente cliente = (Cliente) request.getAttribute("cliente");
            if (cliente != null) {
        %>
        <h3 class="text-center"><%= cliente.getNombre() %></h3>
        <p><strong>Código:</strong> <%= cliente.getId() %></p>
        <p><strong>Dirección:</strong> <%= cliente.getDireccion() %></p>
        <p><strong>Teléfono:</strong> <%= cliente.getTelefono() %></p>
        <p><strong>Fecha de nacimiento:</strong> <%= cliente.getFechaNacimiento() %></p>
        <form method="get" action="ListadoClientesServlet">
            <button class="btn btn-secondary mt-3">Volver</button>
        </form>
        <%
        } else {
        %>
        <p class="text-center text-danger">Cliente no encontrado.</p>
        <%
            }
        %>
    </div>
</div>
</body>
</html>
