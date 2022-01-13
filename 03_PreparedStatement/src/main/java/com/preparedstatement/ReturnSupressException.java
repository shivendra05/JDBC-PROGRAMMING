package com.preparedstatement;

public class ReturnSupressException {

	public static void main(String[] args) {

		System.out.println("Execution starts");
		SupressException();
		System.out.println("Execution Ends");
		SupressException();
		System.out.println("Execution Ends");
	}

	public static void SupressException() {
		try{
			System.out.println("In Try");
			System.out.println(10/0);
		}catch(NullPointerException e){

		System.out.println("In Catch");
		}
		finally{
			System.out.println("Exception is going to be supressed");
			return;
		}
	}
	
	public static void SupressException1() {
		try{
			System.out.println("In Try");
			System.out.println(10/0);
		}catch(NullPointerException e){

		System.out.println("In Catch");
		}
		finally{
			System.out.println("Exception is going to be supressed");
			return;
		}
	}
}

