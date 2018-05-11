
package ar.com.fdvs.dj.core;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import net.sf.jasperreports.engine.fill.JRFillParameter;

public class ParameterMapWrapper implements Map {

    private Map<String, JRFillParameter> map;
    private String reportName;

    public ParameterMapWrapper() {
        map = Collections.emptyMap();
    }

    public ParameterMapWrapper(Map<String, JRFillParameter> map) {
        this.map = map;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean containsKey(Object key) {
        boolean contains = map.containsKey(key);
        return contains || map.containsKey(reportName + "_" + key);
    }

    @Override
    public boolean containsValue(Object value) {
        throw new DJException("Method not implemented");
    }

    @Override
    public Set entrySet() {
        return map.entrySet();
    }

    @Override
    public boolean equals(Object o) {
        return map.equals(o);
    }

    @Override
    public Object get(Object key) {
        Object value = map.get(key);
        if (value == null) {
            value = map.get(reportName + "_" + key);
        }
        if (value == null) {
            return null;
        }

        return ((JRFillParameter) value).getValue();
    }

    @Override
    public int hashCode() {
        return map.hashCode();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public Set keySet() {
        return map.keySet();
    }

    @Override
    public Object put(Object arg0, Object arg1) {
        return map.put((String) arg0, (JRFillParameter) arg1);
    }

    @Override
    public void putAll(Map arg0) {
        map.putAll(arg0);
    }

    @Override
    public Object remove(Object key) {
        return map.remove(key);
    }

    public void setMap(Map<String, JRFillParameter> parsm) {
        map = parsm;

    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public Collection values() {
        throw new DJException("Method not implemented");
    }
}
