<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Acceso Permitido</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container d-flex justify-content-center align-items-center" style="height: 100vh;">
    <div class="card p-4 shadow text-center" style="width: 25rem;">
        <h3>Control de acceso</h3>
        <div class="mt-3">
            <img src="https://cdn-icons-png.flaticon.com/512/190/190411.png" alt="Acceso permitido" width="100">
        </div>
        <p class="mt-3">Acceso permitido a la aplicacion.</p>
        <form class="d-grid" method="get" action="PiramideServlet">
            <button class="btn btn-success">ACEPTAR</button>
        </form>
    </div>
</div>
</body>
</html>
