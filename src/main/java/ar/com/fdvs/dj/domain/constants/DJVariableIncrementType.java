
package ar.com.fdvs.dj.domain.constants;

public class DJVariableIncrementType extends BaseDomainConstant {

    private static final long serialVersionUID = 1L;

    public static final DJVariableIncrementType REPORT = new DJVariableIncrementType((byte) 1);

    public static final DJVariableIncrementType PAGE = new DJVariableIncrementType((byte) 2);
    public static final DJVariableIncrementType COLUMN = new DJVariableIncrementType((byte) 3);
    public static final DJVariableIncrementType GROUP = new DJVariableIncrementType((byte) 4);
    public static final DJVariableIncrementType NONE = new DJVariableIncrementType((byte) 5);
    private byte value;

    public DJVariableIncrementType(byte val) {
        value = val;
    }

    public byte getValue() {
        return value;
    }

}
