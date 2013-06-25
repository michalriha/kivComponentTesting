package cz.zcu.kiv.bp.unimocker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import cz.zcu.kiv.bp.unimocker.bindings.TAnyValue;

public class ArgumentScenarioTable
{	
	public class Posibility extends ArrayList<Object> implements Comparable<Posibility>
	{
		private static final long serialVersionUID = 1L;

		private boolean posible = true;
		
		Object returnValue = null;

		private int universalArgsCount = 0;
		
		private void align()
		{
			this.universalArgsCount = 0;
			for (Object arg : this)
			{
				if (arg instanceof TAnyValue) this.universalArgsCount++;
			}
		}
		
		@Override
		public int compareTo(Posibility compared)
		{			
			return (int) Math.signum(universalArgsCount - compared.universalArgsCount);
		}
	}
	
    private static int ARGS_COUNT;
	
    private Class<?>[] parametrTypes;
    
	List<Posibility> matrix = new ArrayList<>();
	
	public ArgumentScenarioTable(Class<?>[] parametrTypes)
	{
		this.parametrTypes = parametrTypes;
		ARGS_COUNT = parametrTypes.length;
	}
	
	public void addPosibility(Posibility pos)
	{
		if (pos.size() != ARGS_COUNT) throw new IllegalArgumentException("Invalid arguments count!");
		
		// must be called for proper sorting
		pos.align();
		this.matrix.add(pos);
	}
	
	public Object find(Object... args) throws Exception, IllegalArgumentException
	{
		if (args != null && args.length != ARGS_COUNT) throw new IllegalArgumentException("Invalid arguments count!(" + args.length + " / " + ARGS_COUNT + ")");
		
		Object ret = null;
		boolean found = false;
		
		this.filterMatrix(args);

		List<Posibility> universalRows = new ArrayList<>();
        for (int row = 0; row < this.matrix.size(); row++)
        {
            if (this.matrix.get(row).posible)
            {
            	// check if given row contains universal argument
            	if (this.hasUniversaArguments(row))
            	{ // store universal arguments for later and try another possibility
            		universalRows.add(this.matrix.get(row));
            		continue;
            	}
                ret = this.matrix.get(row).returnValue;
                found = true;
                break;
            }
        }
        // if no exact possibility has been found, try to find the less universal
        if (!found && !universalRows.isEmpty())
        {
        	// sort by number of universal arguments
        	Collections.sort(universalRows);
	        ret = universalRows.get(0).returnValue;
	        found = true;
        }
        
        // reset possibility markers for next call
        this.resetPosibleMarkers();

        if (!found) throw new Exception("No valid argument combination has been found.\n" + Arrays.deepToString(args));

        return ret;
	}

	private boolean hasUniversaArguments(int row) {
		boolean contains = false;
		for (Object arg: this.matrix.get(row))
		{
			if (arg instanceof TAnyValue)
			{
				contains = true;
				break;
			}
		}
		return contains;
	}

	private void filterMatrix(Object... args)
	{
		for (int col = 0; col < ARGS_COUNT; col++)
        {
            for (int row = 0; row < this.matrix.size(); row++)
            {
                if (this.matrix.get(row).posible)
                { // Previous arguments in this possibility matched
                    
                    if (!this.match(this.matrix.get(row).get(col), args[col], this.parametrTypes[col]))
                    {
                        this.matrix.get(row).posible = false;
                    }
                }
                else
                { // line has been abandoned
                    continue;
                }
            }
        }
	}

	private void resetPosibleMarkers() {
		for (Posibility pos : this.matrix)
        {
        	pos.posible = true;
        }
	}

	private boolean match(Object posibleObj, Object actualObj, Class<?> parametrType)
	{
		if (posibleObj instanceof TAnyValue)
		{ // no matching
			return true;
		}
		
		if (posibleObj.equals(actualObj))
		{
			return true;
		}
		
		if (Comparable.class.isAssignableFrom(parametrType))
		{
			@SuppressWarnings("unchecked")
			Comparable<Object> comp1 = (Comparable<Object>) posibleObj;
			if (comp1.compareTo(actualObj) == 0)
			{
				return true;
			}
		}

		if (parametrType.isArray())
		{
			if (Arrays.deepEquals((Object[]) posibleObj, (Object[]) actualObj))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public String toString()
	{
		return this.matrix.toString();
	}
}
