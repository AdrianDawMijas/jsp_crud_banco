<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pirámide de Gatos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container d-flex justify-content-center align-items-center" style="height: 100vh;">
    <div class="card p-4 shadow" style="width: 25rem;">
        <h3 class="text-center">Pinta una pirámide</h3>
        <form action="PiramideServlet" method="post">
            <div class="mb-3">
                <label for="altura" class="form-label">Altura</label>
                <input type="number" class="form-control" id="altura" name="altura" required min="1">
            </div>
            <div class="d-grid">
                <button type="submit" class="btn btn-success">ACEPTAR</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>
