package org.kmymoney.base.basetypes.complex;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Currency;
import java.util.Objects;

import org.kmymoney.base.Const;
import org.kmymoney.base.basetypes.simple.InvalidKMMIDException;
import org.kmymoney.base.basetypes.simple.KMMCurrID;
import org.kmymoney.base.basetypes.simple.KMMIDNotSetException;
import org.kmymoney.base.basetypes.simple.KMMSecID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import xyz.schnorxoborx.base.dateutils.LocalDateHelpers;

/**
 * KMyMoney has no IDs for the price objects (neither on the price-pair level
 * nor on the price level).
 * <br>
 * I cannot understand this -- how can you possibly work with hundreds, thousands 
 * or even tens of thousands of prices without properly identifying them?
 * <br>
 * Anyway: this fact is the reason why we here have pseudo-ID for a price object: 
 * The tuple ( from-currency, to-currency, date ),
 * or, alternatively:
 * The tuple ( (ID of parent price-pair object), date ).
 *
 * @see KMMPrcPrID
 */
public class KMMPrcID {
    
    @SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(KMMPrcID.class);

    protected static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern(Const.STANDARD_DATE_FORMAT);
    private   static final char SEPARATOR = ':';
    
    // -----------------------------------------------------------

    protected String fromSecCurr;
    protected String toCurr;
    protected String dateStr;
    
    // ---------------------------------------------------------------

	public KMMPrcID() {
		init();
		
		this.fromSecCurr = "(unset)";
		this.toCurr = "(unset)";
		this.dateStr = "(unset)";
	}

	public KMMPrcID(String fromSecCurr, String toCurr, String dateStr) {
		init();
		
		this.fromSecCurr = fromSecCurr;
		this.toCurr = toCurr;
		this.dateStr = dateStr;
	}

	public KMMPrcID(KMMQualifSecCurrID fromSecCurr, KMMQualifCurrID toCurr, LocalDate date) {
		init();
		
		this.fromSecCurr = fromSecCurr.getCode();
		this.toCurr = toCurr.getCode();
		this.dateStr = DATE_FORMAT.format(date);
	}
    
	public KMMPrcID(KMMPrcPrID prcPr, LocalDate date) {
		init();
		
		this.fromSecCurr = prcPr.getFromSecCurr().getCode();
		this.toCurr = prcPr.getToCurr().getCode();
		this.dateStr = DATE_FORMAT.format(date);
	}
    
    // ---------------------------------------------------------------
	
	private void init() {
		// ::EMPTY
	}

	public boolean isSet() {
		if ( fromSecCurr.isBlank() ||
			 fromSecCurr.equals("(unset)") ) {
			return false;
		}
		
		if ( toCurr.isBlank() ||
			 toCurr.equals("(unset)") ) {
			return false;
		}
			
		if ( dateStr.isBlank() ||
			 dateStr.equals("(unset)") ) {
			return false;
		}
				
		return true;
	}

	public void reset() {
		fromSecCurr = "(unset)";
		toCurr      = "(unset)";
		dateStr     = "(unset)";
	}

    // ---------------------------------------------------------------

    public String getFromSecCurr() {
        return fromSecCurr;
    }

    public void setFromSecCurr(String fromSecCurr) {
    	if ( fromSecCurr == null ) {
    		throw new IllegalArgumentException("null security-currency-ID given");
    	}
    	
        this.fromSecCurr = fromSecCurr;
    }

    public void setFromSecCurr(KMMQualifSecCurrID fromSecCurr) {
    	if ( fromSecCurr == null ) {
    		throw new IllegalArgumentException("null security-currency-ID given");
    	}
    	
        setFromSecCurr(fromSecCurr.getCode());
    }

    public void setFromSecID(KMMSecID fromSecID) throws KMMIDNotSetException {
    	if ( fromSecID == null ) {
    		throw new IllegalArgumentException("null security-ID given");
    	}
    	
        setFromSecCurr(fromSecID.get());
    }

    public void setFromCurrID(KMMCurrID fromCurrID) throws KMMIDNotSetException {
    	if ( fromCurrID == null ) {
    		throw new IllegalArgumentException("null fromCurrID given");
    	}
    	
        setFromCurr(fromCurrID.get());
    }

    public void setFromCurr(Currency fromCurr) throws KMMIDNotSetException {
    	if ( fromCurr == null ) {
    		throw new IllegalArgumentException("null currency given");
    	}
    	
        setFromSecCurr(fromCurr.getCurrencyCode());
    }

    public void setFromCurr(String fromCurrCode) throws KMMIDNotSetException {
    	if ( fromCurrCode == null ) {
    		throw new IllegalArgumentException("null currency given");
    	}
    	
        setFromCurr(Currency.getInstance(fromCurrCode));
    }

    public String getToCurr() {
        return toCurr;
    }

    public void setToCurr(String toCurr) {
    	if ( toCurr == null ) {
    		throw new IllegalArgumentException("null currency-ID given");
    	}
    	
        this.toCurr = toCurr;
    }

    public void setToCurr(KMMQualifCurrID toCurr) {
    	if ( toCurr == null ) {
    		throw new IllegalArgumentException("null currency-ID given");
    	}
    	
        setToCurr(toCurr.getCode());
    }

    public void setToCurr(KMMCurrID toCurr) throws KMMIDNotSetException {
    	if ( toCurr == null ) {
    		throw new IllegalArgumentException("null currency-ID given");
    	}
    	
        setToCurr(toCurr.get());
    }

    public void setToCurr(Currency toCurr) throws KMMIDNotSetException {
    	if ( toCurr == null ) {
    		throw new IllegalArgumentException("null currency given");
    	}
    	
        setToCurr(toCurr.getCurrencyCode());
    }

//    public void setToCurr(String toCurrCode) throws KMMIDNotSetException {
//    	if ( toCurrCode == null ) {
//    		throw new IllegalArgumentException("null currency given");
//    	}
//    	
//        setFromCurr(Currency.getInstance(toCurrCode));
//    }

    public String getDateStr() {
        return dateStr;
    }

    public LocalDate getDate() {
    	if ( getDateStr() == null )
    		return null;
	
    	return LocalDate.parse(getDateStr());
    }
    
    public void setDateStr(String dateStr) {
    	if ( dateStr == null ) {
    		throw new IllegalArgumentException("null date given");
    	}
    	
        this.dateStr = dateStr;
    }

    public void setDate(LocalDate date) {
    	if ( date == null ) {
    		throw new IllegalArgumentException("null date given");
    	}
    	
    	setDateStr(DATE_FORMAT.format(date));
    }
    
    // ----------------------------

	public void set(KMMPrcID id) {
		setFromSecCurr(id.getFromSecCurr());
		setToCurr(id.getToCurr());
		setDateStr(id.getDateStr());
	}
        
    // ---------------------------------------------------------------
	
	public KMMPrcPrID getPricePairID() {
		return new KMMPrcPrID(getFromSecCurr(), getToCurr());
	}

    // ---------------------------------------------------------------
    
	public static KMMPrcID parse(String str) throws Exception {
		if ( str == null )
			throw new IllegalArgumentException("argument <str> is null");

		if ( str.isBlank() )
			throw new IllegalArgumentException("argument <str> is blank");

		int posSep1 = str.indexOf(SEPARATOR);
		// Plausi ::MAGIC
		if ( posSep1 < 3 || posSep1 >= str.length() - 2 )
			throw new InvalidKMMIDException();

		int posSep2 = str.substring(posSep1 + 1).indexOf(SEPARATOR);
		posSep2 += posSep1 + 1; // sic
		// Plausi ::MAGIC
		if ( posSep2 - posSep1 - 1 != 3 )
			throw new InvalidKMMIDException();

		String fromSecCurrStrLoc = str.substring(0, posSep1).trim();
		String toCurrStrLoc      = str.substring(posSep1 + 1, posSep2).trim();
		String dateStrLoc        = str.substring(posSep2 + 1, str.length()).trim();
		
//		System.err.println("pp1: '" + fromSecCurrStrLoc + "'");
//		System.err.println("pp2: '" + toCurrStrLoc + "'");
//		System.err.println("pp3: '" + dateStrLoc + "'");
		
		KMMQualifSecCurrID fromSecCurrID = new KMMQualifSecCurrID();
		if ( fromSecCurrStrLoc.startsWith(KMMQualifSecCurrID.PREFIX_SECURITY) ) {
			fromSecCurrID.setType(KMMQualifSecCurrID.Type.SECURITY);
		} else {
			fromSecCurrID.setType(KMMQualifSecCurrID.Type.CURRENCY);
		}
		
		fromSecCurrID.setCode(fromSecCurrStrLoc);
		
		KMMQualifCurrID toCurrID = new KMMQualifCurrID(toCurrStrLoc);
		
		LocalDate date = LocalDateHelpers.parseLocalDate(dateStrLoc);
		
		KMMPrcID result = new KMMPrcID(fromSecCurrID, toCurrID, date);

		return result;
	}
    
    // ---------------------------------------------------------------

    @Override
    public int hashCode() {
	return Objects.hash(dateStr, fromSecCurr, toCurr);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (!(obj instanceof KMMPrcID)) {
	    return false;
	}
	KMMPrcID other = (KMMPrcID) obj;
	return Objects.equals(dateStr, other.dateStr) && 
	       Objects.equals(fromSecCurr, other.fromSecCurr) && 
	       Objects.equals(toCurr, other.toCurr);
    }

    // ---------------------------------------------------------------

    @Override
    public String toString() {
	return toStringShort();
    }
        
    public String toStringShort() {
	return fromSecCurr + SEPARATOR + toCurr + SEPARATOR + dateStr;
    }
        
    public String toStringLong() {
	return "KMMPriceID [fromSecCurr=" + fromSecCurr + 
			              ", toCurr=" + toCurr + 
			             ", dateStr=" + dateStr + "]";
    }

}
