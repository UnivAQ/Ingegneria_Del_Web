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

public class Inserimento extends HttpServlet {

    private TemplateManager tpl;
    private Curriculum cur;
   
    private HttpSession s;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        //inzializziamo il layer di accesso ai dati

       
        tpl = TemplateManager.getInstance(getServletContext());
    }

    private void makeLogoutButton(PrintWriter out) {
        //inseriamo il bottone di logout
        out.println("<form method=\"post\" action=\"logout\">");
        out.println("<p><input value=\"logout\" name=\"logout\" type=\"submit\"/></p>");
        out.println("</form>");
    }

    private void makeLoggedPage(PrintWriter out, String opt, String save, HttpServletRequest request, String id_utente) throws ServletException {
        Map data = tpl.getDefaultDataModel();
        //s = SecurityHelpers.checkSession(request);
        String temp = (String) s.getAttribute("tipologia");
        data.put("tipologia", Integer.parseInt(temp));

        //visualizzazione inserimento o modifica del curriculum nel menu utente laterale
        DataAccess a = DataAccess.newDataAccess();
        boolean b = a.checkCurriculum(id_utente);
        data.put("link", b);

        int x = Integer.parseInt(opt);
        String y;
        switch (x) {

            case 0: {

                try {
                    tpl.processTemplate("curriculum_insert_0", data, out);
                } catch (ServletException e) {
                    tpl.processTemplate("errorpage", data, out);

                }

                break;
            }

            case 1: {

                if (save.equals("1")) {

                    cur.setNumIstruzione(cur.getNumIstruzione() + 1);
                    Istruzione ist = new Istruzione(cur.getNumIstruzione(), request.getParameter("titolo"), request.getParameter("data"), request.getParameter("istituzione"), Integer.parseInt(request.getParameter("voto")), Integer.parseInt(request.getParameter("tipologia")));
                    // Istruzione ist= new Istruzione(cur.getNumIstruzione(), request.getParameter("titolo"),request.getParameter("data"),request.getParameter("istituzione"),1, 1);
                    cur.addIstruzione(ist);

                } else {
                    //salvo anagrafica (case 0)
                    cur = new Curriculum(0, request.getParameter("nome"), request.getParameter("cognome"), request.getParameter("sesso"), request.getParameter("dataNascita"), request.getParameter("email"), request.getParameter("indirizzo"), request.getParameter("provincia"), request.getParameter("telefono"));

                    // out.println( cur.getNome());
                }

                //data put template
                data.put("istruzione", cur.getIstruzione());
                //data.put("anagrafica",cur);
                try {
                    tpl.processTemplate("curriculum_insert_1", data, out);
                } catch (ServletException e) {
                    tpl.processTemplate("errorpage", data, out);
                }
                //call template_case1.html
                break;
            }

            case 2: {

                if (save.equals("1")) {
                    cur.setNumLingue(cur.getNumLingue() + 1);
                    Lingue lin = new Lingue(cur.getNumLingue(), request.getParameter("nome"), Integer.parseInt(request.getParameter("livelloParlato")), Integer.parseInt(request.getParameter("livelloScritto")));
                    cur.addLingue(lin);

                }

                data.put("lingue", cur.getLingue());

                try {
                    tpl.processTemplate("curriculum_insert_2", data, out);
                } catch (ServletException e) {
                    tpl.processTemplate("errorpage", data, out);
                }
                //call template_case2.html
                break;
            }

            case 3: {
                if (save.equals("1")) {
                    cur.setNumEsperienze(cur.getNumEsperienze() + 1);
                    Esperienze esp = new Esperienze(cur.getNumEsperienze(), request.getParameter("datore"), request.getParameter("incarico"), request.getParameter("periodo"));
                    cur.addEsperienze(esp);

                }
                data.put("esperienze", cur.getEsperienze());
                try {
                    tpl.processTemplate("curriculum_insert_3", data, out);
                } catch (ServletException e) {
                    tpl.processTemplate("errorpage", data, out);
                }
                //call template_case3.html
                break;


            }
            case 4: {

                if (save.equals("1")) {

                    cur.setNumCapacita(cur.getNumCapacita() + 1);
                    Capacita cap = new Capacita(cur.getNumCapacita(), request.getParameter("capacita"));
                    cur.addCapacita(cap);

                }
                data.put("capacita", cur.getCapacita());
                try {
                    tpl.processTemplate("curriculum_insert_4", data, out);
                } catch (ServletException e) {
                    tpl.processTemplate("errorpage", data, out);
                }
                //call template_case4.html
                break;


            }
            case 5: {

                try {
                    tpl.processTemplate("curriculum_insert_5", data, out);
                } catch (ServletException e) {
                    tpl.processTemplate("errorpage", data, out);
                }
                break;
            }
            case 6: {
                a = DataAccess.newDataAccess();


                TipoImpiego imp = new TipoImpiego(cur.getKey(), request.getParameter("provincia"), checkBox(request.getParameter("partTime")), checkBox(request.getParameter("fullTime")), checkBox(request.getParameter("determinato")), checkBox(request.getParameter("indeterminato")), checkBox(request.getParameter("dirigente")), checkBox(request.getParameter("subordinato")), checkBox(request.getParameter("lavoroEstero")), checkBox(request.getParameter("soggiornoEstero")));
                cur.setTipoImpiego(imp);

                //inserimento nel database
                if (a.setCurriculum(cur, id_utente)) {


                    data.put("anagrafica", cur);


                    //tipo impiego
                    TipoImpiego tip = cur.getTipoImpiego();
                    data.put("tipoImpiego", tip);

                    //Istruzione
                    List<Istruzione> ist = cur.getIstruzione();
                    data.put("istruzione", ist);

                    //ligue
                    List<Lingue> lin = cur.getLingue();
                    data.put("lingue", lin);

                    //capacita
                    List<Capacita> cap = cur.getCapacita();
                    data.put("capacita", cap);

                    //esperienze
                    List<Esperienze> esp = cur.getEsperienze();
                    data.put("esperienze", esp);



                    try {
                        tpl.processTemplate("curriculum_insert_6", data, out);
                    } catch (ServletException e) {
                        tpl.processTemplate("errorpage", data, out);
                    }

                } //inserire pagina di errore
                else {
                    tpl.processTemplate("errorpage", data, out);
                }



            }
        }
    }

    //questo metodo genera la pagina di ricerca per gli utenti non loggati
   

    //funzione string to bool per la check box
    private boolean checkBox(String str) {
        if (str != null) {
            return true;
        } else {
            return false;
        }
    }

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=ISO-8859-1");
        s = SecurityHelpers.checkSession(request);
        PrintWriter out = response.getWriter();
        String save = request.getParameter("save");
        String opt = request.getParameter("opt");
        //id utente loggato

        String id_utente = (String) s.getAttribute("userid");

       
        if (s == null) {
            //utente non loggato
            response.sendRedirect("index");
        } else {
            //utente loggato
            makeLoggedPage(out, opt, save, request, id_utente);

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
