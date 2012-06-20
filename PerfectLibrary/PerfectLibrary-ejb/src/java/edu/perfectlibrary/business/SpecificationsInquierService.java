/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.perfectlibrary.business;

import edu.perfectlibrary.common.SpecifiedLibraryDocumentProperty;
import edu.perfectlibrary.model.library.Book;
import edu.perfectlibrary.model.library.LibraryDocument;
import edu.perfectlibrary.enums.BookCoverType;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 *
 * @author Užívateľ
 */

//@Stateless
public class SpecificationsInquierService implements SpecificationsInquierServiceLocal {

    List<Field> fields;
    List<String> propertiesNames;
    Map<String, Object> map;
    LibraryDocument document;

    public SpecificationsInquierService(LibraryDocument doc) {
        this.document = doc;
        fields = new ArrayList<Field>();
        propertiesNames = new ArrayList<String>();
        map = new HashMap<String, Object>();
        init();
    }

    private void init() {
        populateFields(document.getClass());
        populateProperties();
        populateSpecifiedPropertesMap();
    }

    private void populateFields(Class c) {
        fields.addAll(0, Arrays.asList(c.getDeclaredFields()));

        Class superClass = c.getSuperclass();
        if (superClass != LibraryDocument.class) {
            populateFields(superClass);
        }
    }

    private void populateProperties() {
        for (Field field : fields) {
            if (field.isAnnotationPresent(SpecifiedLibraryDocumentProperty.class)) {
                propertiesNames.add(field.getName());
            }
        }
    }

    private void populateSpecifiedPropertesMap() {
        StringBuilder sb;
        Class c;
        Method m;
        Object invoked = null;
        for (String name : propertiesNames) {
            sb = new StringBuilder();
            sb.append(name.substring(0, 1).toUpperCase()).append(name.substring(1, name.length()));
            try {
                m = document.getClass().getMethod("get" + sb);
                invoked = m.invoke(document);
            } catch (NoSuchMethodException e) {
                e.printStackTrace(System.out);
            } catch (InvocationTargetException f) {
                f.printStackTrace(System.out);
            } catch (IllegalAccessException g) {
                g.printStackTrace(System.out);
            }
            map.put(name, invoked);
        }
    }

    /**
     * @return list of fields names annoted with
     * @SpecifiedLibraryDocumentProperty
     */
    public List<String> getPropertiesNames() {
        return propertiesNames;
    }

    /**
     * @return map implementation, where keys are names of all fields of object
     * that is subclass of LibraryDocment annoted with
     * @SpecifiedLibraryDocumentProperty and values are values of these fields
     */
    public Map<String, Object> getSpecifiedPropertiesMap() {
        return map;
    }
    
    @Deprecated
    public Map<String, Object> getSpecifiedPropertiesByDocument(LibraryDocument document) {
        this.document = document;
        propertiesNames.clear();
        fields.clear();
        init();
        return getSpecifiedPropertiesMap();
    }
    
    
//    public static void test() {
//        Book b = new Book();
//        b.setBookCoverType(BookCoverType.HARDCOVER);
//        b.setPages(1525);
//        b.setLanguage("english");
//        b.setTitle("Snehulienka");
//        LibraryDocument d = b;
//        SpecificationsInquierService service = new SpecificationsInquierService(d);
//        System.out.println(service.getPropertiesNames());
//        System.out.println(service.getSpecifiedPropertiesMap());
//        System.out.println(service.getSpecifiedPropertiesMap().get(service.getPropertiesNames().get(2)));
//        Book c=new Book();
//        c.setBookCoverType(BookCoverType.PAPERBACK);
//        c.setTitle("piso");
//        c.setPages(888989);
//        LibraryDocument g=c;
//        System.out.println(service.getSpecifiedPropertiesByDocument(g));
//        System.out.println(service.getPropertiesNames());
//    }
//
//    public static void main(String[] args) {
//        test();
//    }
}
