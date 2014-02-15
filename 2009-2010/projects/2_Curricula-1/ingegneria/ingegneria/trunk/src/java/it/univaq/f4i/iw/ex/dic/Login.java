package it.univaq.f4i.iw.ex.dic;

import it.univaq.f4i.iw.ex.dic.data.DataAccess;
import java.io.*;

import java.util.Calendar;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;


public class Login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession s = request.getSession(true);
        String username = request.getParameter("u");
        String password = request.getParameter("p");
        DataAccess a = DataAccess.newDataAccess();

        if (a.checkLogin(username, password)) {
            s.setAttribute("tipologia", a.getTipologia(username, password));

            //se la validazione ha successo
            s.setAttribute("username", username);
            s.setAttribute("ip", request.getRemoteHost());
            s.setAttribute("inizio-sessione", Calendar.getInstance());

            s.setAttribute("userid", a.getIdAccount(username));
            
            response.sendRedirect("pannello");
        } else {
            response.sendRedirect("index?err=1");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>
}
