/*
 * TemplateManager.java
 *
 * Un semplice strato di API che gestiscono la logica specifica del template engine FreeMarker
 * Utilizza una versione "parametrica" del pattern singleton
 *
 */

package org.univaq.f2i.iw.examples;

import freemarker.template.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 *
 * @author Ingegneria del Web
 */
public class TemplateManager {
    private static Map<ServletContext,TemplateManager> instances = new HashMap();
    public synchronized static TemplateManager getInstance(ServletContext s) {
        if (!instances.containsKey(s)) instances.put(s,new TemplateManager(s));
        return instances.get(s);
    }
    
    private Configuration cfg;
    
    /** Creates a new instance of TemplateManager */
    private TemplateManager(ServletContext s) {
        cfg = new Configuration();
        cfg.setServletContextForTemplateLoading(s,"templates");
        cfg.setObjectWrapper(new DefaultObjectWrapper());
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
    }
    
    public void processTemplate(String template, Object data, PrintWriter out) throws ServletException {
        try {
            Template t = cfg.getTemplate(template+".ftl.html");
            t.process(data,out);
        } catch (IOException e) {
            throw new ServletException("Errore nell'elaborazione del template",e);
        } catch (TemplateException e) {
            throw new ServletException("Errore nell'elaborazione del template",e);
        }
    }
    
    
    
}
