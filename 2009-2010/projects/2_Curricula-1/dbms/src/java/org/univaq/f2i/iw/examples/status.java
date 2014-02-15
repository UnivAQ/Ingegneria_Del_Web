/*
 * status.java
 *
 */

package org.univaq.f2i.iw.examples;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Ingegneria del Web
 * @version
 */
public class status extends HttpServlet {
    TemplateManager tpl;
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession s = request.getSession(true);
        response.setContentType("text/html;charset=ISO-8859-1");
        PrintWriter out = response.getWriter();
        
        if (s.getAttribute("userid")==null) {
            tpl.processTemplate("login",null,out);
        } else {
            Map data = new HashMap();
            data.put("username",s.getAttribute("username"));
            tpl.processTemplate("status",data,out);
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
        return "Short description";
    }
    // </editor-fold>

    public void init() throws ServletException {
        super.init();
        tpl = TemplateManager.getInstance(getServletContext());
    }


}
