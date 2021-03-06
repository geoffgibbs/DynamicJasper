/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.fdvs.dj.test.crosstab;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.core.layout.LayoutManager;
import ar.com.fdvs.dj.domain.CustomExpression;
import ar.com.fdvs.dj.domain.DJCalculation;
import ar.com.fdvs.dj.domain.DJCrosstab;
import ar.com.fdvs.dj.domain.DJLabel;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.BuilderException;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.CrosstabBuilder;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Page;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import ar.com.fdvs.dj.test.ReportExporter;

/**
 * @author rve
 */
public class InnerCrossTabBuilder4 extends TestCase {

    private static DJCrosstab buildCrosstab() {
        CrosstabBuilder cb = new CrosstabBuilder().setUseFullWidth(true).setAutomaticTitle(false).setMainHeaderTitle("")
                .setHeight(50)
                // .setCaption(new DJLabel("\" sales for year \" + $F{field2}",null,true) )
                .setCaption(new DJLabel(new CustomExpression() {

                    @Override
                    public Object evaluate(Map fields, Map variables, Map parameters) {
                        return "Title from custom expresion for year " + fields.get("year");
                    }

                    @Override
                    public String getClassName() {
                        return String.class.getName();
                    }
                }, null)).setCellBorder(Border.PEN_1_POINT())
                .setDatasource("detail", DJConstants.DATA_SOURCE_ORIGIN_FIELD, DJConstants.DATA_SOURCE_TYPE_COLLECTION);

        cb.addColumn("Type", "field2", String.class.getName(), false);
        cb.addRow("Genre", "field3", String.class.getName(), false);
        cb.addMeasure("field1", Integer.class.getName(), DJCalculation.SUM, "Sales", null);

        return cb.build();
    }

    public static LayoutManager getLayoutManager() {
        return new ClassicLayoutManager();
    }

    public static List getList() {
        List list = new ArrayList();

        // Add the first line
        Map result1 = new HashMap();
        result1.put("sales", 150);
        result1.put("year", String.valueOf("2007"));

        List data1 = new ArrayList();
        Map data1_1 = new HashMap();
        data1_1.put("field1", 25);
        data1_1.put("field2", String.valueOf("DVD"));
        data1_1.put("field3", String.valueOf("SF"));
        data1.add(data1_1);

        Map data1_2 = new HashMap();
        data1_2.put("field1", 25);
        data1_2.put("field2", String.valueOf("DVD"));
        data1_2.put("field3", String.valueOf("Fantasy"));
        data1.add(data1_2);

        Map data1_3 = new HashMap();
        data1_3.put("field1", 50);
        data1_3.put("field2", String.valueOf("Book"));
        data1_3.put("field3", String.valueOf("SF"));
        data1.add(data1_3);

        Map data1_4 = new HashMap();
        data1_4.put("field1", 50);
        data1_4.put("field2", String.valueOf("Book"));
        data1_4.put("field3", String.valueOf("Fantasy"));
        data1.add(data1_4);

        result1.put("detail", data1);

        list.add(result1);

        // Add the second line
        Map result2 = new HashMap();
        result2.put("sales", 250);
        result2.put("year", String.valueOf("2008"));

        List data2 = new ArrayList();
        Map data2_1 = new HashMap();
        data2_1.put("field1", 25);
        data2_1.put("field2", String.valueOf("DVD"));
        data2_1.put("field3", String.valueOf("SF"));
        data2.add(data2_1);

        Map data2_2 = new HashMap();
        data2_2.put("field1", 25);
        data2_2.put("field2", String.valueOf("DVD"));
        data2_2.put("field3", String.valueOf("Fantasy"));
        data2.add(data2_2);

        Map data2_3 = new HashMap();
        data2_3.put("field1", 100);
        data2_3.put("field2", String.valueOf("Book"));
        data2_3.put("field3", String.valueOf("SF"));
        data2.add(data2_3);

        Map data2_4 = new HashMap();
        data2_4.put("field1", 100);
        data2_4.put("field2", String.valueOf("Book"));
        data2_4.put("field3", String.valueOf("Fantasy"));
        data2.add(data2_4);

        result2.put("detail", data2);

        list.add(result2);

        // Add the third line
        Map result3 = new HashMap();
        result3.put("sales", 203);
        result3.put("year", String.valueOf("2009"));

        List data3 = new ArrayList();
        Map data3_1 = new HashMap();
        data3_1.put("field1", 25);
        data3_1.put("field2", String.valueOf("DVD"));
        data3_1.put("field3", String.valueOf("SF"));
        data3.add(data3_1);

        Map data3_2 = new HashMap();
        data3_2.put("field1", 33);
        data3_2.put("field2", String.valueOf("DVD"));
        data3_2.put("field3", String.valueOf("Fantasy"));
        data3.add(data3_2);

        Map data3_3 = new HashMap();
        data3_3.put("field1", 75);
        data3_3.put("field2", String.valueOf("Book"));
        data3_3.put("field3", String.valueOf("SF"));
        data3.add(data3_3);

        Map data3_4 = new HashMap();
        data3_4.put("field1", 70);
        data3_4.put("field2", String.valueOf("Book"));
        data3_4.put("field3", String.valueOf("Fantasy"));
        data3.add(data3_4);

        result3.put("detail", data3);

        list.add(result3);

        return list;
    }

    public static void main(String[] args) throws Exception {
        InnerCrossTabBuilder4 test = new InnerCrossTabBuilder4();
        test.testReport();
        JasperViewer.viewReport(test.jp);
    }

    JasperReport jr;
    JasperPrint jp;

    public DynamicReport buildDynamicReport() {
        FastReportBuilder drb = null;
        drb = new FastReportBuilder();
        drb.setGrandTotalLegend("Total");
        drb.setPageSizeAndOrientation(new Page(585, 842));
        drb.setUseFullPageWidth(false);
        drb.setAllowDetailSplit(false);
        drb.setWhenNoData("No data", null, true, true);
        drb.setReportName("This is the main report");

        AbstractColumn colSales = ColumnBuilder.getNew().setColumnProperty("sales", Integer.class.getName())
                .setTitle("Sales").setWidth(50).build();

        AbstractColumn colYear = ColumnBuilder.getNew()
                // .setColumnProperty("year", String.class.getName())
                .setTitle("Year").setWidth(50).setCustomExpression(new CustomExpression() {

                    @Override
                    public Object evaluate(Map fields, Map variables, Map parameters) {
                        return fields.get("year") + " - " + fields.get("sales");
                    }

                    @Override
                    public String getClassName() {
                        return String.class.getName();
                    }
                }).build(); // WRONG Class was used, it is Integer

        AbstractColumn colexp = new ColumnBuilder().setTitle("exp").setCustomExpression(new CustomExpression() {

            @Override
            public Object evaluate(Map fields, Map variables, Map parameters) {
                return fields.get("year") + " - " + fields.get("sales");
            }

            @Override
            public String getClassName() {
                return String.class.getName();
            }
        }).setWidth(100).build();
        drb.addColumn(colYear);
        drb.addColumn(colSales);
        drb.addColumn(colexp);

        drb.addGroups(1);

        drb.addField("year", String.class.getName()); // IMPORTANT!!! this must be declared
        drb.addField("detail", Collection.class.getName()); // IMPORTANT!!! this must be declared
        // drb.addConcatenatedReport(buildInnerDynamicReport(), new
        // ClassicLayoutManager(), "ds_cross", DJConstants.DATA_SOURCE_ORIGIN_PARAMETER,
        // DJConstants.DATA_SOURCE_TYPE_COLLECTION);

        return drb.build();

    }

    public void testReport() throws JRException, FileNotFoundException, ClassNotFoundException, BuilderException {
        List list = getList();
        JRDataSource ds = new JRBeanCollectionDataSource(list);
        Map params = new HashMap();
        params.put("ds_cross", list);

        jr = DynamicJasperHelper.generateJasperReport(buildDynamicReport(), getLayoutManager(), params);
        // jr = DynamicJasperHelper.generateJasperReport(buildInnerDynamicReport(),
        // getLayoutManager(), params);
        jp = JasperFillManager.fillReport(jr, params, ds);

        ReportExporter.exportReport(jp,
                System.getProperty("user.dir") + "/target/" + this.getClass().getName() + ".pdf");
        DynamicJasperHelper.generateJRXML(jr, "UTF-8",
                System.getProperty("user.dir") + "/target/" + this.getClass().getName() + ".jrxml");

    }
}