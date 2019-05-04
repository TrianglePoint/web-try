package net.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.action.Action;
import net.action.ActionForward;
import net.action.FrontController;

/**
 * Servlet implementation class BoardFrontController
 */

public class BoardFrontController extends HttpServlet implements FrontController{
	private static final long serialVersionUID = 1L;
       
	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Action action = null;
		ActionForward forward = null;
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		
		if(command.equals("/show.bo")) {
			// Just show the list.
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/board/justShow.html");
		}else if(command.equals("/list.bo")) {
			action = new BoardListAction();
			forward = action.execute(request, response);
		}
		
		if(forward.isRedirect()) {
			// SendRedirect.
			response.sendRedirect(forward.getPath());
		}else {
			// Forward.
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(forward.getPath());
			requestDispatcher.forward(request, response);
		}
	}
  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
}
