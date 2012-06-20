/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.perfectlibrary.model.library;

import edu.perfectlibrary.common.SpecifiedLibraryDocumentProperty;
import edu.perfectlibrary.enums.BookCoverType;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author Užívateľ
 */
@MappedSuperclass
public abstract class LibraryReadingDocument extends LibraryDocument {

    @SpecifiedLibraryDocumentProperty
    private int pages;
    @SpecifiedLibraryDocumentProperty
    private String language;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
