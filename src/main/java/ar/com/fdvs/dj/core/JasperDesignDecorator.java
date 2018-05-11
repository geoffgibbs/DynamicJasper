
package ar.com.fdvs.dj.core;

import java.util.Map;

import net.sf.jasperreports.engine.design.JasperDesign;

/**
 * Created by mamana on 28/9/2016. This class gives the opportunity to modify
 * JasperDesign object directly. Use with caution, since you can break the
 * design causing an exception at compile time.
 */
public class JasperDesignDecorator {

    public void afterLayout(JasperDesign jd, Map param) {
    }

    public void beforeLayout(JasperDesign jd, Map param) {
    }

}
