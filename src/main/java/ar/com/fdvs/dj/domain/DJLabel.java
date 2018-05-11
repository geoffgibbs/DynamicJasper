
package ar.com.fdvs.dj.domain;

import ar.com.fdvs.dj.domain.constants.LabelPosition;
import ar.com.fdvs.dj.domain.entities.Entity;

public class DJLabel extends DJBaseElement {

    private static final long serialVersionUID = Entity.SERIAL_VERSION_UID;

    protected boolean isJasperExpression = false;

    protected String text;
    protected CustomExpression labelExpression;
    protected Style style;
    protected int height = 15;

    public DJLabel() {
    }

    public DJLabel(CustomExpression labelExpression, Style labelStyle) {
        super();
        this.labelExpression = labelExpression;
        style = labelStyle;
    }

    public DJLabel(String text, Style labelStyle) {
        super();
        this.text = text;
        style = labelStyle;
    }

    public DJLabel(String text, Style labelStyle, boolean isJasperExpression) {
        super();
        this.text = text;
        style = labelStyle;
        this.isJasperExpression = isJasperExpression;
    }

    /**
     * @Deprecated
     */
    public DJLabel(String text, Style labelStyle, LabelPosition labelPosition) {
        super();
        this.text = text;
        style = labelStyle;
    }

    public int getHeight() {
        return height;
    }

    public CustomExpression getLabelExpression() {
        return labelExpression;
    }

    public Style getStyle() {
        return style;
    }

    public String getText() {
        return text;
    }

    public boolean isJasperExpression() {
        return isJasperExpression;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setJasperExpression(boolean isJasperExpression) {
        this.isJasperExpression = isJasperExpression;
    }

    public void setLabelExpression(CustomExpression labelExpression) {
        this.labelExpression = labelExpression;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public void setText(String text) {
        this.text = text;
    }

}
