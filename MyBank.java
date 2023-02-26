package My_banking_Application;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

//MyBank class is the super class of the other two classes
public class MyBank {

	static String fqcn = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306?user=root&password=Sushant@123";
	static Connection con = null;
	static String insert_cust = "insert into mybank.customer_information (CUSTOMER_NAME,CUSTOMER_ADRESS,CUSTOMER_MOBILE,CUSTOMER_EMAIL,C_ADHAR_NUMBER,CUSTOMER_PASS) values (?,?,?,?,?,?) ";
	static String insert_acc = "insert into  mybank.account_information values(?,?,?,?,?)";
	static String insert_with = "insert into bank.transaction values(?,?,?)";
	static String qry_insert_info = "insert into mybank.customer_information (CUSTOMER_NAME,CUSTOMER_ADRESS,CUSTOMER_MOBILE,CUSTOMER_EMAIL,C_ADHAR_NUMBER,CUSTOMER_PASS) values (?,?,?,?,?,?) ";
	static String qry_verify_user = "SELECT * FROM mybank.customer_information WHERE  CUSTOMER_MOBILE= ?";
	static String qry_verify_pass = "SELECT * FROM mybank.customer_information WHERE CUSTOMER_MOBILE=? AND CUSTOMER_PASS=?";

	static String bank_user = "MyBank2022";
	static String bank_pass = "MyBank@2022";

	static PreparedStatement pstm = null;
	static ResultSet rs = null;
	static Scanner sc = new Scanner(System.in);

	// To execute some task before main method but only once
	static {
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("                                                   WELCOME TO MY BANK   ");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------");
	}

	public static void main(String[] args) {

		System.out.println("                                                    <-- MAIN PAGE -->       ");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------");
		choice_1();
		All_Choice();

	}

	public static void choice_1() {

		System.out.println("                                ");
		System.out.println("ENTER 1 --> IF YOU ARE BANK AUTHORISED PERSON");
		System.out.println("ENTER 2 --> IF YOU ARE USER ");

		System.out.print("ENTER YOUR CHOICE -->");

		String choice_1 = sc.next();

		switch (choice_1) {

		case "1":
			Bank.main(null);
			break;

		case "2":
			User.main(null);
			break;

		default:
			System.out.println("ENTER VALID INPUT");
			System.out.println();
			System.out.print("<--ENTER VALID CHOICE -->");
			System.out.println();
			choice_1();
		}
	}

	public static void All_Choice() {
		String choice_1 = null;
		do {
			System.out.println("                                ");
			System.out.println("ENTER 1 --> MAIN MENU ");
			System.out.println("ENTER 2 --> MY BANK PAGE");
			System.out.println("ENTER 3 --> USER PAGE ");
			System.out.println("ENTER 4 --> TO EXIT");
			System.out.print("ENTER YOUR CHOICE -->");

			choice_1 = sc.next();

			switch (choice_1) {

			case "1":
				MyBank.main(null);
				break;

			case "2":
				Bank.main(null);
				break;
			case "3":
				User.main(null);
				break;
			case "4":
				System.out.println(
						"------------------------------------------------------------------------------------------------------------------------------------");
				System.out.println("                                                   THANK YOU    ");
				System.out.println("                                                HAVE A GREAT DAY    ");

				System.out.println(
						"------------------------------------------------------------------------------------------------------------------------------------");

				break;
			default:
				System.out.println("ENTER VALID INPUT");
				System.out.println();
				System.out.print("<--ENTER VALID CHOICE -->");
				System.out.println();
				All_Choice();
			}
		} while (Integer.parseInt(choice_1) != 4);
	}

}
