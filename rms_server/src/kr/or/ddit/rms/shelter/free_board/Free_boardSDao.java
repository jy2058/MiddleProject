package kr.or.ddit.rms.shelter.free_board;

import java.util.List;

import kr.or.ddit.rms.vo.BlacklistVO;
import kr.or.ddit.rms.vo.Board_detailVO;

public interface Free_boardSDao {
	List<Board_detailVO> getBoard_detailAll();
	List<Board_detailVO> getSearchBoard_detail(Board_detailVO vo);
	List<Board_detailVO> getFree_detailTextSearch(Board_detailVO vo);
	int insertBoard_detail(Board_detailVO vo);
	int updateBoard_detail(Board_detailVO vo);
	int deleteBoard_detail(Board_detailVO vo);
}
