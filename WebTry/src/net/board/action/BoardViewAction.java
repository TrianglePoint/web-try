package net.board.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.action.Action;
import net.action.ActionForward;
import net.board.db.BoardBean;
import net.board.db.BoardDAO;

public class BoardViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BoardDAO dao = new BoardDAO();	
		
		BoardBean bean = new BoardBean();
		
		bean.setNum(Integer.parseInt(request.getParameter("num")));
		
		if(!dao.readcountUp(bean)) { // Request that increase the read_count of post.
			dao.close();
			System.err.println("Failed increase the read_count");
			return null;
		}
		
		bean = dao.getPost(bean); // Request get the post.
		dao.close();
		if(bean == null){
			System.err.println("Failed get the post");
			return null;
		}
		
		request.setAttribute("boardBean", bean);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/jsp/board/boardView.jsp");
		
		return forward;
	}

}
