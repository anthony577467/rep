package pe.edu.vallegrande.demo2.dao;

import pe.edu.vallegrande.demo2.dto.user;
import pe.edu.vallegrande.demo2.conexion.conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class userDAO {

    private static final String listar = "SELECT * FROM users";
    private static final String inactivo = "SELECT * FROM users WHERE activo = 0";
    private static final String insert = "INSERT INTO users (nombre, apellido, correo, activo) VALUES (?, ?, ?, ?)";
    private static final String update = "UPDATE users SET nombre = ?, apellido = ?, correo = ?, activo = ? WHERE id = ?";
    private static final String delete = "UPDATE users SET activo = 0 WHERE id = ?";
    private static final String restablecer = "UPDATE users SET activo = 1 WHERE id = ?";
    private static final String selectid = "SELECT * FROM users WHERE id = ?";

    private user map(ResultSet rs) throws SQLException {
        return new user(
                rs.getLong("id"),
                rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getString("correo"),
                rs.getBoolean("activo")
        );
    }

    public List<user> listar() throws SQLException {
        List<user> users = new ArrayList<>();
        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(listar);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                users.add(map(rs));
            }
        }
        return users;
    }

    public List<user> inactivo() throws SQLException {
        List<user> users = new ArrayList<>();
        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(inactivo);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                users.add(map(rs));
            }
        }
        return users;
    }

    public void save(user user) throws SQLException {
        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insert)) {
            stmt.setString(1, user.getNombre());
            stmt.setString(2, user.getApellido());
            stmt.setString(3, user.getCorreo());
            stmt.setBoolean(4, user.isActivo());
            stmt.executeUpdate();
        }
    }

    public void update(user user) throws SQLException {
        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(update)) {
            stmt.setString(1, user.getNombre());
            stmt.setString(2, user.getApellido());
            stmt.setString(3, user.getCorreo());
            stmt.setBoolean(4, user.isActivo());
            stmt.setLong(5, user.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(Long id) throws SQLException {
        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(delete)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    public void restablecer(Long id) throws SQLException {
        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(restablecer)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    public user ID(Long id) throws SQLException {
        user user = null;
        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(selectid)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = map(rs);
                }
            }
        }
        return user;
    }
}
