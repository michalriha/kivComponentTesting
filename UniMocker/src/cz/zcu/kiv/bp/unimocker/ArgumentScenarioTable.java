package cz.zcu.kiv.bp.unimocker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import cz.zcu.kiv.bp.unimocker.bindings.TAnyValue;
import cz.zcu.kiv.bp.unimocker.bindings.adapted.Invocation;

/**
 * Implementation of argument matrix for storing and matching arguments against it's return values.
 * @author Michal
 */
public class ArgumentScenarioTable
{	
	/**
	 * @author Michal
	 * Row of argument matrix, comparable by number of universal values (argument is instance of TAnyValue). 
	 */
	public class Posibility extends ArrayList<Object> implements Comparable<Posibility>
	{
		private static final long serialVersionUID = 1L;

		/**
		 * filtration marker
		 */
		private boolean posible = true;
		
		/**
		 * return value matching to contained argument values
		 */
//		Object returnValue = null;
		Invocation invocationDescription = null;

		/**
		 * convenient marker for comparison
		 */
		private int universalArgsCount = 0;
		
		/**
		 * Convenient method for quicker comparison.
		 * Needs to be called after all argument values has been added!
		 */
		private void align()
		{
			this.universalArgsCount = 0;
			for (Object arg : this)
			{
				if (arg instanceof TAnyValue) this.universalArgsCount++;
			}
		}
		
		/**
		 * Compares this instance with argument by the number of universal arguments.
		 */
		@Override
		public int compareTo(Posibility compared)
		{			
			return (int) Math.signum(universalArgsCount - compared.universalArgsCount);
		}
	}
	
	/**
	 * Number of columns in this matrix.
	 */
    private static int ARGS_COUNT;
	
    /**
     * stores types of arguments in matrix
     */
    private Class<?>[] parametrTypes;
    
    /**
     * stores all possibilities (pairs argument(s) => return value)
     */
	List<Posibility> matrix = new ArrayList<>();
	
	/**
	 * Creates new matrix with columns of paramTypes.
	 * @param parametrTypes argument types in columns of the matrix
	 */
	public ArgumentScenarioTable(Class<?>[] parametrTypes)
	{
		this.parametrTypes = parametrTypes;
		ARGS_COUNT = parametrTypes.length;
	}
	
	/**
	 * Adds given possibility into matrix.
	 * Possibility must have the number of arguments as the matrix has columns.
	 * No types are checked!
	 * @param pos filled possibility
	 */
	public void addPosibility(Posibility pos)
	{
		if (pos.size() != ARGS_COUNT) throw new IllegalArgumentException("Invalid arguments count!");
		
		// must be called for proper sorting
		pos.align();
		this.matrix.add(pos);
	}
	
	/**
	 * Seeks the matrix for matching possibility described by values of given arguments and return it's return value.
	 * @param args arguments to match in matrix
	 * @return return value of matching possibility
	 * @throws UndefinedPossibilityException Matrix does not contain any row with matching argument values.
	 * @throws IllegalArgumentException Given arguments to match is either null or the array has different
	 * 		   number of values than the matrix has columns.
	 */
	public Invocation /*Object*/ find(Object... args)
	throws UndefinedPossibilityException, IllegalArgumentException
	{
		if (args != null && args.length != this.parametrTypes.length)
		{
			throw new IllegalArgumentException("Invalid arguments count!(" + args.length + " / " + ARGS_COUNT + ")");
		}
		
		Invocation /*Object*/ ret = null;
		boolean found = false;
		
		// find all matching rows in the matrix
		this.filterMatrix(args);

		// storage for all matching rows that have some universal value
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
                ret = this.matrix.get(row).invocationDescription;
                found = true;
                break;
            }
        }
        // if no exact possibility has been found, try to find the less universal
        if (!found && !universalRows.isEmpty())
        {
        	// sort by number of universal arguments
        	Collections.sort(universalRows);
	        ret = universalRows.get(0).invocationDescription;
	        found = true;
        }
        
        // reset possibility markers for next call (cancel filtering)
        this.resetPosibleMarkers();

        if (!found)
        {
        	throw new UndefinedPossibilityException("No valid argument combination has been found.\n" + Arrays.deepToString(args));
        }

        return ret;
	}

	/**
	 * Determines whether the given row contains any universal value.
	 * @param row row to probe
	 * @return true if some universal value is present, otherwise false
	 */
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

	/**
	 * Marks all rows in matrix that do not match given arguments as not valid possibility. 
	 * @param args arguments to match
	 */
	private void filterMatrix(Object... args)
	{
		for (int col = 0; col < ARGS_COUNT; col++)
        {
            for (int row = 0; row < this.matrix.size(); row++)
            {
                if (this.matrix.get(row).posible)
                { // Previous arguments in this possibility matched
                    
                    if (!this.match(this.matrix.get(row).get(col), args[col], this.parametrTypes[col]))
                    { // mark current possibility as not possible so it can be skipped
                        this.matrix.get(row).posible = false;
                    }
                }
                else
                { // line has been abandoned as not possible
                    continue;
                }
            }
        }
	}

	/**
	 * Clears filtering markers in matrix.
	 */
	private void resetPosibleMarkers() {
		for (Posibility pos : this.matrix)
        {
        	pos.posible = true;
        }
	}

	/**
	 * Tries to determine whether two given objects match.
	 * @param posibleObj first object to match
	 * @param actualObj second object to match
	 * @param parametrType type that both objects should have - used in case of Comparable has to be used 
	 * @return true if objects are decided as equal, false otherwise
	 */
	private boolean match(Object posibleObj, Object actualObj, Class<?> parametrType)
	{
		if ((posibleObj == null || actualObj == null) && posibleObj != actualObj)
		{ // only one argument is null
			return false;
		}
		
		if (posibleObj == null && posibleObj == actualObj)
		{ // both values are null
			return true;
		}

		if (posibleObj instanceof TAnyValue)
		{ // no matching necessary
			return true;
		}
		
		if (posibleObj.equals(actualObj))
		{ // objects match by simple equal as defined in Object
			return true;
		}

		if (parametrType.isArray())
		{ // objects should be arrays
			if (Arrays.deepEquals((Object[]) posibleObj, (Object[]) actualObj))
			{ // all array items match by simple equal method inherited from Object
				return true;
			}
		}
		
		///TODO test this
		if (Collection.class.isAssignableFrom(parametrType))
		{ // objects should be come sort of collection
			Collection<?> map1 = (Collection<?>) posibleObj;
			Collection<?> map2 = (Collection<?>) actualObj;
			
			boolean res = CollectionUtils.isEqualCollection(map1, map2);
			if (!res)
			{
				return false;
			}
			return true;
		}
		
		if (Comparable.class.isAssignableFrom(parametrType))
		{ // objects should be instances of Comparable
			@SuppressWarnings("unchecked")
			Comparable<Object> comp1 = (Comparable<Object>) posibleObj;
			if (comp1.compareTo(actualObj) == 0)
			{
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public String toString()
	{
		return this.matrix.toString();
	}
}
