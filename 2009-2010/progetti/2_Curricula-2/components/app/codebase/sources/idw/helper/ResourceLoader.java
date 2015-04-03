package idw.helper;

import java.util.ArrayList;
//import java.util.Map;
import java.util.HashMap;

import org.springframework.web.servlet.ModelAndView;

import idw.presentation.LoadableResourceInterface;

public class ResourceLoader
{
        protected HashMap<String, Object> _resources;

        public static ResourceLoader use(ModelAndView mav)
        {
                return new ResourceLoader(mav.getModelMap());
        }

        @SuppressWarnings({"unchecked"})
        public ResourceLoader(HashMap<String, Object> model)
        {
                if (! model.containsKey("resources")) {
                        HashMap<String, ArrayList<String>> resources = new HashMap<String, ArrayList<String>>();

                        resources.put("css", new ArrayList<String>());
                        resources.put("js", new ArrayList<String>());

                        model.put("resources", resources);
                }

                this._resources =(HashMap<String, Object>) model.get("resources");
        }

        @SuppressWarnings({"unchecked"})
        public ResourceLoader add(LoadableResourceInterface res)
        {
                ((ArrayList<String>) this._resources.get(res.getType())).add(
                        res.getResource()
                );

                return this;
        }
}
