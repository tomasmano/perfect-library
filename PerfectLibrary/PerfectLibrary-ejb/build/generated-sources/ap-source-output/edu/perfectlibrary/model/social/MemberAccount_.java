package edu.perfectlibrary.model.social;

import edu.perfectlibrary.model.library.Issue;
import edu.perfectlibrary.model.library.Reservation;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-06-20T11:07:36")
@StaticMetamodel(MemberAccount.class)
public class MemberAccount_ extends Account_ {

    public static volatile ListAttribute<MemberAccount, Issue> issues;
    public static volatile ListAttribute<MemberAccount, Reservation> reservations;

}