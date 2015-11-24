package ru.ifmo.javawebapp;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Сервлет контакт листа.
 * 
 * Реализован простой REST JSON API:
 *   /contacts?action=createdb - создать БД.
 *   /contacts?action=list - получить список контактов.
 *   /contacts?action=add&fio=...&phone=...&email=... - добавить контакт.
 *   /contacts?action=remove&id=... - удалить контакт.
 * 
 * @author Anton Skshidlevsky
 */
@WebServlet(name = "ContactsServlet", urlPatterns = {"/contacts"})
public class ContactsServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Если не задан параметр action, то возвратить ошибку 400.
        if (request.getParameter("action") == null) {
            response.sendError(400, "Bad Request");
            return;
        }

        // Отправить ответ клиенту в формате JSON
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            ContactList cl = new ContactList();

            if (request.getParameter("action").equals("list")) {
                out.println(cl.list());
            }

            if (request.getParameter("action").equals("add")) {
                Contact c = new Contact(null,
                        request.getParameter("fio"),
                        request.getParameter("phone"),
                        request.getParameter("email"));
                out.println(cl.add(c));
            }

            if (request.getParameter("action").equals("remove")) {
                out.println(cl.remove(request.getParameter("id")));
            }
            
            if (request.getParameter("action").equals("createdb")) {
                out.println(cl.createDB());
            }            
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
