package idw.controller;

import idw.helper.ResourceLoader;
import idw.presentation.CssResource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("request")
@RequestMapping("/faq/")
public class FaqController extends AbstractController
{
        @RequestMapping(method=RequestMethod.GET)
        public ModelAndView get()
        {
                this._mav.setViewName("faq@get");

                this._mav.getModelMap().put("active", "faq");

                ResourceLoader.use(this._mav).add(new CssResource("base/faq"));

                return this._mav;
        }
}
