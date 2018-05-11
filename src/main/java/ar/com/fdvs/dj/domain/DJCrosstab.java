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
import java.util.Collections;
import java.util.List;

import ar.com.fdvs.dj.core.layout.CrossTabColorShema;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.entities.Entity;

public class DJCrosstab extends DJBaseElement {

    private static final long serialVersionUID = Entity.SERIAL_VERSION_UID;

    private List<DJCrosstabRow> rows = new ArrayList<DJCrosstabRow>();
    private List<DJCrosstabColumn> columns = new ArrayList<DJCrosstabColumn>();
    private List<DJCrosstabMeasure> measures = new ArrayList<DJCrosstabMeasure>();

    private DJLabel caption;

    private int height;
    private int width;

    private int bottomSpace = 10; // blank space AFTER the crosstab
    private int topSpace = 10; // blank space BEFORE the crosstab

    /**
     * if true, the width will be the page width - margins
     */
    private boolean useFullWidth = true;

    private DJDataSource datasource;

    private String mainHeaderTitle = "";

    private boolean automaticTitle = false;

    private boolean ignoreWidth;

    /**
     * Default styles, can be overwritten by the column and row objects
     */
    private Style headerStyle;

    private Style columnHeaderStyle;
    private Style columnTotalheaderStyle;
    private Style columnTotalStyle;

    private Style rowHeaderStyle;
    private Style rowTotalheaderStyle;
    private Style rowTotalStyle;

    private Style measureStyle;

    private int colorScheme = 0;

    private CrossTabColorShema ctColorScheme = null;

    /**
     * If not null or NO_BORDER, all cells will have this border
     */
    private Border cellBorder;

    /**
     * From here on, pass-throug properties
     */
    private int columnBreakOffset = 10;

    public int getBottomSpace() {
        return bottomSpace;
    }

    public DJLabel getCaption() {
        return caption;
    }

    public Border getCellBorder() {
        return cellBorder;
    }

    public int getColorScheme() {
        return colorScheme;
    }

    public int getColumnBreakOffset() {
        return columnBreakOffset;
    }

    public Style getColumnHeaderStyle() {
        return columnHeaderStyle;
    }

    public List<DJCrosstabColumn> getColumns() {
        return columns;
    }

    public Style getColumnTotalheaderStyle() {
        return columnTotalheaderStyle;
    }

    public Style getColumnTotalStyle() {
        return columnTotalStyle;
    }

    public CrossTabColorShema getCtColorScheme() {
        return ctColorScheme;
    }

    public DJDataSource getDatasource() {
        return datasource;
    }

    public Style getHeaderStyle() {
        return headerStyle;
    }

    public int getHeight() {
        return height;
    }

    public String getMainHeaderTitle() {
        return mainHeaderTitle;
    }

    public DJCrosstabMeasure getMeasure(int index) {
        return measures.get(index);
    }

    public List<DJCrosstabMeasure> getMeasures() {
        return measures;
    }

    public Style getMeasureStyle() {
        return measureStyle;
    }

    public Style getRowHeaderStyle() {
        return rowHeaderStyle;
    }

    public List<DJCrosstabRow> getRows() {
        return rows;
    }

    public Style getRowTotalheaderStyle() {
        return rowTotalheaderStyle;
    }

    public Style getRowTotalStyle() {
        return rowTotalStyle;
    }

    public int getTopSpace() {
        return topSpace;
    }

    public List<DJCrosstabMeasure> getVisibleMeasures() {
        List<DJCrosstabMeasure> visibleMeasures = new ArrayList<DJCrosstabMeasure>();

        for (Object measureObject : getMeasures()) {
            DJCrosstabMeasure measure = DJCrosstabMeasure.class.cast(measureObject);
            if (measure.getVisible() != null && measure.getVisible().equals(Boolean.TRUE)) {
                visibleMeasures.add(measure);
            }
        }

        return Collections.unmodifiableList(visibleMeasures);
    }

    public int getWidth() {
        return width;
    }

    public boolean isAutomaticTitle() {
        return automaticTitle;
    }

    public boolean isIgnoreWidth() {
        return ignoreWidth;
    }

    public boolean isUseFullWidth() {
        return useFullWidth;
    }

    public void setAutomaticTitle(boolean automaticTitle) {
        this.automaticTitle = automaticTitle;
    }

    public void setBottomSpace(int bottomSpace) {
        this.bottomSpace = bottomSpace;
    }

    public void setCaption(DJLabel caption) {
        this.caption = caption;
    }

    public void setCellBorder(Border cellBorder) {
        this.cellBorder = cellBorder;
    }

    public void setColorScheme(int colorScheme) {
        this.colorScheme = colorScheme;
    }

    public void setColumnBreakOffset(int columnBreakOffset) {
        this.columnBreakOffset = columnBreakOffset;
    }

    /**
     * Default style. Can be overwritten by the column
     * 
     * @param columnHeaderStyle
     */
    public void setColumnHeaderStyle(Style columnHeaderStyle) {
        this.columnHeaderStyle = columnHeaderStyle;
    }

    public void setColumns(List<DJCrosstabColumn> columns) {
        this.columns = columns;
    }

    /**
     * Default style. Can be overwritten by the column
     * 
     * @param columnTotalheaderStyle
     */
    public void setColumnTotalheaderStyle(Style columnTotalheaderStyle) {
        this.columnTotalheaderStyle = columnTotalheaderStyle;
    }

    public void setColumnTotalStyle(Style columnTotalStyle) {
        this.columnTotalStyle = columnTotalStyle;
    }

    public void setCtColorScheme(CrossTabColorShema ctColorScheme) {
        this.ctColorScheme = ctColorScheme;
    }

    public void setDatasource(DJDataSource datasource) {
        this.datasource = datasource;
    }

    public void setHeaderStyle(Style headerStyle) {
        this.headerStyle = headerStyle;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setIgnoreWidth(boolean ignoreWidth) {
        this.ignoreWidth = ignoreWidth;
    }

    public void setMainHeaderTitle(String mainHeaderTitle) {
        this.mainHeaderTitle = mainHeaderTitle;
    }

    public void setMeasures(List<DJCrosstabMeasure> measures) {
        this.measures = measures;
    }

    public void setMeasureStyle(Style measureStyle) {
        this.measureStyle = measureStyle;
    }

    /**
     * Default style. Can be overwritten by the row
     * 
     * @param rowHeaderStyle
     */
    public void setRowHeaderStyle(Style rowHeaderStyle) {
        this.rowHeaderStyle = rowHeaderStyle;
    }

    public void setRows(List<DJCrosstabRow> rows) {
        this.rows = rows;
    }

    /**
     * Default style. Can be overwritten by the row
     * 
     * @param rowTotalheaderStyle
     */
    public void setRowTotalheaderStyle(Style rowTotalheaderStyle) {
        this.rowTotalheaderStyle = rowTotalheaderStyle;
    }

    public void setRowTotalStyle(Style rowTotalStyle) {
        this.rowTotalStyle = rowTotalStyle;
    }

    public void setTopSpace(int topSpace) {
        this.topSpace = topSpace;
    }

    public void setUseFullWidth(boolean useFullWidth) {
        this.useFullWidth = useFullWidth;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
