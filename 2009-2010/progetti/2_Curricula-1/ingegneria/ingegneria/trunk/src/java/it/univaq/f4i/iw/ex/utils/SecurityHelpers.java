package it.univaq.f4i.iw.ex.utils;

import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SecurityHelpers {

   
    public static HttpSession checkSession(HttpServletRequest r) {
        boolean check = true;

        HttpSession s = r.getSession(false);
        //per prima cosa vediamo se la sessione � attiva
        if (s == null) {
            return null;
        }

        //check sulla validit� della sessione
        if (s.getAttribute("userid") == null) {
            check = false;
            //check sull'ip del client
        } else if (!((String) s.getAttribute("ip")).equals(r.getRemoteHost())) {
            check = false;
            //check sulle date
        } else {
            //inizio sessione
            Calendar begin = (Calendar) s.getAttribute("inizio-sessione");
            //ultima azione
            Calendar last = (Calendar) s.getAttribute("ultima-azione");
            //data/ora correnti
            Calendar now = Calendar.getInstance();
            if (begin == null) {
                check = false;
            } else {
                //millisecondi trascorsi dall'inizio della sessione
                long secondsfrombegin = (now.getTimeInMillis() - begin.getTimeInMillis()) / 1000;
                //dopo tre ore la sessione scade
                if (secondsfrombegin / 60 > 3 * 60) {
                    check = false;
                } else if (last != null) {
                    //millisecondi trascorsi dall'ultima azione
                    long secondsfromlast = (now.getTimeInMillis() - last.getTimeInMillis()) / 1000;
                    //dopo 30 minuti dall'ultima operazione la sessione è invalidata
                    if (secondsfromlast / 60 > 1 * 60) {
                        check = false;
                    }
                }
            }
        }
        if (!check) {
            s.invalidate();
            return null;
        } else {
            //reimpostiamo la data/ora dell'ultima azione
            s.setAttribute("ultima-azione", Calendar.getInstance());
            return s;
        }
    }
}
