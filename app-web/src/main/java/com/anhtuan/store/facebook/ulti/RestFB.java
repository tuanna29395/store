package com.anhtuan.store.facebook.ulti;

import com.anhtuan.store.dto.response.UserFbDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.json.JsonObject;
import com.restfb.json.JsonValue;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

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


    public UserFbDto getUserInfo(final String accessToken) {
        FacebookClient facebookClient = new DefaultFacebookClient(accessToken, secretKey,
                Version.LATEST);
        JsonObject userData = facebookClient.fetchObject("me", JsonObject.class, Parameter.with("fields", "picture,first_name,name,gender,id"));
        UserFbDto userFbDto = new UserFbDto();
        JsonObject pictureUser = (JsonObject) userData.get("picture");
        JsonObject dataPicture = (JsonObject) pictureUser.get("data");
        JsonValue urlPicture = dataPicture.get("url");
        userFbDto.setUserName(userData.getString("name", ""));
        userFbDto.setAvatar(urlPicture.asString());
        userFbDto.setUserAppId(userData.get("id").asString());
        return userFbDto;
    }


}
