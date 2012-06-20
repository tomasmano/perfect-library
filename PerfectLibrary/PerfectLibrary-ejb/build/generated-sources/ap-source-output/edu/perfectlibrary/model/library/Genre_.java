package edu.perfectlibrary.model.library;

import edu.perfectlibrary.enums.GenreType;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-06-20T11:07:36")
@StaticMetamodel(Genre.class)
public class Genre_ { 

    public static volatile SingularAttribute<Genre, Long> id;
    public static volatile SingularAttribute<Genre, GenreType> genreName;

}