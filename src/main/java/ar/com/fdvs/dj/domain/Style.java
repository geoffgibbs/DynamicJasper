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

import java.awt.Color;
import java.io.Serializable;

import net.sf.jasperreports.engine.base.JRBaseStyle;
import net.sf.jasperreports.engine.design.JRDesignConditionalStyle;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.type.HorizontalAlignEnum;
import net.sf.jasperreports.engine.type.ModeEnum;
import net.sf.jasperreports.engine.type.RotationEnum;
import net.sf.jasperreports.engine.type.VerticalAlignEnum;

import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Rotation;
import ar.com.fdvs.dj.domain.constants.Stretching;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.entities.Entity;
import ar.com.fdvs.dj.util.LayoutUtils;

/**
 * Class that should be used to define the different styles in a friendly </br>
 * and strict way.</br>
 * </br>
 * Usage example:</br>
 * Style headerStyle = new Style();</br>
 * headerStyle.setFont(Font.ARIAL_MEDIUM_BOLD);</br>
 * headerStyle.setBorder(Border.PEN_2_POINT());</br>
 * headerStyle.setHorizontalAlign(HorizontalAlign.CENTER);</br>
 * headerStyle.setVerticalAlign(VerticalAlign.MIDDLE);</br>
 */
public class Style implements Serializable, Cloneable {

    private static final long serialVersionUID = Entity.SERIAL_VERSION_UID;

    /**
     * Creates a blank style (no default values). Useful when we need a style with a
     * parent style, not defined properties (null ones) will be inherited from
     * parent style
     *
     * @param name
     *            style name
     * @return Style
     */
    public static Style createBlankStyle(String name) {
        Style style = new Style(name);

        style.setBackgroundColor(null);
        style.setBorderColor(null);
        style.setTransparency(null);
        style.setTextColor(null);
        style.setBorder(null);
        style.setFont(null);
        style.setPadding(null);
        style.setRadius(null);
        style.setVerticalAlign(null);
        style.setHorizontalAlign(null);
        style.setRotation(null);
        style.setStreching(null);

        return style;

    }

    public static Style createBlankStyle(String name, String parent) {
        Style s = createBlankStyle(name);
        s.setParentStyleName(parent);
        return s;
    }

    private String name;
    private String parentStyleName;

    private Color backgroundColor = Color.WHITE;

    private Color textColor = Color.BLACK;

    private Font font = Font.ARIAL_MEDIUM;
    private Border border = Border.NO_BORDER();
    private Border borderTop = null;
    private Border borderBottom = null;

    private Border borderLeft = null;

    private Border borderRight = null;
    private Integer paddingBottom, paddingTop, paddingLeft, paddingRight;

    private Integer padding = 2;

    private Integer radius = 0;
    private Transparency transparency = Transparency.TRANSPARENT;
    private VerticalAlign verticalAlign = VerticalAlign.BOTTOM;

    private HorizontalAlign horizontalAlign = HorizontalAlign.LEFT;

    private Rotation rotation = Rotation.NONE;
    private Stretching streching = Stretching.RELATIVE_TO_TALLEST_OBJECT;

    private boolean stretchWithOverflow = true;

    private boolean blankWhenNull = true;

    private String pattern;

    /**
     * If true and another style exists in the design with the same name, this style
     * overrides the existing one.
     */
    private boolean overridesExistingStyle = false;

    public Style() {
    }

    public Style(String name) {
        this.name = name;
    }

    public Style(String name, String parentName) {
        this.name = name;
        parentStyleName = parentName;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Style style = (Style) super.clone();
        style.setFont(font);
        return style;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public Border getBorder() {
        return border;
    }

    public Border getBorderBottom() {
        return borderBottom;
    }

    /**
     * use #Style.getBorder().getColor() instead
     * 
     * @return
     */
    @Deprecated
    public Color getBorderColor() {
        if (getBorder() == null) {
            return null;
        }
        return getBorder().getColor();
    }

    public Border getBorderLeft() {
        return borderLeft;
    }

    public Border getBorderRight() {
        return borderRight;
    }

    public Border getBorderTop() {
        return borderTop;
    }

    public Font getFont() {
        return font;
    }

    public HorizontalAlign getHorizontalAlign() {
        return horizontalAlign;
    }

    public String getName() {
        return name;
    }

    public Integer getPadding() {
        return padding;
    }

    public Integer getPaddingBottom() {
        return paddingBottom;
    }

    public Integer getPaddingLeft() {
        return paddingLeft;
    }

    public Integer getPaddingRight() {
        return paddingRight;
    }

    public Integer getPaddingTop() {
        return paddingTop;
    }

    public String getParentStyleName() {
        return parentStyleName;
    }

    public String getPattern() {
        return pattern;
    }

    public Integer getRadius() {
        return radius;
    }

    public Rotation getRotation() {
        return rotation;
    }

    public Stretching getStreching() {
        return streching;
    }

    public Color getTextColor() {
        return textColor;
    }

    public Transparency getTransparency() {
        return transparency;
    }

    public VerticalAlign getVerticalAlign() {
        return verticalAlign;
    }

    public boolean isBlankWhenNull() {
        return blankWhenNull;
    }

    public boolean isOverridesExistingStyle() {
        return overridesExistingStyle;
    }

    public boolean isStretchWithOverflow() {
        return stretchWithOverflow;
    }

    public boolean isTransparent() {
        return transparency.equals(Transparency.TRANSPARENT);
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setBlankWhenNull(boolean blankWhenNull) {
        this.blankWhenNull = blankWhenNull;
    }

    public void setBorder(Border border) {
        this.border = border;
    }

    public void setBorderBottom(Border borderBottom) {
        this.borderBottom = borderBottom;
    }

    /**
     * Use #Style.setBorder(...) instead
     * 
     * @param borderColor
     */
    @Deprecated
    public void setBorderColor(Color borderColor) {
        if (getBorder() == null) {
            return;
        }

        getBorder().setColor(borderColor);
    }

    public void setBorderLeft(Border borderLeft) {
        this.borderLeft = borderLeft;
    }

    public void setBorderRight(Border borderRight) {
        this.borderRight = borderRight;
    }

    public void setBorderTop(Border borderTop) {
        this.borderTop = borderTop;
    }

    public void setFont(Font font) {
        if (font != null) {
            this.font = (Font) font.clone();
        } else {
            this.font = null;
        }
    }

    public void setHorizontalAlign(HorizontalAlign horizontalAlign) {
        this.horizontalAlign = horizontalAlign;
    }

    protected void setJRBaseStyleProperties(JRBaseStyle transformedStyle) {
        transformedStyle.getLineBox().getPen();
        if (getBorder() != null) {
            LayoutUtils.convertBorderToPen(getBorder(), transformedStyle.getLineBox().getPen());
        }

        if (getBorderBottom() != null) {
            LayoutUtils.convertBorderToPen(getBorderBottom(), transformedStyle.getLineBox().getBottomPen());
        }
        // transformedStyle.setBottomBorder(getBorderBottom().getValue());
        if (getBorderTop() != null) {
            LayoutUtils.convertBorderToPen(getBorderTop(), transformedStyle.getLineBox().getTopPen());
        }
        // transformedStyle.setTopBorder(getBorderTop().getValue());
        if (getBorderLeft() != null) {
            LayoutUtils.convertBorderToPen(getBorderLeft(), transformedStyle.getLineBox().getLeftPen());
        }
        // transformedStyle.setLeftBorder(getBorderLeft().getValue());
        if (getBorderRight() != null) {
            LayoutUtils.convertBorderToPen(getBorderRight(), transformedStyle.getLineBox().getRightPen());
            // transformedStyle.setRightBorder(getBorderRight().getValue());
        }

        // Padding
        transformedStyle.getLineBox().setPadding(getPadding());

        if (paddingBottom != null) {
            transformedStyle.getLineBox().setBottomPadding(paddingBottom);
        }
        if (paddingTop != null) {
            transformedStyle.getLineBox().setTopPadding(paddingTop);
        }
        if (paddingLeft != null) {
            transformedStyle.getLineBox().setLeftPadding(paddingLeft);
        }
        if (paddingRight != null) {
            transformedStyle.getLineBox().setRightPadding(paddingRight);
        }

        // Aligns
        if (getHorizontalAlign() != null) {
            transformedStyle.setHorizontalAlignment(HorizontalAlignEnum.getByValue(getHorizontalAlign().getValue()));
        }

        if (getVerticalAlign() != null) {
            transformedStyle.setVerticalAlignment(VerticalAlignEnum.getByValue(getVerticalAlign().getValue()));
        }

        transformedStyle.setBlankWhenNull(blankWhenNull);

        // Font
        if (font != null) {
            transformedStyle.setFontName(font.getFontName());
            transformedStyle.setFontSize(font.getFontSize());
            transformedStyle.setBold(font.isBold());
            transformedStyle.setItalic(font.isItalic());
            transformedStyle.setUnderline(font.isUnderline());
            transformedStyle.setPdfFontName(font.getPdfFontName());
            transformedStyle.setPdfEmbedded(font.isPdfFontEmbedded());
            transformedStyle.setPdfEncoding(font.getPdfFontEncoding());
        }

        transformedStyle.setBackcolor(getBackgroundColor());
        transformedStyle.setForecolor(getTextColor());

        if (getTransparency() != null) {
            transformedStyle.setMode(ModeEnum.getByValue(getTransparency().getValue()));
        }

        if (getRotation() != null) {
            transformedStyle.setRotation(RotationEnum.getByValue(getRotation().getValue()));
        }

        if (getRadius() != null) {
            transformedStyle.setRadius(getRadius().intValue());
        }

        transformedStyle.setPattern(pattern);

        /*
         * This values are needed when exporting to JRXML
         */
        // TODO Check if this is still necessary
        /*
         * transformedStyle.setPen((byte)0); transformedStyle.setFill((byte)1);
         * transformedStyle.setScaleImage(ImageScaleMode.NO_RESIZE.getValue());
         */

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOverridesExistingStyle(boolean overridesExistingStyle) {
        this.overridesExistingStyle = overridesExistingStyle;
    }

    public void setPadding(Integer padding) {
        this.padding = padding;
    }

    public void setPaddingBottom(Integer paddingBottom) {
        this.paddingBottom = paddingBottom;
    }

    public void setPaddingLeft(Integer paddingLeft) {
        this.paddingLeft = paddingLeft;
    }

    public void setPaddingRight(Integer paddingRight) {
        this.paddingRight = paddingRight;
    }

    public void setPaddingTop(Integer paddingTop) {
        this.paddingTop = paddingTop;
    }

    public void setParentStyleName(String parentStyleName) {
        this.parentStyleName = parentStyleName;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public void setRadius(Integer radius) {
        this.radius = radius;
    }

    public void setRotation(Rotation rotation) {
        this.rotation = rotation;
    }

    public void setStreching(Stretching streching) {
        this.streching = streching;
    }

    public void setStretchWithOverflow(boolean stretchWithOverflow) {
        this.stretchWithOverflow = stretchWithOverflow;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public void setTransparency(Transparency transparency) {
        this.transparency = transparency;
    }

    public void setTransparent(boolean transparent) {
        if (transparent) {
            setTransparency(Transparency.TRANSPARENT);
        } else {
            setTransparency(Transparency.OPAQUE);
        }
    }

    public void setVerticalAlign(VerticalAlign verticalAlign) {
        this.verticalAlign = verticalAlign;
    }

    public JRDesignStyle transform() {
        JRDesignStyle transformedStyle = new JRDesignStyle();
        transformedStyle.setName(name);
        transformedStyle.setParentStyleNameReference(parentStyleName);
        setJRBaseStyleProperties(transformedStyle);
        return transformedStyle;
    }

    public JRDesignConditionalStyle transformAsConditinalStyle() {
        JRDesignConditionalStyle ret = new JRDesignConditionalStyle();
        setJRBaseStyleProperties(ret);
        return ret;

    }
}
