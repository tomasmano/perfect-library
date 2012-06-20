/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.perfectlibrary.transfer;

import edu.perfectlibrary.common.SpecificationsProcessingStrategy;
import edu.perfectlibrary.common.SpecificationsProcessingStrategyValue;
import edu.perfectlibrary.business.LibrarySearchServiceLocal;
import edu.perfectlibrary.business.SpecificationsInquierService;
import edu.perfectlibrary.model.library.LibraryDocument;
import edu.perfectlibrary.enums.Specification;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.el.ValueExpression;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.html.HtmlColumn;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.context.FacesContext;

/**
 *
 * @author Užívateľ
 */
//@Named(value = "dynamicDatatableSearchOutputTransfer")
//@SessionScoped
public class DynamicDatatable implements Serializable {

    private HtmlPanelGroup dataTableGroup;
    @ManagedProperty("#{issue.item.libraryDocument}")
    LibraryDocument libraryDocument;
    List<Specification> dynamicHeaders;
    Map<Specification, Object> specs;
    HtmlDataTable dt;
    @EJB
    LibrarySearchServiceLocal librarySearchService;

    /**
     * Creates a new instance of DynamicDatatableSearchOutputTransfer
     */
    public DynamicDatatable() {
    }

    public HtmlDataTable getDt() {
        return dt;
    }

    public void setDt(HtmlDataTable dt) {
        this.dt = dt;
    }

    public HtmlPanelGroup getDataTableGroup() {
        dataTableGroup = null;
        // This will be called once in the first RESTORE VIEW phase.
        if (dataTableGroup == null) {
            pickPopulationStrategy(); // Populate datatable.
        }
        return dataTableGroup;
    }

    public void setDataTableGroup(HtmlPanelGroup dataTableGroup) {
        this.dataTableGroup = dataTableGroup;
    }

    public LibraryDocument getLibraryDocument() {
        return libraryDocument;
    }

    public void setLibraryDocument(LibraryDocument libraryDocument) {
        System.out.println("DEBUG setting library document: >>>> " + libraryDocument);
        this.libraryDocument = libraryDocument;
    }

    private void loadDynamicList() {
        dynamicHeaders = new ArrayList<Specification>();
        System.out.println("libraryDoc: " + libraryDocument);


        int specificationsSize = libraryDocument.getSpecificationsKeys().size();

        for (int j = 0; j < specificationsSize; j++) {
            dynamicHeaders.add(libraryDocument.getSpecificationsKeys().get(j));
        }
    }
    
    private void pickPopulationStrategy(){
        if (libraryDocument==null) {
            return;
        }
        Class c=libraryDocument.getClass();
        if (c.isAnnotationPresent(SpecificationsProcessingStrategy.class)) {
            SpecificationsProcessingStrategy annotation = (SpecificationsProcessingStrategy)c.getAnnotation(SpecificationsProcessingStrategy.class);
            SpecificationsProcessingStrategyValue value = annotation.value();
            if (value==SpecificationsProcessingStrategyValue.REFLEXION) {
                populateDataTableUsingReflection();
                return;
            }
            if (value==SpecificationsProcessingStrategyValue.VISITOR_PATTERN) {
                populateDataTable();
                return;
            }
            throw new RuntimeException("Value of annotation ["+annotation+"] on object ["+libraryDocument+"] doesnt match any known value of enumeration type 'SpecificationsProcessingStrategyValue'.");
        }   
        throw new RuntimeException("No population stratagy specified on the ["+libraryDocument+"] (Annotation 'SpecificationsProcessingStrategyValue' on the type ["+libraryDocument+"] is missing.).");
    }

    private void populateDataTable() {
        if (libraryDocument == null) {
            System.out.println("librarydoc is: " + libraryDocument);
            return;
        }
        loadDynamicList();


        // Create <h:dataTable value="#{myBean.dynamicList}" var="dynamicItem">.
        HtmlDataTable dynamicDataTable = new HtmlDataTable();
//        dynamicDataTable.setValueExpression(
//                "value",
//                createValueExpression("#{dynamic.libraryDocuments}",
//                List.class));
//        dynamicDataTable.setVar("item");

        // Iterate over columns.

        for (int j = 0; j < libraryDocument.getSpecifications().size(); j++) {

            // Create <h:column>.
            HtmlColumn column = new HtmlColumn();
            dynamicDataTable.getChildren().add(column);

            // Create <h:outputText value="dynamicHeaders[i]"> for <f:facet name="header"> of column.
            HtmlOutputText header = new HtmlOutputText();
            header.setValue(dynamicHeaders.get(j).toString().toLowerCase() + ": ");
            column.setHeader(header);

//            HtmlOutputText output3 = new HtmlOutputText();
//            output3.setValue(dynamicHeaders.get(j).toString().toLowerCase());

            HtmlOutputText output2 = new HtmlOutputText();
            Object specificationValue = libraryDocument.getSpecifications().get(dynamicHeaders.get(j));
            System.out.println("DEBUG OUT with VISITOR_PATTERN: " + specificationValue);
            output2.setValue(specificationValue);
            // Create <h:outputText value="#{dynamicItem[" + i + "]}"> for the body of column.
//            HtmlOutputText output = new HtmlOutputText();
//                output.setValueExpression("value",
//                        createValueExpression("#{item."+dynamicHeaders.get(j).toString().toLowerCase() +"}", String.class));
//            column.getChildren().add(output3);
            dynamicDataTable.getChildren().add(output2);
//            System.out.println("column.getChildren().size() >>>" + column.getChildren().size());
        }

        // Finally add the datatable to <h:panelGroup binding="#{myBean.dataTableGroup}">.
        dataTableGroup = new HtmlPanelGroup();
        dataTableGroup.getChildren().add(dynamicDataTable);
    }

    public void populateDataTableUsingReflection() {
        if (libraryDocument == null) {
            return;
        }

        // Create <h:dataTable value="#{myBean.dynamicList}" var="dynamicItem">.
        HtmlDataTable dynamicDataTable = new HtmlDataTable();

        SpecificationsInquierService specificationsInquierService=new SpecificationsInquierService(libraryDocument);
        List<String> propertiesNames=specificationsInquierService.getPropertiesNames();
        Map<String, Object> propertiesMap = specificationsInquierService.getSpecifiedPropertiesMap();
        
        for (int j = 0; j < propertiesMap.size(); j++) {

            // Create <h:column>.
            HtmlColumn column = new HtmlColumn();
            dynamicDataTable.getChildren().add(column);

            // Create <h:outputText value="dynamicHeaders[i]"> for <f:facet name="header"> of column.
            HtmlOutputText header = new HtmlOutputText();
            String headerName = propertiesNames.get(j);
//            header.setValue(headerName.toLowerCase() + ": "+);
              HtmlOutputText specificationValueOutput = new HtmlOutputText();
            Object specificationValue =propertiesMap.get(headerName);
            System.out.println("DEBUG OUT (with REFLECTIOn), VALUE: " + specificationValue);
            header.setValue(headerName.toLowerCase() + ": "+specificationValue);
            column.setHeader(header);
            System.out.println("DEBUG OUT (with REFLECTIOn), NAME: " + headerName);

            
//            HtmlOutputText specificationValueOutput = new HtmlOutputText();
//            Object specificationValue =propertiesMap.get(headerName);
//            System.out.println("DEBUG OUT (with REFLECTIOn), VALUE: " + specificationValue);
//            specificationValueOutput.setValue(specificationValue);

            
            dynamicDataTable.getChildren().add(specificationValueOutput);
        }

        // Finally add the datatable to <h:panelGroup binding="#{myBean.dataTableGroup}">.
        dataTableGroup = new HtmlPanelGroup();
        dataTableGroup.getChildren().add(dynamicDataTable);
    }

    private ValueExpression createValueExpression(String valueExpression, Class<?> valueType) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ValueExpression created = facesContext.getApplication().getExpressionFactory().createValueExpression(facesContext.getELContext(), valueExpression, valueType);
        return created;
    }
}
