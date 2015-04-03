package it.univaq.f4i.iw.ex.dic;

import it.univaq.f4i.iw.ex.dic.data.Curriculum;
import it.univaq.f4i.iw.ex.dic.data.CurriculumRef;
import it.univaq.f4i.iw.ex.dic.data.DataAccess;
import it.univaq.f4i.iw.ex.dic.data.searchData;
import it.univaq.f4i.iw.ex.utils.SecurityHelpers;
import it.univaq.f4i.iw.ex.utils.TemplateManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.String;
import java.sql.Struct;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author IngegneriaDelWeb
 */
public class RisultatiMod extends HttpServlet {

    private DataAccess da;
    private TemplateManager tpl;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        //inzializziamo il layer di accesso ai dati
        da = DataAccess.newDataAccess();
        tpl = TemplateManager.getInstance(getServletContext());
    }

  
    private void makeResultPage(List<String> results, PrintWriter out, String tipologia, String id_utente) throws ServletException {

        Map data = tpl.getDefaultDataModel();

        if (!tipologia.equals("")) {
            data.put("tipologia", Integer.parseInt(tipologia));
        }
        if (tipologia.equals("1")) {
            data.put("link", da.checkCurriculum(id_utente));
        }

        data.put("risultati", results);
        boolean b = da.checkCurriculum(id_utente);
        data.put("link", b);


        try {
            tpl.processTemplate("risultati", data, out);
        } catch (ServletException e) {
            tpl.processTemplate("errorpage", data, out);

        }

    }

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=ISO-8859-1");
        PrintWriter out = response.getWriter();
        Map data = tpl.getDefaultDataModel();
        HttpSession s = SecurityHelpers.checkSession(request);
        String tipologia = "";
        String id_utente = "";

        List<String> result = null;
        if (s != null) {
            tipologia = (String) s.getAttribute("tipologia");
            id_utente = (String) s.getAttribute("userid");
            result = da.getMod(id_utente);
            da.aggRic(id_utente);
        }
       


        // se ci sono profili da visualizzare...
        if (result != null && result.size() > 0) {
            if (result.size() == 1) {
                //se il risultato è uno solo, selezioniamolo automaticamente
                //"fingendo" che l'utente ne abbia cliccato il link sulla pagina
                response.sendRedirect("curriculum?l=" + result.get(0));
            } else {
                //altrimenti creiamo la tabella dei risultati
                makeResultPage(result, out, tipologia, id_utente);
            }
        } else {
            //generiamo pagine di errore in tutti gli altri casi
            tpl.processTemplate("errorpage", data, out);
        }
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
