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

    private static Map<ServletContext, TemplateManager> instances = new HashMap();

    public synchronized static TemplateManager getInstance(ServletContext s) {
        if (!instances.containsKey(s)) {
            instances.put(s, new TemplateManager(s));
        }
        return instances.get(s);
    }
    
    private Configuration cfg;
    private Template outline;
    private String outline_file;   

    /** Creates a new instance of TemplateManager */
    private TemplateManager(ServletContext s) {
        cfg = new Configuration();
        cfg.setServletContextForTemplateLoading(s, "templates");
        cfg.setObjectWrapper(new DefaultObjectWrapper());
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        outline = null;        
    }

    public void setOutlineTemplate(String template) throws ServletException {
        if (template != null) {
            try {
                outline_file = template + ".ftl.html";
                outline = cfg.getTemplate(outline_file);                
            } catch (IOException e) {
                throw new ServletException("Errore nell'elaborazione del template", e);
            }
        } else {
            outline = null;
        }
    }
    
    public Map getDefaultDataModel() {
        Map result = new HashMap();
        Map outlinedata = new HashMap();        
        outlinedata.put("contenttpl", "dummy.ftl.html");
        result.put("outline", outlinedata);      
        return result;
    }

    public void processTemplate(String template, Map<String, Object> data, PrintWriter out) throws ServletException {
        Template t;
        try {
            if (outline == null) {
                t = cfg.getTemplate(template + ".ftl.html");
            } else {
                t = outline;
                if (data == null) data = getDefaultDataModel();
                ((Map) data.get("outline")).put("contenttpl", template + ".ftl.html");
            }
            t.process(data, out);
        } catch (IOException e) {
            throw new ServletException("Errore nell'elaborazione del template", e);
        } catch (TemplateException e) {
            throw new ServletException("Errore nell'elaborazione del template", e);
        }
    }
}
