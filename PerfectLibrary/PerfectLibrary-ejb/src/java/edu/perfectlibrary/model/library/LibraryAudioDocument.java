/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.perfectlibrary.model.library;

import edu.perfectlibrary.common.SpecifiedLibraryDocumentProperty;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author Užívateľ
 */
@MappedSuperclass
public abstract class LibraryAudioDocument extends LibraryDocument{
    
    @SpecifiedLibraryDocumentProperty
    private int length;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
