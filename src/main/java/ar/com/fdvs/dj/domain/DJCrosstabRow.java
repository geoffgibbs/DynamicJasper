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

import net.sf.jasperreports.engine.analytics.dataset.BucketOrder;

import ar.com.fdvs.dj.domain.entities.Entity;

public class DJCrosstabRow extends DJBaseElement {

    private static final long serialVersionUID = Entity.SERIAL_VERSION_UID;

    private ColumnProperty property;
    private int height = -1;
    private int headerWidth = -1;
    private int totalHeaderHeight = -1;

    private String title;

    private boolean showTotals = false;

    private Style totalStyle;
    private Style totalHeaderStyle;
    private Style headerStyle;

    private String totalLegend;

    private DJHyperLink link;

    private BucketOrder order = BucketOrder.ASCENDING;

    private ColumnProperty orderByProperty;

    public Style getHeaderStyle() {
        return headerStyle;
    }

    public int getHeaderWidth() {
        return headerWidth;
    }

    public int getHeight() {
        return height;
    }

    public DJHyperLink getLink() {
        return link;
    }

    public BucketOrder getOrder() {
        return order;
    }

    public ColumnProperty getOrderByProperty() {
        return orderByProperty;
    }

    public ColumnProperty getProperty() {
        return property;
    }

    public String getTitle() {
        return title;
    }

    public int getTotalHeaderHeight() {
        return totalHeaderHeight;
    }

    public Style getTotalHeaderStyle() {
        return totalHeaderStyle;
    }

    public String getTotalLegend() {
        return totalLegend;
    }

    public Style getTotalStyle() {
        return totalStyle;
    }

    public boolean isShowTotals() {
        return showTotals;
    }

    public void setHeaderStyle(Style headerStyle) {
        this.headerStyle = headerStyle;
    }

    public void setHeaderWidth(int headerWidth) {
        this.headerWidth = headerWidth;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setLink(DJHyperLink link) {
        this.link = link;
    }

    public void setOrder(BucketOrder order) {
        this.order = order;
    }

    public void setOrderByProperty(ColumnProperty orderByProperty) {
        this.orderByProperty = orderByProperty;
    }

    public void setProperty(ColumnProperty property) {
        this.property = property;
    }

    public void setShowTotals(boolean showTotals) {
        this.showTotals = showTotals;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTotalHeaderHeight(int totalHeaderHeight) {
        this.totalHeaderHeight = totalHeaderHeight;
    }

    public void setTotalHeaderStyle(Style totalHeaderStyle) {
        this.totalHeaderStyle = totalHeaderStyle;
    }

    public void setTotalLegend(String totalLegend) {
        this.totalLegend = totalLegend;
    }

    public void setTotalStyle(Style totalStyle) {
        this.totalStyle = totalStyle;
    }
}
