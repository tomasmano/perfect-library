package edu.perfectlibrary.model.social;

import edu.perfectlibrary.model.library.LibraryDocument;
import edu.perfectlibrary.model.social.Person;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-06-20T11:07:36")
@StaticMetamodel(Author.class)
public class Author_ { 

    public static volatile ListAttribute<Author, LibraryDocument> libraryDocuments;
    public static volatile SingularAttribute<Author, Person> person;
    public static volatile SingularAttribute<Author, Long> authorId;

}