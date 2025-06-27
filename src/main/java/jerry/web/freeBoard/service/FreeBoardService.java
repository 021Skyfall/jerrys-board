package jerry.web.freeBoard.service;

import jerry.web.freeBoard.dto.FreeBoardDto;
import jerry.web.freeBoard.pagination.Paging;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FreeBoardService {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public Map<String, Object> freeBoardList(Paging paging) {
        Map<String, Object> response = new HashMap<>();

        int totalRowCount = sqlSessionTemplate.selectOne("getBoardCount", paging);
        paging.setTotalRowCount(totalRowCount);

        paging.pageSetting();

        List<FreeBoardDto> list = sqlSessionTemplate.selectList("freeBoardGetList", paging);

        response.put("list", list);
        response.put("paging", paging);

        return response;
	}

	public int freeBoardInsertPro(FreeBoardDto dto){
		sqlSessionTemplate.insert("freeBoardInsertPro",dto);
		return dto.getNum();
	}

	public FreeBoardDto getDetailByNum(int num){
		return sqlSessionTemplate.selectOne("freeBoardDetailByNum", num);
	}

	public int getNewNum(){
		return sqlSessionTemplate.selectOne("freeBoardNewNum");
	}

	public void freeBoardModify(FreeBoardDto dto){
		sqlSessionTemplate.update("freeBoardModify", dto);
	}

	public void FreeBoardDelete (int num) {
		sqlSessionTemplate.delete("freeBoardDelete", num);

	}
	
	public void FreeBoardDeleteAll (String[] arr) {
		List<Integer> deleteList = 
				Arrays.stream(arr).mapToInt(e -> Integer.parseInt(e)).boxed().collect(Collectors.toList());
		
		sqlSessionTemplate.delete("freeBoardDeleteAll", deleteList);
	}

}
