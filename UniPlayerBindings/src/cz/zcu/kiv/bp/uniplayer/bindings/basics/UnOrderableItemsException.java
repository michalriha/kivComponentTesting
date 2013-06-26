package cz.zcu.kiv.bp.uniplayer.bindings.basics;

import javax.xml.bind.JAXBException;

/**
 * Thrown when the scenario is valid but contains list that has more items with same ord-num attribute.
 * @author Michal
 */
public class UnOrderableItemsException extends JAXBException
{
    public UnOrderableItemsException(String message)
    {
        super(message);
    }

    private static final long serialVersionUID = 1325011384752919875L;

}
