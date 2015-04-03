package idw.service;
import idw.service.UriMapperService.Generator;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("request")
public class UriGeneratorService
{
        @Autowired
        protected UriMapperService _mapper;

        @Autowired
        protected HttpServletRequest _request;

        @Autowired
        protected UidPassAuthService _auth;

        @PostConstruct
        public void _init_()
        {
                final UriMapperService mapper = this._mapper;
                final String context = this._request.getContextPath();
                final UidPassAuthService auth = this._auth;

                mapper.add("919d15a198e10234", new Generator() { @Override public String gen() {
                        return context + "/";
                }});

                mapper.add("c27ca611dd6d8c6d", new Generator() { @Override public String gen() {
                        return context + "/signup/";
                }});

                mapper.add("cf6a2248d8292274", new Generator() { @Override public String gen() {
                        return context + "/signin/";
                }});

                mapper.add("ecca508fe1b71b35", new Generator() { @Override public String gen() {
                        return context + "/user/";
                }}).add("a8d606833b2b5bb0", new Generator() { @Override public String gen() {
                        return mapper.get("ecca508fe1b71b35") + "c/";
                }}).add("8473c511418ab366", new Generator() { @Override public String gen() {
                        return mapper.get("a8d606833b2b5bb0") + "prefs/";
                }}).add("1aed5fa2352d0236", new Generator() { @Override public String gen() {
                        return mapper.get("a8d606833b2b5bb0") + "studies/";
                }}).add("30e79708da5585f9", new Generator() { @Override public String gen() {
                        return mapper.get("a8d606833b2b5bb0") + "skills/";
                }}).add("c6ef29cdc9f68c43", new Generator() { @Override public String gen() {
                        return mapper.get("a8d606833b2b5bb0") + "experiences/";
                }}).add("44ac11e708413260", new Generator() { @Override public String gen() {
                        return mapper.get("a8d606833b2b5bb0") + "summary/";
                }});

                mapper.add("74cde451fd87d769", new Generator() { @Override public String gen() {
                        return context + "/company/";
                }}).add("90ee875f41c4df3b", new Generator() { @Override public String gen() {
                        return mapper.get("74cde451fd87d769") + "oneshot/";
                }});

                mapper.add("c24a3ebe339a033a", new Generator() { @Override public String gen() {
                        return context + "/search/c/";
                }});

                mapper.add("570cba9bd68eda73", new Generator() { @Override public String gen() {
                        return context + "/faq/";
                }});

                mapper.add("484c9f0d7efb8ec9", new Generator() { @Override public String gen() {
                        return context + "/?signout=";
                }});

                /* This URL targets the user's home, specific for his account type */
                mapper.add("94611bb80ded8d7d", new Generator() { @Override public String gen() {
                        return context + (auth.isCandidate() ?
                                mapper.get("ecca508fe1b71b35", false):
                                mapper.get("74cde451fd87d769", false)
                        );
                }});
        }
}
