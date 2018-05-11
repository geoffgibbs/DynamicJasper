
package ar.com.fdvs.dj.domain.entities;

import ar.com.fdvs.dj.domain.CustomExpression;
import ar.com.fdvs.dj.domain.DJBaseElement;
import ar.com.fdvs.dj.domain.DJCalculation;
import ar.com.fdvs.dj.domain.constants.DJVariableIncrementType;
import ar.com.fdvs.dj.domain.constants.DJVariableResetType;

public class DJVariable extends DJBaseElement {

    private static final long serialVersionUID = Entity.SERIAL_VERSION_UID;

    private String name;
    private String className;

    private DJCalculation calculation = DJCalculation.NOTHING;
    private DJGroup resetGroup;
    private DJVariableResetType resetType = DJVariableResetType.REPORT;

    private DJGroup incrementGroup;
    private DJVariableIncrementType incrementType = DJVariableIncrementType.NONE;

    private CustomExpression expression;
    private CustomExpression initialValueExpression;

    public DJVariable() {
    }

    public DJVariable(String name, String className, DJCalculation calculation, CustomExpression expression) {
        super();
        this.name = name;
        this.className = className;
        this.calculation = calculation;
        this.expression = expression;
    }

    public DJCalculation getCalculation() {
        return calculation;
    }

    public String getClassName() {
        return className;
    }

    public CustomExpression getExpression() {
        return expression;
    }

    public DJGroup getIncrementGroup() {
        return incrementGroup;
    }

    public DJVariableIncrementType getIncrementType() {
        return incrementType;
    }

    public CustomExpression getInitialValueExpression() {
        return initialValueExpression;
    }

    public String getName() {
        return name;
    }

    public DJGroup getResetGroup() {
        return resetGroup;
    }

    public DJVariableResetType getResetType() {
        return resetType;
    }

    public void setCalculation(DJCalculation calculation) {
        this.calculation = calculation;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setExpression(CustomExpression expression) {
        this.expression = expression;
    }

    public void setIncrementGroup(DJGroup incrementGroup) {
        this.incrementGroup = incrementGroup;
    }

    public void setIncrementType(DJVariableIncrementType incrementType) {
        this.incrementType = incrementType;
    }

    public void setInitialValueExpression(CustomExpression initialValueExpression) {
        this.initialValueExpression = initialValueExpression;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setResetGroup(DJGroup resetGroup) {
        this.resetGroup = resetGroup;
    }

    public void setResetType(DJVariableResetType resetType) {
        this.resetType = resetType;
    }

}
