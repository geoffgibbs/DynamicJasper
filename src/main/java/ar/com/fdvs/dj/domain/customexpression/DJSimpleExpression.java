
package ar.com.fdvs.dj.domain.customexpression;

import java.util.Map;

import ar.com.fdvs.dj.core.DJException;
import ar.com.fdvs.dj.domain.CustomExpression;

public class DJSimpleExpression implements CustomExpression {

    private static final long serialVersionUID = 1L;

    public static final byte TYPE_FIELD = 0;
    public static final byte TYPE_VARIABLE = 1;
    public static final byte TYPE_PARAMATER = 2;

    private final String variableName;
    private final String className;
    private final byte type;

    public DJSimpleExpression(byte type, String variableName, String className) {
        this.variableName = variableName;
        this.className = className;
        this.type = type;
    }

    @Override
    public Object evaluate(Map fields, Map variables, Map parameters) {
        throw new DJException("This method should have not been called");
    }

    @Override
    public String getClassName() {
        return className;
    }

    public byte getType() {
        return type;
    }

    public String getVariableName() {
        return variableName;
    }

}
