package idw.controller;

import com.mongodb.DBObject;
import idw.dao.CvDao;
import idw.helper.ResourceLoader;
import idw.presentation.CssResource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("request")
@RequestMapping("/user/c/")
public class UserCurriculumController extends AbstractController
{
        @Autowired
        protected CvDao _cv;

        @RequestMapping(method=RequestMethod.GET)
        public ModelAndView get()
        {
                if (! this._auth.isCandidate()) {
                        this._mav.setViewName("redirect:/");

                        return this._mav;
                }

                this._mav.setViewName("user.c@get");

                this._setCv();

                this._mav.getModelMap().put("active", "personal-area");
                this._mav.getModelMap().put("cm_active", "cm-personal");

                ResourceLoader.use(this._mav).add(new CssResource("base/interno"));
                ResourceLoader.use(this._mav).add(new CssResource("base/form"));
                ResourceLoader.use(this._mav).add(new CssResource("base/curriculum"));

                return this._mav;
        }

        @RequestMapping(method=RequestMethod.POST)
        public ModelAndView post(
                @RequestParam("name") String name,
                @RequestParam("surname") String surname,
                @RequestParam("gender") String gender,
                @RequestParam("birthday") String birthday,
                @RequestParam("province") String province,
                @RequestParam("city") String city,
                @RequestParam("email") String email
        ) {
                if (! this._auth.isCandidate()) {
                        this._mav.setViewName("redirect:/");

                        return this._mav;
                }

                this._mav.setViewName("user.c@get");

                if (    birthday.equals("")
                        || province.equals("")
                        || city.equals("")
                ) {
                       this._mav.getModelMap().put("msg", false);
                } else {
                        this._mav.getModelMap().put("msg", true);

                        DBObject cv = this._getCv();
                        cv.put("name", name.toLowerCase());
                        cv.put("surname", surname.toLowerCase());
                        cv.put("gender", gender);
                        cv.put("birthday", birthday);
                        cv.put("province", province.toLowerCase());
                        cv.put("city", city.toLowerCase());
                        cv.put("email", email);
                        cv.put("new", false);
                        this._cv.store(cv);
                }

                this._setCv();

                this._mav.getModelMap().put("active", "personal-area");
                this._mav.getModelMap().put("cm_active", "cm-personal");

                ResourceLoader.use(this._mav).add(new CssResource("base/interno"));
                ResourceLoader.use(this._mav).add(new CssResource("base/form"));
                ResourceLoader.use(this._mav).add(new CssResource("base/curriculum"));

                return this._mav;
        }

        @RequestMapping(value="/prefs/", method=RequestMethod.GET)
        public ModelAndView prefs()
        {
                if (! this._auth.isCandidate()) {
                        this._mav.setViewName("redirect:/");

                        return this._mav;
                }

                this._mav.setViewName("user.c.prefs@get");

                this._setCv();

                this._mav.getModelMap().put("active", "personal-area");
                this._mav.getModelMap().put("cm_active", "cm-prefs");

                ResourceLoader.use(this._mav).add(new CssResource("base/interno"));
                ResourceLoader.use(this._mav).add(new CssResource("base/form"));
                ResourceLoader.use(this._mav).add(new CssResource("base/curriculum"));

                return this._mav;
        }

        @RequestMapping(value="/prefs/", method=RequestMethod.POST)
        public ModelAndView prefs_post() {
                if (! this._auth.isCandidate()) {
                        this._mav.setViewName("redirect:/");

                        return this._mav;
                }

                this._mav.setViewName("user.c.prefs@get");

                DBObject cv = this._getCv();
                cv.put("partime", this._request.getParameter("partime") != null ? 1 : 0);
                cv.put("fulltime", this._request.getParameter("fulltime") != null ? 1 : 0);
                cv.put("turni", this._request.getParameter("turni") != null ? 1 : 0);
                cv.put("project", this._request.getParameter("project") != null ? 1 : 0);
                this._cv.store(cv);

                this._setCv();

                this._mav.getModelMap().put("active", "personal-area");
                this._mav.getModelMap().put("cm_active", "cm-prefs");

                ResourceLoader.use(this._mav).add(new CssResource("base/interno"));
                ResourceLoader.use(this._mav).add(new CssResource("base/form"));
                ResourceLoader.use(this._mav).add(new CssResource("base/curriculum"));

                return this._mav;
        }

        @RequestMapping(value="/studies/", method=RequestMethod.GET)
        public ModelAndView studies()
        {
                if (! this._auth.isCandidate()) {
                        this._mav.setViewName("redirect:/");

                        return this._mav;
                }

                this._mav.setViewName("user.c.studies@get");

                this._setCv();

                this._mav.getModelMap().put("active", "personal-area");
                this._mav.getModelMap().put("cm_active", "cm-studies");

                ResourceLoader.use(this._mav).add(new CssResource("base/interno"));
                ResourceLoader.use(this._mav).add(new CssResource("base/form"));
                ResourceLoader.use(this._mav).add(new CssResource("base/curriculum"));

                return this._mav;
        }

        @RequestMapping(value="/studies/", method=RequestMethod.POST)
        public ModelAndView studies_post(
                @RequestParam("studies") String studies
        ) {
                if (! this._auth.isCandidate()) {
                        this._mav.setViewName("redirect:/");

                        return this._mav;
                }

                this._mav.setViewName("user.c.studies@get");

                if (studies.equals("")) {
                        this._mav.getModelMap().put("msg", false);
                } else {
                        this._mav.getModelMap().put("msg", true);

                        DBObject cv = this._getCv();
                        cv.put("studies", studies.toLowerCase());
                        this._cv.store(cv);
                }

                this._setCv();

                this._mav.getModelMap().put("active", "personal-area");
                this._mav.getModelMap().put("cm_active", "cm-studies");

                ResourceLoader.use(this._mav).add(new CssResource("base/interno"));
                ResourceLoader.use(this._mav).add(new CssResource("base/form"));
                ResourceLoader.use(this._mav).add(new CssResource("base/curriculum"));

                return this._mav;
        }

        @RequestMapping(value="/experiences/", method=RequestMethod.GET)
        public ModelAndView experiences()
        {
                if (! this._auth.isCandidate()) {
                        this._mav.setViewName("redirect:/");

                        return this._mav;
                }

                this._mav.setViewName("user.c.experiences@get");

                this._setCv();

                this._mav.getModelMap().put("active", "personal-area");
                this._mav.getModelMap().put("cm_active", "cm-experiences");

                ResourceLoader.use(this._mav).add(new CssResource("base/interno"));
                ResourceLoader.use(this._mav).add(new CssResource("base/form"));
                ResourceLoader.use(this._mav).add(new CssResource("base/curriculum"));

                return this._mav;
        }

        @RequestMapping(value="/experiences/", method=RequestMethod.POST)
        public ModelAndView experiences_post(
                    @RequestParam("azienda") String azienda,
                    @RequestParam("inizio") String inizio,
                    @RequestParam("fine") String fine,
                    @RequestParam("ruolo") String ruolo,
                    @RequestParam("retribuzione") String retribuzione
        ) {

                if (! this._auth.isCandidate()) {
                        this._mav.setViewName("redirect:/");

                        return this._mav;
                }

                this._mav.setViewName("user.c.experiences@get");

                if (inizio.equals("") || fine.equals("") || ruolo.equals("")) {
                        this._mav.getModelMap().put("msg", false);
                } else {
                        this._mav.getModelMap().put("msg", true);

                        if(azienda.equals("")) {
                                azienda = "********";
                        }

                        DBObject cv = this._getCv();

                        ArrayList<HashMap<String, Object>> experiences =(ArrayList<HashMap<String, Object>>)
                                cv.get("experiences")
                        ;

                        if (experiences == null) {
                                experiences = new ArrayList<HashMap<String, Object>>();
                        }

                        HashMap<String, Object> one = new HashMap<String, Object>();
                        one.put("azienda", azienda.toLowerCase());
                        one.put("inizio", inizio);
                        one.put("fine", fine);
                        one.put("ruolo", ruolo.toLowerCase());
                        one.put("retribuzione", retribuzione);

                        experiences.add(one);

                        cv.put("experiences", experiences);
                        this._cv.store(cv);
                }

                this._setCv();

                this._mav.getModelMap().put("active", "personal-area");
                this._mav.getModelMap().put("cm_active", "cm-experiences");

                ResourceLoader.use(this._mav).add(new CssResource("base/interno"));
                ResourceLoader.use(this._mav).add(new CssResource("base/form"));
                ResourceLoader.use(this._mav).add(new CssResource("base/curriculum"));

                return this._mav;
        }
        
        @RequestMapping(value="/experiences/delete/{index}/", method=RequestMethod.GET)
        public ModelAndView experiences_del(@PathVariable String index)
        {
                if (! this._auth.isCandidate()) {
                        this._mav.setViewName("redirect:/");

                        return this._mav;
                }

                DBObject cv = this._getCv();

                ArrayList<HashMap<String, Object>> experiences =(ArrayList<HashMap<String, Object>>)
                        cv.get("experiences")
                ;

                experiences.remove(Integer.parseInt(index));

                cv.put("experiences", experiences);
                this._cv.store(cv);

                this._mav.setViewName("redirect:/user/c/experiences/");

                return this._mav;
        }

        @RequestMapping(value="/skills/", method=RequestMethod.GET)
        public ModelAndView skills()
        {
                if (! this._auth.isCandidate()) {
                        this._mav.setViewName("redirect:/");

                        return this._mav;
                }

                this._mav.setViewName("user.c.skills@get");

                this._setCv();

                this._mav.getModelMap().put("active", "personal-area");
                this._mav.getModelMap().put("cm_active", "cm-skills");

                ResourceLoader.use(this._mav).add(new CssResource("base/interno"));
                ResourceLoader.use(this._mav).add(new CssResource("base/form"));
                ResourceLoader.use(this._mav).add(new CssResource("base/curriculum"));

                return this._mav;
        }

        @RequestMapping(value="/skills/", method=RequestMethod.POST)
        public ModelAndView skills_post(
                    @RequestParam("settore") String settore,
                    @RequestParam("tipo") String tipo,
                    @RequestParam("livello") String livello
        ) {
                if (! this._auth.isCandidate()) {
                        this._mav.setViewName("redirect:/");

                        return this._mav;
                }

                this._mav.setViewName("user.c.skills@get");


                if (settore.equals("") || tipo.equals("") || livello.equals("")) {
                        this._mav.getModelMap().put("msg", false);
                } else {
                        this._mav.getModelMap().put("msg", true);

                        DBObject cv = this._getCv();

                        ArrayList<HashMap<String, Object>> skills =(ArrayList<HashMap<String, Object>>)
                                cv.get("skills")
                        ;

                        if (skills == null) {
                                skills = new ArrayList<HashMap<String, Object>>();
                        }

                        HashMap<String, Object> one = new HashMap<String, Object>();
                        one.put("settore", settore.toLowerCase());
                        one.put("tipo", tipo.toLowerCase());
                        one.put("livello", livello.toLowerCase());

                        skills.add(one);

                        cv.put("skills", skills);
                        this._cv.store(cv);
                }

                this._setCv();

                this._mav.getModelMap().put("active", "personal-area");
                this._mav.getModelMap().put("cm_active", "cm-skills");

                ResourceLoader.use(this._mav).add(new CssResource("base/interno"));
                ResourceLoader.use(this._mav).add(new CssResource("base/form"));
                ResourceLoader.use(this._mav).add(new CssResource("base/curriculum"));

                return this._mav;
        }

        @RequestMapping(value="/skills/delete/{index}/", method=RequestMethod.GET)
        public ModelAndView skills_del(@PathVariable String index)
        {
                if (! this._auth.isCandidate()) {
                        this._mav.setViewName("redirect:/");

                        return this._mav;
                }

                DBObject cv = this._getCv();

                ArrayList<HashMap<String, Object>> skills =(ArrayList<HashMap<String, Object>>)
                        cv.get("skills")
                ;

                skills.remove(Integer.parseInt(index));

                cv.put("skills", skills);
                this._cv.store(cv);

                this._mav.setViewName("redirect:/user/c/skills/");

                return this._mav;
        }

        @RequestMapping(value="/summary/", method=RequestMethod.GET)
        public ModelAndView summary()
        {
                if (! this._auth.isCandidate()) {
                        this._mav.setViewName("redirect:/");

                        return this._mav;
                }

                this._mav.setViewName("user.c.summary@get");

                this._setCv();

                this._mav.getModelMap().put("active", "personal-area");
                this._mav.getModelMap().put("cm_active", "cm-summary");

                ResourceLoader.use(this._mav).add(new CssResource("base/interno"));
                ResourceLoader.use(this._mav).add(new CssResource("base/form"));
                ResourceLoader.use(this._mav).add(new CssResource("base/curriculum"));

                return this._mav;
        }

        protected DBObject _getCv()
        {
                return this._cv.getCvOf((String) this._auth.get("id")).next();
        }

        protected void _setCv()
        {
                this._mav.getModelMap().put("cv", this._getCv().toMap());
        }
}
