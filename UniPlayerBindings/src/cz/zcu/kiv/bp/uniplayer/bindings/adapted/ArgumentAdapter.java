package cz.zcu.kiv.bp.uniplayer.bindings.adapted;

//import java.io.File;
//import java.lang.reflect.Array;
//import java.math.BigDecimal;
//import java.math.BigInteger;
//import java.util.LinkedList;
//import java.util.List;
//
//import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import cz.zcu.kiv.bp.uniplayer.bindings.TArgument;
//import cz.zcu.kiv.bp.uniplayer.bindings.TBigDecimalList;
//import cz.zcu.kiv.bp.uniplayer.bindings.TBigIntegerList;
//import cz.zcu.kiv.bp.uniplayer.bindings.TBooleanList;
//import cz.zcu.kiv.bp.uniplayer.bindings.TByteArrayList;
//import cz.zcu.kiv.bp.uniplayer.bindings.TByteList;
//import cz.zcu.kiv.bp.uniplayer.bindings.TCollection;
//import cz.zcu.kiv.bp.uniplayer.bindings.TDoubleList;
//import cz.zcu.kiv.bp.uniplayer.bindings.TFileList;
//import cz.zcu.kiv.bp.uniplayer.bindings.TFloatList;
//import cz.zcu.kiv.bp.uniplayer.bindings.TIntList;
//import cz.zcu.kiv.bp.uniplayer.bindings.TLongList;
//import cz.zcu.kiv.bp.uniplayer.bindings.TShortList;
//import cz.zcu.kiv.bp.uniplayer.bindings.TStringList;
import cz.zcu.kiv.bp.uniplayer.bindings.TValue;


public class ArgumentAdapter extends XmlAdapter<TArgument, Argument>
{

    @Override
    public Argument unmarshal(TArgument arg) throws Exception
    {
        ValueAdapter valAdapt = new ValueAdapter();
        Value unmVal = valAdapt.unmarshal((TValue) arg);
        Argument ret = new Argument();
        ret.setType(unmVal.getType());
        ret.setVal(unmVal.getVal());
        ret.setArgumentOrder(arg.getOrdNum());

        return ret;
    }

    @Override
    public TArgument marshal(Argument arg) throws Exception
    {   
        ValueAdapter valAdapt = new ValueAdapter();
        TValue marVal = valAdapt.marshal(arg);
        TArgument ret = createArgumentFromValue(marVal);
        ret.setOrdNum(arg.getArgumentOrder());
        return ret;
    }

	private TArgument createArgumentFromValue(TValue marVal) {
		TArgument ret = new TArgument();
        ret.setLong(marVal.getLong());
        ret.setInt(marVal.getInt());
        ret.setShort(marVal.getShort());
        ret.setByte(marVal.getByte());
        ret.setDouble(marVal.getDouble());
        ret.setFloat(marVal.getFloat());
        ret.setBigDecimal(marVal.getBigDecimal());
        ret.setBigInteger(marVal.getBigInteger());
        ret.setBoolean(marVal.getBoolean());
        ret.setBase64(marVal.getBase64());
        ret.setString(marVal.getString());
        ret.setFile(marVal.getFile());
        ret.setLongList(marVal.getLongList());
        ret.setIntList(marVal.getIntList());
        ret.setShortList(marVal.getShortList());
        ret.setByteList(marVal.getByteList());
        ret.setDoubleList(marVal.getDoubleList());
        ret.setFloatList(marVal.getFloatList());
        ret.setBigDecimalList(marVal.getBigDecimalList());
        ret.setBigIntegerList(marVal.getBigIntegerList());
        ret.setBooleanList(marVal.getBooleanList());
        ret.setByteArrayList(marVal.getByteArrayList());
        ret.setStringList(marVal.getStringList());
        ret.setFileList(marVal.getFileList());
		return ret;
	}
}
