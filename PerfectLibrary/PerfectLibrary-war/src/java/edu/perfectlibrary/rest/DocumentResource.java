/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.perfectlibrary.rest;

import edu.perfectlibrary.business.LibraryDocumentServiceLocal;
import edu.perfectlibrary.common.NoDocumentWithGivenIdException;
import edu.perfectlibrary.model.library.LibraryDocument;
import edu.perfectlibrary.model.library.Review;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
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
@Path("document")
//@RequestScoped
@Stateless
//@Dependent
public class DocumentResource {

    @Context
    private UriInfo context;
    @EJB
    LibraryDocumentServiceLocal libraryDocumentService;
    
    private static final Response NO_CONTENT_204 = Response.noContent().build();

    /**
     * Creates a new instance of DocumentResource
     */
    public DocumentResource() {
    }

    /**
     * Retrieves representation of an instance of
     * edu.perfectlibrary.restapi.DocumentResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("{id}")
    @Produces({"application/xml"})
    public Response getDocumentByIdInXml(@PathParam("id") Long id) {
        LibraryDocument document = null;
        Response response = null;
        try {
            document = libraryDocumentService.getDocumentById(id);
        } catch (NoDocumentWithGivenIdException e) {
//            response = Response.noContent().build();
            return NO_CONTENT_204;
        }
        response = Response.ok(document).build();
        return response;
    }

    @GET
    @Path("/review/{id}")
    @Produces({"application/xml"})
    public Response getReviewByDocumentIdInXml(@PathParam("id") Long id) {
        LibraryDocument document = null;
        Response response = null;
        try {
            document = libraryDocumentService.getDocumentById(id);
        } catch (NoDocumentWithGivenIdException e) {
//            response = Response.noContent().build();
            return NO_CONTENT_204;
        }
        List<Review> reviews = libraryDocumentService.getAllReviewsByLibraryDocument(document);
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
