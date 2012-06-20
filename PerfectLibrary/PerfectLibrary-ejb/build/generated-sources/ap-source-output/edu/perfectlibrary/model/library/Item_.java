package edu.perfectlibrary.model.library;

import edu.perfectlibrary.model.library.Issue;
import edu.perfectlibrary.model.library.LibraryDocument;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-06-20T11:07:36")
@StaticMetamodel(Item.class)
public class Item_ { 

    public static volatile SingularAttribute<Item, Issue> issue;
    public static volatile SingularAttribute<Item, LibraryDocument> libraryDocument;
    public static volatile SingularAttribute<Item, Long> itemId;
    public static volatile SingularAttribute<Item, Boolean> isAvailable;

}