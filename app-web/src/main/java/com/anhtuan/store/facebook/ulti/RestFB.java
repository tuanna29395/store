package com.anhtuan.store.facebook.ulti;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class RestFB {

    @Value("${facebook.link.get.token}")
    private String linkGetToken;

    @Value("${facebook.app.secret}")
    private String secretKey;

    @Value("${facebook.app.id}")
    private String appId;

    @Value("${facebook.redirect.uri}")
    private String redirectUri;

    public String getToken(final String code) throws ClientProtocolException, IOException {
        String link = String.format(linkGetToken, appId,
                secretKey, redirectUri, code);
        String response = Request.Get(link).execute().returnContent().asString();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(response).get("access_token");
        return node.textValue();
    }

    public com.restfb.types.User getUserInfo(final String accessToken) {
        FacebookClient facebookClient = new DefaultFacebookClient(accessToken, secretKey,
                Version.LATEST);
        return facebookClient.fetchObject("me", com.restfb.types.User.class);
    }

    public UserDetails buildUser(com.restfb.types.User user) {
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        UserDetails userDetail = new User(user.getId() + user.getName(), "", enabled, accountNonExpired, credentialsNonExpired,
                accountNonLocked, authorities);

        return userDetail;
    }


}
