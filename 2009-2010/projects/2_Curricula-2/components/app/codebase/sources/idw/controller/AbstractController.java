package idw.controller;

import com.mongodb.DBCursor;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import idw.service.ModelMapService;
import idw.service.UidPassAuthService;
import idw.service.UriGeneratorService;
import idw.service.UriMapperService;

public abstract class AbstractController
{
        @Autowired
        protected HttpServletRequest _request;

        @Autowired
        protected HttpSession _session;

        @Autowired
        protected UidPassAuthService _auth;

        @Autowired
        protected ModelMapService _modelMap;

        @Autowired
        protected UriMapperService _uriMapper;

        @Autowired
        protected UriGeneratorService _uriGenerator;

        protected ModelAndView _mav = new ModelAndView();

        public AbstractController()
        {}

        @PostConstruct
        protected void _init_()
        {
                this._auth.authenticate(this._request);
        }

        @ModelAttribute("@")
        protected ModelMapService _setModelMap()
        {
                return this._modelMap;
        }
}
