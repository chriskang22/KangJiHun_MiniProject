package com.kang.subwayAlarm.board;

import java.util.List;

public interface BoardMapper {
	public abstract int getAllBoardCount();
	
	public abstract int regBoardWrite(Board b);
		
	public abstract int getSearchBoardCount(BoardSelector sSel);
	
	public abstract List<Board> getBoard(BoardSelector sSel);
	
	public abstract Board boardDetail(int no);
	
	public abstract int boardGoodCount(Board b);
	
	public abstract int boardGoodCountReset(Board b);
	
	public abstract int boardDelete(Board b);
	
	public abstract int boardViews(int b_no);
	
	public abstract int boardUpdate(Board b);
	
	public abstract int getAllReplyCount();
	
	public abstract int regReply(Reply ry);
	
	public abstract List<Reply> replyList(int r_b_no);
	
	public abstract int replyDelete(Reply ry);
}
