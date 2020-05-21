package com.partiravec.gatewayservice;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Add user id to the query params before forwarding the request
 * Get the id with the Bearer Token from the Authorisation header,
 * the token is parsed with Keycloak
 */
@Component
public class UserFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        // Get the current context and asociate request
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        // If the parameter "_user" is already in the queryparams, it throws an error
        if (request.getParameter("_user") != null) {
            throw new ZuulException("Bad request", 400, "_user in request body");
        }
        // Parse the token with Keycloak
        KeycloakPrincipal principal = (KeycloakPrincipal)request.getUserPrincipal();
        KeycloakSecurityContext session = principal.getKeycloakSecurityContext();
        AccessToken accessToken = session.getToken();

        // Create the new list of query params and add all of the previous ones
        Map<String, List<String>> finalParameters = new HashMap<>();
        Map<String, String[]> parameters = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
            String key = entry.getKey();
            String[] values = entry.getValue();
            finalParameters.put(key, Arrays.asList(values));
        }

        // Add user id to the query parameters
        finalParameters.put("_user", Arrays.asList(accessToken.getId()));

        // Assign the new list of query parameters to the current context
        ctx.setRequestQueryParams(finalParameters);

        return null;
    }
}
