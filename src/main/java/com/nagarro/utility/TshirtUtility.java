package com.nagarro.utility;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nagarro.categories.Availability;
import com.nagarro.categories.Gender;
import com.nagarro.categories.Size;
import com.nagarro.entities.Tshirt;
import com.nagarro.validation.DataNotSufficientException;
import com.nagarro.validation.InvalidCategoryException;
import com.nagarro.validation.InvalidHeadingException;

public class TshirtUtility {

	private String heading;
	private String[] tokenHeading;
	private Map<String,String> tdata;
	
	
	public final static String ID="ID";
	public final static String NAME="NAME";
	public final static String COLOUR="COLOUR";
	public final static String GENDER_RECOMMENDATION="GENDER_RECOMMENDATION";
	public final static String SIZE="SIZE";
	public final static String PRICE="PRICE";
	public final static String RATING="RATING";
	public final static String AVAILABILITY="AVAILABILITY";
	/**
	 * @param raw
	 * @param heading
	 * @throws InvalidHeadingException 
	 */
	public TshirtUtility(String heading) throws InvalidHeadingException {
		super();
		
		this.heading = heading;
		tokenHeading= this.heading.split("\\|");
		tdata=new HashMap<>();
		setHeadInMap();
	}

	/**
	 * 
	 */
	public TshirtUtility() {
		super();
	}

	private void setHeadInMap() throws InvalidHeadingException {
		
		int n=tokenHeading.length;
		
		for(int i=0;i<n;i++) {
		String str=tokenHeading[i];
		switch(str) {
		
		case ID:tdata.put(str, null);
			break;
		case NAME:tdata.put(str, null);
		break;
		case COLOUR:tdata.put(str, null);
		break;
		case GENDER_RECOMMENDATION:tdata.put(str, null);
		break;
		case SIZE:tdata.put(str, null);
		break;
		case PRICE:tdata.put(str, null);
		break;
		case RATING:tdata.put(str, null);
		break;
		case AVAILABILITY:tdata.put(str, null);
		break;
		default: throw new InvalidHeadingException("looks like you are using invalid File");
		}
		}
	}
	public Gender getGender(String gen) throws InvalidCategoryException {
		
		if(gen.equalsIgnoreCase(Gender.M.toString())) return Gender.M;
		
		if(gen.equalsIgnoreCase(Gender.F.toString())) return Gender.F;
		
		if(gen.equalsIgnoreCase(Gender.U.toString())) return Gender.U;
		
		throw new InvalidCategoryException("Invalid Gender **Enter only M, F or U");
		
	}
	
	public Size getTSize(String size) throws InvalidCategoryException {
		
		switch(size.toLowerCase()) {
		case "s": return Size.S;
		case "m": return Size.M;
		case "l": return Size.L;
		case "xl":return Size.XL;
		case "xxl":return Size.XXL;
		default: throw new InvalidCategoryException("Invalid Size **Enter only S,M,L,XL or XXL");
		}		
		
	}
	public Availability getTAvailability(String av) throws InvalidCategoryException {
		
		if(av.equalsIgnoreCase(Availability.Y.toString())) return Availability.Y;
		
		if(av.equalsIgnoreCase(Availability.N.toString())) return Availability.N;
		
		throw new InvalidCategoryException("Invalid Availability **Enter only Y or N");
		
	}
	
	public void setValuesInMap(String raw) throws DataNotSufficientException {
		String[] tokenRaw=raw.split("\\|");
		int n=tokenRaw.length;
		
		for(int i=0;i<n;i++) {
			if(tdata.containsKey(tokenHeading[i])) {
				tdata.put(tokenHeading[i],tokenRaw[i]);
			}else {
				throw new DataNotSufficientException("Value not matched with heading");
			}
		}
	}
	
	public boolean isMatchTshirt(String color,String size,String gender) throws DataNotSufficientException {

		
		if(!tdata.get("COLOUR").equalsIgnoreCase(color)) return false;
		
		if(!tdata.get("SIZE").equalsIgnoreCase(size)) return false;
		
		if(!tdata.get("GENDER_RECOMMENDATION").equalsIgnoreCase(gender)) return false;
		
		if(!tdata.get("AVAILABILITY").equalsIgnoreCase(Availability.Y.toString())) return false;
		
		return true;
	}
	public Tshirt parseTshirt(String raw) throws InvalidCategoryException,NumberFormatException, DataNotSufficientException {
		

		Tshirt ts=new Tshirt();
		
		//throw Exception
		if(tdata.size()!=8)
				throw new DataNotSufficientException("Insufficient Data");
		
		ts.setId(tdata.get(ID));
		ts.setName(tdata.get(NAME));
		ts.setColor(tdata.get(COLOUR));
		ts.setGender(getGender(tdata.get(GENDER_RECOMMENDATION)));
		ts.setSize(getTSize(tdata.get(SIZE)));
		ts.setPrice(Float.parseFloat(tdata.get(PRICE)));
		ts.setRating(Float.parseFloat(tdata.get(RATING)));
		ts.setAvailability(getTAvailability(tdata.get(AVAILABILITY)));
		return ts;
	}
	
	
}
