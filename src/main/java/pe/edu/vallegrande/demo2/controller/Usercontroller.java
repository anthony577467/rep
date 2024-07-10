package pe.edu.vallegrande.demo2.controller;





import pe.edu.vallegrande.demo2.dto.user;
import pe.edu.vallegrande.demo2.service.userservice;



import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.ServletException;


import java.io.IOException;
import java.sql.SQLException;
    import java.util.List;


@WebServlet({"/getall", "/getallinactivo", "/crear", "/editar", "/eliminar", "/restablecer"})
public class Usercontroller extends HttpServlet {
    private final userservice userService = new userservice();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            switch (req.getServletPath()) {
                case "/getall" -> lista(req, resp, true);
                case "/getallinactivo" -> lista(req, resp, false);
                case "/crear" -> formm(req, resp, "user-form.jsp");
                case "/editar" -> editt(req, resp);
                case "/eliminar" -> estatus(req, resp, false);
                case "/restablecer" -> estatus(req, resp, true);
                default -> lista(req, resp, true);
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            switch (req.getParameter("action")) {
                case "save" -> saveUser(req, resp);
                case "update" -> updateUser(req, resp);
                case "delete" -> estatus(req, resp, false);
                case "restablecer" -> estatus(req, resp, true);
                default -> lista(req, resp, true);
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void lista(HttpServletRequest req, HttpServletResponse resp, boolean active) throws SQLException, ServletException, IOException {
        List<user> users = active ? userService.listar() : userService.inactivo();
        req.setAttribute("users", users);
        req.getRequestDispatcher(active ? "user-list.jsp" : "inactive-user-list.jsp").forward(req, resp);
    }

    private void formm(HttpServletRequest req, HttpServletResponse resp, String formPage) throws ServletException, IOException {
        req.getRequestDispatcher(formPage).forward(req, resp);
    }

    private void editt(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        req.setAttribute("user", userService.ID(id));
        req.getRequestDispatcher("user-edit.jsp").forward(req, resp);
    }

    private void estatus(HttpServletRequest req, HttpServletResponse resp, boolean active) throws SQLException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        if (active) {
            userService.restablecer(id);
        } else {
            userService.delete(id);
        }
        resp.sendRedirect(active ? "getall" : "getallinactivo");
    }

    private void saveUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        user newUser = new user(
                null,
                req.getParameter("nombre"),
                req.getParameter("apellido"),
                req.getParameter("correo"),
                Boolean.parseBoolean(req.getParameter("activo"))
        );
        userService.save(newUser);
        resp.sendRedirect("getall");
    }

    private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        user updatedUser = new user(
                id,
                req.getParameter("nombre"),
                req.getParameter("apellido"),
                req.getParameter("correo"),
                Boolean.parseBoolean(req.getParameter("activo"))
        );
        userService.update(updatedUser);
        resp.sendRedirect("getall");
    }
}