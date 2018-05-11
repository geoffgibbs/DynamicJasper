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

package ar.com.fdvs.dj.test;

import java.util.Date;

import net.sf.jasperreports.view.JasperDesignViewer;
import net.sf.jasperreports.view.JasperViewer;

import ar.com.fdvs.dj.core.BarcodeTypes;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.builders.StyleBuilder;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.ImageScaleMode;

public class BarcodeColumnReportTest extends BaseDjReportTest {

    public static void main(String[] args) throws Exception {
        BarcodeColumnReportTest test = new BarcodeColumnReportTest();
        test.testReport();
        JasperViewer.viewReport(test.jp); // finally display the report report
        JasperDesignViewer.viewReportDesign(test.jr);
    }

    @Override
    public DynamicReport buildReport() throws Exception {

        new StyleBuilder(true).setHorizontalAlign(HorizontalAlign.CENTER).build();
        /*
         * Creates the DynamicReportBuilder and sets the basic options for the report
         */
        FastReportBuilder drb = new FastReportBuilder();
        drb.addColumn("State", "state", String.class.getName(), 20)
                .addColumn("Branch", "branch", String.class.getName(), 30)
                .addColumn("Quantity", "quantity", Long.class.getName(), 60, true)
                .addColumn("Amount", "amount", Float.class.getName(), 70, true)
                .addBarcodeColumn("Bar-Code", "amount", Long.class.getName(), BarcodeTypes.USD3, true, false, null, 100,
                        true, ImageScaleMode.FILL, null)
                .addGroups(1).setDetailHeight(30).addField("productLine", String.class.getName())
                .setTitle("November " + getYear() + " sales report")
                .setSubtitle("This report was generated at " + new Date()).setUseFullPageWidth(true);

        DynamicReport dr = drb.build();

        return dr;
    }

}
