package pe.edu.vallegrande.demo2.service;
import pe.edu.vallegrande.demo2.dao.userDAO;
import pe.edu.vallegrande.demo2.dto.user;

import java.sql.SQLException;
import java.util.List;
public class userservice {
    private final userDAO userDAO = new userDAO();

    public List<user> listar() throws SQLException {
        return userDAO.listar();
    }

    public List<user> inactivo() throws SQLException {
        return userDAO.inactivo();
    }

    public void save(user user) throws SQLException {
        userDAO.save(user);
    }

    public void update(user user) throws SQLException {
        userDAO.update(user);
    }

    public void delete(Long id) throws SQLException {
        userDAO.delete(id);
    }

    public void restablecer(Long id) throws SQLException {
        userDAO.restablecer(id);
    }

    public user ID(Long id) throws SQLException {
        return userDAO.ID(id);
    }
}

