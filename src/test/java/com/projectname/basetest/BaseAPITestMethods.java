package com.projectname.basetest;



public interface BaseAPITestMethods {	

	/**
	 * Publisher
	 * @param testcaseid
	 */
	public void verifyCheckWhetherAbleToCreateThePublisher(String testcaseid);	
	public void verifyCheckWhetherAbleToReadThePublisher(String testcaseid);
	public void verifyCheckWhetherAbleToUpdateThePublisher(String testcaseid);
	public void verifyCheckWhetherAbleToDeleteThePublisher(String testcaseid);
	
	
	/**
	 * Journal class
	 * @param testcaseid
	 */
	public void verifyCheckWhetherAbleToCreateJournalClass(String testcaseid);
	public void verifyCheckWhetherAbleToReadJournalClass(String testcaseid);
	public void verifyCheckWhetherAbleToUpdateJournalClass(String testcaseid);
	public void verifyCheckWhetherAbleToDeleteJournalClass(String testcaseid);
	
	/**
	 * Journal subclass
	 * @param testcaseid
	 */
	public void verifyCheckWhetherAbleToCreateJournalSubClass(String testcaseid);
	public void verifyCheckWhetherAbleToReadJournalSubClass(String testcaseid);
	

}
