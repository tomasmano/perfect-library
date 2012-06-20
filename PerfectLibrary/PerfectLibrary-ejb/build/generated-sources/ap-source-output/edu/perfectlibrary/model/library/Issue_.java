package edu.perfectlibrary.model.library;

import edu.perfectlibrary.model.library.Item;
import edu.perfectlibrary.model.library.Penalty;
import edu.perfectlibrary.model.social.MemberAccount;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-06-20T11:07:36")
@StaticMetamodel(Issue.class)
public class Issue_ { 

    public static volatile SingularAttribute<Issue, Long> id;
    public static volatile SingularAttribute<Issue, Item> item;
    public static volatile SingularAttribute<Issue, Date> since;
    public static volatile SingularAttribute<Issue, Penalty> penalty;
    public static volatile SingularAttribute<Issue, MemberAccount> memberAccount;
    public static volatile SingularAttribute<Issue, Date> until;

}