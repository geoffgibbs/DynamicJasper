/*
 * DynamicJasper: A library for creating reports dynamically by specifying
 * columns, groups, styles, etc. at runtime. It also saves a lot of development
 * time in many cases! (http://sourceforge.net/projects/dynamicjasper)
 *
 * Copyright (C) 2008 FDV Solutions (http://www.fdvsolutions.com)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 *
 * License as published by the Free Software Foundation; either
 *
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 *
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 *
 *
 */

package ar.com.fdvs.dj.test.watermark;

import java.awt.Color;
import java.util.Date;
import java.util.Map;

import net.sf.jasperreports.view.JasperDesignViewer;
import net.sf.jasperreports.view.JasperViewer;

import ar.com.fdvs.dj.domain.DJCalculation;
import ar.com.fdvs.dj.domain.DJValueFormatter;
import ar.com.fdvs.dj.domain.DJWaterMark;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.test.BaseDjReportTest;

public class WatermarkReportTest2 extends BaseDjReportTest {

    public static void main(String[] args) throws Exception {
        WatermarkReportTest2 test = new WatermarkReportTest2();
        test.testReport();
        test.exportToJRXML();
        JasperViewer.viewReport(test.jp); // finally display the report report
        JasperDesignViewer.viewReportDesign(test.jr);
    }

    @Override
    public DynamicReport buildReport() throws Exception {

        /*
         * Creates the DynamicReportBuilder and sets the basic options for the report
         */
        FastReportBuilder drb = new FastReportBuilder();
        Font courierNewBigBold = (Font) Font.COURIER_NEW_BIG_BOLD.clone();
        courierNewBigBold.setFontSize(80);

        drb.addColumn("State", "state", String.class.getName(), 30)
                .addColumn("Branch", "branch", String.class.getName(), 30)
                .addColumn("Product Line", "productLine", String.class.getName(), 50)
                .addColumn("Item", "item", String.class.getName(), 50)
                .addColumn("Item Code", "id", Long.class.getName(), 30, true)
                .addColumn("Quantity", "quantity", Long.class.getName(), 60, true)
                .addColumn("Amount", "amount", Float.class.getName(), 70, true).addGroups(2)
                .setTitle("November " + getYear() + " sales report")
                .setSubtitle("This report was generated at " + new Date())
                .addWatermark("SUPER CONFIDEN TIAL", courierNewBigBold, Color.CYAN, DJWaterMark.ANGLE_0)
                .setUseFullPageWidth(true);

        drb.addGlobalFooterVariable(drb.getColumn(4), DJCalculation.COUNT, null, new DJValueFormatter() {

            @Override
            public Object evaluate(Object value, Map fields, Map variables, Map parameters) {
                return (value == null ? "0" : value.toString()) + " Clients";
            }

            @Override
            public String getClassName() {
                return String.class.getName();
            }
        });

        DynamicReport dr = drb.build();

        return dr;
    }

}
