package idw.service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("request")
public class ModelMapService
{
        @Autowired protected HttpServletRequest request;
                   protected String context;
        @Autowired protected UriMapperService   uri;
        @Autowired protected UidPassAuthService auth;

        @PostConstruct
        protected void _init()
        {
                this.context = this.request.getContextPath();
        }

        public HttpServletRequest getRequest() { return this.request; }
        public String             getContext() { return this.context; }
        public UriMapperService   getUri()     { return this.uri;     }
        public UidPassAuthService getAuth()    { return this.auth;    }
}
