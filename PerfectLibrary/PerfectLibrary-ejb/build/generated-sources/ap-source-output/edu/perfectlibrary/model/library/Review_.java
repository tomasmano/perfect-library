package edu.perfectlibrary.model.library;

import edu.perfectlibrary.model.library.LibraryDocument;
import edu.perfectlibrary.model.social.MemberAccount;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-06-20T11:07:36")
@StaticMetamodel(Review.class)
public class Review_ { 

    public static volatile SingularAttribute<Review, Long> id;
    public static volatile SingularAttribute<Review, String> content;
    public static volatile SingularAttribute<Review, Date> created;
    public static volatile SingularAttribute<Review, LibraryDocument> libraryDocument;
    public static volatile SingularAttribute<Review, MemberAccount> memberAccount;

}