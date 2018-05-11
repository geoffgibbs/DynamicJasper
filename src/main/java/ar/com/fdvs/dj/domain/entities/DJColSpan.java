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
 */

package ar.com.fdvs.dj.domain.entities;

import java.util.List;

import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;

public class DJColSpan {

    private String title;
    private String count;
    private int width;
    private Style colspanHeaderStyle;
    private List<AbstractColumn> columns;

    public Style getColspanHeaderStyle() {
        return colspanHeaderStyle;
    }

    public List<AbstractColumn> getColumns() {
        return columns;
    }

    public String getCount() {
        return count;
    }

    public int getHeight() {
        return 0;
    }

    public String getTitle() {
        return title;
    }

    /**
     * The total width is the sum of the width of eache column
     * 
     * @return a colspan width
     */
    public int getWidth() {
        if (width == 0) {
            for (AbstractColumn col : columns) {
                width += col.getWidth();
            }
        }

        return width;
    }

    public boolean isFirstColum(AbstractColumn col) {
        return getColumns().get(0).equals(col);
    }

    public void setColspanHeaderStyle(Style colspanHeaderStyle) {
        this.colspanHeaderStyle = colspanHeaderStyle;
    }

    public void setColumns(List<AbstractColumn> columns) {
        this.columns = columns;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public void setHeight() {

    }

    public void setTitle(String title) {
        this.title = title;
    }
}
