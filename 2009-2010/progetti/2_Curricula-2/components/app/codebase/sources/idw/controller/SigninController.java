package idw.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("request")
@RequestMapping("/signin/")
public class SigninController extends AbstractController
{
        @RequestMapping(method=RequestMethod.POST)
        public ModelAndView post()
        {
                /* We redirect the user to his personal account page. */
                this._mav.setViewName("redirect:"
                        + this._uriMapper.get("94611bb80ded8d7d", false)
                );

                return this._mav;
        }
}
