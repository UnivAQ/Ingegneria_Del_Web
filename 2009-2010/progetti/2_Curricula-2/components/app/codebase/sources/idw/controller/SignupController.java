package idw.controller;

import idw.helper.ResourceLoader;
import idw.presentation.CssResource;
import idw.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("request")
@RequestMapping("/signup/")
public class SignupController extends AbstractController
{
        @Autowired
        protected UserDao _user;

        @RequestMapping(method=RequestMethod.GET)
        public ModelAndView get()
        {
                this._mav.setViewName("signup@get");

                this._mav.getModelMap().put("active", "signup");
                this._mav.getModelMap().put("error", "");

                ResourceLoader.use(this._mav).add(new CssResource("base/form"));
                ResourceLoader.use(this._mav).add(new CssResource("base/registrazione"));

                return this._mav;
        }

        @RequestMapping(method=RequestMethod.POST)
        public ModelAndView post(
                @RequestParam("uid") String uid,
                @RequestParam("pass") String pass,
                @RequestParam("pass2") String pass2,
                @RequestParam("type") String type,
                @RequestParam("email") String email,
                @RequestParam("piva") String piva

        ) {
                this._mav.setViewName("signup@get");

                this._mav.getModelMap().put("active", "signup");

                ResourceLoader.use(this._mav).add(new CssResource("base/form"));
                ResourceLoader.use(this._mav).add(new CssResource("base/areapersonale"));
                ResourceLoader.use(this._mav).add(new CssResource("base/registrazione"));

                if (uid.length() == 0) {
                        this._mav.getModelMap().put("error", "user-length");
                        return this._mav;
                } else if (! pass.equals(pass2)) {
                        this._mav.getModelMap().put("error", "pass-different");
                        return this._mav;
                } else if (pass.length() == 0) {
                        this._mav.getModelMap().put("error", "pass-length");
                        return this._mav;
                } else if (email.length() == 0) {
                        this._mav.getModelMap().put("error", "email-length");
                        return this._mav;
                } else if ( email.indexOf("@") == -1
                            || email.indexOf("@") != email.lastIndexOf("@")
                            || email.length() <= 3
                ) {
                        this._mav.getModelMap().put("error", "email-invalid");
                        return this._mav;
                } else if (this._user.exists(new UserDao.Uid<String>(uid))) {
                        /* We need to check that the uid doesn't exist yet. */
                        this._mav.getModelMap().put("error", "user-exists");
                        return this._mav;
                }

                this._mav.setViewName("signup@post");

                Integer t = type.equals("company") ?
                        (piva.trim().length() > 1 ?
                                3:
                                2
                        ): 1
                ;

                this._auth.authenticate(
                        this._user.newOne()
                                .set(new UserDao.Uid<String>(uid))
                                .set(new UserDao.Pass<String>(pass))
                                .set(new UserDao.Email<String>(email))
                                .set(new UserDao.Type<Integer>(t))
                                .set(new UserDao.Piva<String>(piva))
                                .save()
                );

                this._mav.getModelMap().put("uid", uid);

                return this._mav;
        }
}
