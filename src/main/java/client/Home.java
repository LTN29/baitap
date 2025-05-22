package client;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

import client.models.utils.DBUtil;

/**
 * Servlet implementation class Home
 */
@WebServlet({"/home"})
public class Home extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Home() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the RequestDispatcher for the target resource
    	Connection conn=DBUtil.getInstance().getConnect();
    	if (conn != null) {
    	    System.out.println("Kết nối thành công!");
    	} else {
    	    System.out.println("Kết nối thất bại!");
    	}
    	request.getRequestDispatcher("/View/Home.jsp").forward(request, response);
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
