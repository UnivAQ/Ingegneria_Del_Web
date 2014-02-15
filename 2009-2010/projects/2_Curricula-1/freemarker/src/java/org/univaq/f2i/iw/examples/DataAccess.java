/*
 * DataAccess.java
 *
 * Un semplice strato di astrazione sulla sorgente dati.
 * Utilizza il pattern singleton
 *
 */


package org.univaq.f2i.iw.examples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;

/**
 *
 * @author Giuseppe Della Penna
 */
public class DataAccess {
    //singleton
    private static DataAccess singleton = null;
    public synchronized static DataAccess newDataAccess() throws ServletException {
        if (singleton == null) singleton = new DataAccess();
        return singleton;
    }
    
    private Connection c = null;
    private PreparedStatement sRisultati = null; // versione con query non precompilata
    
    private DataAccess() throws ServletException {
        /*
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/testdb","website","website");            
            sRisultati = c.prepareStatement("SELECT * FROM risultati WHERE IDesame= ?"); //versione con query precompilata
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
            throw new ServletException("Errore di caricamento driver database",e);
        } catch(SQLException e) {
            e.printStackTrace();
            throw new ServletException("Errore di connessione al database",e);
        }
         */
    }
    /*
    public List<Risultato> getRisultati(int idesame) {
        List<Risultato> result = new ArrayList();        
        
        ResultSet r = null;
        //Statement s = null; // versione con query non precompilata
        try {                        
            sRisultati.setInt(1,idesame);  //versione con query precompilata
            r = sRisultati.executeQuery(); //versione con query precompilata        
            //s = c.createStatement(); // versione con query non precompilata
            //r = s.executeQuery("SELECT * FROM risultati WHERE IDesame="+idesame); // versione con query non precompilata
            while(r.next()) {                
                result.add(new Risultato(r.getString("nome"),r.getString("matricola"),r.getInt("voto")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (r!=null) r.close();
                //if (s!=null) s.close(); // versione con query non precompilata
            } catch (SQLException e) { }
        }               
        return result;
    }
    */
    
     public List<Risultato> getRisultati(int idesame) {
        List<Risultato> result = new ArrayList();
        int nrighe=10;
        for(int riga=0; riga<nrighe;++riga) {
            result.add(new Risultato("Nome"+riga,"Matricola"+riga,riga));
        }
        return result;
    }

    protected void finalize() throws Throwable {
        super.finalize();
        /*
        if (c!=null) c.close();
        if (sRisultati!=null) sRisultati.close(); // versione con query non precompilata
        */
    }
}
