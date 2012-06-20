/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.perfectlibrary.transfer;

import edu.perfectlibrary.business.LibrarySearchServiceLocal;
import edu.perfectlibrary.model.library.LibraryDocument;
import edu.perfectlibrary.enums.Specification;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.el.ValueExpression;
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
public class DynamicDatatableSearchOutputTransfer implements Serializable {

    private HtmlPanelGroup dataTableGroup;
    LibraryDocument libraryDocument;
    List<Specification> dynamicHeaders;
    Map<Specification, Object> specs;
    List<LibraryDocument> searchOutput;
    HtmlDataTable dt;
    String testInput;
    @EJB
    LibrarySearchServiceLocal librarySearchService;

//    
    public String getTestInput() {
        return testInput;
    }

    public void setTestInput(String testInput) {
        this.testInput = testInput;
    }

    public String testAction2() {
        searchOutput = librarySearchService.findLibraryDocumentByTitle2(testInput);
        return "testresponse";
    }

    public List<LibraryDocument> getSearchOutput() {
        return searchOutput;
    }

    public void setSearchOutput(List<LibraryDocument> searchOutput) {
        this.searchOutput = searchOutput;
    }

//    
    /**
     * Creates a new instance of DynamicDatatableSearchOutputTransfer
     */
    public DynamicDatatableSearchOutputTransfer() {
    }

    public HtmlDataTable getDt() {
        return dt;
    }

    public void setDt(HtmlDataTable dt) {
        this.dt = dt;
    }

    public HtmlPanelGroup getDataTableGroup() {
        // This will be called once in the first RESTORE VIEW phase.
        if (dataTableGroup == null) {
            populateDataTable2(); // Populate datatable.
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
        this.libraryDocument = libraryDocument;
    }

    private void loadDynamicList() {
        dynamicHeaders = new ArrayList<Specification>();
        for (int i = 0; i < searchOutput.size(); i++) {
            libraryDocument = searchOutput.get(i);
            specs = libraryDocument.getSpecifications();
            int specificationsSize = libraryDocument.getSpecificationsKeys().size();

            for (int j = 0; j < specificationsSize; j++) {
                dynamicHeaders.add(libraryDocument.getSpecificationsKeys().get(j));
            }
        }
//        int size = libraryDocument.getSpecifications().size();
//        for (int i = 0; i < size; i++) {
//            dynamicHeaders[i] = libraryDocument.getSpecificationValues().get(i).toString();
//        }
    }

    private void populateDataTable() {

        loadDynamicList();

        // Create <h:dataTable value="#{myBean.dynamicList}" var="dynamicItem">.
        HtmlDataTable dynamicDataTable = new HtmlDataTable();
        dynamicDataTable.setValueExpression(
                "value",
                createValueExpression("#{dynamicTable.searchOutput}",
                List.class));
        dynamicDataTable.setVar("item");

        // Iterate over columns.

        for (int i = 0; i < searchOutput.size(); i++) {   // pozor zle

            for (int j = 0; j < searchOutput.get(i).getSpecifications().size(); j++) {

                // Create <h:column>.
                HtmlColumn column = new HtmlColumn();
                dynamicDataTable.getChildren().add(column);

                // Create <h:outputText value="dynamicHeaders[i]"> for <f:facet name="header"> of column.
                HtmlOutputText header = new HtmlOutputText();
                header.setValue(dynamicHeaders.get(j).toString());
                column.setHeader(header);

                HtmlOutputText output2 = new HtmlOutputText();
                output2.setValue("PRDEL");
                System.out.println("HEEEEEEEEEEEEEEj");
//                output2.setValue(searchOutput.get(i).getSpecifications().get(dynamicHeaders.get(j))); // working
                // Create <h:outputText value="#{dynamicItem[" + i + "]}"> for the body of column.
                HtmlOutputText output = new HtmlOutputText();
//                output.setValue("HOVNO");
//                output.setValueExpression("value",
//                        createValueExpression("#{item."+dynamicHeaders.get(j).toString().toLowerCase() +"}", String.class));
                column.getChildren().add(output2);
                System.out.println(column.getAttributes());
            }


            // Finally add the datatable to <h:panelGroup binding="#{myBean.dataTableGroup}">.
            dataTableGroup = new HtmlPanelGroup();
            dataTableGroup.getChildren().add(dynamicDataTable);
        }
    }

    private void populateDataTable2() {

        loadDynamicList();

        // Create <h:dataTable value="#{myBean.dynamicList}" var="dynamicItem">.
        HtmlPanelGroup dynamicDataTable = new HtmlPanelGroup();
//        dynamicDataTable.setValueExpression(
//                "value",
//                createValueExpression("#{dynamicTable.searchOutput}",
//                List.class));
//        dynamicDataTable.setVar("item");

        // Iterate over columns.

        for (int i = 0; i < searchOutput.size(); i++) {   // pozor zle

            for (int j = 0; j < searchOutput.get(i).getSpecifications().size(); j++) {


                // Create <h:outputText value="dynamicHeaders[i]"> for <f:facet name="header"> of column.
                HtmlOutputText header = new HtmlOutputText();
                header.setValue(dynamicHeaders.get(j).toString());

                HtmlOutputText output2 = new HtmlOutputText();
                output2.setValue(searchOutput.get(i).getSpecifications().get(dynamicHeaders.get(j)));
                // Create <h:outputText value="#{dynamicItem[" + i + "]}"> for the body of column.
                HtmlOutputText output = new HtmlOutputText();
                dynamicDataTable.getChildren().add(header);
                dynamicDataTable.getChildren().add(output);
                dynamicDataTable.getChildren().add(output2);
//                output.setValue("HOVNO");
//                output.setValueExpression("value",
//                        createValueExpression("#{item."+dynamicHeaders.get(j).toString().toLowerCase() +"}", String.class));
            }


            // Finally add the datatable to <h:panelGroup binding="#{myBean.dataTableGroup}">.
            dataTableGroup = new HtmlPanelGroup();
            dataTableGroup.getChildren().add(dynamicDataTable);
        }
    }

    private ValueExpression createValueExpression(String valueExpression, Class<?> valueType) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ValueExpression created = facesContext.getApplication().getExpressionFactory().createValueExpression(facesContext.getELContext(), valueExpression, valueType);
        return created;
    }
}
