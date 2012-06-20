/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.perfectlibrary.transfer.utils;

import edu.perfectlibrary.business.LibraryDocumentService;
import edu.perfectlibrary.business.LibraryDocumentServiceLocal;
import edu.perfectlibrary.model.library.LibraryDocument;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Užívateľ
 */
public class LibraryDocumentConverter implements Converter {

    @EJB
    LibraryDocumentServiceLocal libraryDocumentService;

    public LibraryDocumentConverter() {
        InitialContext ic;
        try {
            ic = new InitialContext();
            // INFO: EJB5181:Portable JNDI names for EJB LibraryDocumentService: 
            // [java:global/PerfectLibrary/PerfectLibrary5-ejb/LibraryDocumentService!edu.perfectlibrary.business.LibraryDocumentServiceLocal, 
            // java:global/PerfectLibrary/PerfectLibrary5-ejb/LibraryDocumentService]
            //
            // INFO: EJB5181:Portable JNDI names for EJB LibraryDocumentService: [java:global/PerfectLibrary-war/LibraryDocumentService, java:global/PerfectLibrary-war/LibraryDocumentService!edu.perfectlibrary.business.LibraryDocumentServiceLocal]
            Object proxy = ic.lookup("java:global/eja-PerfectLibrary/PerfectLibrary5-ejb/LibraryDocumentService!edu.perfectlibrary.business.LibraryDocumentServiceLocal");
            libraryDocumentService = (LibraryDocumentServiceLocal) proxy;
        } catch (NamingException e) {
            e.printStackTrace(System.out);
        }

    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
        List<LibraryDocument> libDocsList = libraryDocumentService.getAllLibraryDocuments();
        if (submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                for (LibraryDocument doc : libDocsList) {
                    if (doc.getTitle().equals(submittedValue)) {
                        return doc;
                    }
                }
                // ?? Number format exception ??
            } catch (NumberFormatException exception) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid doc"));
            }
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return ((LibraryDocument) value).getTitle();
        }
    }
}
