package com.nagarro.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import com.nagarro.comparator.CompareBy;
import com.nagarro.dao.TshirtDao;
import com.nagarro.entities.Tshirt;
import com.nagarro.validation.DataNotSufficientException;
import com.nagarro.validation.IncompleteDetailsException;
import com.nagarro.validation.InvalidCategoryException;
import com.nagarro.validation.InvalidDetailsException;
import com.nagarro.validation.InvalidHeadingException;
import com.nagarro.validation.NoResultFoundException;

/**
 * Hello world!
 *
 */
public class App implements Runnable {
	
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	private String Input;
	
	private File[] files;
	private String color=null;
	private String size=null;
	private String gender=null;
	private String OPpref=null;
	private char ch='y';
	private Thread thread;
	public App() {
		Runnable loadFiles=this;
		thread=new Thread(loadFiles);
		thread.start();
	}
	private File[] getAllCsv() throws FileNotFoundException {
	
		File file=new File("data");
		
		File files[]=file.listFiles();
		
		return files;
	}

	@Override
	public void run() {
		while(ch!='n') {
			try {
				setFiles();
				thread.sleep(1000);
			} catch (FileNotFoundException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	private void setFiles() throws FileNotFoundException {
		files=getAllCsv();
	}
	
	//main method to start execution
	public void process() {
		
		while(ch!='n') {
			
			try {
				getInput();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				
			}
			try {
				parseInput();
				
				CSVUtility csu=new  CSVUtility();
				//files=getAllCsv();
				//setFiles();
				
				//search from csv files
				
			//	List<Tshirt> tshirts=csu.getTshirtsFromFiles(files,color,size,gender);
			//	displayTshirts(tshirts);
				
				//search from database
				
				//checking for valid gender or size
				TshirtUtility tsu=new TshirtUtility();
				tsu.getGender(gender);
				tsu.getTSize(size);
				
				csu.storeTshirtsInDBFromFiles(files);
				List<Tshirt> ts=AppContext.getContext().getBean("tshirtDao",TshirtDao.class).getTshirts(color,size,gender);
				displayTshirts(ts);
				
				
			} catch (InvalidDetailsException | NoResultFoundException | FileNotFoundException | InvalidHeadingException | DataNotSufficientException e1) {
				System.out.println(e1.getMessage());
			}catch(NoSuchElementException e) {
				System.out.println("Incomplete Deatils");
			}catch (NullPointerException e) {
				System.out.println("Invalid and incomplete details");
				//e.printStackTrace();
			} catch (NumberFormatException e) {
				System.out.println("Provided files are inappropriate");
			}catch(IncompleteDetailsException e) {
				System.out.println(e.getMessage());
			}catch(InvalidCategoryException e) {
				System.out.println(e.getMessage());
			}
			try {
				System.out.println("Do you want to search more Tshirts (y/n)");
				
				color=null;
				size=null;
				gender=null;
				OPpref=null;
				ch = br.readLine().charAt(0);
			} catch (IOException e) {
			
			}catch(StringIndexOutOfBoundsException e) {
				
			}
			
		}
		
		System.out.println("Bye :)");
	}
	
	
	private void displayTshirts(List<Tshirt> tshirts) throws NoResultFoundException {
		
		if(tshirts.size()==0)
			throw new NoResultFoundException("No result found");
		if(OPpref.equalsIgnoreCase("rating")) {
			Collections.sort(tshirts, CompareBy.rating());
		}else {
			if(OPpref.equalsIgnoreCase("price")) {
				Collections.sort(tshirts, CompareBy.price());
			}
		}
		
		System.out.println(TshirtUtility.ID+"\t\t\t"+TshirtUtility.NAME+"\t\t"+TshirtUtility.COLOUR+"\t"+TshirtUtility.GENDER_RECOMMENDATION+"\t"+TshirtUtility.SIZE+"\t"+TshirtUtility.PRICE+"\t"+TshirtUtility.RATING+"\t"+TshirtUtility.AVAILABILITY);
		for(Tshirt t: tshirts)
			System.out.println(t.getId()+"\t"+t.getName()+"\t"+t.getColor()+"\t\t"+t.getGender()+"\t\t"+t.getSize()+"\t"+t.getPrice()+"\t"+t.getRating()+"\t"+t.getAvailability());
	}
	private void parseInput() throws IncompleteDetailsException, InvalidDetailsException{
		StringTokenizer st = new StringTokenizer(Input, " -");
		int tokenCount = st.countTokens();

		for (int i = 0; i < tokenCount; i += 2) {

			setValues(st.nextToken(), st.nextToken());

		}

		if (color == null || size == null || gender == null)
			throw new IncompleteDetailsException("incomplete details");
		
		

	}
	
	private void setValues(String valueName, String Value) throws InvalidDetailsException
			 {

		switch (valueName.toLowerCase()) {
		case "color":
			color = Value;
			break;
		case "size":
			size=Value;
			break;
		case "gender":
			gender = Value;
			break;
		case "outputpreference":
			OPpref=Value;
			break;
		default:
			throw new InvalidDetailsException("Invalid details *enter like this color xxxx size x gender x outputprefernce xxxx");
		}

	}
	
	public void getInput() throws IOException {
		System.out.println("Enter Tshirt deatils");
		Input=br.readLine();
	}
	
    public static void main( String[] args )
    {
    	System.out.println("color,size,gender or outputpreference");
        new App().process();
    }

	
}
