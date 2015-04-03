package idw.annotation;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

@Scope(value=WebApplicationContext.SCOPE_REQUEST, proxyMode=ScopedProxyMode.TARGET_CLASS)
public @interface RequestScope
{
}
