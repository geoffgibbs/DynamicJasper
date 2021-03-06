
package ar.com.fdvs.dj.util;

import java.lang.reflect.Constructor;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.jasperreports.engine.JRBand;
import net.sf.jasperreports.engine.JRChild;
import net.sf.jasperreports.engine.JRDefaultStyleProvider;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPen;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignGraphicElement;
import net.sf.jasperreports.engine.design.JRDesignGroup;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JRDesignSection;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.type.LineStyleEnum;
import net.sf.jasperreports.engine.type.SplitTypeEnum;

import ar.com.fdvs.dj.core.DJException;
import ar.com.fdvs.dj.core.layout.LayoutManager;
import ar.com.fdvs.dj.core.registration.EntitiesRegistrationException;
import ar.com.fdvs.dj.domain.CustomExpression;
import ar.com.fdvs.dj.domain.DynamicJasperDesign;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.entities.DJGroup;

public class LayoutUtils {

    static final Log log = LogFactory.getLog(LayoutUtils.class);

    public static void convertBorderToPen(Border border, JRPen pen) {
        if (border == null) {
            return;
        }

        pen.setLineWidth(border.getWidth());
        pen.setLineStyle(LineStyleEnum.getByValue(border.getLineStyle()));
        pen.setLineColor(border.getColor());

    }

    /**
     * Copy bands elements from source to dest band, also makes sure copied elements
     * are placed below existing ones (Y offset is calculated)
     * 
     * @param destBand
     * @param sourceBand
     */
    public static void copyBandElements(JRDesignBand destBand, JRBand sourceBand) {
        int offset = findVerticalOffset(destBand);

        if (destBand == null) {
            throw new DJException("destination band cannot be null");
        }

        if (sourceBand == null) {
            return;
        }

        for (JRChild jrChild : sourceBand.getChildren()) {
            JRDesignElement element = (JRDesignElement) jrChild;
            JRDesignElement dest = null;
            try {
                if (element instanceof JRDesignGraphicElement) {
                    Constructor<? extends JRDesignElement> constructor = element.getClass()
                            .getConstructor(JRDefaultStyleProvider.class);
                    JRDesignStyle style = new JRDesignStyle();
                    dest = constructor.newInstance(style.getDefaultStyleProvider());
                } else {
                    dest = element.getClass().newInstance();
                }

                BeanUtils.copyProperties(dest, element);
                dest.setY(dest.getY() + offset);
            } catch (Exception e) {
                log.error("Exception copying elements from band to band: " + e.getMessage(), e);
            }
            destBand.addElement(dest);
        }
    }

    public static DJGroup findChildDJGroup(DJGroup djgroup, DynamicReport dr) {
        DJGroup child = null;
        int gidx = dr.getColumnsGroups().indexOf(djgroup);
        if (gidx + 1 < dr.getColumnsGroups().size()) {
            gidx++;
            child = dr.getColumnsGroups().get(gidx);
        }
        return child;
    }

    public static JRDesignGroup findParentJRGroup(DJGroup djgroup, DynamicReport dr, DynamicJasperDesign djd,
            LayoutManager layoutManager) {
        JRDesignGroup registeredGroup;
        int gidx = dr.getColumnsGroups().indexOf(djgroup);
        if (gidx > 0) {
            gidx--;
            DJGroup djParentGroup = dr.getColumnsGroups().get(gidx);
            registeredGroup = LayoutUtils.getJRDesignGroup(djd, layoutManager, djParentGroup);
        } else {
            registeredGroup = null;
        }
        return registeredGroup;
    }

    /**
     * Finds "Y" coordinate value in which more elements could be added in the band
     * 
     * @param band
     * @return
     */
    public static int findVerticalOffset(JRDesignBand band) {
        int finalHeight = 0;
        if (band != null) {
            for (JRChild jrChild : band.getChildren()) {
                JRDesignElement element = (JRDesignElement) jrChild;
                int currentHeight = element.getY() + element.getHeight();
                if (currentHeight > finalHeight) {
                    finalHeight = currentHeight;
                }
            }
            return finalHeight;
        }
        return finalHeight;
    }

    /**
     * Returns the firs band from the section
     * 
     * @param section
     * @return
     */
    public static JRDesignBand getBandFromSection(JRDesignSection section) {
        return (JRDesignBand) section.getBandsList().get(0);
    }

    /**
     * Returns the JRDesignGroup for the DJGroup passed
     * 
     * @param jd
     * @param layoutManager
     * @param group
     * @return
     */
    public static JRDesignGroup getJRDesignGroup(DynamicJasperDesign jd, LayoutManager layoutManager, DJGroup group) {
        Map references = layoutManager.getReferencesMap();
        for (Object o : references.keySet()) {
            String groupName = (String) o;
            DJGroup djGroup = (DJGroup) references.get(groupName);
            if (group == djGroup) {
                return (JRDesignGroup) jd.getGroupsMap().get(groupName);
            }
        }
        return null;
    }

    /**
     * FIXME there are 3 types of SplitTypeEnum, we are not using the "stretch"
     * 
     * @param allowsplit
     * @return
     */
    public static SplitTypeEnum getSplitTypeFromBoolean(boolean allowsplit) {
        if (allowsplit) {
            return SplitTypeEnum.IMMEDIATE;
        }

        return SplitTypeEnum.PREVENT;
    }

    /**
     * Moves the elements contained in "band" in the Y axis "yOffset"
     * 
     * @param yOffset
     * @param band
     */
    public static void moveBandsElemnts(int yOffset, JRDesignBand band) {
        if (band == null) {
            return;
        }

        for (JRChild jrChild : band.getChildren()) {
            JRDesignElement elem = (JRDesignElement) jrChild;
            elem.setY(elem.getY() + yOffset);
        }
    }

    public static void registerCustomExpressionParameter(DynamicJasperDesign design, String name,
            CustomExpression customExpression) {
        if (customExpression == null) {
            return;
        }
        JRDesignParameter dparam = new JRDesignParameter();
        dparam.setName(name);
        dparam.setValueClassName(CustomExpression.class.getName());
        log.debug("Registering customExpression parameter with name " + name);
        try {
            design.addParameter(dparam);
        } catch (JRException e) {
            throw new EntitiesRegistrationException(e.getMessage(), e);
        }
        design.getParametersWithValues().put(name, customExpression);
    }
}
