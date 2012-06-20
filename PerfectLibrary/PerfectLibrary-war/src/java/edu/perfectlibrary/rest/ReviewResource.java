/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.perfectlibrary.rest;

import edu.perfectlibrary.business.LibraryDocumentServiceLocal;
import edu.perfectlibrary.business.MemberAccountServiceLocal;
import edu.perfectlibrary.common.UserNotFoundException;
import edu.perfectlibrary.model.library.Review;
import edu.perfectlibrary.model.social.MemberAccount;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Užívateľ
 */
@Path("reviews")
//@RequestScoped
@Stateless
//@Dependent
public class ReviewResource {

    @Context
    private UriInfo context;
    @EJB
    LibraryDocumentServiceLocal libraryDocumentService;
    @EJB
    MemberAccountServiceLocal memberAccountService;
    
    private static final Response NO_CONTENT_204 = Response.noContent().build();

    /**
     * Creates a new instance of DocumentResource
     */
    public ReviewResource() {
    }

    @GET
    @Path("/{username}")
    @Produces({"application/xml"})
    public Response getReviewByUsername(@PathParam("username") String username) {
        Long id;
        Response response = null;
        try {
            id = memberAccountService.getAccountIdByUsername(username);
        } catch (UserNotFoundException e) {
//            response = Response.noContent().build();
            return NO_CONTENT_204;
        }
        MemberAccount account = memberAccountService.findMemberAccoundById(id);
        List<Review> reviews = memberAccountService.getAllReviewsByMember(account);
        final GenericEntity<List<Review>> entity = new GenericEntity<List<Review>>(reviews) {
        };
        response = Response.ok().entity(entity).build();
        return response;
    }
    
    @GET
    @Path("/{first}-{last}")
    @Produces({"application/xml"})
    public Response getReviewByFullName(@PathParam("first") String first, @PathParam("last") String last) {
        Long id;
        Response response = null;
        try {
            id = memberAccountService.getAccountIdByFullname(first, last);
        } catch (UserNotFoundException e) {
//            response = Response.noContent().build();
            return NO_CONTENT_204;
        }
        MemberAccount account = memberAccountService.findMemberAccoundById(id);
        List<Review> reviews = memberAccountService.getAllReviewsByMember(account);
        final GenericEntity<List<Review>> entity = new GenericEntity<List<Review>>(reviews) {
        };
        response = Response.ok().entity(entity).build();
        return response;
    }

    /**
     * PUT method for updating or creating an instance of DocumentResource
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
