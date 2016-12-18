package com.redbus.utilities;
	
	@SuppressWarnings("serial")
	public class TestFailedException extends Throwable	
	{
		
		String message="Test Failed";
		public TestFailedException(String message){
			this.message=message;
		}
		public String getMessage(){
			return message;
		}
	}

