package PPS_Project.web.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import PPS_Project.DAO.PPS_Price_DAO;
import PPS_Project.DAO.Transactions_DAO;
import PPS_Project.DAO.User_DAO;
import PPS_Project.bean.PPS_Price;
import PPS_Project.bean.Transactions;
import PPS_Project.bean.User;

/**
 * Servlet implementation class Buy_and_Sell_Servlet
 */
@WebServlet("/Buy_and_Sell_Servlet")
public class Buy_and_Sell_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private User_DAO userDAO;
	private Transactions_DAO transactionDAO;
	private PPS_Price_DAO PPS_PriceDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Buy_and_Sell_Servlet() {
        super();
        // TODO Auto-generated constructor stub
        userDAO = new User_DAO();
		transactionDAO = new Transactions_DAO();
		PPS_PriceDAO = new PPS_Price_DAO();
    }
    
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getServletPath();
        System.out.println(action);
        try {
            switch (action) {
            case "/buy":
                System.out.println("The action is: login");
                buyPPS(request, response);
                break;
            case "/sell":
                System.out.println("The action is: withdraw");
                sellPPS(request, response);
                break;
            default:
                System.out.println("Not sure which action, we will treat it as the list action");
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
	}
	
	private void buyPPS(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession(false);
		if (session == null) {
			// User not signed in. Or session expired.
			// Forward to user login page.
			request.getRequestDispatcher("user-loginForm.jsp").forward(request, response);
			return;
		}
		
		User user = (User) session.getAttribute("user");
		if (user == null) {
			// User not signed in. Or session expired.
			// Forward to user login page.
			request.getRequestDispatcher("user-loginForm.jsp").forward(request, response);
			return;
		}
		
		try {
			user = userDAO.selectUser(user.getUser_email());
			User root = userDAO.selectUser("root");
			
			int root_PPS_balance = root.getPPS_balance();
			
			int PPS_Amount = Integer.parseInt(request.getParameter("PPS-to-buy-amount"));
			
			PPS_Price PPS_price = PPS_PriceDAO.getLastest_PPS_price();
			
			double currentPPS_price = PPS_price.getPrice();
			
			// Calculate the the price of the pps amount 
			//double priceOfPPSAmount = do the calucalation here;
			
		
			
			return;
		} catch (Exception e) {
			System.out.println(e);
			request.setAttribute("errorMessage", "Error Occurred. Could NOT update the balance.");
			request.getRequestDispatcher("user-buyPage.jsp").forward(request, response);
			return;
		}
		
	}
	
	private void sellPPS(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession(false);
		if (session == null) {
			// User not signed in. Or session expired.
			// Forward to user login page.
			request.getRequestDispatcher("user-loginForm.jsp").forward(request, response);
			return;
		}
		
		User user = (User) session.getAttribute("user");
		if (user == null) {
			// User not signed in. Or session expired.
			// Forward to user login page.
			request.getRequestDispatcher("user-loginForm.jsp").forward(request, response);
			return;
		}
		
		try {
			user = userDAO.selectUser(user.getUser_email());
			User root = userDAO.selectUser("root");
			
			int root_PPS_balance = root.getPPS_balance();
			
			int PPS_Amount = Integer.parseInt(request.getParameter("PPS-to-sell-amount"));
			
			PPS_Price PPS_price = PPS_PriceDAO.getLastest_PPS_price();
			
			double currentPPS_price = PPS_price.getPrice();
			
			// Calculate the the price of the pps amount 
			//double priceOfPPSAmount = do the calucalation here;///
			
		
			
			return;
		} catch (Exception e) {
			System.out.println(e);
			request.setAttribute("errorMessage", "Error Occurred. Could NOT update the balance.");
			request.getRequestDispatcher("user-sellPage.jsp").forward(request, response);
			return;
		}
		
	}

	

}
