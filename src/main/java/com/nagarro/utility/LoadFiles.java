package com.nagarro.utility;

import java.io.File;
import java.io.FileNotFoundException;

import com.nagarro.validation.DataNotSufficientException;
import com.nagarro.validation.InvalidCategoryException;
import com.nagarro.validation.InvalidHeadingException;

public class LoadFiles implements Runnable {

	boolean exit;
	private File[] files;
	private Thread thread;
	private String path;
	public LoadFiles(String path) {
		Runnable loadFiles=this;
		this.path=path;
		this.exit=false;
		thread=new Thread(loadFiles);
		thread.start();
	}
	
	
	public boolean isExit() {
		return exit;
	}


	public void setExit(boolean exit) {
		this.exit = exit;
	}


	public File[] getFiles() {
		return files;
	}


	public File[] getAllCsv() throws FileNotFoundException {
		
		File file=new File(path);
		
		File files[]=file.listFiles();
		
		return files;
	}
	
	public void interruptThread() {
		thread.interrupt();
	}
	
	private void setFiles() throws FileNotFoundException {
		files=getAllCsv();
	}
	
	@Override
     public void run() {
		
		CSVUtility csu=AppContext.getContext().getBean("csvUtility",CSVUtility.class);
		
		while(!exit) {
				try {
					setFiles();
					System.out.println("inside thread");
				
					
							thread.sleep(2000);
			
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	

}