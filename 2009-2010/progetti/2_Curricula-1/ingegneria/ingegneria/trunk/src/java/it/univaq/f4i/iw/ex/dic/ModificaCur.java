package it.univaq.f4i.iw.ex.dic;

import com.mysql.jdbc.Statement;
import it.univaq.f4i.iw.ex.dic.data.Capacita;
import it.univaq.f4i.iw.ex.dic.data.Curriculum;
import it.univaq.f4i.iw.ex.dic.data.DataAccess;
import it.univaq.f4i.iw.ex.dic.data.Esperienze;
import it.univaq.f4i.iw.ex.dic.data.Istruzione;
import it.univaq.f4i.iw.ex.dic.data.Lingue;
import it.univaq.f4i.iw.ex.dic.data.TipoImpiego;
import it.univaq.f4i.iw.ex.utils.SecurityHelpers;
import it.univaq.f4i.iw.ex.utils.TemplateManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Timestamp;
import java.sql.ResultSet;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ModificaCur extends HttpServlet {

    private TemplateManager tpl;
    private DataAccess da;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=ISO-8859-1");
        HttpSession s = SecurityHelpers.checkSession(request);
        PrintWriter out = response.getWriter();
        da = DataAccess.newDataAccess();

        tpl = TemplateManager.getInstance(getServletContext());
        Map data = tpl.getDefaultDataModel();


        Curriculum cur = null;
        if (s != null) {
            String id_utente = (String) s.getAttribute("userid");
            String tipologia = (String) s.getAttribute("tipologia");
            data.put("tipologia", Integer.parseInt(tipologia));
            String id_curriculum = da.getIdCurriculum(id_utente);
            cur = da.getCurriculum(id_curriculum);
            boolean b = da.checkCurriculum(id_utente);
            data.put("link", b);
            if (request.getParameter("mod") != null) {
                switch (Integer.parseInt(request.getParameter("mod"))) {
                    case 0: {
                        //anagrafica
                        data.put("key", cur.getKey());
                        data.put("nome", cur.getNome());
                        data.put("cognome", cur.getCognome());
                        data.put("sesso", cur.getSesso());
                        data.put("dataNascita", cur.getDataNascita());
                        data.put("email", cur.getEmail());
                        data.put("indirizzo", cur.getIndirizzo());
                        data.put("provincia", cur.getProvincia());
                        data.put("telefono", cur.getTelefono());
                        try {
                            tpl.processTemplate("anagrafica_mod", data, out);
                        } catch (ServletException e) {
                            tpl.processTemplate("errorpage", data, out);
                        }
                        break;
                    }
                    case 1: {
                        //istruzione
                        if (!request.getParameter("id_ist").equals(null)) {
                            Istruzione ist = da.getIst(request.getParameter("id_ist"));
                            data.put("istruzione", ist);
                        }
                        try {
                            tpl.processTemplate("istruzione_mod", data, out);
                        } catch (ServletException e) {
                            tpl.processTemplate("errorpage", data, out);
                        }
                        break;

                    }
                    case 2: {
                        //eperienze
                        if (!request.getParameter("id_esp").equals(null)) {
                            Esperienze esp = da.getEsp(request.getParameter("id_esp"));
                            data.put("esperienze", esp);
                        }
                        try {
                            tpl.processTemplate("esperienze_mod", data, out);
                        } catch (ServletException e) {
                            tpl.processTemplate("errorpage", data, out);
                        }
                        break;

                    }
                    case 3: {
                        //lingue
                        if (!request.getParameter("id_lin").equals(null)) {
                            Lingue lin = da.getLin(request.getParameter("id_lin"));
                            data.put("lingue", lin);
                        }
                        try {
                            tpl.processTemplate("lingue_mod", data, out);
                        } catch (ServletException e) {
                            tpl.processTemplate("errorpage", data, out);
                        }
                        break;

                    }
                    case 4: {
                        //tipo impiego
                        if (!request.getParameter("id_tip").equals(null)) {
                            TipoImpiego tip = cur.getTipoImpiego();
                            data.put("tipoImpiego", tip);
                        }
                        try {
                            tpl.processTemplate("tipoImpiego_mod", data, out);
                        } catch (ServletException e) {
                            tpl.processTemplate("errorpage", data, out);
                        }
                        break;

                    }
                }
            }

            if (request.getParameter("add") != null) {
                switch (Integer.parseInt(request.getParameter("add"))) {

                    case 1: {
                        //istruzione
                        try {
                            tpl.processTemplate("istruzione_add", data, out);
                        } catch (ServletException e) {
                            tpl.processTemplate("errorpage", data, out);
                        }


                        break;
                    }
                    case 2: {
                        try {
                            tpl.processTemplate("esperienze_add", data, out);
                        } catch (ServletException e) {
                            tpl.processTemplate("errorpage", data, out);
                        }
                        break;
                    }
                    case 3: {
                        try {
                            tpl.processTemplate("lingue_add", data, out);
                        } catch (ServletException e) {
                            tpl.processTemplate("errorpage", data, out);
                        }
                        break;
                    }
                    case 4: {
                        try {
                            tpl.processTemplate("capacita_add", data, out);
                        } catch (ServletException e) {
                            tpl.processTemplate("errorpage", data, out);
                        }
                        break;
                    }
                }
            }
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
            throws ServletException,
            IOException {
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
            throws ServletException,
            IOException {
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
