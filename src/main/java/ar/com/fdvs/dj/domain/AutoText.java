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
/*

 */

package ar.com.fdvs.dj.domain;

import net.sf.jasperreports.engine.JRExpression;

import ar.com.fdvs.dj.core.layout.HorizontalBandAlignment;
import ar.com.fdvs.dj.domain.entities.Entity;

/**
 * @author msimone
 *
 */
public class AutoText extends DJBaseElement {

    private static final long serialVersionUID = Entity.SERIAL_VERSION_UID;

    public static final int WIDTH_NOT_SET = Integer.MIN_VALUE;
    public static final int DEFAULT_WIDTH = 80;
    public static final int DEFAULT_WIDTH2 = 30;
    public static final byte POSITION_FOOTER = 0;
    public static final byte POSITION_HEADER = 1;

    public static final byte AUTOTEXT_PAGE_X_OF_Y = 0;
    public static final byte AUTOTEXT_PAGE_X_SLASH_Y = 1;
    public static final byte AUTOTEXT_PAGE_X = 2;
    public static final byte AUTOTEXT_CREATED_ON = 3;
    public static final byte AUTOTEXT_CUSTOM_MESSAGE = 4;
    public static final byte AUTOTEXT_JREXPRESSION = 5;

    /**
     * @Deprecated due to miss spell use ALIGNMENT_LEFT
     */
    public static final byte ALIGMENT_LEFT = 1;
    /**
     * @Deprecated due to miss spell use ALIGNMENT_CENTER
     */
    public static final byte ALIGMENT_CENTER = 2;
    /**
     * @Deprecated due to miss spell use ALIGNMENT_RIGHT
     */
    public static final byte ALIGMENT_RIGHT = 3;

    public static final byte ALIGNMENT_LEFT = 1;
    public static final byte ALIGNMENT_CENTER = 2;
    public static final byte ALIGNMENT_RIGHT = 3;

    public static final byte PATTERN_DATE_DATE_ONLY = 1;
    public static final byte PATTERN_DATE_TIME_ONLY = 2;
    public static final byte PATTERN_DATE_DATE_TIME = 3;

    private HorizontalBandAlignment alignment;

    private byte type;

    private byte position;

    private String messageKey;
    private JRExpression expresion;
    private int pageOffset = 0;
    private boolean useI18n;
    private byte pattern; // Applies for CREATED_ON, its the pattern used for dates

    private int height = 15;

    private Style style = null;

    /**
     *
     */
    private int width = WIDTH_NOT_SET;

    /**
     * For autotexts that consists in two parts (like: "page x of y" and "x / y").
     * <br>
     * These autotext are compound by 2 text-fields with different render time (one
     * is immediate, the other generally "time-report")<br>
     * The first part is the "immediate" second part is the "y" (time-report)<br>
     * width2 defines how width is the second part. This is for fine tuning of the
     * layout. depending on the size of report the total pages can be a small or big
     * number, making this width wide enough should prevent the text to override the
     * space given
     */
    private int width2 = WIDTH_NOT_SET;

    /**
     * tells if the API can modify the with if needed
     */
    private boolean fixedWith = true;
    private BooleanExpression printWhenExpression;

    public AutoText(byte type, byte position, HorizontalBandAlignment alignment) {
        this.type = type;
        this.position = position;
        this.alignment = alignment;
    }

    public AutoText(byte type, byte position, HorizontalBandAlignment alignment, byte pattern) {
        this.type = type;
        this.position = position;
        this.alignment = alignment;
        this.pattern = pattern;
    }

    public AutoText(byte type, byte position, HorizontalBandAlignment alignment, byte pattern, int width) {
        this.type = type;
        this.position = position;
        this.alignment = alignment;
        this.pattern = pattern;
        this.width = width;
    }

    public AutoText(byte type, byte position, HorizontalBandAlignment alignment, byte pattern, int width, int width2) {
        this.type = type;
        this.position = position;
        this.alignment = alignment;
        this.pattern = pattern;
        this.width = width;
        this.width2 = width2;
    }

    public AutoText(JRExpression expression, byte position, HorizontalBandAlignment alignment, int with) {
        type = AUTOTEXT_JREXPRESSION;
        this.position = position;
        this.alignment = alignment;
        expresion = expression;
        width = with;
        fixedWith = false;
    }

    public AutoText(String message, byte position, HorizontalBandAlignment alignment) {
        type = AUTOTEXT_CUSTOM_MESSAGE;
        this.position = position;
        this.alignment = alignment;
        messageKey = message;
        fixedWith = false;
    }

    public AutoText(String message, byte position, HorizontalBandAlignment alignment, int with) {
        type = AUTOTEXT_CUSTOM_MESSAGE;
        this.position = position;
        this.alignment = alignment;
        messageKey = message;
        width = with;
        fixedWith = false;
    }

    public HorizontalBandAlignment getAlignment() {
        return alignment;
    }

    public JRExpression getExpresion() {
        return expresion;
    }

    public int getHeight() {
        return height;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public int getPageOffset() {
        return pageOffset;
    }

    public byte getPattern() {
        return pattern;
    }

    public byte getPosition() {
        return position;
    }

    public BooleanExpression getPrintWhenExpression() {
        return printWhenExpression;
    }

    /**
     * returns the style
     *
     * @return can be null if no style has been set
     */
    public Style getStyle() {
        return style;
    }

    public byte getType() {
        return type;
    }

    public int getWidth() {
        return width;
    }

    public int getWidth2() {
        return width2;
    }

    public boolean isFixedWith() {
        return fixedWith;
    }

    public boolean isUseI18n() {
        return useI18n;
    }

    public void setAlignment(HorizontalBandAlignment alignment) {
        this.alignment = alignment;
    }

    public void setExpresion(JRExpression expresion) {
        this.expresion = expresion;
    }

    public void setFixedWith(boolean fixedWith) {
        this.fixedWith = fixedWith;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setMessageKey(String message) {
        messageKey = message;
    }

    public void setPageOffset(int pageStartNumber) {
        pageOffset = pageStartNumber;
    }

    public void setPosition(byte position) {
        this.position = position;
    }

    public void setPrintWhenExpression(BooleanExpression printWhenExpression) {
        this.printWhenExpression = printWhenExpression;
    }

    public AutoText setStyle(Style newStyle) {
        style = newStyle;
        return this;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public void setUseI18n(boolean useI18n) {
        this.useI18n = useI18n;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setWidth2(int width2) {
        this.width2 = width2;
    }
}
