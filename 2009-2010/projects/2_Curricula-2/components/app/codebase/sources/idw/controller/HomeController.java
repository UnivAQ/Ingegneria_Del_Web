package idw.controller;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import idw.dao.CvDao;
import idw.helper.ResourceLoader;
import idw.presentation.CssResource;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("request")
@RequestMapping("/")
public class HomeController extends AbstractController
{
        @Autowired
        protected CvDao _cv;

        @RequestMapping(method=RequestMethod.GET)
        public ModelAndView get()
        {
                this._mav.setViewName("home");

                 this._mav.getModelMap().put("active", "home");

                 ResourceLoader.use(this._mav).add(new CssResource("base/home"));
                 ResourceLoader.use(this._mav).add(new CssResource("base/form"));
                 ResourceLoader.use(this._mav).add(new CssResource("base/faq"));

                return this._mav;
        }

        @RequestMapping(value="/search/", method=RequestMethod.GET)
        public ModelAndView search(
                @RequestParam("gender") String gender,
                @RequestParam("province") String province,
                @RequestParam("language") String language,
                @RequestParam("studies") String studies
        ) {
                this._mav.setViewName("home");

                this._mav.getModelMap().put("active", "search-curriculum");

                BasicDBObject query = new BasicDBObject();

                ArrayList<BasicDBObject> or = new ArrayList<BasicDBObject>();

                if (! gender.equals("")) {
                        if (gender.equals("b")) {
                                or.add(new BasicDBObject("gender", "m"));
                                or.add(new BasicDBObject("gender", "f"));
                        } else {
                                or.add(new BasicDBObject("gender", gender));
                        }
                }
                if (! province.equals("")) {
                        or.add(new BasicDBObject("province", province.toLowerCase()));
                }
                if (! language.equals("")) {
                        or.add(new BasicDBObject("language", province));
                }
                if (! studies.equals("")) {
                        or.add(new BasicDBObject("studies", province));
                }

                query.put("$or", or);

                DBCursor results = this._cv.col().find(query);

                this._mav.getModelMap().put("results", results.toArray());

                this._mav.getModelMap().put("active", "home");

                ResourceLoader.use(this._mav).add(new CssResource("base/home"));
                ResourceLoader.use(this._mav).add(new CssResource("base/form"));
                ResourceLoader.use(this._mav).add(new CssResource("base/faq"));

                return this._mav;
        }
}
