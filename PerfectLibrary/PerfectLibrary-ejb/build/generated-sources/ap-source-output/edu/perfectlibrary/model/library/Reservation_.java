package edu.perfectlibrary.model.library;

import edu.perfectlibrary.model.library.LibraryDocument;
import edu.perfectlibrary.model.social.MemberAccount;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-06-20T11:07:36")
@StaticMetamodel(Reservation.class)
public class Reservation_ { 

    public static volatile SingularAttribute<Reservation, Long> id;
    public static volatile SingularAttribute<Reservation, LibraryDocument> libraryDocument;
    public static volatile SingularAttribute<Reservation, Date> since;
    public static volatile SingularAttribute<Reservation, MemberAccount> memberAccount;

}