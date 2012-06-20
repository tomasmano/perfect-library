package edu.perfectlibrary.model.library;

import edu.perfectlibrary.model.library.Issue;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-06-20T11:07:36")
@StaticMetamodel(Penalty.class)
public class Penalty_ { 

    public static volatile SingularAttribute<Penalty, Long> id;
    public static volatile SingularAttribute<Penalty, Issue> issue;
    public static volatile SingularAttribute<Penalty, Date> since;

}