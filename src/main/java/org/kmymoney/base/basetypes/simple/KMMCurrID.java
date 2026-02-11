package org.kmymoney.base.basetypes.simple;

import java.util.Currency;
import java.util.Locale;

import org.kmymoney.base.basetypes.complex.InvalidQualifSecCurrTypeException;
import org.kmymoney.base.basetypes.complex.KMMQualifSecCurrID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * (Almost) trivial wrapper class for @java.util.Currency,
 * defined for symmetry / consistency.
 */
public class KMMCurrID /* NOT POSSIBLE: extends Currency */ {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(KMMCurrID.class);

	// ---------------------------------------------------------------

	private Currency curr;

	// ---------------------------------------------------------------

	public KMMCurrID() {
		super();
		
		init();
	}

	public KMMCurrID(Currency curr) {
		init();
		
		set(curr);
	}

	public KMMCurrID(String iso4217CurrCode) {
		init();
		
		set(iso4217CurrCode);
	}

	public KMMCurrID(KMMQualifSecCurrID secCurrID)
			{
		if ( secCurrID.getType() != KMMQualifSecCurrID.Type.CURRENCY )
			throw new InvalidQualifSecCurrTypeException();

		init();
		
		set(secCurrID.getCode());
	}

	// ---------------------------------------------------------------
	
	private void init() {
		curr = Currency.getInstance(Locale.getDefault());
	}

    // ---------------------------------------------------------------

	public Currency get() {
		return curr;
	}

	public void set(Currency curr) {
		if ( curr == null )
			throw new IllegalArgumentException("Argument currency is null");

		this.curr = curr;
	}

	public void set(String iso4217CurrCode) {
		if ( iso4217CurrCode == null )
			throw new IllegalArgumentException("Argument string is null");

		set(Currency.getInstance(iso4217CurrCode));
	}

    // ----------------------------
    
    public void set(KMMCurrID val) {
    	set(val.get());
    }
    
	// ---------------------------------------------------------------
    
    public boolean isSet() {
    	return ! curr.toString().equals("");
    }

	// ---------------------------------------------------------------

	@Override
	public int hashCode() {
		return curr.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		KMMCurrID other = (KMMCurrID) obj;
		if ( curr == null ) {
			if ( other.curr != null )
				return false;
		} else if ( !curr.equals(other.curr) )
			return false;
		return true;
	}

	// ---------------------------------------------------------------

    @Override
    public String toString() {
    	return toStringShort();
    }
    
	public String toStringShort() {
		return curr.getCurrencyCode();
	}

	public String toStringLong() {
    	return "KMMCurrID [curr=" + curr.toString() + "]";
	}

}
