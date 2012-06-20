/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.perfectlibrary.rest;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Užívateľ
 */
@Path("app")
@Produces(MediaType.TEXT_PLAIN)
public class GeneralInfoResource {
    
    @GET
    @Path("ping")
    public String ping(){
        return "alive";
    }
    
    @GET
    @Path("whoareyou")
    public String whoAreYou(){
        return "Perfect Library App";
    }
}
