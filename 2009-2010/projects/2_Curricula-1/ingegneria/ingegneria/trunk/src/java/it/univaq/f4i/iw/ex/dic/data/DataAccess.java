package it.univaq.f4i.iw.ex.dic.data;
import com.mysql.jdbc.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class DataAccess {

//private String query;
    //singleton
    private static DataAccess singleton = null;

    public synchronized static DataAccess newDataAccess() throws ServletException {
        if (singleton == null) {
            singleton = new DataAccess();
        }
        return singleton;
    }
    private Connection c = null;
    private PreparedStatement sRisultati = null; // versione con query non precompilata
    private HttpServletRequest request;

    private DataAccess() throws ServletException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/job", "root", "root");
            //sRisultati = c.prepareStatement("SELECT * FROM account WHERE tipologia= ?"); //versione con query precompilata
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new ServletException("Errore di caricamento driver database", e);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Errore di connessione al database", e);
        }
    }

    public boolean checkUsername(String username) {
        //controllo username già presente nel db
        ResultSet r = null;
        Statement s = null; // versione con query non precompilata
        try {
            // sRisultati.setInt(1,tipologia);  //versione con query precompilata
            // r = sRisultati.executeQuery(); //versione con query precompilata
            s = (Statement) c.createStatement(); // versione con query non precompilata
            String query_cont = "SELECT COUNT(*) AS conteggio FROM account WHERE username = '" + username + "';";
            r = s.executeQuery(query_cont);
            r.next();
            String cont = r.getString("conteggio");
            if (cont.equals("0")) {
                return true;
            }
            return false;
        } catch (SQLException e) {

            e.printStackTrace();
            return false;
            //return null;
        } finally {
            try {
                //if (r!=null) r.close();
                if (s != null) {
                    s.close(); // versione con query non precompilata
                }
            } catch (SQLException e) {
            }
        }

    }

    public boolean insertUtente(String username, String password, String email, String tipologia) throws SQLException {
        ResultSet r = null;
        Statement s = null;
        s = (Statement) c.createStatement();

        if (checkUsername(username)) {


            //inserire l'account sul database
            try {

                s.executeUpdate("INSERT INTO account VALUES ( NULL , '" + username + "','" + password + "','" + email + "','" + tipologia + "');");

                return true;
            } catch (SQLException e) {

                e.printStackTrace();
                return false;
                //return null;
            } finally {
                try {
                    //if (r!=null) r.close();
                    if (s != null) {
                        s.close(); // versione con query non precompilata
                    }
                } catch (SQLException e) {
                }
            }
        } else //username esistente
        {
            return false;
        }
    }

    public boolean insertAzienda(String username, String password, String email, String tipologia, String ragione, String piva) throws SQLException {
        ResultSet r = null;
        Statement s = null;
        s = (Statement) c.createStatement();

        if (checkUsername(username)) {


            //inserire l'account sul database
            try {

                s.executeUpdate("INSERT INTO account VALUES ( NULL , '" + username + "','" + password + "','" + email + "','" + tipologia + "');");
                r = s.executeQuery("SELECT * FROM account WHERE username = '" + username + "';");
                r.next();
                String id_account = r.getString("id_account");
                s.executeUpdate("INSERT INTO aziende VALUES ( NULL , '" + id_account + "','" + ragione + "','" + piva + "');");
                return true;
            } catch (SQLException e) {

                e.printStackTrace();
                return false;
                //return null;
            } finally {
                try {
                    //if (r!=null) r.close();
                    if (s != null) {
                        s.close(); // versione con query non precompilata
                    }
                } catch (SQLException e) {
                }
            }
        } else //username esistente
        {
            return false;
        }
    }
//    public List<String> getRisultati(int tipologia) {
//        List<String> result = new ArrayList();
//
//        ResultSet r = null;
//        //Statement s = null; // versione con query non precompilata
//        try {
//            sRisultati.setInt(1,tipologia);  //versione con query precompilata
//            r = sRisultati.executeQuery(); //versione con query precompilata
//            //s = c.createStatement(); // versione con query non precompilata
//            //r = s.executeQuery("SELECT * FROM risultati WHERE IDesame="+idesame); // versione con query non precompilata
//            while(r.next()) {
//                result.add(r.getString("username"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        } finally {
//            try {
//                if (r!=null) r.close();
//                //if (s!=null) s.close(); // versione con query non precompilata
//            } catch (SQLException e) { }
//        }
//
//
//        return result;
//    }

    public boolean setCurriculum(Curriculum cur, String id_utente) {
        String query = "";

        ResultSet r = null;
        Statement s = null; // versione con query non precompilata
        try {
            // sRisultati.setInt(1,tipologia);  //versione con query precompilata
            // r = sRisultati.executeQuery(); //versione con query precompilata
            s = (Statement) c.createStatement(); // versione con query non precompilata

            //inserimento nella tabella curriculum
            //String id_utente = (String) sessione.getAttribute("userid");

            s.executeUpdate("INSERT INTO curriculum VALUES (NULL,'" + id_utente + "'," + cur.getNumLingue() + "," + cur.getNumIstruzione() + "," + cur.getNumCapacita() + "," + cur.getNumEsperienze() + ", unix_timestamp(now()));");

            //id del curriculum
            r = s.executeQuery("SELECT * FROM curriculum WHERE id_account = '" + id_utente + "';");
            r.next();
            String key = r.getString("id_curriculum");




            /* */
            //inserimento in anagrafica
            query = "INSERT INTO anagrafica VALUES (NULL,'" + key + "','" + cur.getNome() + "','" + cur.getCognome() + "','" + cur.getSesso() + "','" + cur.getDataNascita() + "','" + cur.getEmail() + "','" + cur.getIndirizzo() + "','" + cur.getProvincia() + "','" + cur.getTelefono() + "');";
            s.executeUpdate(query);

            //inserimento nella tabella lingue
            for (Lingue lin : cur.getLingue()) {
                s.executeUpdate("INSERT INTO lingue VALUES (NULL,'" + key + "','" + lin.getNome() + "'," + lin.getLivelloParlato() + "," + lin.getLivelloScritto() + ");");
            }

            //inserimento nella tabella Istruzione
            for (Istruzione ist : cur.getIstruzione()) {
                s.executeUpdate("INSERT INTO istruzione VALUES (NULL,'" + key + "','" + ist.getTitolo() + "','" + ist.getData() + "','" + ist.getIstituzione() + "'," + ist.getVoto() + "," + ist.getTipologia() + ");");
            }



            //inserimento nella tabella Capacità
            for (Capacita cap : cur.getCapacita()) {
                s.executeUpdate("INSERT INTO capacita VALUES (NULL," + key + ",'" + cap.getCapacita() + "');");
            }

            //inserimento nella tabella Esperienze
            for (Esperienze esp : cur.getEsperienze()) {
                s.executeUpdate("INSERT INTO esperienze VALUES (NULL," + key + ",'" + esp.getDatore() + "','" + esp.getIncarico() + "','" + esp.getPeriodo() + "');");
            }

            //inserimento nella tabella tipo impiego
            TipoImpiego tip = cur.getTipoImpiego();
            s.executeUpdate("INSERT INTO tipoImpiego VALUES (NULL," + key + ",'" + tip.getProvincia() + "'," + tip.getPartTime() + "," + tip.getFullTime() + "," + tip.getDeterminato() + "," + tip.getIndeterminato() + "," + tip.getDirigente() + "," + tip.getSubordinato() + "," + tip.getLavoroEstero() + "," + tip.getSoggiornoEstero() + ");");

            return true;

            //r = s.executeQuery("SELECT now(); ");

            // versione con query non precompilata
            //return true;
//            while(r.next()) {
//               // result.add(r.getString("username"));
//
//            }
        } catch (SQLException e) {

            e.printStackTrace();
            return false;
            //return null;
        } finally {
            try {
                //if (r!=null) r.close();
                if (s != null) {
                    s.close(); // versione con query non precompilata
                }
            } catch (SQLException e) {
            }
        }

    }

    public List<String> searchCurriculum(searchData data) {
        String query = "";
        List<String> listaId = new ArrayList();
        ResultSet r = null;
        Statement s = null; // versione con query non precompilata
        try {
            s = (Statement) c.createStatement(); // versione con query non precompilata

            //query di ricerca 
            query = "SELECT distinct id_curriculum FROM lingue where nome LIKE \"%" + data.getLingua1() + "%\" ";
            query += " and id_curriculum IN (SELECT distinct id_curriculum FROM lingue where nome LIKE \"%" + data.getLingua2() + "%\") ";
            query += " and id_curriculum IN (SELECT distinct id_curriculum FROM lingue where nome LIKE \"%" + data.getLingua3() + "%\") ";
            if (!(data.getPartTime().equals("0") && data.getFullTime().equals("0") && data.getDeterminato().equals("0") && data.getIndeterminato().equals("0") && data.getDirigente().equals("0") && data.getSubordinato().equals("0") && data.getLavoroEstero().equals("0") && data.getSoggiornoEstero().equals("0") && data.getProvincia().equals(""))) {
                query += "and id_curriculum IN (SELECT distinct id_curriculum FROM tipoImpiego WHERE (true ";
                if (data.getPartTime().equals("1")) {
                    query += " and partTime = 1  ";
                }

                if (data.getFullTime().equals("1")) {
                    query += " and fullTime = 1  ";
                }
                if (data.getDeterminato().equals("1")) {
                    query += " and determinato = 1  ";
                }
                if (data.getIndeterminato().equals("1")) {
                    query += " and indeterminato = 1  ";
                }
                if (data.getSubordinato().equals("1")) {
                    query += " and subordinato = 1  ";
                }
                if (data.getDirigente().equals("1")) {
                    query += " and dirigente = 1  ";
                }
                if (data.getLavoroEstero().equals("1")) {
                    query += " and lavoroEstero = 1  ";
                }
                if (data.getSoggiornoEstero().equals("1")) {
                    query += " and soggiornoEstero = 1  ";
                }
                query += ")";
                if (!data.getProvincia().equals("")) {
                    query += " and (provincia LIKE '%" + data.getProvincia() + "%' )";
                }
                query += ")";
            }
            if (!(data.getTipologia().equals("") || data.getDenominazione().equals(""))) {
                query += " and id_curriculum IN (SELECT distinct id_curriculum FROM istruzione where tipologia=" + data.getTipologia() + " and (titolo like \"%" + data.getDenominazione() + "%\" OR titolo like \"%" + data.getDenominazione() + "%\")) ";
            } else if (!(data.getTipologia().equals(""))) {
                query += " and id_curriculum IN (SELECT distinct id_curriculum FROM istruzione where tipologia=" + data.getTipologia() + " ) ";
            } else {
                query += " and id_curriculum IN (SELECT distinct id_curriculum FROM istruzione where titolo like \"%" + data.getDenominazione() + "%\" OR titolo like \"%" + data.getDenominazione() + "%\") ";
            }

            if (data.getSesso().equals("MF")) {
                query += " and id_curriculum IN (SELECT distinct id_curriculum FROM anagrafica where now() >= DATE_ADD( dataNascita , INTERVAL '" + data.getEtaMin() + "' year ) and now() < DATE_ADD( dataNascita , INTERVAL '" + data.getEtaMax() + "'+1 year )) ;";
            } else {
                query += " and id_curriculum IN (SELECT distinct id_curriculum FROM anagrafica where sesso=\"" + data.getSesso() + "\" and now() >= DATE_ADD( dataNascita , INTERVAL '" + data.getEtaMin() + "' year ) and now() < DATE_ADD( dataNascita , INTERVAL '" + data.getEtaMax() + "'+1 year )) ;";
            }




            r = s.executeQuery(query);
            while (r.next()) {
                listaId.add(r.getString("id_curriculum"));
            }

            return listaId;

        } catch (SQLException e) {

            e.printStackTrace();
            return listaId;
            //return null;
        } finally {
            try {
                //if (r!=null) r.close();
                if (s != null) {
                    s.close(); // versione con query non precompilata
                }
            } catch (SQLException e) {
            }
        }


    }

    public Curriculum getCurriculum(String id_curriculum) {
        //istruzioni fittizie per creazione oggetti a caso da rimpiazzare con DATABASE

        ResultSet r = null;
        Statement s = null; // versione con query non precompilata
        try {
            s = (Statement) c.createStatement(); // versione con query non precompilata

            //id del curriculum
//        r=s.executeQuery("SELECT * FROM curriculum WHERE id_account = '"+id_utente+"';");
//        r.next();
//        String id_curriculum = r.getString("id_curriculum");
            //String id_curriculum = "1";
/**/

            // Anagrafica
            r = s.executeQuery("SELECT * FROM anagrafica WHERE anagrafica.id_curriculum='" + id_curriculum + "'");
            //     r = s.executeQuery("SELECT * FROM curriculum,anagrafica WHERE curriculum.id_curriculum = anagrafica.id_curriculum AND anagrafica.id_curriculum='"+id_curriculum+"';");
            r.next();
            Curriculum result = new Curriculum(Integer.parseInt(r.getString("id_curriculum")), r.getString("nome"), r.getString("cognome"), r.getString("sesso"), r.getString("dataNascita"), r.getString("email"), r.getString("indirizzo"), r.getString("provincia"), r.getString("telefono"));

            // Tipo Impiego
            r = s.executeQuery("SELECT * FROM tipoImpiego WHERE tipoImpiego.id_curriculum='" + id_curriculum + "';");
            r.next();
            TipoImpiego tipoImpiego = new TipoImpiego(Integer.parseInt(r.getString("id_tipoImpiego")), r.getString("provincia"), checkBoolean(r.getString("partTime")), checkBoolean(r.getString("fullTime")), checkBoolean(r.getString("determinato")), checkBoolean(r.getString("indeterminato")), checkBoolean(r.getString("dirigente")), checkBoolean(r.getString("subordinato")), checkBoolean(r.getString("lavoroEstero")), checkBoolean(r.getString("soggiornoEstero")));
            result.setTipoImpiego(tipoImpiego);

            // Esperienze
            r = s.executeQuery("SELECT * FROM esperienze WHERE esperienze.id_curriculum='" + id_curriculum + "';");
            while (r.next()) {
                Esperienze esp = new Esperienze(Integer.parseInt(r.getString("id_esperienze")), r.getString("datore"), r.getString("incarico"), r.getString("periodo"));
                result.addEsperienze(esp);
            }

            // Lingue
            r = s.executeQuery("SELECT * FROM lingue WHERE lingue.id_curriculum='" + id_curriculum + "';");
            while (r.next()) {
                Lingue lin = new Lingue(Integer.parseInt(r.getString("id_lingue")), r.getString("nome"), Integer.parseInt(r.getString("livelloParlato")), Integer.parseInt(r.getString("livelloScritto")));
                result.addLingue(lin);
            }

            // Capacità
            r = s.executeQuery("SELECT * FROM capacita WHERE capacita.id_curriculum='" + id_curriculum + "';");
            while (r.next()) {
                Capacita cap = new Capacita(Integer.parseInt(r.getString("id_capacita")), r.getString("capacita"));
                result.addCapacita(cap);
            }

            // Istruzione
            r = s.executeQuery("SELECT * FROM istruzione WHERE istruzione.id_curriculum='" + id_curriculum + "';");
            while (r.next()) {
                Istruzione ist = new Istruzione(Integer.parseInt(r.getString("id_istruzione")), r.getString("titolo"), r.getString("data"), r.getString("istituzione"), Integer.parseInt(r.getString("voto")), Integer.parseInt(r.getString("tipologia")));
                result.addIstruzione(ist);
            }

            return result;
        } catch (SQLException e) {

            e.printStackTrace();
            return null;
            //return null;
        } finally {
            try {
                //if (r!=null) r.close();
                if (s != null) {
                    s.close(); // versione con query non precompilata
                }
            } catch (SQLException e) {
            }
        }


    }

    public searchData getRicerca(String id_azienda) {

        searchData ricerca = new searchData("", "", "", "", "", "", "", "", "", null, null, null, null, null, null, null, null);
        ResultSet r = null;
        Statement s = null; // versione con query non precompilata
        try {

            s = (Statement) c.createStatement(); // versione con query non precompilata


            r = s.executeQuery("SELECT * FROM ricerca WHERE id_azienda = '" + id_azienda + "';");
            r.next();
            if (!r.getString("etaMin").equals("")) {
                ricerca = new searchData(r.getString("etaMin"), r.getString("etaMax"), r.getString("sesso"), r.getString("lingua1"), r.getString("lingua2"), r.getString("lingua3"), r.getString("tipologia"), r.getString("denominazione"), r.getString("provincia"), r.getString("partTime"), r.getString("fullTime"), r.getString("determinato"), r.getString("indeterminato"), r.getString("dirigente"), r.getString("subordinato"), r.getString("lavoroEstero"), r.getString("soggiornoEstero"));
                return ricerca;
            } else {
                return ricerca;
            }
        } catch (SQLException e) {
            return ricerca;
        }



    }

    public String setRicerca(searchData res, String id_azienda) {
        ResultSet r = null;
        Statement s = null; // versione con query non precompilata
        String query = "";
        try {
            // sRisultati.setInt(1,tipologia);  //versione con query precompilata
            // r = sRisultati.executeQuery(); //versione con query precompilata
            s = (Statement) c.createStatement(); // versione con query non precompilata

            //inserimento nella tabella curriculum
            //String id_utente = (String) sessione.getAttribute("userid");
            s.executeUpdate("DELETE FROM ricerca WHERE id_azienda = '" + id_azienda + "';");
            query = "INSERT INTO ricerca VALUES (NULL,'" + id_azienda + "','" + res.getEtaMin() + "','" + res.getEtaMax() + "','" + res.getSesso() + "','" + res.getLingua1() + "','" + res.getLingua2() + "','" + res.getLingua3() + "','" + res.getTipologia() + "','" + res.getDenominazione() + "','" + res.getPartTime() + "','" + res.getFullTime() + "','" + res.getDeterminato() + "','" + res.getIndeterminato() + "','" + res.getDirigente() + "','" + res.getSubordinato() + "','" + res.getLavoroEstero() + "','" + res.getSoggiornoEstero() + "','" + res.getProvincia() + "',unix_timestamp(now()));";
            //query="INSERT INTO ricerca VALUES (NULL , '2', '3', '4', 'M', 'l', 'l', 'l', '2', 'l', '1', '1', '1', '1', '1', '1', '1', '1', 'pe', '123');";

            s.executeUpdate(query);
            return "ok";
        } catch (SQLException e) {
            return query;
        } finally {
            try {
                //if (r!=null) r.close();
                if (s != null) {
                    s.close(); // versione con query non precompilata
                }
            } catch (SQLException e) {
            }
        }
    }

    public boolean checkCurriculum(String id_utente) {
        //istruzioni fittizie per creazione oggetti a caso da rimpiazzare con DATABASE


        ResultSet r = null;
        Statement s = null; // versione con query non precompilata
        try {
            s = (Statement) c.createStatement(); // versione con query non precompilata

            //id del curriculum
            r = s.executeQuery("SELECT count(*) as res FROM curriculum WHERE id_account = '" + id_utente + "';");
            r.next();
            String res = r.getString("res");

            if (res.equals("1")) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
            //return null;
        } finally {
            try {
                //if (r!=null) r.close();
                if (s != null) {
                    s.close(); // versione con query non precompilata
                }
            } catch (SQLException e) {
            }
        }



    }

    public Istruzione getIst(String id_ist) {
        //istruzioni fittizie per creazione oggetti a caso da rimpiazzare con DATABASE


        ResultSet r = null;
        Statement s = null; // versione con query non precompilata
        try {
            s = (Statement) c.createStatement(); // versione con query non precompilata

            //id del curriculum
            r = s.executeQuery("SELECT * FROM istruzione WHERE id_istruzione = '" + id_ist + "';");
            r.next();
            Istruzione ist = new Istruzione(Integer.parseInt(r.getString("id_istruzione")), r.getString("titolo"), r.getString("data"), r.getString("istituzione"), Integer.parseInt(r.getString("voto")), Integer.parseInt(r.getString("tipologia")));


            return ist;


        } catch (SQLException e) {

            e.printStackTrace();
            return null;
            //return null;
        } finally {
            try {
                //if (r!=null) r.close();
                if (s != null) {
                    s.close(); // versione con query non precompilata
                }
            } catch (SQLException e) {
            }
        }



    }

    public Esperienze getEsp(String id_esp) {
        //istruzioni fittizie per creazione oggetti a caso da rimpiazzare con DATABASE


        ResultSet r = null;
        Statement s = null; // versione con query non precompilata
        try {
            s = (Statement) c.createStatement(); // versione con query non precompilata

            //id del curriculum
            r = s.executeQuery("SELECT * FROM esperienze WHERE id_esperienze = '" + id_esp + "';");
            r.next();
            Esperienze esp = new Esperienze(Integer.parseInt(r.getString("id_esperienze")), r.getString("datore"), r.getString("incarico"), r.getString("periodo"));


            return esp;


        } catch (SQLException e) {

            e.printStackTrace();
            return null;
            //return null;
        } finally {
            try {
                //if (r!=null) r.close();
                if (s != null) {
                    s.close(); // versione con query non precompilata
                }
            } catch (SQLException e) {
            }
        }



    }

    public Lingue getLin(String id_lin) {
        //istruzioni fittizie per creazione oggetti a caso da rimpiazzare con DATABASE


        ResultSet r = null;
        Statement s = null; // versione con query non precompilata
        try {
            s = (Statement) c.createStatement(); // versione con query non precompilata

            //id del curriculum
            r = s.executeQuery("SELECT * FROM lingue WHERE id_lingue = '" + id_lin + "';");
            r.next();
            Lingue lin = new Lingue(Integer.parseInt(r.getString("id_lingue")), r.getString("nome"), Integer.parseInt(r.getString("livelloParlato")), Integer.parseInt(r.getString("livelloParlato")));


            return lin;


        } catch (SQLException e) {

            e.printStackTrace();
            return null;
            //return null;
        } finally {
            try {
                //if (r!=null) r.close();
                if (s != null) {
                    s.close(); // versione con query non precompilata
                }
            } catch (SQLException e) {
            }
        }



    }

    public String checkMod(String id_azienda) {
        String query = "";
        List<String> listaId = new ArrayList();
        ResultSet r = null;
        Statement s = null; // versione con query non precompilata
        String cont;
        try {
            s = (Statement) c.createStatement();
            searchData data = getRicerca(id_azienda);
            //cambiare minore con maggiore
            query = "SELECT COUNT(*) AS cont FROM curriculum, ricerca WHERE curriculum.dataModifica > ricerca.data AND id_curriculum IN (";
            query += "SELECT distinct id_curriculum FROM lingue where nome LIKE \"%" + data.getLingua1() + "%\" ) ";
            query += " and id_curriculum IN (SELECT distinct id_curriculum FROM lingue where nome LIKE \"%" + data.getLingua2() + "%\") ";
            query += " and id_curriculum IN (SELECT distinct id_curriculum FROM lingue where nome LIKE \"%" + data.getLingua3() + "%\") ";
            if (!(data.getPartTime().equals("0") && data.getFullTime().equals("0") && data.getDeterminato().equals("0") && data.getIndeterminato().equals("0") && data.getDirigente().equals("0") && data.getSubordinato().equals("0") && data.getLavoroEstero().equals("0") && data.getSoggiornoEstero().equals("0") && data.getProvincia().equals(""))) {
                query += "and id_curriculum IN (SELECT distinct id_curriculum FROM tipoImpiego WHERE (true ";
                if (data.getPartTime().equals("1")) {
                    query += " and partTime = 1  ";
                }

                if (data.getFullTime().equals("1")) {
                    query += " and fullTime = 1  ";
                }
                if (data.getDeterminato().equals("1")) {
                    query += " and determinato = 1  ";
                }
                if (data.getIndeterminato().equals("1")) {
                    query += " and indeterminato = 1  ";
                }
                if (data.getSubordinato().equals("1")) {
                    query += " and subordinato = 1  ";
                }
                if (data.getDirigente().equals("1")) {
                    query += " and dirigente = 1  ";
                }
                if (data.getLavoroEstero().equals("1")) {
                    query += " and lavoroEstero = 1  ";
                }
                if (data.getSoggiornoEstero().equals("1")) {
                    query += " and soggiornoEstero = 1  ";
                }
                query += ")";
                if (!data.getProvincia().equals("")) {
                    query += " and (provincia LIKE '%" + data.getProvincia() + "%' )";
                }
                query += ")";
            }
            if (!(data.getTipologia().equals("") || data.getDenominazione().equals(""))) {
                query += " and id_curriculum IN (SELECT distinct id_curriculum FROM istruzione where tipologia=" + data.getTipologia() + " and (titolo like \"%" + data.getDenominazione() + "%\" OR titolo like \"%" + data.getDenominazione() + "%\")) ";
            } else if (!(data.getTipologia().equals(""))) {
                query += " and id_curriculum IN (SELECT distinct id_curriculum FROM istruzione where tipologia=" + data.getTipologia() + " ) ";
            } else {
                query += " and id_curriculum IN (SELECT distinct id_curriculum FROM istruzione where titolo like \"%" + data.getDenominazione() + "%\" OR titolo like \"%" + data.getDenominazione() + "%\") ";
            }

            if (data.getSesso().equals("MF")) {
                query += " and id_curriculum IN (SELECT distinct id_curriculum FROM anagrafica where now() >= DATE_ADD( dataNascita , INTERVAL '" + data.getEtaMin() + "' year ) and now() < DATE_ADD( dataNascita , INTERVAL '" + data.getEtaMax() + "'+1 year )) ;";
            } else {
                query += " and id_curriculum IN (SELECT distinct id_curriculum FROM anagrafica where sesso=\"" + data.getSesso() + "\" and now() >= DATE_ADD( dataNascita , INTERVAL '" + data.getEtaMin() + "' year ) and now() < DATE_ADD( dataNascita , INTERVAL '" + data.getEtaMax() + "'+1 year )) ;";
            }


            r = s.executeQuery(query);
            r.next();
            cont = r.getString("cont");


            return cont;




            //r=s.executeQuery(query);
        } catch (SQLException e) {


            return null;
            //return null;
        } finally {
            try {
                //if (r!=null) r.close();
                if (s != null) {
                    s.close(); // versione con query non precompilata
                }
            } catch (SQLException e) {
            }
        }
    }

    public boolean updateCur(String update) {
        ResultSet r = null;
        Statement s = null; // versione con query non precompilata

        try {
            s = (Statement) c.createStatement();


            s.executeUpdate(update);



            return true;


            //r=s.executeQuery(query);
        } catch (SQLException e) {


            return false;
            //return null;
        } finally {
            try {
                //if (r!=null) r.close();
                if (s != null) {
                    s.close(); // versione con query non precompilata
                }
            } catch (SQLException e) {
            }
        }
    }

    public boolean addElem(String insert) {
        ResultSet r = null;
        Statement s = null; // versione con query non precompilata

        try {
            s = (Statement) c.createStatement();


            s.executeUpdate(insert);



            return true;


            //r=s.executeQuery(query);
        } catch (SQLException e) {


            return false;
            //return null;
        } finally {
            try {
                //if (r!=null) r.close();
                if (s != null) {
                    s.close(); // versione con query non precompilata
                }
            } catch (SQLException e) {
            }
        }
    }

    public boolean deleteElem(String delete) {
        ResultSet r = null;
        Statement s = null; // versione con query non precompilata

        try {
            s = (Statement) c.createStatement();


            s.executeUpdate(delete);



            return true;


            //r=s.executeQuery(query);
        } catch (SQLException e) {


            return false;
            //return null;
        } finally {
            try {


                //if (r!=null) r.close();
                if (s != null) {
                    s.close(); // versione con query non precompilata
                }
            } catch (SQLException e) {
            }
        }
    }

    public List<String> getMod(String id_azienda) {
        String query = "";
        List<String> listaId = new ArrayList();
        ResultSet r = null;
        Statement s = null; // versione con query non precompilata
        String cont;
        try {
            s = (Statement) c.createStatement();
            searchData data = getRicerca(id_azienda);
            //cambiare minore con maggiore
            query = "SELECT * FROM curriculum, ricerca WHERE curriculum.dataModifica > ricerca.data AND id_curriculum IN (";
            query += "SELECT distinct id_curriculum FROM lingue where nome LIKE \"%" + data.getLingua1() + "%\" ) ";
            query += " and id_curriculum IN (SELECT distinct id_curriculum FROM lingue where nome LIKE \"%" + data.getLingua2() + "%\") ";
            query += " and id_curriculum IN (SELECT distinct id_curriculum FROM lingue where nome LIKE \"%" + data.getLingua3() + "%\") ";
            if (!(data.getPartTime().equals("0") && data.getFullTime().equals("0") && data.getDeterminato().equals("0") && data.getIndeterminato().equals("0") && data.getDirigente().equals("0") && data.getSubordinato().equals("0") && data.getLavoroEstero().equals("0") && data.getSoggiornoEstero().equals("0") && data.getProvincia().equals(""))) {
                query += "and id_curriculum IN (SELECT distinct id_curriculum FROM tipoImpiego WHERE (true ";
                if (data.getPartTime().equals("1")) {
                    query += " and partTime = 1  ";
                }

                if (data.getFullTime().equals("1")) {
                    query += " and fullTime = 1  ";
                }
                if (data.getDeterminato().equals("1")) {
                    query += " and determinato = 1  ";
                }
                if (data.getIndeterminato().equals("1")) {
                    query += " and indeterminato = 1  ";
                }
                if (data.getSubordinato().equals("1")) {
                    query += " and subordinato = 1  ";
                }
                if (data.getDirigente().equals("1")) {
                    query += " and dirigente = 1  ";
                }
                if (data.getLavoroEstero().equals("1")) {
                    query += " and lavoroEstero = 1  ";
                }
                if (data.getSoggiornoEstero().equals("1")) {
                    query += " and soggiornoEstero = 1  ";
                }
                query += ")";
                if (!data.getProvincia().equals("")) {
                    query += " and (provincia LIKE '%" + data.getProvincia() + "%' )";
                }
                query += ")";
            }
            if (!(data.getTipologia().equals("") || data.getDenominazione().equals(""))) {
                query += " and id_curriculum IN (SELECT distinct id_curriculum FROM istruzione where tipologia=" + data.getTipologia() + " and (titolo like \"%" + data.getDenominazione() + "%\" OR titolo like \"%" + data.getDenominazione() + "%\")) ";
            } else if (!(data.getTipologia().equals(""))) {
                query += " and id_curriculum IN (SELECT distinct id_curriculum FROM istruzione where tipologia=" + data.getTipologia() + " ) ";
            } else {
                query += " and id_curriculum IN (SELECT distinct id_curriculum FROM istruzione where titolo like \"%" + data.getDenominazione() + "%\" OR titolo like \"%" + data.getDenominazione() + "%\") ";
            }

            if (data.getSesso().equals("MF")) {
                query += " and id_curriculum IN (SELECT distinct id_curriculum FROM anagrafica where now() >= DATE_ADD( dataNascita , INTERVAL '" + data.getEtaMin() + "' year ) and now() < DATE_ADD( dataNascita , INTERVAL '" + data.getEtaMax() + "'+1 year )) ;";
            } else {
                query += " and id_curriculum IN (SELECT distinct id_curriculum FROM anagrafica where sesso=\"" + data.getSesso() + "\" and now() >= DATE_ADD( dataNascita , INTERVAL '" + data.getEtaMin() + "' year ) and now() < DATE_ADD( dataNascita , INTERVAL '" + data.getEtaMax() + "'+1 year )) ;";
            }


            r = s.executeQuery(query);
            while (r.next()) {
                listaId.add(r.getString("id_curriculum"));
            }
            String update ="UPDATE ricerca SET data=unix_timestamp(now()) WHERE id_azienda='"+id_azienda+"';";
            s.executeUpdate(update);
            return listaId;


            //r=s.executeQuery(query);
        } catch (SQLException e) {


            return null;
            //return null;
        } finally {
            try {
                //if (r!=null) r.close();
                if (s != null) {
                    s.close(); // versione con query non precompilata
                }
            } catch (SQLException e) {
            }
        }
    }

    public boolean aggRic(String id_azienda) {
        ResultSet r = null;
        Statement s = null; // versione con query non precompilata
        String query = "";
        try {
            // sRisultati.setInt(1,tipologia);  //versione con query precompilata
            // r = sRisultati.executeQuery(); //versione con query precompilata
            s = (Statement) c.createStatement(); // versione con query non precompilata

            //inserimento nella tabella curriculum
            //String id_utente = (String) sessione.getAttribute("userid");

            query = "UPDATE ricerca SET data = unix_timestamp(now()) WHERE id_azienda = '" + id_azienda + "' ;";
            //query="INSERT INTO ricerca VALUES (NULL , '2', '3', '4', 'M', 'l', 'l', 'l', '2', 'l', '1', '1', '1', '1', '1', '1', '1', '1', 'pe', '123');";

            s.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            return false;
        } finally {
            try {
                //if (r!=null) r.close();
                if (s != null) {
                    s.close(); // versione con query non precompilata
                }
            } catch (SQLException e) {
            }
        }

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (c != null) {
            c.close();
        }
        //if (sRisultati!=null) sRisultati.close(); // versione con query non precompilata
    }

    public String getIdAccount(String username) {
        ResultSet r = null;
        Statement s = null; // versione con query non precompilata
        try {
            s = (Statement) c.createStatement(); // versione con query non precompilata
            r = s.executeQuery("SELECT id_account FROM account WHERE username='" + username + "';");

            r.next();
            return r.getString("id_account");

        } catch (SQLException e) {

            e.printStackTrace();
            return "-1";
        } finally {
            try {
                if (s != null) {
                    s.close(); // versione con query non precompilata
                }
            } catch (SQLException e) {
            }
        }

    }

    public String getTipologia(String username, String password) {
        ResultSet r = null;
        Statement s = null; // versione con query non precompilata
        try {
            s = (Statement) c.createStatement(); // versione con query non precompilata
            r = s.executeQuery("SELECT tipologia FROM account WHERE username='" + username + "' and password='" + password + "';");
            r.next();
            return r.getString("tipologia");

        } catch (SQLException e) {

            e.printStackTrace();
            return "-1";
        } finally {
            try {
                if (s != null) {
                    s.close(); // versione con query non precompilata
                }
            } catch (SQLException e) {
            }
        }

    }

    public String getIdCurriculum(String id_utente) {
        ResultSet r = null;
        Statement s = null; // versione con query non precompilata
        try {
            s = (Statement) c.createStatement(); // versione con query non precompilata
            r = s.executeQuery("SELECT id_curriculum FROM curriculum WHERE id_account = '" + id_utente + "';");
            r.next();
            return r.getString("id_curriculum");

        } catch (SQLException e) {

            e.printStackTrace();
            return "-1";
        } finally {
            try {
                if (s != null) {
                    s.close(); // versione con query non precompilata
                }
            } catch (SQLException e) {
            }
        }
    }

    public int checkFlag(String str) {
        if (str != null) {
            return 1;
        }
        return 0;
    }

    public boolean checkLogin(String username, String password) {



        ResultSet r = null;
        Statement s = null; // versione con query non precompilata
        try {
            s = (Statement) c.createStatement(); // versione con query non precompilata
            r = s.executeQuery("SELECT count(*) as conteggio FROM account WHERE username='" + username + "' and password='" + password + "';");
            r.next();

            if (Integer.parseInt(r.getString("conteggio")) == 0) {
                return false;
            } else {
                return true;
            }

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        } finally {
            try {
                if (s != null) {
                    s.close(); // versione con query non precompilata
                }
            } catch (SQLException e) {
            }
        }

    }

    public boolean checkBoolean(String var) {
        if (var.equals("0")) {
            return false;
        } else {
            return true;
        }
    }
}
