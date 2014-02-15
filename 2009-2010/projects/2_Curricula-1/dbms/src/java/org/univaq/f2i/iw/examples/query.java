/*
 * query.java
 *
 */

package org.univaq.f2i.iw.examples;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Giuseppe Della Penna
 * @version
 */
public class query extends HttpServlet {
    
    private DataAccess da;
    private TemplateManager tpl;
    
    public void init() throws ServletException {
        super.init();
        da =  DataAccess.newDataAccess();
        tpl = TemplateManager.getInstance(getServletContext());
    }
    
    
    
    private void makeTable(int idesame, List<Risultato> risultati, PrintWriter out) {
        Map data = new HashMap();
        data.put("idesame",new Integer(idesame));
        data.put("risultati",risultati);
        try {
            tpl.processTemplate("risultati",data,out);
        } catch (ServletException e) {
            makeErrorPage(e.getMessage(),out);
        }
    }
    
    private void makeErrorPage(String message, PrintWriter out) {
        Map data = new HashMap();
        data.put("message",message);
        try {
            tpl.processTemplate("errore",data,out);
        } catch (ServletException e) {
            out.println("<h1>Errore di Elaborazione</h1>");
            out.println("<p>Il sistema ha rilevato il seguente errore durante l'elaborazione della vostra richiesta:</p>");
            out.println("<p><b>"+message+"</b></p>");
        }
    }
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession s = request.getSession(true);
        
        response.setContentType("text/html;charset=ISO-8859-1");
        PrintWriter out = response.getWriter();
        
        if (s.getAttribute("userid")!=null) { //autenticato
            String idesame = request.getParameter("idesame");
            if (idesame !=null) {
                try {
                    int id = Integer.parseInt(idesame);
                    List<Risultato> risultati = da.getRisultati(id);
                    if (risultati !=null) {
                        makeTable(id,risultati,out);
                    } else {
                        makeErrorPage("Informazioni esame non disponibili",out);
                    }
                } catch (NumberFormatException ex) {
                    makeErrorPage("Identificativo esame non valido",out);
                }
            } else {
                makeErrorPage("Identificativo esame non fornito",out);
            }
        } else {
            makeErrorPage("Utente non autorizzato",out);
        }
        out.close();
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
        return "Raw construction of results table";
    }
    // </editor-fold>
    
    
}
