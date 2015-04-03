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



public class Aggiornamento extends HttpServlet {

    private DataAccess da;
    private TemplateManager tpl;
    Map data;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        //inzializziamo il layer di accesso ai dati
        //da = DataAccess2.getInstance();
        da = DataAccess.newDataAccess();
        tpl = TemplateManager.getInstance(getServletContext());
        data = tpl.getDefaultDataModel();
    }

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=ISO-8859-1");
        HttpSession s = SecurityHelpers.checkSession(request);
        PrintWriter out = response.getWriter();
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
        String queryCur = "UPDATE curriculum SET dataModifica=unix_timestamp(now())  WHERE id_curriculum =" + id_curriculum + ";";
        da.updateCur(queryCur);
        if (request.getParameter("save") != null) {

            switch (Integer.parseInt(request.getParameter("save"))) {
                case 0: {
                    //modifica di anagrafica
                    String query = "UPDATE anagrafica SET nome ='" + request.getParameter("nome") + "', cognome ='" + request.getParameter("cognome") + "', sesso ='" + request.getParameter("sesso") + "',dataNascita ='" + request.getParameter("dataNascita") + "', email ='" + request.getParameter("email") + "', indirizzo ='" + request.getParameter("indirizzo") + "', provincia ='" + request.getParameter("provincia") + "', telefono ='" + request.getParameter("telefono") + "' WHERE id_curriculum =" + request.getParameter("id_cur") + ";";
                    da.updateCur(query);
                    break;
                }
                case 1: {
                    String query = "UPDATE istruzione SET titolo ='" + request.getParameter("titolo") + "', data ='" + request.getParameter("data") + "', istituzione ='" + request.getParameter("istituzione") + "',voto ='" + request.getParameter("voto") + "', tipologia ='" + request.getParameter("tipologia") + "' WHERE id_istruzione =" + request.getParameter("id_ist") + ";";
                    da.updateCur(query);
                    break;
                }
                case 2: {
                    String query = "UPDATE esperienze SET datore ='" + request.getParameter("datore") + "', incarico ='" + request.getParameter("incarico") + "', periodo ='" + request.getParameter("periodo") + "' WHERE id_esperienze =" + request.getParameter("id_esp") + ";";
                    da.updateCur(query);
                    break;
                }
                case 3: {
                    String query = "UPDATE lingue SET nome ='" + request.getParameter("nome") + "', livelloParlato ='" + request.getParameter("livelloParlato") + "', livelloScritto ='" + request.getParameter("livelloScritto") + "' WHERE id_lingue =" + request.getParameter("id_lin") + ";";
                    out.println(query);
                    da.updateCur(query);
                    break;
                }
                case 4: {
                    //String query ="SELECT NOW();";
                    String query = "UPDATE tipoImpiego SET provincia ='" + request.getParameter("provincia") + "',partTime =" + da.checkFlag(request.getParameter("partTime")) + " 	,fullTime  =" + da.checkFlag(request.getParameter("fullTime")) + "	,determinato  =" + da.checkFlag(request.getParameter("determinato")) + "	,indeterminato  =" + da.checkFlag(request.getParameter("indeterminato")) + "	,dirigente  =" + da.checkFlag(request.getParameter("dirigente")) + "	,subordinato  =" + da.checkFlag(request.getParameter("subordinato")) + "	,lavoroEstero  =" + da.checkFlag(request.getParameter("lavoroEstero")) + "	,soggiornoEstero  =" + da.checkFlag(request.getParameter("soggiornoEstero")) + " WHERE id_tipoImpiego ='" + request.getParameter("id_tip") + "';";
                    da.updateCur(query);
                    break;
                }
            }
        }

        if (request.getParameter("del") != null) {

            switch (Integer.parseInt(request.getParameter("del"))) {
                case 1: {
                    //delete istruzione
                    String query = "DELETE FROM istruzione WHERE id_curriculum='" + id_curriculum + "' and id_istruzione='" + request.getParameter("id_ist") + "';";
                    da.deleteElem(query);
                    break;
                }
                case 2: {
                    //delete esperienze
                    String query = "DELETE FROM esperienze WHERE id_curriculum='" + id_curriculum + "' and id_esperienze='" + request.getParameter("id_esp") + "';";
                    da.deleteElem(query);
                    break;
                }
                case 3: {
                    //delete lingue
                    String query = "DELETE FROM lingue WHERE id_curriculum='" + id_curriculum + "' and id_lingue='" + request.getParameter("id_lin") + "';";
                    da.deleteElem(query);
                    break;
                }
                case 4: {
                    //delete capacità
                    String query = "DELETE FROM capacita WHERE id_curriculum='" + id_curriculum + "' and id_capacita='" + request.getParameter("id_cap") + "';";
                    da.deleteElem(query);
                    break;
                }
            }
        }
        if (request.getParameter("add") != null) {

            switch (Integer.parseInt(request.getParameter("add"))) {
                case 1: {
                    //istruzione

                    String query = "INSERT INTO istruzione VALUES (NULL,'" + id_curriculum + "','" + request.getParameter("titolo") + "','" + request.getParameter("data") + "','" + request.getParameter("istituzione") + "'," + request.getParameter("voto") + "," + request.getParameter("tipologia") + ");";
                    out.println(query);
                    da.addElem(query);
                    break;
                }
                case 2: {
                    //esperienze
                    String query = "INSERT INTO esperienze VALUES (NULL,'" + id_curriculum + "','" + request.getParameter("datore") + "','" + request.getParameter("incarico") + "','" + request.getParameter("periodo") + "');";
                    //out.println(query);
                    da.addElem(query);
                    break;
                }
                case 3: {
                    //lingue
                    String query = "INSERT INTO lingue VALUES (NULL,'" + id_curriculum + "','" + request.getParameter("nome") + "','" + request.getParameter("livelloParlato") + "','" + request.getParameter("livelloScritto") + "');";
                    //out.println(query);
                    da.addElem(query);
                    break;
                }
                case 4: {
                    //capacita
                    String query = "INSERT INTO capacita VALUES (NULL,'" + id_curriculum + "','" + request.getParameter("capacita") + "');";
//                    out.println(query);
                    da.addElem(query);
                    break;
                }
            }
        }



       

        try {
           

            if (result != null) {


                response.sendRedirect("modifica#why");
               
            } else {
                //in tutti gli altri casi, generiamo opportune pagine di errore
                
                tpl.processTemplate("errorpage", data, out);
            }
        } catch (NumberFormatException nfe) {
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
