package com.opphack.sevaikarangal.blooddonor.servlet;
import java.io.IOException;
import javax.servlet.http.*;


//From sample GAE web app not used currently in this app

@SuppressWarnings("serial")
public class BloodDonorServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
	}
}
