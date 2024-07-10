<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit User</title>
    <!-- Agregar Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Estilos adicionales si los necesitas */
    </style>
</head>
<body>
<div class="container">
    <h2 class="my-4">Edit User</h2>
    <form action="editar" method="post">
        <input type="hidden" name="id" value="${user.id}">
        <div class="form-group">
            <label for="nombre">Nombre:</label>
            <input type="text" class="form-control" id="nombre" name="nombre" value="${user.nombre}" required>
        </div>

        <div class="form-group">
            <label for="apellido">Apellido:</label>
            <input type="text" class="form-control" id="apellido" name="apellido" value="${user.apellido}" required>
        </div>

        <div class="form-group">
            <label for="correo">Correo:</label>
            <input type="email" class="form-control" id="correo" name="correo" value="${user.correo}" required>
        </div>

        <div class="form-check">
            <input type="checkbox" class="form-check-input" id="activo" name="activo" value="true" ${user.activo ? 'checked' : ''}>
            <label class="form-check-label" for="activo">Activo</label>
        </div>

        <input type="hidden" name="action" value="update">
        <button type="submit" class="btn btn-primary mt-3">Actualizar</button>
    </form>
</div>

<!-- Agregar Bootstrap JS al final del cuerpo -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>
