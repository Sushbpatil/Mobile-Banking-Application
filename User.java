package My_banking_Application;

import java.sql.*;
import java.sql.SQLException;

//Sub class of MyBank
//User interaction with Bank Authorized person 

public class User extends MyBank {

	static String name;
	static String choice_4;

	public static void main(String[] args) {
		System.out.println();
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("                                                    <-- USER PAGE -->       ");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------");
		choice_3();
	}

	public static void choice_3() {

		System.out.println("                                ");
		System.out.println("IF YOU ARE NEW USER THEN PLEASE FIRST SIGN UP -->  ");

		System.out.println("ENTER 1 -->  TO SIGN-UP  ");
		System.out.println("ENTER 2 -->  TO LOGIN ");

		System.out.print("ENTER YOUR CHOICE -->");

		String choice_3 = sc.next();
		switch (choice_3) {

		case "1":
			signUp();
			break;

		case "2":
			logiN();
			break;

		default:
			System.out.println("ENTER VALID INPUT");
			System.out.println();
			System.out.print("<--ENTER VALID CHOICE -->");
			System.out.println();
			choice_3();
		}

	}

	public static void signUp() {

		System.out.println();
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("                                                    <-- SIGN-UP PAGE -->       ");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------");
		choiceSignUp();
	}

	public static void choiceSignUp() {

		System.out.println("Enter First Name --> ");
		String fname = sc.next();

		System.out.println("Enter Middle Name --> ");
		String mname = sc.next();

		System.out.println("Enter Last Name --> ");
		String lname = sc.next();

		name = fname + " " + mname + " " + lname;

		System.out.print("Enter adress --> ");
		String add = sc.next();

		System.out.print("Enter MobileNO --> ");
		String mobileno = sc.next();

		try {
			Class.forName(fqcn);
			con = DriverManager.getConnection(url);
			pstm = con.prepareStatement(qry_verify_user);

			pstm.setString(1, mobileno);

			rs = pstm.executeQuery();

			if (rs.next()) {
				System.out.println("Entered Mobile No is Already Exist ");
				System.out.print("Enter Valid Mobile No --> ");
				mobileno = sc.next();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {

			if (rs == null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstm == null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con == null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		char[] ch = mobileno.toCharArray();
		for (int i = 0; i < ch.length; i++) {

			if (mobileno.charAt(0) >= '0' || mobileno.charAt(0) <= '9') {
				if (ch.length != 10) {
					System.out.print("Enter Valid Mobile No --> ");
					mobileno = sc.next();
				}
			} else {
				System.out.print("Enter Valid Mobile No --> ");
				mobileno = sc.next();
			}
		}

		System.out.print("Enter Email --> ");
		String email = sc.next();
		if (email.endsWith("@gmail.com")) {

		} else {
			System.out.print("Enter Valid Email --> ");
			email = sc.next();
		}

		System.out.print("Enter Adhar Number --> ");
		String adhar = sc.next();
		char[] ch1 = adhar.toCharArray();
		for (int i = 0; i < ch1.length; i++) {
			if (adhar.charAt(0) >= '0' || adhar.charAt(0) <= '9') {
				if (ch1.length != 12) {
					System.out.print("Enter Valid Adhar No --> ");
					adhar = sc.next();
				}
			} else {
				System.out.print("Enter Valid Aadhar No --> ");
				adhar = sc.next();
			}
		}

		System.out.print("Enter Password -->");
		String pass = sc.next();
		if (pass.length() <= 8) {
			System.out.print("Enter Strong Password -->");
			pass = sc.next();
		}

		try {

			pstm = con.prepareStatement(qry_insert_info);
			pstm.setString(1, name);
			pstm.setString(2, add);
			pstm.setString(3, mobileno);
			pstm.setString(4, email);
			pstm.setString(5, adhar);
			pstm.setString(6, pass);
			pstm.executeUpdate();
			System.out.println("Data Inserted");

//			login();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs == null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstm == null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con == null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void logiN() {

		System.out.println();
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("                                                    <-- LOGIN PAGE -->       ");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("                                ");

		try {
			Class.forName(fqcn);
			con = DriverManager.getConnection(url);
			System.out.print("Enter Mobile Number -->");
			String i_mobile = sc.next();
			System.out.print("Enter Password -->");
			String i_pass = sc.next();
			pstm = con.prepareStatement(qry_verify_pass);
			pstm.setString(1, i_mobile);
			pstm.setString(2, i_pass);
			rs = pstm.executeQuery();
			boolean b = false;
			if (rs.next()) {
				System.out.println("                                ");
				System.out.println("You have been Succesfully Logged");
				System.out.println("                                ");

				b = true;
			} else {
				System.out.println("Invalid MobileNo or Password ");
				System.out.print("Enter valid mobile number  -->");
				i_mobile = sc.next();
				System.out.print("Enter valid password -->");
				i_pass = sc.next();
				pstm = con.prepareStatement(qry_verify_pass);
				pstm.setString(1, i_mobile);
				pstm.setString(2, i_pass);
				rs = pstm.executeQuery();
				if (rs.next()) {
					System.out.println("                                ");
					System.out.println("You have been Succesfully Logged");
					System.out.println("                                ");
					b = true;
				}
			}
			if (b == false) {
				System.out.println("Please SignUp first");
				main(null);
			}
			rs = pstm.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs == null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstm == null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con == null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		loginINFO();
	}

//	public static void choice_4() {
//
//		System.out.println("                                              ");
//		System.out.println("ENTER 1 -->  TO DEPOSITE AMOUNT  ");
//		System.out.println("ENTER 2 -->  TO BALANCE ENQIRY ");
//		System.out.println("ENTER 3 -->  TO WITHDRAW AMOUNT ");
//		System.out.println("                                              ");
//		System.out.print("ENTER YOUR CHOICE -->");
//		String choice_4 = sc.next();
//		switch (choice_4) {
//		case "1":
//			depositeAmount();
//			break;
//		case "2":
//			balanceEnquiry();
//			break;
//		case "3":
//			withdrawAmount();
//			break;
//
//		default:
//			System.out.println("ENTER VALID INPUT");
//			System.out.println();
//			System.out.print("<--ENTER VALID CHOICE -->");
//			System.out.println();
//			choice_4();
//		}
//	}

	public static void loginINFO() {

		do {
			System.out.println("                                              ");
			System.out.println("ENTER 1 -->  TO DEPOSITE AMOUNT  ");
			System.out.println("ENTER 2 -->  TO BALANCE ENQIRY ");
			System.out.println("ENTER 3 -->  TO WITHDRAW AMOUNT ");
			System.out.println("ENTER 4 -->  TO EXIT");
			System.out.println("                                              ");
			System.out.print("ENTER YOUR CHOICE -->");
			choice_4 = sc.next();
			switch (choice_4) {
			case "1":
				depositeAmount();
				break;
			case "2":
				balanceEnquiry();
				break;
			case "3":
				withdrawAmount();
				break;

			case "4":
				System.out.println("THANK YOU !!!");
				break;

			default:
				System.out.println("ENTER VALID INPUT");
				System.out.println();
				System.out.print("<--ENTER VALID CHOICE -->");
				System.out.println();
				loginINFO();
			}
		} while (Integer.parseInt(choice_4) != 4);

	}

	public static void depositeAmount() {
		System.out.println();
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("                                                    <-- DEPOSITE AMOUNT PAGE -->       ");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("                                ");

		String qry_deposite = "UPDATE mybank.account_information SET Account_balance = Account_balance + ? WHERE account_no = ?";
		String qry_acinfo = "Select Account_balance from mybank.account_information  WHERE account_no = ?";
		System.out.print("Enter Amount to be Deposited --> ");
		double amt = sc.nextDouble();

		System.out.print("Enter Account Number -->");
		int acno = sc.nextInt();

		try {
			Class.forName(fqcn);

			con = DriverManager.getConnection(url);

			pstm = con.prepareStatement(qry_deposite);

			pstm.setDouble(1, amt);

			pstm.setInt(2, acno);

			pstm.executeUpdate();

			pstm = con.prepareStatement(qry_acinfo);

			pstm.setInt(1, acno);

			rs = pstm.executeQuery();

			if (rs.next()) {
				System.out.println(rs.getDouble(1) + "  ");
				System.out.println(" ");
				System.out.println("Amount Added Succesfully !!!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs == null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstm == null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con == null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void balanceEnquiry() {
		System.out.println();
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("                                                <-- BALANCE EQUIRY PAGE -->       ");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("                                ");

		choice_9();
	}

	public static void choice_9() {

		String sql_balance = "SELECT * FROM mybank.account_information INNER JOIN mybank.customer_information ON mybank.account_information.C_Id = mybank.customer_information.Customer_Id   WHERE mybank.customer_information.Customer_mobile = ?";

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url);
			pstm = con.prepareStatement(sql_balance);
			System.out.print("ENER MOBILE NUMBER --> ");
			String mob = sc.next();
			char[] ch = mob.toCharArray();
			for (int i = 0; i < ch.length; i++) {

				if (mob.charAt(0) >= '0' || mob.charAt(0) <= '9') {
					if (ch.length != 10) {
						System.out.print("Enter Valid Mobile No --> ");
						choice_9();
					}
				} else {
					System.out.print("Enter Valid Mobile No --> ");
					choice_9();
				}
			}
			pstm.setString(1, mob);

			rs = pstm.executeQuery();

			if (rs.next()) {
				System.out.print(rs.getInt("Account_no") + " ");
				System.out.print(rs.getString("CUSTOMER_NAME") + "  ");
				System.out.print(rs.getString("Account_balance") + "  ");
				System.out.print(rs.getString("CUSTOMER_MOBILE") + "  ");
				System.out.print(rs.getString("CUSTOMER_EMAIL") + "  ");
				System.out.println("");
				System.out.println(
						"------------------------------------------------------------------------------------------------------------------------------------");
			} else {
				System.out.println("MOBILE NUMBER IS NOT EXIST ");
				System.out.println("<-- ENTER VALID MOBILE NUMBER -->");
				choice_9();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs == null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstm == null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con == null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public static void withdrawAmount() {
		System.out.println();
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("                                                <-- WITHDRAW AMOUNT PAGE -->       ");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("                                ");

		String qry_withdraw = "UPDATE mybank.account_information SET Account_balance = Account_balance - ? WHERE account_no = ?";

		String qry_acinfo = "Select Account_balance from mybank.account_information  WHERE account_no = ?";
		System.out.print("Enter Amount to be WithDraw --> ");
		double amt = sc.nextDouble();

		System.out.print("Enter Account Number -->");
		int acno = sc.nextInt();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url);
			pstm = con.prepareStatement(qry_withdraw);
			pstm.setDouble(1, amt);

			pstm.setInt(2, acno);

			pstm.executeUpdate();

			pstm = con.prepareStatement(qry_acinfo);

			pstm.setInt(1, acno);

			rs = pstm.executeQuery();

			if (rs.next()) {
				System.out.println(rs.getDouble(1));
				System.out.println("                                ");
				System.out.println("Amount WithDraw Succesfully !!!");
				System.out.println("                                ");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs == null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstm == null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con == null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
