package it.univaq.f4i.iw.ex.dic;

import it.univaq.f4i.iw.ex.dic.data.DataAccess;
import it.univaq.f4i.iw.ex.dic.data.searchData;
import it.univaq.f4i.iw.ex.utils.SecurityHelpers;
import it.univaq.f4i.iw.ex.utils.TemplateManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Predefinito extends HttpServlet {

    private TemplateManager tpl;
    private DataAccess db;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=ISO-8859-1");
        HttpSession s = SecurityHelpers.checkSession(request);
        PrintWriter out = response.getWriter();
        db = DataAccess.newDataAccess();
        tpl = TemplateManager.getInstance(getServletContext());
        Map data = tpl.getDefaultDataModel();
        String id_azienda = (String) s.getAttribute("userid");
        String tipologia = (String) s.getAttribute("tipologia");
        if (tipologia.equals("2")) {
            String cont = db.checkMod(id_azienda);
            //out.println(cont);
            if (Integer.parseInt(cont) > 0) {
                data.put("cont", cont);
            }

            try {




                data.put("tipologia", Integer.parseInt(tipologia));


                searchData ricerca = db.getRicerca(id_azienda);
                //out.println(ricerca.getPartTime());
                //out.println(ricerca.getFullTime());
                data.put("etaMin", ricerca.getEtaMin());
                data.put("etaMax", ricerca.getEtaMax());
                data.put("sesso", ricerca.getSesso());

                data.put("provincia", ricerca.getProvincia());
                data.put("lingua1", ricerca.getLingua1());
                data.put("lingua2", ricerca.getLingua2());
                data.put("lingua3", ricerca.getLingua3());
                data.put("tipologiaLav", ricerca.getTipologia());
                data.put("denominazione", ricerca.getDenominazione());
                data.put("partTime", ricerca.getPartTime());
                data.put("fullTime", ricerca.getFullTime());
                data.put("determinato", ricerca.getDeterminato());
                data.put("indeterminato", ricerca.getIndeterminato());
                data.put("dirigente", ricerca.getDirigente());
                data.put("subordinato", ricerca.getSubordinato());
                data.put("lavoroEstero", ricerca.getLavoroEstero());
                data.put("soggiornoEstero", ricerca.getSoggiornoEstero());
                data.put("provincia", ricerca.getProvincia());

                tpl.processTemplate("predefinito", data, out);

            } catch (ServletException e) {
                tpl.processTemplate("errorpage", data, out);

            }

            out.close();
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
