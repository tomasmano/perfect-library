package edu.perfectlibrary.model.social;

import edu.perfectlibrary.model.library.LibraryDocument;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-06-20T11:07:36")
@StaticMetamodel(Publisher.class)
public class Publisher_ { 

    public static volatile SingularAttribute<Publisher, Long> id;
    public static volatile ListAttribute<Publisher, LibraryDocument> libraryDocuments;
    public static volatile SingularAttribute<Publisher, String> name;

}