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

package ar.com.fdvs.dj.domain.entities.columns;

import java.text.Format;
import java.util.ArrayList;
import java.util.List;

import ar.com.fdvs.dj.domain.DJBaseElement;
import ar.com.fdvs.dj.domain.DJCalculation;
import ar.com.fdvs.dj.domain.DJHyperLink;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.entities.DJColSpan;
import ar.com.fdvs.dj.domain.entities.Entity;
import ar.com.fdvs.dj.domain.entities.conditionalStyle.ConditionalStyle;

/**
 * Abstract Class used as base for the different Column types.
 */
public abstract class AbstractColumn extends DJBaseElement {

    private static final long serialVersionUID = Entity.SERIAL_VERSION_UID;

    /**
     * Internal column name. used to generate expression names related to this
     * column (variables, custom expression, etc) Its name is given in the
     * ColumnRegistrationManager
     */
    private String name;

    /**
     * For internal use only. will be initialized with the report name and
     * subclasses can benefit from it to create expression which needs the report
     * prefix
     */
    private String reportName;

    private String title;
    private int posX = 0;
    private int posY = 0;
    private int width = 100;
    private boolean fixedWidth = Boolean.FALSE;
    private Style style = new Style();
    private Style headerStyle = null;
    private String pattern;
    private boolean printRepeatedValues = Boolean.TRUE;
    private boolean blankWhenNull = Boolean.TRUE;
    private String truncateSuffix = null;
    private Format textFormatter;

    /**
     * Markup to use in the column data (html, styled, etc)
     */
    private String markup;

    /**
     * Markup to use in the column header (html, styled, etc)
     */
    private String headerMarkup;

    private DJHyperLink link;

    private List<ConditionalStyle> conditionalStyles = new ArrayList<ConditionalStyle>();

    private DJColSpan colSpan;

    public DJColSpan getColSpan() {
        return colSpan;
    }

    public List<ConditionalStyle> getConditionalStyles() {
        return conditionalStyles;
    }

    /**
     * 
     * @param type
     *            "FOOTER" or "HEADER"
     * @param columnToGroupByProperty
     * @return The name of group of variables
     */
    public abstract String getGroupVariableName(String type, String columnToGroupByProperty);

    public String getHeaderMarkup() {
        return headerMarkup;
    }

    public Style getHeaderStyle() {
        return headerStyle;
    }

    public abstract String getInitialExpression(DJCalculation op);

    public DJHyperLink getLink() {
        return link;
    }

    public String getMarkup() {
        return markup;
    }

    public String getName() {
        return name;
    }

    public String getPattern() {
        return pattern;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public String getReportName() {
        return reportName;
    }

    public Style getStyle() {
        return style;
    }

    public abstract String getTextForExpression();

    public Format getTextFormatter() {
        return textFormatter;
    }

    public String getTitle() {
        return title;
    }

    public String getTruncateSuffix() {
        return truncateSuffix;
    }

    public abstract String getValueClassNameForExpression();

    public abstract String getVariableClassName(DJCalculation op);

    public int getWidth() {
        return width;
    }

    public boolean hasParentCol() {
        return colSpan != null;
    }

    public boolean isBlankWhenNull() {
        return blankWhenNull;
    }

    public boolean isFixedWidth() {
        return fixedWidth;
    }

    public boolean isPrintRepeatedValues() {
        return printRepeatedValues;
    }

    public void setBlankWhenNull(boolean blankWhenNull) {
        this.blankWhenNull = blankWhenNull;
    }

    public void setColSpan(DJColSpan colSpan) {
        this.colSpan = colSpan;
    }

    public void setConditionalStyles(List<ConditionalStyle> conditionalStyles) {
        this.conditionalStyles = conditionalStyles;
    }

    public void setFixedWidth(boolean fixedWidth) {
        this.fixedWidth = fixedWidth;
    }

    public void setHeaderMarkup(String headerMarkup) {
        this.headerMarkup = headerMarkup;
    }

    public void setHeaderStyle(Style headerStyle) {
        this.headerStyle = headerStyle;
    }

    public void setLink(DJHyperLink link) {
        this.link = link;
    }

    public void setMarkup(String markup) {
        this.markup = markup;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setPrintRepeatedValues(boolean printRepeatedValues) {
        this.printRepeatedValues = printRepeatedValues;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public void setTextFormatter(Format textFormatter) {
        this.textFormatter = textFormatter;
    }

    public void setTitle(String label) {
        title = label;
    }

    public void setTruncateSuffix(String truncateSuffix) {
        this.truncateSuffix = truncateSuffix;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}