package org.kmymoney.base.basetypes.complex;

import java.util.Currency;

import org.kmymoney.base.basetypes.simple.KMMCurrID;
import org.kmymoney.base.basetypes.simple.KMMIDNotSetException;
import org.kmymoney.base.basetypes.simple.KMMSecID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KMMQualifCurrID extends KMMQualifSecCurrID {

	private static final Logger LOGGER = LoggerFactory.getLogger(KMMQualifCurrID.class);

	// ---------------------------------------------------------------

	private KMMCurrID currID;

	// ---------------------------------------------------------------

	public KMMQualifCurrID() {
		super();
		
		init();
		
		type = Type.CURRENCY;
	}

	public KMMQualifCurrID(KMMCurrID currID) {
		super(currID);

		init();
	}

	public KMMQualifCurrID(Currency curr) {
		super(Type.CURRENCY, curr.getCurrencyCode());

		init();
		
		setType(Type.CURRENCY);
		try {
			setCurrID(curr.getCurrencyCode());
		} catch (KMMIDNotSetException e) {
			LOGGER.error("KMMQualifCurrID: Currency ID cannot be set from '" + curr + "'");
			throw new IllegalArgumentException("Currency ID cannot be set from '" + curr + "'");
		}
	}

	public KMMQualifCurrID(String currStr) {
		super(Type.CURRENCY, currStr);

		init();
		
		setType(Type.CURRENCY);
		try {
			setCurrID(currStr);
		} catch (KMMIDNotSetException e) {
			LOGGER.error("KMMQualifCurrID: Currency ID cannot be set from '" + currStr + "'");
			throw new IllegalArgumentException("Currency ID cannot be set from '" + currStr + "'");
		}
	}

	public KMMQualifCurrID(KMMQualifSecCurrID secCurrID) {
		super(secCurrID.getType(), secCurrID.getCode());

		if ( secCurrID.getType() != Type.CURRENCY )
			throw new InvalidQualifSecCurrTypeException();

		init();
		
		setType(Type.CURRENCY);
		try {
			setCurrID(code);
		} catch (KMMIDNotSetException e) {
			LOGGER.debug("KMMQualifCurrID: Could not set Currency-ID from '" + code + "'");
			throw new IllegalArgumentException("Currency ID cannot be set from '" + code + "'");
		}
	}

	public KMMQualifCurrID(KMMSecID secID) throws KMMIDNotSetException {
		super(secID);

		init();
	}

	// ---------------------------------------------------------------
	
	private void init() {
		currID = new KMMCurrID();
	}

	// ---------------------------------------------------------------

	@Override
	public void setType(Type type) {
//        if ( type != Type.CURRENCY )
//            throw new InvalidCmdtyCurrIDException();

		super.setType(type);
	}

    // ---------------------------------------------------------------

	public KMMCurrID getCurrID() {
    	if ( type != Type.CURRENCY )
    		throw new InvalidQualifSecCurrTypeException();
	
		return currID;
	}

	public void setCurrID(KMMCurrID currID) {
		if ( type != Type.CURRENCY )
			throw new InvalidQualifSecCurrTypeException();

		if ( currID == null )
			throw new IllegalArgumentException("Argument <currID> is null");

		this.currID = currID;
		this.code   = currID.get().getCurrencyCode();
	}

	public void setCurrID(String currStr) throws KMMIDNotSetException {
		if ( currStr == null )
			throw new IllegalArgumentException("Argument string is null");

		setCurrID(new KMMCurrID(currStr));
	}

    // ---------------------------------------------------------------
    
    public void set(KMMQualifCurrID val) {
    	super.set(val);
    	setCurrID(val.getCurrID());
    }
    
	// ---------------------------------------------------------------

	public static KMMQualifCurrID parse(String str)
			throws InvalidQualifSecCurrIDException {
		if ( str == null )
			throw new IllegalArgumentException("Argument <str> is null");

		if ( str.equals("") )
			throw new IllegalArgumentException("Argument <str> is empty");

		KMMQualifCurrID result = new KMMQualifCurrID();

		int posSep = str.indexOf(SEPARATOR);
		// Plausi ::MAGIC
		if ( posSep <= 3 || posSep >= str.length() - 2 )
			throw new InvalidQualifSecCurrIDException();

		String typeStr = str.substring(0, posSep).trim();
		String currCodeStr = str.substring(posSep + 1, str.length()).trim();

		if ( typeStr.equals(Type.CURRENCY.toString()) ) {
			result.setType(Type.CURRENCY);
			try {
				result.setCurrID(currCodeStr);
			} catch (KMMIDNotSetException e) {
				LOGGER.error("parse: Cannot set KMMCurrID with code '" + currCodeStr + "'");
				throw new InvalidQualifSecCurrIDException();
			}
		} else {
			LOGGER.error("parse: Unknown currency type '" + typeStr + "'");
			throw new InvalidQualifSecCurrTypeException();
		}

		return result;
	}

	// ---------------------------------------------------------------

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currID == null) ? 0 : currID.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		KMMQualifCurrID other = (KMMQualifCurrID) obj;
		if ( type != other.type )
			return false;
		if ( currID == null ) {
			if ( other.currID != null )
				return false;
		} else if ( !currID.equals(other.currID) )
			return false;
		return true;
	}

	// ---------------------------------------------------------------

    @Override
    public String toString() {
    	return toStringShort();
    }
    
	public String toStringShort() {
		if ( type != Type.CURRENCY )
			return "ERROR";

		String result = Type.CURRENCY.toString() + 
						SEPARATOR + 
						currID.toString();

		return result;
	}

	public String toStringLong() {
		if ( type != Type.CURRENCY )
			return "ERROR";

    	return "KMMQualifCurrID [type=" + type + ", curr='" + currID.toString() + "']";
	}

}
