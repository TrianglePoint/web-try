package net.board.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.action.Action;
import net.action.ActionForward;
import net.board.db.BoardBean;
import net.board.db.BoardDAO;

public class BoardDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BoardDAO dao = new BoardDAO();
		
		BoardBean bean = new BoardBean();
		
		bean.setNum(Integer.parseInt(request.getParameter("num")));
		bean.setPwd(request.getParameter("pwd"));
		
		ActionForward forward = new ActionForward();
		if(!dao.deletePost(bean)){ // Request delete the post.
			dao.close();
			System.err.println("Failed delete the post - num : " + bean.getNum());
			
			forward.setRedirect(true);
			forward.setPath("view.bo?num=" + bean.getNum());
			
			return forward;
		}
		dao.close();

		forward.setRedirect(true);
		forward.setPath("list.bo");
		
		return forward;
	}

}
