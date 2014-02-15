package it.univaq.f4i.iw.ex.dic;

import it.univaq.f4i.iw.ex.dic.data.DataAccess;
import it.univaq.f4i.iw.ex.utils.SecurityHelpers;
import it.univaq.f4i.iw.ex.utils.TemplateManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Pannello extends HttpServlet {

    private TemplateManager tpl;
    private HttpSession s;
    private Map data;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        //inzializziamo il layer di accesso ai dati
        tpl = TemplateManager.getInstance(getServletContext());
        data = tpl.getDefaultDataModel();
    }

    //questo metodo genera la pagina di ricerca per gli utenti loggati
    private void makeLoggedPage(PrintWriter out, String tipologia, String id_utente) throws ServletException {

        DataAccess a = DataAccess.newDataAccess();
        data.put("tipologia", Integer.parseInt(tipologia));

        if (tipologia.equals("1")) {

            a = DataAccess.newDataAccess();


            boolean b = a.checkCurriculum(id_utente);
            data.put("link", b);



            try {
                tpl.processTemplate("pannello", data, out);
            } catch (ServletException e) {
                tpl.processTemplate("errorpage", data, out);

            }

        } else {

            a = DataAccess.newDataAccess();
            //va a vedere se ci sono cambiamenti nei curriculum dall'ultima ricerca dell'azienda
            String cont = a.checkMod(id_utente);
            
            
            if (Integer.parseInt(cont)>0) {
                data.put("cont", cont);
            }
            else
            {
                data.put("cont", null);
            }
            try {
                tpl.processTemplate("pannello", data, out);
            } catch (ServletException e) {
                tpl.processTemplate("errorpage", data, out);

            }
        }
    }

    //questo metodo genera la pagina di ricerca per gli utenti non loggati
    private void makeNotLoggedPage(PrintWriter out) throws ServletException {


        try {
            tpl.processTemplate("pannello", data, out);
        } catch (ServletException e) {
            tpl.processTemplate("errorpage", data, out);

        }
    }

    //questo metodo crea il form di ricerca
    private void makeSearchForm(PrintWriter out) throws ServletException {




        try {
            tpl.processTemplate("pannello", data, out);
        } catch (ServletException e) {
            tpl.processTemplate("errorpage", data, out);


        }

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=ISO-8859-1");
        HttpSession s = SecurityHelpers.checkSession(request);
        PrintWriter out = response.getWriter();


        if (s == null) {
            //utente non loggato
            makeNotLoggedPage(out);
        } else {
            //utente loggato

            String id_utente = (String) s.getAttribute("userid");
            String tipologia = (String) s.getAttribute("tipologia");




            makeLoggedPage(out, tipologia, id_utente);
        }
     
        out.close();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
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
        processRequest(request, response);
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
