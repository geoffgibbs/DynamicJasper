
package ar.com.fdvs.dj.test.web.webwork;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork.ActionSupport;

import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.test.FastReportTest;
import ar.com.fdvs.dj.test.TemplateStyleReportTest;

public class GenerateReportAction extends ActionSupport {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private DynamicReport dynamicReport;

    private Collection dataSource;

    private Map params = new HashMap();

    public String doReportWithTemplate() throws Exception {

        TemplateStyleReportTest frt = new TemplateStyleReportTest();

        dynamicReport = frt.buildReport();
        // this.params = frt.getParams();
        dataSource = frt.getDummyCollectionSorted(dynamicReport.getColumns());

        params.put("leftHeader2", "This value comes from GenerateReportAction.params.leftHeader");

        return SUCCESS;
    }

    @Override
    public String execute() throws Exception {

        FastReportTest frt = new FastReportTest();

        dynamicReport = frt.buildReport();
        params = frt.getParams();
        dataSource = frt.getDummyCollectionSorted(dynamicReport.getColumns());
        return SUCCESS;
    }

    public Collection getDataSource() {
        return dataSource;
    }

    public DynamicReport getDynamicReport() {
        return dynamicReport;
    }

    public String getLeftHeader() {
        String leftHeader = "DJ MAMANA 2008";
        return leftHeader;
    }

    public String getLmanager() {
        String lmanager = "list";
        return lmanager;
    }

    public Map getParams() {
        return params;
    }

}
