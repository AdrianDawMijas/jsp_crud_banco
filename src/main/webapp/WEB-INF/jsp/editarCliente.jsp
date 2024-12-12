<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.iesvdm.jsp_servlet_jdbc.model.Cliente" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestibank - Editar Cliente</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center">Editar Cliente</h1>
    <%
        Cliente cliente = (Cliente) request.getAttribute("cliente");
        if (cliente != null) {
    %>
    <form action="EditarClienteServlet" method="post" class="mt-4">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="<%= cliente.getId() %>">
        <div class="mb-3">
            <label for="nombre" class="form-label">Nombre</label>
            <input type="text" class="form-control" id="nombre" name="nombre" value="<%= cliente.getNombre() %>" required>
        </div>
        <div class="mb-3">
            <label for="direccion" class="form-label">Dirección</label>
            <input type="text" class="form-control" id="direccion" name="direccion" value="<%= cliente.getDireccion() %>" required>
        </div>
        <div class="mb-3">
            <label for="telefono" class="form-label">Teléfono</label>
            <input type="text" class="form-control" id="telefono" name="telefono" value="<%= cliente.getTelefono() %>" required>
        </div>
        <div class="mb-3">
            <label for="fechaNacimiento" class="form-label">Fecha de Nacimiento</label>
            <input type="date" class="form-control" id="fechaNacimiento" name="fechaNacimiento" value="<%= cliente.getFechaNacimiento() %>" required>
        </div>
        <button type="submit" class="btn btn-primary">Actualizar</button>
    </form>
    <%
    } else {
    %>
    <p class="text-center text-danger">Cliente no encontrado</p>
    <%
        }
    %>
</div>
</body>
</html>
