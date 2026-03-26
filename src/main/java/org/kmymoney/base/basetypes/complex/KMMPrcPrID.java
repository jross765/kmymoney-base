package org.kmymoney.base.basetypes.complex;

import java.util.Currency;
import java.util.Objects;

import org.kmymoney.base.basetypes.simple.KMMSecID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * KMyMoney has no IDs for the price objects (neither on the price-pair level
 * nor on the price level).
 * <br>
 * I cannot understand this -- how can you possibly work with hundreds, thousands 
 * or even tens of thousands of prices without properly identifying them?
 * <br>
 * Anyway: this fact is the reason why we here have pseudo-ID for a price-pair object: 
 * The tuple ( from-currency, to-currency ).
 *
 * @see KMMPrcID
 */
public class KMMPrcPrID {
    
    @SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(KMMPrcPrID.class);

    private   static final char SEPARATOR = ':';

    // ---------------------------------------------------------------

    private KMMQualifSecCurrID fromSecCurr;
    private KMMQualifCurrID    toCurr;
    
    // ---------------------------------------------------------------

	public KMMPrcPrID(KMMQualifSecCurrID fromSecCurr, KMMQualifCurrID toCurr) {
		if ( fromSecCurr == null ) {
			throw new IllegalArgumentException("null from-security-currency-ID given");
		}

		if ( toCurr == null ) {
			throw new IllegalArgumentException("null to-currency-ID given");
		}
		
		init();
		
		this.fromSecCurr.set(fromSecCurr);
		this.toCurr.set(toCurr);
	}

	public KMMPrcPrID(KMMQualifSecID fromSec, KMMQualifCurrID toCurr) {
		if ( fromSec == null ) {
			throw new IllegalArgumentException("null from-security-currency-ID given");
		}

		if ( toCurr == null ) {
			throw new IllegalArgumentException("null to-currency-ID given");
		}
		
		init();
		
		this.fromSecCurr.set(fromSec);
		this.toCurr.set(toCurr);
	}

	public KMMPrcPrID(KMMQualifCurrID fromCurr, KMMQualifCurrID toCurr) {
		if ( fromCurr == null ) {
			throw new IllegalArgumentException("null from-security-currency-ID given");
		}

		if ( toCurr == null ) {
			throw new IllegalArgumentException("null to-currency-ID given");
		}
		
		init();
		
		this.fromSecCurr.set(fromCurr);
		this.toCurr.set(toCurr);
	}

	public KMMPrcPrID(KMMSecID fromSec, KMMQualifCurrID toCurr) {
		if ( fromSec == null ) {
			throw new IllegalArgumentException("null from-security-currency-ID given");
		}

		if ( toCurr == null ) {
			throw new IllegalArgumentException("null to-currency-ID given");
		}
		
		init();
		
		KMMQualifSecID qualifID = new KMMQualifSecID(fromSec);
		this.fromSecCurr.set(qualifID);
		this.toCurr.set(toCurr);
	}

	public KMMPrcPrID(Currency fromCurr, KMMQualifCurrID toCurr) {
		if ( fromCurr == null ) {
			throw new IllegalArgumentException("null from-security-currency-ID given");
		}

		if ( toCurr == null ) {
			throw new IllegalArgumentException("null to-currency-ID given");
		}
		
		init();
		
		KMMQualifCurrID qualifID = new KMMQualifCurrID(fromCurr);
		this.fromSecCurr.set(qualifID);
		this.toCurr.set(toCurr);
	}

	public KMMPrcPrID(String fromSecCurr, String toCurr)
			throws InvalidQualifSecCurrIDException {
		if ( fromSecCurr == null ) {
			throw new IllegalArgumentException("null from-security-currency-ID given");
		}

		if ( toCurr == null ) {
			throw new IllegalArgumentException("null to-currency-ID given");
		}
		
		init();
		
		setFromSecCurr(fromSecCurr);
		setToCurr(toCurr);
	}
    
    // ---------------------------------------------------------------
	
	private void init() {
		fromSecCurr = new KMMQualifSecCurrID();
		toCurr      = new KMMQualifCurrID();
	}

	public boolean isSet() {
		return ( fromSecCurr.isSet() && toCurr.isSet() );
	}

    // ---------------------------------------------------------------

    public KMMQualifSecCurrID getFromSecCurr() {
        return fromSecCurr;
    }

    public void setFromSecCurr(KMMQualifSecCurrID fromSecCurr) {
		if ( fromSecCurr == null ) {
			throw new IllegalArgumentException("null from-security-currency-ID given");
		}

        this.fromSecCurr.set(fromSecCurr);
    }

	public void setFromSecCurr(String fromSecCurr) {
		if ( fromSecCurr == null ) {
			throw new IllegalArgumentException("null from-security-currency-ID given");
		}

		if ( fromSecCurr.startsWith(KMMQualifSecCurrID.PREFIX_SECURITY) ) {
			this.fromSecCurr = new KMMQualifSecID(fromSecCurr);
		} else {
			this.fromSecCurr = new KMMQualifCurrID(fromSecCurr);
		}
	}

    public KMMQualifCurrID getToCurr() {
        return toCurr;
    }

    public void setToCurr(KMMQualifCurrID toCurr) {
		if ( toCurr == null ) {
			throw new IllegalArgumentException("null to-currency-ID given");
		}
		
        this.toCurr.set(toCurr);
    }

    public void setToCurr(String toCurr) {
		if ( toCurr == null ) {
			throw new IllegalArgumentException("null to-currency-ID given");
		}
		
        this.toCurr = new KMMQualifCurrID(toCurr);
    }

    // ---------------------------------------------------------------
    
    public void set(KMMPrcPrID prcPrID) {
		if ( prcPrID == null ) {
			throw new IllegalArgumentException("null price-pair-ID given");
		}

		// ::TODO
//		if ( ! prcPrID.isSet() ) {
//			throw new IllegalArgumentException("unset price-pair-ID given");
//		}
		
        setFromSecCurr(prcPrID.getFromSecCurr());
        setToCurr(prcPrID.getToCurr());
    }

    // ---------------------------------------------------------------
    
	@Override
	public int hashCode() {
		return Objects.hash(fromSecCurr, toCurr);
	}

	@Override
	public boolean equals(Object obj) {
		if ( this == obj ) {
			return true;
		}
		if ( !(obj instanceof KMMPrcPrID) ) {
			return false;
		}
		KMMPrcPrID other = (KMMPrcPrID) obj;
		return Objects.equals(fromSecCurr.toString(), other.getFromSecCurr().toString()) && // <-- important: toString()!
			   Objects.equals(toCurr.toString(),      other.getToCurr().toString());        // <-- here optional: toString()!
	}

    // ---------------------------------------------------------------
    
	@Override
	public String toString() {
		return toStringShort();
	}

	public String toStringShort() {
		return fromSecCurr.getCode() + SEPARATOR + toCurr.getCode();
	}

	public String toStringLong() {
		return "KMMPricePairID [fromSecCurr=" + fromSecCurr.toString() + 
				                  ", toCurr=" + toCurr.toString() + "]";
	}

}
