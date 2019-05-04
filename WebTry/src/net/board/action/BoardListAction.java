package net.board.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.action.Action;
import net.action.ActionForward;
import net.board.db.BoardBean;
import net.board.db.BoardDAO;

public class BoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BoardDAO dao = new BoardDAO();
		
		List<BoardBean> beans = null;
		
		beans = dao.getList();
		dao.close();
		if(beans == null) {
			System.err.println("Failed get the board list");
			return null;
		}
		
		request.setAttribute("boardBeans", beans);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/board/boardList.jsp");
		
		return forward;
	}

}
