<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.iesvdm.jsp_servlet_jdbc.model.Cliente" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestibank - Listado de Clientes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center">Gestibank</h1>

    <!-- Tabla de clientes -->
    <table class="table table-striped mt-4">
        <thead>
        <tr>
            <th>Código</th>
            <th>Nombre</th>
            <th>Dirección</th>
            <th>Teléfono</th>
            <th>Fecha de Nacimiento</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientes");
            if (clientes != null && !clientes.isEmpty()) {
                for (Cliente cliente : clientes) {
        %>
        <tr>
            <td><%= cliente.getId() %></td>
            <td><%= cliente.getNombre() %></td>
            <td><%= cliente.getDireccion() %></td>
            <td><%= cliente.getTelefono() %></td>
            <td><%= cliente.getFechaNacimiento() %></td>
            <td>
                <!-- Formulario para editar -->
                <form method="get" action="EditarClienteServlet" style="display:inline;">
                    <input type="hidden" name="id" value="<%= cliente.getId() %>">
                    <button type="submit" class="btn btn-primary btn-sm">Editar</button>
                </form>
                <!-- Formulario para borrar -->
                <form method="get" action="BorrarClienteServlet" style="display:inline;">
                    <input type="hidden" name="id" value="<%= cliente.getId() %>">
                    <button type="submit" class="btn btn-danger btn-sm">Borrar</button>
                </form>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="6" class="text-center">No hay clientes registrados</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>

    <!-- Formulario para agregar un nuevo cliente -->
    <form action="AgregarClienteServlet" method="post" class="mt-4">
        <input type="hidden" name="action" value="create">
        <div class="row">
            <div class="col">
                <input type="text" class="form-control" name="nombre" placeholder="Nombre" required>
            </div>
            <div class="col">
                <input type="text" class="form-control" name="direccion" placeholder="Dirección" required>
            </div>
            <div class="col">
                <input type="text" class="form-control" name="telefono" placeholder="Teléfono" required>
            </div>
            <div class="col">
                <input type="date" class="form-control" name="fechaNacimiento" required>
            </div>
            <div class="col">
                <button type="submit" class="btn btn-success">Añadir</button>
            </div>
        </div>
    </form>
    <br><br>

    <!-- Formulario de búsqueda -->
    <form action="BuscarClienteServlet" method="get" class="d-flex mb-4">
        <input type="hidden" name="action" value="search">
        <input type="text" class="form-control me-2" name="nombre" placeholder="Buscar por nombre" required>
        <button type="submit" class="btn btn-primary">Buscar</button>
    </form>


</div>
</body>
</html>
