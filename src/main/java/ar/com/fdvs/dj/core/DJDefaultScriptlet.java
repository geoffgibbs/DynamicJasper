
package ar.com.fdvs.dj.core;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRScriptletException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.fill.JRFillField;
import net.sf.jasperreports.engine.fill.JRFillGroup;
import net.sf.jasperreports.engine.fill.JRFillParameter;
import net.sf.jasperreports.engine.fill.JRFillVariable;

/**
 * This class handles parameter passing to custom expressions in runtime (during
 * report fill)
 *
 * @author mamana
 */
public class DJDefaultScriptlet extends JRDefaultScriptlet {

    private static final Log logger = LogFactory.getLog(DJDefaultScriptlet.class);

    protected FieldMapWrapper fieldMapWrapper = new FieldMapWrapper();

    protected ParameterMapWrapper parameterMapWrapper = new ParameterMapWrapper();
    protected VariableMapWrapper variableMapWrapper = new VariableMapWrapper();

    public DJDefaultScriptlet() {
        super();
    }

    @Override
    public void beforeReportInit() throws JRScriptletException {
        super.beforeReportInit();
        JasperReport jr = (JasperReport) getParameterValue(JRParameter.JASPER_REPORT);
        variableMapWrapper.setReportName(jr.getName());
        parameterMapWrapper.setReportName(jr.getName());
    }

    public Map getCurrentFields() {
        return fieldMapWrapper;
    }

    public Map getCurrentParams() {
        return parameterMapWrapper;
    }

    public Map getCurrentVariables() {
        return variableMapWrapper;
    }

    public Map getPreviousFields() {
        return fieldMapWrapper.getPreviousValues();
    }

    protected void putValuesInMap() {
        fieldMapWrapper.setMap(fieldsMap);
        parameterMapWrapper.setMap(parametersMap);
        variableMapWrapper.setMap(variablesMap);
    }

    @Override
    public void setData(Map<String, JRFillParameter> parsm, Map<String, JRFillField> fldsm,
            Map<String, JRFillVariable> varsm, JRFillGroup[] grps) {
        super.setData(parsm, fldsm, varsm, grps);
        putValuesInMap();
    }
}
