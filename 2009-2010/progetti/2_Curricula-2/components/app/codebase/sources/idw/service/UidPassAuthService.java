package idw.service;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import idw.dao.UserDao;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.bson.types.ObjectId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("request")
public class UidPassAuthService
{
        protected HttpSession _session;
        protected HashMap<String, Object> _storage;

        protected UserDao _model;

        protected HashMap<String, Object> _data = new HashMap<String, Object>();

        @Autowired
        public UidPassAuthService(HttpSession session, UserDao model)
        {
                this._session = session;

                HashMap<String, Object> auth =(HashMap<String, Object>) this._session.getAttribute("auth");
                if (auth == null) {
                        this._session.setAttribute("auth", new HashMap<String, Object>());
                }
                this._storage =(HashMap) this._session.getAttribute("auth");

                this._model = model;
        }

        public UidPassAuthService authenticate(HttpServletRequest req)
        {
                if (this._storage.get("id") != null) {
                        this.authenticate((String) this._storage.get("id"));
                }

                if (req.getParameter("signin") != null
                        && req.getParameter("uid") != null
                        && req.getParameter("pass") != null
                ) {
                        String uid  = req.getParameter("uid");
                        String pass = req.getParameter("pass");

                        this.authenticate(uid, pass);
                }

                if (req.getParameter("signout") != null) {
                        this.forget();
                }

                return this;
        }

        public UidPassAuthService forget()
        {
                this._data.clear();
                this._storage.clear();

                return this;
        }

        public UidPassAuthService authenticate(String id)
        {
                this._import(this._normalize(this._model.getBy(
                        new UserDao.Id<String>(id)
                )));

                return this;
        }

        public UidPassAuthService authenticate(String uid, String pass)
        {
                DBCursor u = this._model.getBy(new UserDao.Uid<String>(uid));

                if (u.count() == 1) {
                        DBObject o = this._normalize(u);

                        String field = new UserDao.Pass<String>(null).getKey();

                        if (o.get(field).equals(pass)) {
                                this._import(o);
                        }
                }

                return this;
        }

        public Boolean isAuthenticated()
        {
                return this._data.get("id") != null;
        }

        public Boolean isCandidate()
        {
                return this.isAuthenticated()
                        && ((Integer) this._data.get("type")) == 1
                ;
        }

        public Boolean isCompany()
        {
                return this.isAuthenticated()
                        && ((Integer) this._data.get("type")) > 1
                ;
        }

        public Boolean isTrustedCompany()
        {
                return this.isCompany()
                        && ((Integer) this._data.get("type")) == 3
                ;
        }

        public Object get(String property)
        {
                return this._data.get(property);
        }

        protected DBObject _normalize(DBCursor doc)
        {
                /* We need to import only one record. */
                return doc.next();
        }

        protected UidPassAuthService _import(DBObject doc)
        {
                for (String key: doc.keySet()) {
                        Object value = doc.get(key);
                        if (key.equals("_id")) {
                                String id = ((ObjectId) value).toString();
                                this._storage.put("id", id);
                                this._data.put("id", id);
                        } else {
                                this._data.put(key, value);
                        }
                }


                return this;
        }
}
