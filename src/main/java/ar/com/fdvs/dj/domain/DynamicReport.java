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

package ar.com.fdvs.dj.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.core.JasperDesignDecorator;
import ar.com.fdvs.dj.domain.builders.StyleBuilder;
import ar.com.fdvs.dj.domain.constants.Stretching;
import ar.com.fdvs.dj.domain.entities.DJGroup;
import ar.com.fdvs.dj.domain.entities.DJVariable;
import ar.com.fdvs.dj.domain.entities.Entity;
import ar.com.fdvs.dj.domain.entities.Parameter;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import ar.com.fdvs.dj.domain.entities.columns.ExpressionColumn;
import ar.com.fdvs.dj.domain.entities.columns.SimpleColumn;

/**
 * One of the main classes of this product. It represents the report itself.
 */
public class DynamicReport extends DJBaseElement {

    private static final long serialVersionUID = Entity.SERIAL_VERSION_UID;
    protected Map<String, java.awt.Font> fontsMap = new HashMap<String, java.awt.Font>(); // <String, java.awt.Font>
    private String reportName;
    /**
     * Sets the language of the expressions used in the report (can be one of java,
     * groovy, or javascript).
     *
     * Default is DJConstants#REPORT_LANGUAGE_JAVA
     *
     * @see DJConstants#REPORT_LANGUAGE_JAVA DJConstants#REPORT_LANGUAGE_GROOVY
     *      DJConstants#REPORT_LANGUAGE_JAVASCRIPT
     *
     */
    private String language = DJConstants.REPORT_LANGUAGE_JAVA;
    private String title;
    private boolean titleIsJrExpression = false;
    private String subtitle;
    private Style titleStyle = new StyleBuilder(false, "reportTitleStyle").setStretching(Stretching.NO_STRETCH).build();
    private Style subtitleStyle = new StyleBuilder(false, "reportSubtitleStyle").setStretching(Stretching.NO_STRETCH)
            .build();
    private Locale reportLocale = Locale.getDefault();
    private String resourceBundle = null;
    private List<AbstractColumn> columns = new ArrayList<AbstractColumn>();

    // <DJGroup>
    private List<DJGroup> columnsGroups = new ArrayList<DJGroup>();

    // <DJChart>
    private List<DJChart> charts = new ArrayList<DJChart>();

    // <DJChart>
    private List<ar.com.fdvs.dj.domain.chart.DJChart> newCharts = new ArrayList<ar.com.fdvs.dj.domain.chart.DJChart>();

    private DynamicReportOptions options;

    /**
     * List of ColumnProperty Other fields to register, not necessary assigned to
     * columns
     */
    private List<ColumnProperty> fields = new ArrayList<ColumnProperty>();

    // Other parameters needed (E.g. Subreports)
    private List<Parameter> parameters = new ArrayList<Parameter>();

    private List<DJVariable> variables = new ArrayList<DJVariable>();

    private Map<String, String> properties = new HashMap<String, String>();

    private String templateFileName = null;
    private boolean templateImportDatasets = false;
    private boolean templateImportFields = false;
    private boolean templateImportVariables = false;
    private boolean templateImportParameters = true;

    private List<AutoText> autoTexts = new ArrayList<AutoText>();

    private Map<String, Style> styles = new LinkedHashMap<String, Style>();
    private DJQuery query;

    private String whenNoDataText;
    private Style whenNoDataStyle;
    private boolean whenNoDataShowTitle = true;
    private boolean whenNoDataShowColumnHeader = true;

    private boolean allowDetailSplit = true;

    /**
     * Defines the behaviour when the datasource is empty. Valid values are:
     * DJConstants.WHEN_NO_DATA_TYPE_NO_PAGES
     * DJConstants.WHEN_NO_DATA_TYPE_BLANK_PAGE
     * DJConstants.WHEN_NO_DATA_TYPE_ALL_SECTIONS_NO_DETAIL
     * DJConstants.WHEN_NO_DATA_TYPE_NO_DATA_SECTION
     *
     * Defatul is: DJConstants.WHEN_NO_DATA_TYPE_NO_PAGES
     */
    private byte whenNoDataType = DJConstants.WHEN_NO_DATA_TYPE_NO_PAGES;

    /**********************
     * Defines what to show if a missing resource is referenced Possible values
     * are:<br>
     * DJConstants.WHEN_RESOURCE_MISSING_TYPE_EMPTY: Leaves and empty field.<br/>
     * DJConstants.WHEN_RESOURCE_MISSING_TYPE_ERROR: Throwns and exception.<br/>
     * DJConstants.WHEN_RESOURCE_MISSING_TYPE_KEY: Shows the key of the missing
     * resource.<br/>
     * DJConstants.WHEN_RESOURCE_MISSING_TYPE_NULL: returns NULL
     **********************/
    private byte whenResourceMissing = DJConstants.WHEN_RESOURCE_MISSING_TYPE_KEY;

    private DJWaterMark waterMark;

    private JasperDesignDecorator jasperDesignDecorator;

    private String defaultEncoding;

    public DynamicReport() {
    }

    public void addParameter(Parameter parameter) {
        parameters.add(parameter);
    }

    public void addParameter(String name, String className) {
        parameters.add(new Parameter(name, className));
    }

    public void addStyle(Style style) {
        styles.put(style.getName(), style);
    }

    /**
     * Collects all the fields from columns and also the fields not bounds to
     * columns
     * 
     * @return List<ColumnProperty>
     */
    public List<ColumnProperty> getAllFields() {
        ArrayList<ColumnProperty> l = new ArrayList<ColumnProperty>();
        for (AbstractColumn abstractColumn : getColumns()) {
            if (abstractColumn instanceof SimpleColumn && !(abstractColumn instanceof ExpressionColumn)) {
                l.add(((SimpleColumn) abstractColumn).getColumnProperty());
            }
        }
        l.addAll(getFields());

        return l;

    }

    public List<AutoText> getAutoTexts() {
        return autoTexts;
    }

    public List<DJChart> getCharts() {
        return charts;
    }

    public List<AbstractColumn> getColumns() {
        return columns;
    }

    public List<DJGroup> getColumnsGroups() {
        return columnsGroups;
    }

    public String getDefaultEncoding() {
        return defaultEncoding;
    }

    public List<ColumnProperty> getFields() {
        return fields;
    }

    public Map<String, java.awt.Font> getFontsMap() {
        return fontsMap;
    }

    public JasperDesignDecorator getJasperDesignDecorator() {
        return jasperDesignDecorator;
    }

    public String getLanguage() {
        return language;
    }

    public List<ar.com.fdvs.dj.domain.chart.DJChart> getNewCharts() {
        return newCharts;
    }

    public DynamicReportOptions getOptions() {
        return options;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public DJQuery getQuery() {
        return query;
    }

    public Locale getReportLocale() {
        return reportLocale;
    }

    public String getReportName() {
        return reportName;
    }

    public String getResourceBundle() {
        return resourceBundle;
    }

    public Map<String, Style> getStyles() {
        return styles;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public Style getSubtitleStyle() {
        return subtitleStyle;
    }

    public String getTemplateFileName() {
        return templateFileName;
    }

    public String getTitle() {
        return title;
    }

    public Style getTitleStyle() {
        return titleStyle;
    }

    public List<DJVariable> getVariables() {
        return variables;
    }

    public DJWaterMark getWaterMark() {
        return waterMark;
    }

    public Style getWhenNoDataStyle() {
        return whenNoDataStyle;
    }

    public String getWhenNoDataText() {
        return whenNoDataText;
    }

    public byte getWhenNoDataType() {
        return whenNoDataType;
    }

    public byte getWhenResourceMissing() {
        return whenResourceMissing;
    }

    public boolean isAllowDetailSplit() {
        return allowDetailSplit;
    }

    public boolean isTemplateImportDatasets() {
        return templateImportDatasets;
    }

    public boolean isTemplateImportFields() {
        return templateImportFields;
    }

    public boolean isTemplateImportParameters() {
        return templateImportParameters;
    }

    public boolean isTemplateImportVariables() {
        return templateImportVariables;
    }

    public boolean isTitleIsJrExpression() {
        return titleIsJrExpression;
    }

    public boolean isWhenNoDataShowColumnHeader() {
        return whenNoDataShowColumnHeader;
    }

    public boolean isWhenNoDataShowTitle() {
        return whenNoDataShowTitle;
    }

    public void setAllowDetailSplit(boolean allowDetailSplit) {
        this.allowDetailSplit = allowDetailSplit;
    }

    public void setAutoTexts(List<AutoText> autoTexts) {
        this.autoTexts = autoTexts;
    }

    public void setCharts(List<DJChart> charts) {
        this.charts = charts;
    }

    public void setColumns(List<AbstractColumn> columns) {
        this.columns = columns;
    }

    public void setColumnsGroups(List<DJGroup> columnsGroups) {
        this.columnsGroups = columnsGroups;
    }

    public void setDefaultEncoding(String defaultEncoding) {
        this.defaultEncoding = defaultEncoding;
    }

    public void setFields(List<ColumnProperty> fields) {
        this.fields = fields;
    }

    public void setFontsMap(Map<String, java.awt.Font> fontsMap) {
        this.fontsMap = fontsMap;
    }

    public void setJasperDesignDecorator(JasperDesignDecorator jasperDesignDecorator) {
        this.jasperDesignDecorator = jasperDesignDecorator;
    }

    /**
     * @see DynamicReport#language
     * @param language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    public void setNewCharts(List<ar.com.fdvs.dj.domain.chart.DJChart> charts) {
        newCharts = charts;
    }

    public void setOptions(DynamicReportOptions options) {
        this.options = options;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    /**
     * Must be a Map<String, String>
     * 
     * @param properties
     */
    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public void setProperty(String name, String value) {
        properties.put(name, value);
    }

    public void setQuery(DJQuery query) {
        this.query = query;
    }

    public void setReportLocale(Locale reportLocale) {
        this.reportLocale = reportLocale;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public void setResourceBundle(String resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    public void setStyles(Map<String, Style> styles) {
        this.styles = styles;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setSubtitleStyle(Style subtitleStyle) {
        this.subtitleStyle = subtitleStyle;
    }

    public void setTemplateFileName(String templateFileName) {
        this.templateFileName = templateFileName;
    }

    public void setTemplateImportDatasets(boolean templateImportDatasets) {
        this.templateImportDatasets = templateImportDatasets;
    }

    public void setTemplateImportFields(boolean templateImportFields) {
        this.templateImportFields = templateImportFields;
    }

    public void setTemplateImportParameters(boolean templateImportParameters) {
        this.templateImportParameters = templateImportParameters;
    }

    public void setTemplateImportVariables(boolean templateImportVariables) {
        this.templateImportVariables = templateImportVariables;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTitleIsJrExpression(boolean titleIsJrExpression) {
        this.titleIsJrExpression = titleIsJrExpression;
    }

    public void setTitleStyle(Style titleStyle) {
        this.titleStyle = titleStyle;
    }

    public void setTitleStyle(Style titleStyle, boolean isExp) {
        this.titleStyle = titleStyle;
        titleIsJrExpression = isExp;
    }

    public void setVariables(List<DJVariable> variables) {
        this.variables = variables;
    }

    public void setWaterMark(DJWaterMark waterMark) {
        this.waterMark = waterMark;
    }

    public void setWhenNoDataShowColumnHeader(boolean whenNoDataShowColumnHeader) {
        this.whenNoDataShowColumnHeader = whenNoDataShowColumnHeader;
    }

    public void setWhenNoDataShowTitle(boolean whenNoDataShowTitle) {
        this.whenNoDataShowTitle = whenNoDataShowTitle;
    }

    public void setWhenNoDataStyle(Style whenNoDataStyle) {
        this.whenNoDataStyle = whenNoDataStyle;
    }

    public void setWhenNoDataText(String whenNoDataText) {
        this.whenNoDataText = whenNoDataText;
    }

    public void setWhenNoDataType(byte whenNoDataType) {
        this.whenNoDataType = whenNoDataType;
    }

    public void setWhenResourceMissing(byte whenResourceMissing) {
        this.whenResourceMissing = whenResourceMissing;
    }
}