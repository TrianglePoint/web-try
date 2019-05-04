package net.board.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.action.Action;
import net.action.ActionForward;
import net.board.db.BoardBean;
import net.board.db.BoardDAO;

public class BoardWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BoardDAO dao = new BoardDAO();
		
		BoardBean bean = new BoardBean();
		
		request.setCharacterEncoding("UTF-8");
		
		bean.setRead_count(0);
		bean.setText(request.getParameter("text"));
		bean.setTitle(request.getParameter("title"));
		bean.setWrite_date(new java.sql.Date(System.currentTimeMillis()));
		bean.setWriter(request.getParameter("writer"));
		
		if(!dao.insertPost(bean)){ // Request posting.
			dao.close();
			System.err.println("Failed posting");
			return null;
		}
		dao.close();
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("list.bo");
		
		return forward;
	}

}
