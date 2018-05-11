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

package ar.com.fdvs.dj.domain.chart.plot;

import java.awt.Color;

import ar.com.fdvs.dj.domain.CustomExpression;
import ar.com.fdvs.dj.domain.DJBaseElement;
import ar.com.fdvs.dj.domain.StringExpression;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.entities.Entity;
import ar.com.fdvs.dj.domain.hyperlink.LiteralExpression;

public class DJAxisFormat extends DJBaseElement {
    private static final long serialVersionUID = Entity.SERIAL_VERSION_UID;
    private StringExpression labelExpression = null;
    private Font labelFont = null;
    private Color labelColor = null;
    private Font tickLabelFont = null;
    private Color tickLabelColor = null;
    private String tickLabelMask = null;
    private Color lineColor = null;
    private CustomExpression rangeMinValueExpression = null;
    private CustomExpression rangeMaxValueExpression = null;

    public DJAxisFormat() {
    }

    public DJAxisFormat(String label) {
        this(new LiteralExpression(label));
    }

    public DJAxisFormat(StringExpression labelExpression) {
        this.labelExpression = labelExpression;
    }

    /**
     * Returns the label color.
     *
     * @return the label color
     **/
    public Color getLabelColor() {
        return labelColor;
    }

    public StringExpression getLabelExpression() {
        return labelExpression;
    }

    /**
     * Returns the label font.
     *
     * @return the label font
     **/
    public Font getLabelFont() {
        return labelFont;
    }

    /**
     * Returns the line color.
     *
     * @return the line color
     **/
    public Color getLineColor() {
        return lineColor;
    }

    /**
     * Returns the range axis maximum value expression.
     *
     * @return the range axis maximum value expression
     **/
    public CustomExpression getRangeMaxValueExpression() {
        return rangeMaxValueExpression;
    }

    /**
     * Returns the range axis minimum value expression.
     *
     * @return the range axis minimum value expression
     **/
    public CustomExpression getRangeMinValueExpression() {
        return rangeMinValueExpression;
    }

    /**
     * Returns the tick label color.
     *
     * @return the tick label color
     **/
    public Color getTickLabelColor() {
        return tickLabelColor;
    }

    /**
     * Returns the tick label font.
     *
     * @return the tick label font
     **/
    public Font getTickLabelFont() {
        return tickLabelFont;
    }

    /**
     * Returns the tick label mask.
     *
     * @return the tick label mask
     **/
    public String getTickLabelMask() {
        return tickLabelMask;
    }

    /**
     * Sets the label color.
     *
     * @param labelColor
     *            the label color
     **/
    public void setLabelColor(Color labelColor) {
        this.labelColor = labelColor;
    }

    /**
     * Sets the label expression.
     *
     * @param labelExpression
     *            the label expression
     **/
    public void setLabelExpression(StringExpression labelExpression) {
        this.labelExpression = labelExpression;
    }

    /**
     * Sets the label font.
     *
     * @param labelFont
     *            the label font
     **/
    public void setLabelFont(Font labelFont) {
        this.labelFont = labelFont;
    }

    /**
     * Sets the line color.
     *
     * @param lineColor
     *            the line color
     **/
    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    /**
     * Sets the range axis maximum value expression.
     *
     * @param rangeMaxValueExpression
     *            the range axis maximum value expression
     **/
    public void setRangeMaxValueExpression(CustomExpression rangeMaxValueExpression) {
        this.rangeMaxValueExpression = rangeMaxValueExpression;
    }

    /**
     * Sets the range axis minimum value expression.
     *
     * @param rangeMinValueExpression
     *            the range axis minimum value expression
     **/
    public void setRangeMinValueExpression(CustomExpression rangeMinValueExpression) {
        this.rangeMinValueExpression = rangeMinValueExpression;
    }

    /**
     * Sets the tick label color.
     *
     * @param tickLabelColor
     *            the tick label color
     **/
    public void setTickLabelColor(Color tickLabelColor) {
        this.tickLabelColor = tickLabelColor;
    }

    /**
     * Sets the tick label font.
     *
     * @param tickLabelFont
     *            the tick label font
     **/
    public void setTickLabelFont(Font tickLabelFont) {
        this.tickLabelFont = tickLabelFont;
    }

    /**
     * Sets the tick label mask.
     *
     * @param tickLabelMask
     *            the tick label mask
     **/
    public void setTickLabelMask(String tickLabelMask) {
        this.tickLabelMask = tickLabelMask;
    }
}
