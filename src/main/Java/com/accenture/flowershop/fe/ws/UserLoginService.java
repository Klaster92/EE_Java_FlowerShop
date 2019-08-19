package com.accenture.flowershop.fe.ws;

import com.accenture.flowershop.be.BusinessService.User.UserBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Component
@Path("/user")
public class UserLoginService {

    @Autowired
    private UserBusinessService userBusinessService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/login/{login}")
    public Boolean checkLogin(@PathParam("login") String login){
        if(userBusinessService.findUserByLogin(login) != null) {
            return true;
        }
        return false;
    }
}
