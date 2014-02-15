package idw.service;

import java.util.HashMap;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("request")
public class UriMapperService
{
        @Autowired
        protected HttpServletRequest request;

        protected Integer _contextLength;

        protected HashMap<String, Generator> _map = new HashMap<String, Generator>();

        @PostConstruct
        public void _init_()
        {
                this._contextLength = this.request.getContextPath().length();
        }

        public String get(String id)
        {
                Generator gen = this._map.get(id);

                return gen.gen();
        }

        public String get(String id, Boolean context)
        {
                return context ?
                        this.get(id):
                        this.get(id).substring(this._contextLength)
                ;
        }

        public UriMapperService add(String id, Generator gen)
        {
                this._map.put(id, gen);

                return this;
        }

        public interface Generator
        {
                public String gen();
        }
}
