
package ar.com.fdvs.dj.util.customexpression;

import java.util.Map;

import ar.com.fdvs.dj.domain.CustomExpression;
import ar.com.fdvs.dj.domain.entities.Entity;

/**
 * Convenient CustomExpression that returns page number
 * 
 * @author mamana
 *
 */
public class PageNumberCustomExpression implements CustomExpression {
    private static final long serialVersionUID = Entity.SERIAL_VERSION_UID;

    @Override
    public Object evaluate(Map fields, Map variables, Map parameters) {
        return variables.get("PAGE_NUMBER");
    }

    @Override
    public String getClassName() {
        return Integer.class.getName();
    }

}
