package it.univaq.f4i.iw.ex.dic;

import it.univaq.f4i.iw.ex.dic.data.*;
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

public class Modifica extends HttpServlet {

    private DataAccess da;
    private TemplateManager tpl;
    Map data;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        da = DataAccess.newDataAccess();
        tpl = TemplateManager.getInstance(getServletContext());
        data = tpl.getDefaultDataModel();
    }

    
    private void showCurriculumMod(Curriculum curriculum, PrintWriter out, boolean full, String tipologia, String id_utente) throws ServletException {


        if (!tipologia.equals("")) {
            data.put("tipologia", Integer.parseInt(tipologia));
        }
        data.put("anagrafica", curriculum);


        //tipo impiego
        TipoImpiego tip = curriculum.getTipoImpiego();
        data.put("tipoImpiego", tip);
        boolean b = da.checkCurriculum(id_utente);
        data.put("link", b);
        //Istruzione
        List<Istruzione> ist = curriculum.getIstruzione();
        data.put("istruzione", ist);

        //ligue
        List<Lingue> lin = curriculum.getLingue();
        data.put("lingue", lin);

        //capacita
        List<Capacita> cap = curriculum.getCapacita();
        data.put("capacita", cap);

        //esperienze
        List<Esperienze> esp = curriculum.getEsperienze();
        data.put("esperienze", esp);



        try {

            tpl.processTemplate("curriculum_mod", data, out);


        } catch (ServletException e) {
            tpl.processTemplate("errorpage", data, out);
        }

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=ISO-8859-1");
        HttpSession s = SecurityHelpers.checkSession(request);
        PrintWriter out = response.getWriter();
        //creiamo la pagina di dettaglio
        String tipologia = "";
        String id_utente = "";
        Curriculum result = null;
        String id_curriculum = "";
        if (s != null) {
            tipologia = (String) s.getAttribute("tipologia");
            id_utente = (String) s.getAttribute("userid");
            id_curriculum = da.getIdCurriculum(id_utente);
            result = da.getCurriculum(id_curriculum);
        }






        try {



            if (result != null) {



                showCurriculumMod(result, out, true, tipologia, id_utente);


            } else {
                //in tutti gli altri casi, generiamo opportune pagine di errore
                try {
                    data.put("messaggio", "Nessun Parametro di ricerca");
                    tpl.processTemplate("errorpage", data, out);
                } catch (ServletException e) {
                    tpl.processTemplate("errorpage", data, out);
                }

            }
        } catch (NumberFormatException nfe) {
            try {
                data.put("messaggio", "Id Curriculum non valido");
                tpl.processTemplate("errorpage", data, out);
            } catch (ServletException e) {
                tpl.processTemplate("errorpage", data, out);
            }

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
