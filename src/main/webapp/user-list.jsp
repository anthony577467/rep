<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Active Users List</title>
    <!-- Agregar Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Estilos adicionales si los necesitas */
    </style>
</head>
<body>
<div class="container">
    <h2 class="my-4">Active Users List</h2>

    <form action="getallinactivo" method="get" class="mb-3">
        <input type="submit" class="btn btn-danger" value="View Inactive Users">
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
            <c:if test="${user.activo}">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.nombre}</td>
                    <td>${user.apellido}</td>
                    <td>${user.correo}</td>
                    <td>${user.activo ? "Yes" : "No"}</td>
                    <td>
                        <a href="editar?id=${user.id}" class="btn btn-primary">Actualizar</a>
                    </td>
                    <td>
                        <form action="eliminar" method="post">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="id" value="${user.id}">
                            <button type="submit" class="btn btn-danger">Eliminar</button>
                        </form>
                    </td>
                </tr>
            </c:if>
        </c:forEach>
        </tbody>
    </table>
</div>

<!-- Agregar Bootstrap JS al final del cuerpo -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>

