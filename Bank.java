package My_banking_Application;

import java.sql.*;

//Sub class of MyBank
//this class contains only information which can be accessed only by the  Bank Authorized person 

public class Bank extends MyBank {

	public static void main(String[] args) {

		System.out.println();
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("                                                    <-- MY BANK PAGE -->       ");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------");
		bankLogin();
		bankLoginInfo();

	}

	public static void bankLogin() {

		System.out.print("Enter username -->");
		String b_user = sc.next();

		System.out.print("Enter Password -->");
		String b_pass = sc.next();

		if (b_user.equals(bank_user) && b_pass.equals(bank_pass)) {
			System.out.println("You have been SuccesFully logged");
		} else {
			System.out.println("Enter valid Username & Password");
			bankLogin();
		}

		bankLoginInfo();
	}

	public static void choice_2() {

		System.out.println("                                ");
		System.out.println("ENTER 1 -->  FOR CUSTOMER INFORMATION ");
		System.out.println("ENTER 2 -->  FOR ACCOUNTS INFORMATION ");
		System.out.print("ENTER YOUR CHOICE -->");
		String choice_2 = sc.next();
		switch (choice_2) {

		case "1":
			customerInfo();
			;
			break;

		case "2":
			choice_5();
			break;
		

		default:
			System.out.println("ENTER VALID INPUT");
			System.out.println();
			System.out.print("<--ENTER VALID CHOICE -->");
			System.out.println();
			choice_2();
		}

	}
	// after execution one method another

	public static void bankLoginInfo() {
		String choice_2 = null;

		do {
			System.out.println("                                ");
			System.out.println("ENTER 1 -->  FOR CUSTOMER INFORMATION ");
			System.out.println("ENTER 2 -->  FOR ACCOUNTS INFORMATION ");
			System.out.println("ENTER 3 -->  TO EXIT ");

			System.out.print("ENTER YOUR CHOICE -->");

			choice_2 = sc.next();
			switch (choice_2) {

			case "1":
				customerInfo();
				;
				break;

			case "2":
				choice_5();
				break;
				
			case "3":
				All_Choice();
				break;

			default:
				System.out.println("ENTER VALID INPUT");
				System.out.println();
				System.out.print("<--ENTER VALID CHOICE -->");
				System.out.println();
				bankLoginInfo();
			}
		} while (Integer.parseInt(choice_2) != 3);

	}

	public static void customerInfo() {

		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("                         <-- CUSTOMER INFORMATION --> ");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------");

		try {

			Class.forName(fqcn);
			con = DriverManager.getConnection(url);

			pstm = con.prepareStatement("Select * from mybank.customer_information");

			rs = pstm.executeQuery();
			boolean b = true;
			while (rs.next()) {
				System.out.print(rs.getInt("CUSTOMER_ID") + " ");
				System.out.print(rs.getString("CUSTOMER_NAME") + "  ");
				System.out.print(rs.getString("CUSTOMER_ADRESS") + "  ");
				System.out.print(rs.getString("CUSTOMER_MOBILE") + "  ");
				System.out.print(rs.getString("CUSTOMER_EMAIL") + "  ");
				System.out.print(rs.getString("C_ADHAR_NUMBER") + "  ");
				System.out.print(rs.getString("CUSTOMER_PASS"));
				System.out.println("");
				System.out.println(
						"------------------------------------------------------------------------------------------------------------------------------------");
				b = false;
			}

			if (b == true) {
				System.out.println("Table is empty");
			}

			bankLoginInfo();

		} catch (Exception e) {

			e.getMessage();
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

	public static void choice_5() {
		System.out.println("                                ");
		System.out.println("ENTER 1 -->  TO ADD ACCOUNT");
		System.out.println("ENTER 2 -->  FOR ACCOUNTS INFORMATION ");
		System.out.println("ENTER 3 -->  TO DELETE ACCOUNT ");

		System.out.print("ENTER YOUR CHOICE -->");

		String choice_5 = sc.next();
		switch (choice_5) {

		case "1":
			addAccount();
			;
			break;

		case "2":
			accountsInfo();
			break;
		case "3":
			deleteAccount();
			break;

		default:
			System.out.println("ENTER VALID INPUT");
			System.out.println();
			System.out.print("<--ENTER VALID CHOICE -->");
			System.out.println();
			choice_2();
		}

	}

	public static void accountsInfo() {

		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("                         <-- ACCOUNTS INFORMATION --> ");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------");
		String ac_info = "SELECT * FROM mybank.account_information,mybank.customer_information  "
				+ "where mybank.customer_information.Customer_Id= mybank.account_information.C_Id";
		try {

			Class.forName(fqcn);

			con = DriverManager.getConnection(url);

			pstm = con.prepareStatement(ac_info);

			rs = pstm.executeQuery();

			while (rs.next()) {
				System.out.print(rs.getInt("Account_No") + " ");
				System.out.print(rs.getString("Customer_name") + "  ");
				System.out.print(rs.getString("Account_type") + "  ");
				System.out.print(rs.getInt("Customer_Id") + "  ");
				System.out.print(rs.getDouble("Account_Balance") + "  ");
				System.out.println();
				System.out.println(
						"------------------------------------------------------------------------------------------------------------------------------------");

			}
			bankLoginInfo();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public static void addAccount() {

		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("                         <-- ADD ACCOUNT PAGE  --> ");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------");
		String qry_addAccount = "Insert into mybank.account_information (Account_type,Account_Balance,c_id) values (?,?,?) ";
		String qry_customer_id = "SELECT * FROM mybank.customer_information WHERE  Customer_id= ?";
		try {

			System.out.print("Enter Account Type -->");
			String ac_type = sc.next();

			System.out.println("Enter Account Balance --> ");
			double ac_balance = sc.nextDouble();

			System.out.print("Enter Customer ID -->");
			int c_id = sc.nextInt();

			Class.forName(fqcn);

			con = DriverManager.getConnection(url);

			pstm = con.prepareStatement(qry_customer_id);
			pstm.setInt(1, c_id);
			rs = pstm.executeQuery();

			if (rs.next()) {

			} else {
				System.out.println("Cid is not present");
				System.out.print("Enter customer id -->");
				c_id = sc.nextInt();
			}
			pstm = con.prepareStatement(qry_addAccount);
			pstm.setString(1, ac_type);
			pstm.setDouble(2, ac_balance);
			pstm.setInt(3, c_id);
			pstm.executeUpdate();
			System.out.println("Data Inserted SuccesFully");
			bankLoginInfo();
		} catch (Exception e) {
			e.printStackTrace();
			addAccount();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public static void deleteAccount() {
		// Delete from mybank.customer_information where CUSTOMER_ID=?;

		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("                        <-- DELETE ACCOUNT PAGE  --> ");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------");
		choice_7();

	}

	public static void choice_7() {
		String qry_addAccount = "delete FROM mybank.customer_information where CUSTOMER_id=?";

		try {

			Class.forName(fqcn);

			con = DriverManager.getConnection(url);

			pstm = con.prepareStatement(qry_addAccount);

			System.out.print("ENTER CUSTOMER ID  --> ");
			int acc = sc.nextInt();

			System.out.println("COFIRM U WANT TO DELETE YOUR ACCOUNT PERMENANTLY");
			System.out.println();
			System.out.println("ENTER 1 -->  TO DELETE ACCOUNT ");
			System.out.println("ENTER 2 -->  DON'T WANT TO DELETE ACCOUNT ");

			System.out.print("ENTER YOUR CHOICE -->");

			String choice_6 = sc.next();
			switch (choice_6) {
			case "1":
				pstm.setInt(1, acc);
				pstm.executeUpdate();
				System.out.println("RECORDS HAS BEEN DELETED SUCCESFULLY!!");
				break;

			case "2":
				System.out.print("RECORDS NOT CHANGED!!");
				choice_2();
				break;
			default:
				System.out.println("ENTER VALID INPUT");
				System.out.println();
				System.out.print("<--ENTER VALID CHOICE -->");
				System.out.println();
				choice_7();
			}
			bankLoginInfo();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
