package idw.controller;

import idw.dao.CvDao;
import idw.dao.NotificationDao;
import idw.helper.ResourceLoader;
import idw.presentation.CssResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("request")
@RequestMapping("/view/")
public class ViewController extends AbstractController
{
        @Autowired
        protected CvDao _cv;

        @Autowired
        protected NotificationDao _noties;

        @RequestMapping(value="/{uid}/", method=RequestMethod.GET)
        public ModelAndView get(@PathVariable String uid)
        {
                this._mav.setViewName("view@get");

                this._mav.getModelMap().put("active", "cv-search");
                this._mav.getModelMap().put("user_id", uid);
                this._mav.getModelMap().put("cv", this._cv.getCvOf(uid).next().toMap());

                ResourceLoader.use(this._mav).add(new CssResource("base/areapersonale"));
                ResourceLoader.use(this._mav).add(new CssResource("base/form"));

                return this._mav;
        }
}
