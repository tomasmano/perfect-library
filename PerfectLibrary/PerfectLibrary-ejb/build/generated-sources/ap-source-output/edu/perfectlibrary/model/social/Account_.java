package edu.perfectlibrary.model.social;

import edu.perfectlibrary.model.social.Person;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-06-20T11:07:36")
@StaticMetamodel(Account.class)
public abstract class Account_ { 

    public static volatile SingularAttribute<Account, Person> person;
    public static volatile SingularAttribute<Account, String> username;
    public static volatile SingularAttribute<Account, String> phone;
    public static volatile SingularAttribute<Account, Long> accountId;
    public static volatile SingularAttribute<Account, String> hashpassword;
    public static volatile SingularAttribute<Account, String> email;
    public static volatile SingularAttribute<Account, String> address;
    public static volatile SingularAttribute<Account, String> salt;

}