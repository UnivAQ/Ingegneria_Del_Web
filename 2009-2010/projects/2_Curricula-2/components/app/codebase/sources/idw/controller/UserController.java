package idw.controller;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import idw.dao.CvDao;
import idw.dao.NotificationDao;
import idw.dao.UserDao;
import idw.helper.ResourceLoader;
import idw.presentation.CssResource;
import java.util.ArrayList;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("request")
@RequestMapping("/user/")
public class UserController extends AbstractController
{
        @Autowired
        protected UserDao _user;

        @Autowired
        protected CvDao _cv;

        @Autowired
        protected NotificationDao _noties;

        @RequestMapping(method = RequestMethod.GET)
        public ModelAndView get()
        {
                if (!this._auth.isCandidate()) {
                        this._mav.setViewName("redirect:/");
                        return this._mav;
                }

                this._mav.setViewName("user@get");

                DBCursor notifies = this._noties.getBy(new NotificationDao.Uid<String>(
                        (String) this._auth.get("id")
                ));

                ArrayList<Map<String, Object>> ns = new ArrayList<Map<String, Object>>();

                Map e;
                for (DBObject n: notifies) {
                        String cid =(String) n.get(new NotificationDao.Cid<String>(null).getKey());
                        e = this._user.getBy(new UserDao.Id<String>(cid)).next().toMap();
                        e.put("_id", n.get("_id"));
                        ns.add(e);
                }

                this._mav.getModelMap().put("notifies_num", notifies.count());
                this._mav.getModelMap().put("notifies", ns);

                this._setCv();

                ResourceLoader.use(this._mav).add(new CssResource("base/areapersonale"));

                return this._mav;
        }

        @RequestMapping(value="/read-note/{id}/", method = RequestMethod.GET)
        public ModelAndView read(@PathVariable String id)
        {
                if (!this._auth.isCandidate()) {
                        this._mav.setViewName("redirect:/");
                        return this._mav;
                }

                this._mav.setViewName("redirect:"
                        + this._uriMapper.get("94611bb80ded8d7d", false)
                );

                this._noties.remove(this._noties.getBy(new NotificationDao.Id<String>(id)).next());

                return this._mav;
        }

        protected void _setCv()
        {
                final DBCursor cv = this._cv.getCvOf((String) this._auth.get("id"));

                this._mav.getModelMap().put("cv", cv.next().toMap());
        }
}
