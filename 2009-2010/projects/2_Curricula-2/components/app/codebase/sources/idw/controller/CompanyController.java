package idw.controller;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import idw.dao.CvDao;
import idw.dao.NotificationDao;
import idw.dao.ShotDao;
import idw.helper.ResourceLoader;
import idw.presentation.CssResource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
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
@RequestMapping("/company/")
public class CompanyController extends AbstractController
{
        @Autowired
        protected CvDao _cv;

        @Autowired
        protected NotificationDao _noties;

        @Autowired
        protected ShotDao _shots;

        @RequestMapping(method=RequestMethod.GET)
        public ModelAndView get()
        {
                if (! this._auth.isCompany()) {
                        this._mav.setViewName("redirect:/");
                        return this._mav;
                }

                this._mav.setViewName("company@get");

                this._mav.getModelMap().put("active", "cv-search");

                ResourceLoader.use(this._mav).add(new CssResource("base/areapersonale"));
                ResourceLoader.use(this._mav).add(new CssResource("base/form"));

                return this._mav;
        }

        @RequestMapping(value="/oneshot/", method=RequestMethod.GET)
        public ModelAndView oneshot()
        {
                if (! this._auth.isCompany()) {
                        this._mav.setViewName("redirect:/");
                        return this._mav;
                }

                DBCursor r = this._shots.getBy(new ShotDao.Cid<String>(
                        (String) this._auth.get("id")
                ));

                if (r.count() == 0) {
                        this._mav.setViewName("redirect:/company/");

                        return this._mav;
                }

                Map<String, Object> params = r.next().toMap();
                
                HashMap<String, Integer> prefs = new HashMap<String, Integer>();
                prefs.put("fulltime", (Integer) params.get("fulltime"));
                prefs.put("partime", (Integer) params.get("partime"));
                prefs.put("turni", (Integer) params.get("turni"));
                prefs.put("project", (Integer) params.get("project"));

                this.search(
                        (String) params.get("gender"),
                        (String) params.get("province"),
                        (String)params.get("language"),
                        (String) params.get("studies"),
                        (String) params.get("city"),
                        prefs
                );

                this._mav.setViewName("company.oneshot@get");

                this._mav.getModelMap().put("active", "oneshot-search");

                return this._mav;
        }

        @RequestMapping(value="/search/", method=RequestMethod.GET)
        public ModelAndView search(
                @RequestParam("gender") String gender,
                @RequestParam("province") String province,
                @RequestParam("language") String language,
                @RequestParam("studies") String studies,
                @RequestParam("city") String city,
                HashMap<String, Integer> prefs
        ) {
                if (! this._auth.isCompany()) {
                        this._mav.setViewName("redirect:/");
                        return this._mav;
                }

                Integer fulltime = this._request.getParameter("fulltime") != null ? 1 : 0;
                Integer partime = this._request.getParameter("partime") != null ? 1 : 0;
                Integer turni = this._request.getParameter("turni") != null ? 1 : 0;
                Integer project = this._request.getParameter("project") != null ? 1 : 0;

                if (prefs != null) {
                        if (prefs.get("fulltime") != null) {
                                fulltime = prefs.get("fulltime");
                        }
                        if (prefs.get("partime") != null) {
                                partime = prefs.get("partime");
                        }
                        if (prefs.get("turni") != null) {
                                turni = prefs.get("turni");
                        }
                        if (prefs.get("project") != null) {
                                project = prefs.get("project");
                        }
                }

                if (this._request.getParameter("oneshot") != null) {
                        this._mav.setViewName("redirect:/company/");

                        ShotDao.Cid<String> cid = new ShotDao.Cid<String>(
                                (String) this._auth.get("id")
                        );

                        DBCursor s = this._shots.getBy(cid);

                        if (s.count() == 0) {
                                this._shots.newOne()
                                        .set(cid)
                                        .save()
                                ;

                                s = this._shots.getBy(cid);
                        }

                        DBObject o = s.next();
                        o.put("gender", gender);
                        o.put("province", province);
                        o.put("language", language);
                        o.put("studies", studies);
                        o.put("city", city);
                        o.put("fulltime", fulltime);
                        o.put("partime", partime);
                        o.put("turni", turni);
                        o.put("project", project);
                        this._shots.store(o);

                        return this._mav;
                }

                this._mav.setViewName("company.search@get");

                this._mav.getModelMap().put("active", "cv-search");

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
                        or.add(new BasicDBObject("language", language));
                }
                if (! studies.equals("")) {
                        or.add(new BasicDBObject("studies", studies));
                }
                if (! city.equals("")) {
                        or.add(new BasicDBObject("city", city.toLowerCase()));
                }
                or.add(new BasicDBObject("fulltime", fulltime));
                or.add(new BasicDBObject("partime", partime));
                or.add(new BasicDBObject("turni", turni));
                or.add(new BasicDBObject("project", project));

                query.put("$or", or);

                DBCursor results = this._cv.col().find(query);

                this._mav.getModelMap().put("results", results.toArray());

                ResourceLoader.use(this._mav).add(new CssResource("base/areapersonale"));
                ResourceLoader.use(this._mav).add(new CssResource("base/form"));

                return this._mav;
        }

        @RequestMapping(value="/notify/{uid}/", method=RequestMethod.GET)
        public ModelAndView notify_get(@PathVariable String uid)
        {
                if (! this._auth.isCompany()) {
                        this._mav.setViewName("redirect:/");
                        return this._mav;
                }

                this._noties.newOne()
                        .set(new NotificationDao.Uid<String>(uid))
                        .set(new NotificationDao.Cid<String>((String) this._auth.get("id")))
                        .save()
                ;

                this._mav.setViewName("company.notify@get");

                ResourceLoader.use(this._mav).add(new CssResource("base/areapersonale"));

                return this._mav;
        }
}
