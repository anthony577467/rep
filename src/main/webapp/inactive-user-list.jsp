<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Inactive Users List</title>
    <!-- Agregar Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Estilos adicionales si los necesitas */
    </style>
</head>
<body>
<div class="container">
    <h2 class="my-4">Inactive Users List</h2>
    <form action="getall" method="get">
        <input type="submit" class="btn btn-primary mb-3" value="View Active Users">
    </form>

    <table class="table table-bordered">
        <thead class="thead-dark">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Correo</th>
            <th>Activo</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.id}</td>
                <td>${user.nombre}</td>
                <td>${user.apellido}</td>
                <td>${user.correo}</td>
                <td>${user.activo ? "Yes" : "No"}</td>
                <td>
                    <form action="restablecer" method="post">
                        <input type="hidden" name="action" value="restablecer">
                        <input type="hidden" name="id" value="${user.id}">
                        <button type="submit" class="btn btn-success">Restablecer</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<!-- Agregar Bootstrap JS al final del cuerpo -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>
