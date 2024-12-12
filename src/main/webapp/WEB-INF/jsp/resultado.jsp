<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <div class="card p-4 shadow" style="width: auto;">
        <h3 class="text-center">Pirámide de Gatos</h3>
        <div class="mt-3 text-center">
            <%
                int altura = Integer.parseInt(request.getParameter("altura"));
                for (int i = 1; i <= altura; i++) {
                    for (int j = 1; j <= i; j++) {
            %>
            <img src="${pageContext.request.contextPath}/images/cat.jpg" alt="Gato" width="90">
            <%
                    }
                    out.println("<br>");
                }
            %>
        </div>
    </div>
</div>
</body>
</html>
