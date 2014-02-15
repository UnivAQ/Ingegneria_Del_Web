package it.univaq.f4i.iw.ex.dic;

import it.univaq.f4i.iw.ex.dic.data.DataAccess;
import it.univaq.f4i.iw.ex.utils.SecurityHelpers;
import it.univaq.f4i.iw.ex.utils.TemplateManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Reg extends HttpServlet {

    private TemplateManager tpl;
    private DataAccess db;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        //inzializziamo il layer di accesso ai dati

        db = DataAccess.newDataAccess();
        tpl = TemplateManager.getInstance(getServletContext());
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=ISO-8859-1");
        HttpSession s = SecurityHelpers.checkSession(request);

        // String tipo = request.getParameter("tipo");


        tpl = TemplateManager.getInstance(getServletContext());
        Map data = tpl.getDefaultDataModel();
        PrintWriter out = response.getWriter();
        //out.println(db.checkUsername("ricci"));
        if (request.getParameter("tipologia").equals("1")) {
            if (db.insertUtente(request.getParameter("username"), request.getParameter("password"), request.getParameter("email"), request.getParameter("tipologia"))) {
                data.put("username", request.getParameter("username"));
                data.put("password", request.getParameter("password"));
                data.put("email", request.getParameter("email"));
                try {
                    tpl.processTemplate("reg_ok", data, out);
                } catch (ServletException e) {
                    tpl.processTemplate("errorpage", data, out);

                }
            } else {
                data.put("errore", true);

                try {
                    tpl.processTemplate("reg_utente", data, out);
                } catch (ServletException e) {
                    tpl.processTemplate("errorpage", data, out);

                }
            }
        } else {
            if (db.insertAzienda(request.getParameter("username"), request.getParameter("password"), request.getParameter("email"), request.getParameter("tipologia"), request.getParameter("ragione"), request.getParameter("piva"))) {
                data.put("username", request.getParameter("username"));
                data.put("password", request.getParameter("password"));
                data.put("email", request.getParameter("email"));
                try {
                    tpl.processTemplate("reg_ok", data, out);
                } catch (ServletException e) {
                    tpl.processTemplate("errorpage", data, out);

                }
            } else {
                data.put("errore", true);

                try {
                    tpl.processTemplate("reg_azienda", data, out);
                } catch (ServletException e) {
                   // tpl.processTemplate("errorpage", data, out);

                }
            }


        }
    }// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Reg.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Reg.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
