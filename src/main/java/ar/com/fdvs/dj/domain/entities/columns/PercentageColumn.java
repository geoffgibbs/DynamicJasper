
package ar.com.fdvs.dj.domain.entities.columns;

import ar.com.fdvs.dj.core.CoreException;
import ar.com.fdvs.dj.domain.DJCalculation;
import ar.com.fdvs.dj.domain.entities.DJGroup;
import ar.com.fdvs.dj.domain.entities.Entity;

/**
 * This column shows a percentage relative to another column.
 * 
 * @author mamana and Ricardo Mariaca
 *
 */
public class PercentageColumn extends AbstractColumn {
    private static final long serialVersionUID = Entity.SERIAL_VERSION_UID;
    private PropertyColumn percentageColumn;

    /**
     * The group which the variable will be inside (mostly for reset)
     * 
     * @param group
     *            (may be null)
     * @return
     */
    public String getGroupVariableName(DJGroup group) {
        String columnToGroupByProperty = group.getColumnToGroupBy().getColumnProperty().getProperty();
        return "variable-" + columnToGroupByProperty + "_" + getPercentageColumn().getColumnProperty().getProperty()
                + "_percentage";
    }

    @Override
    public String getGroupVariableName(String type, String columnToGroupByProperty) {
        return "variable-" + type + "_" + columnToGroupByProperty + "_"
                + getPercentageColumn().getColumnProperty().getProperty() + "_percentage";
    }

    @Override
    public String getInitialExpression(DJCalculation op) {
        return "new java.lang.Long(\"0\")";
    }

    public PropertyColumn getPercentageColumn() {
        return percentageColumn;
    }

    @Override
    public String getTextForExpression() {
        throw new CoreException("invalid operation on PercentageColumn");
    }

    public String getTextForExpression(DJGroup group) {
        return "new Double((" + getPercentageColumn().getTextForExpression() + ").doubleValue() / $V{" + getReportName()
                + "_" + getGroupVariableName(group) + "}.doubleValue())";
    }

    /**
     * Returns the formula for the percentage
     * 
     * @param group
     * @param type
     * @return
     */
    public String getTextForExpression(DJGroup group, DJGroup childGroup, String type) {
        return "new Double( $V{" + getReportName() + "_" + getGroupVariableName(childGroup) + "}.doubleValue() / $V{"
                + getReportName() + "_"
                + getGroupVariableName(type, group.getColumnToGroupBy().getColumnProperty().getProperty())
                + "}.doubleValue())";
    }

    @Override
    public String getValueClassNameForExpression() {
        return Number.class.getName();
    }

    @Override
    public String getVariableClassName(DJCalculation op) {
        if (op == DJCalculation.COUNT || op == DJCalculation.DISTINCT_COUNT) {
            return Long.class.getName();
        } else {
            return Number.class.getName();
        }
    }

    public void setPercentageColumn(PropertyColumn percentageColumn) {
        this.percentageColumn = percentageColumn;
    }

}
