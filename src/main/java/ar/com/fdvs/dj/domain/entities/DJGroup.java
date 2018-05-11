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

package ar.com.fdvs.dj.domain.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.list.UnmodifiableList;

import ar.com.fdvs.dj.domain.DJBaseElement;
import ar.com.fdvs.dj.domain.DJCrosstab;
import ar.com.fdvs.dj.domain.DJGroupLabel;
import ar.com.fdvs.dj.domain.DynamicReportOptions;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.constants.GroupLayout;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import ar.com.fdvs.dj.domain.entities.columns.PropertyColumn;

/**
 * Entity created to handle groups of columns.</br>
 * Multiple groups can be created for a single report. In this case the result
 * </br>
 * would be a nesting with the latest groups added to the report being the inner
 * ones.
 */
public class DJGroup extends DJBaseElement {

    private static final long serialVersionUID = Entity.SERIAL_VERSION_UID;

    // The column used to group by
    private PropertyColumn columnToGroupBy;

    /**
     * Map<Column, Style>
     */
    private Map<AbstractColumn, Style> columnHeaderStyles = new HashMap<AbstractColumn, Style>();

    private Style defaultColumnHeaederStyle;

    private List<DJGroupVariable> headerVariables = new ArrayList<DJGroupVariable>();
    private List<DJGroupVariable> footerVariables = new ArrayList<DJGroupVariable>();

    private List<DJGroupVariableDef> variables = new ArrayList<DJGroupVariableDef>();
    private boolean fitHeaderHeightToContent = true;
    private boolean fitFooterHeightToContent = true;

    /**
     * For internal use only
     */
    private String name;
    private int headerHeight = DynamicReportOptions.DEFAULT_HEADER_HEIGHT; // for headers

    private int footerHeight = DynamicReportOptions.DEFAULT_FOOTER_VARIABLES_HEIGHT; // for headers

    private int headerVariablesHeight = DynamicReportOptions.UNSET_VALUE; // for values such as calculations, current
                                                                          // value, etc.

    private int footerVariablesHeight = DynamicReportOptions.UNSET_VALUE; // for values such as calculations, current
                                                                          // value, etc.

    private GroupLayout layout = GroupLayout.DEFAULT;

    private List<Subreport> footerSubreports = new ArrayList<Subreport>();

    private List<Subreport> headerSubreports = new ArrayList<Subreport>();
    private List<DJCrosstab> headerCrosstabs = new ArrayList<DJCrosstab>();

    private List<DJCrosstab> footerCrosstabs = new ArrayList<DJCrosstab>();
    private boolean startInNewPage = false;

    private boolean startInNewColumn = false;
    /**
     * If the group is configured to print column names, they will be printed on
     * every page (even if a group is splitted in two pages) NOTE: this may cause
     * unexpected results if header variables are present.
     */
    private boolean reprintHeaderOnEachPage = false;
    /**
     * Same as in JasperReports
     */
    private boolean resetPageNumber = false;

    private DJGroupLabel footerLabel; // general label, goes at the right of the group variables
    /**
     * pass-through property to setup group header band "allowSplit" property. When
     * FALSE, if the content reaches end of the page, the whole band gets pushed to
     * the next page.
     */
    private boolean allowHeaderSplit = true;

    /**
     * pass-through property to setup group footer band "allowSplit" property. When
     * FALSE, if the content reaches end of the page, the whole band gets pushed to
     * the next page.
     */
    private boolean allowFooterSplit = true;
    /**
     * Default Style for variables when showing in footer. First looks for the style
     * at the ColumnsGroupVariable, then the default, finally it uses the columns
     * style.
     */
    private Style defaulFooterVariableStyle;

    /**
     * Default Style for variables when showing in header. The lookup order is the
     * same as for "defaulFooterStyle"
     */
    private Style defaulHeaderVariableStyle;

    public void addColumHeaderStyle(AbstractColumn col, Style style) {
        columnHeaderStyles.put(col, style);
    }

    public void addFooterCrosstab(DJCrosstab cross) {
        footerCrosstabs.add(cross);
    }

    public void addFooterVariable(DJGroupVariable var) {
        footerVariables.add(var);
        var.setGroup(this);
    }

    public void addHeaderCrosstab(DJCrosstab cross) {
        headerCrosstabs.add(cross);
    }

    public void addHeaderVariable(DJGroupVariable var) {
        headerVariables.add(var);
        var.setGroup(this);
    }

    public void addVariable(DJGroupVariableDef var) {
        variables.add(var);
    }

    public Style getColumnHeaderStyle(AbstractColumn col) {
        if (columnHeaderStyles == null) {
            return null;
        }

        return columnHeaderStyles.get(col);
    }

    public Map<AbstractColumn, Style> getColumnHeaderStyles() {
        return columnHeaderStyles;
    }

    public PropertyColumn getColumnToGroupBy() {
        return columnToGroupBy;
    }

    public Style getDefaulFooterVariableStyle() {
        return defaulFooterVariableStyle;
    }

    public Style getDefaulHeaderVariableStyle() {
        return defaulHeaderVariableStyle;
    }

    public Style getDefaultColumnHeaederStyle() {
        return defaultColumnHeaederStyle;
    }

    public List<DJCrosstab> getFooterCrosstabs() {
        return footerCrosstabs;
    }

    public int getFooterHeight() {
        return footerHeight;
    }

    public DJGroupLabel getFooterLabel() {
        return footerLabel;
    }

    public List<Subreport> getFooterSubreports() {
        return footerSubreports;
    }

    public List<DJGroupVariable> getFooterVariables() {
        return new UnmodifiableList<DJGroupVariable>(footerVariables);
    }

    public int getFooterVariablesHeight() {
        return footerVariablesHeight;
    }

    public List<DJCrosstab> getHeaderCrosstabs() {
        return headerCrosstabs;
    }

    public int getHeaderHeight() {
        return headerHeight;
    }

    public List<Subreport> getHeaderSubreports() {
        return headerSubreports;
    }

    public List<DJGroupVariable> getHeaderVariables() {
        return new UnmodifiableList<DJGroupVariable>(headerVariables);
    }

    public int getHeaderVariablesHeight() {
        return headerVariablesHeight;
    }

    public GroupLayout getLayout() {
        return layout;
    }

    public String getName() {
        return name;
    }

    public List<DJGroupVariableDef> getVariables() {
        return variables;
    }

    public boolean isAllowFooterSplit() {
        return allowFooterSplit;
    }

    public boolean isAllowHeaderSplit() {
        return allowHeaderSplit;
    }

    public boolean isFitFooterHeightToContent() {
        return fitFooterHeightToContent;
    }

    public boolean isFitHeaderHeightToContent() {
        return fitHeaderHeightToContent;
    }

    public boolean isReprintHeaderOnEachPage() {
        return reprintHeaderOnEachPage;
    }

    public boolean isResetPageNumber() {
        return resetPageNumber;
    }

    public boolean isStartInNewColumn() {
        return startInNewColumn;
    }

    public boolean isStartInNewPage() {
        return startInNewPage;
    }

    public void setAllowFooterSplit(boolean allowFooterSplit) {
        this.allowFooterSplit = allowFooterSplit;
    }

    public void setAllowHeaederSplit(boolean allowHeaederSplit) {
        allowHeaderSplit = allowHeaederSplit;
    }

    public void setColumnHeaderStyles(Map<AbstractColumn, Style> columnHeaderStyles) {
        this.columnHeaderStyles = columnHeaderStyles;
    }

    public void setColumnToGroupBy(PropertyColumn columnToGroupBy) {
        this.columnToGroupBy = columnToGroupBy;
    }

    public void setDefaulFooterVariableStyle(Style defaulFooterStyle) {
        defaulFooterVariableStyle = defaulFooterStyle;
    }

    public void setDefaulHeaderVariableStyle(Style defaulHeaderStyle) {
        defaulHeaderVariableStyle = defaulHeaderStyle;
    }

    public void setDefaultColumnHeaederStyle(Style defaultColumnHeaederStyle) {
        this.defaultColumnHeaederStyle = defaultColumnHeaederStyle;
    }

    /**
     * When false, the footer height is not shrink to its content (variables in
     * general), leaving a white space
     * 
     * @param fitFooterHeightToContent
     */
    public void setFitFooterHeightToContent(boolean fitFooterHeightToContent) {
        this.fitFooterHeightToContent = fitFooterHeightToContent;
    }

    public void setFitHeaderHeightToContent(boolean fitHeaderHeightToContent) {
        this.fitHeaderHeightToContent = fitHeaderHeightToContent;
    }

    public void setFooterCrosstabs(List<DJCrosstab> footerCrosstabs) {
        this.footerCrosstabs = footerCrosstabs;
    }

    public void setFooterHeight(int footerHeight) {
        this.footerHeight = footerHeight;
    }

    public void setFooterLabel(DJGroupLabel footerLabel) {
        this.footerLabel = footerLabel;
    }

    public void setFooterVariables(List<DJGroupVariable> footerVariables) {
        this.footerVariables = footerVariables;
    }

    public void setFooterVariablesHeight(int footerVariablesHeight) {
        this.footerVariablesHeight = footerVariablesHeight;
    }

    public void setHeaderCrosstabs(List<DJCrosstab> headerCrosstabs) {
        this.headerCrosstabs = headerCrosstabs;
    }

    public void setHeaderHeight(int headerHeight) {
        this.headerHeight = headerHeight;
    }

    public void setHeaderVariables(List<DJGroupVariable> headerVariables) {
        this.headerVariables = headerVariables;
    }

    public void setHeaderVariablesHeight(int headerVariablesHeight) {
        this.headerVariablesHeight = headerVariablesHeight;
    }

    public void setLayout(GroupLayout layout) {
        this.layout = layout;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReprintHeaderOnEachPage(boolean reprintHeaderOnEachPage) {
        this.reprintHeaderOnEachPage = reprintHeaderOnEachPage;
    }

    public void setResetPageNumber(boolean resetPageNumber) {
        this.resetPageNumber = resetPageNumber;
    }

    public void setStartInNewColumn(boolean startInNewColumn) {
        this.startInNewColumn = startInNewColumn;
    }

    public void setStartInNewPage(boolean startInNewPage) {
        this.startInNewPage = startInNewPage;
    }

    public void setVariables(List<DJGroupVariableDef> variables) {
        this.variables = variables;
    }
}
