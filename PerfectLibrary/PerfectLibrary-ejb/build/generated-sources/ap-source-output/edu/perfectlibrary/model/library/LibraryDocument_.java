package edu.perfectlibrary.model.library;

import edu.perfectlibrary.model.library.Genre;
import edu.perfectlibrary.model.library.Item;
import edu.perfectlibrary.model.library.Reservation;
import edu.perfectlibrary.model.library.Review;
import edu.perfectlibrary.model.social.Author;
import edu.perfectlibrary.model.social.Publisher;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.MapAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-06-20T11:07:36")
@StaticMetamodel(LibraryDocument.class)
public abstract class LibraryDocument_ { 

    public static volatile ListAttribute<LibraryDocument, Author> authors;
    public static volatile SingularAttribute<LibraryDocument, String> title;
    public static volatile ListAttribute<LibraryDocument, Review> reviews;
    public static volatile SetAttribute<LibraryDocument, Genre> genres;
    public static volatile MapAttribute<LibraryDocument, Integer, Item> items;
    public static volatile SingularAttribute<LibraryDocument, Long> docId;
    public static volatile SingularAttribute<LibraryDocument, Publisher> publisher;
    public static volatile ListAttribute<LibraryDocument, Reservation> reservations;

}