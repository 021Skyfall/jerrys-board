package jerry.web.freeBoard.controller;

import jerry.web.freeBoard.dto.FreeBoardDto;
import jerry.web.freeBoard.exception.BusinessException;
import jerry.web.freeBoard.exception.ExceptionCode;
import jerry.web.freeBoard.pagination.Paging;
import jerry.web.freeBoard.service.FreeBoardService;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FreeBoardController {

	@Autowired
	private FreeBoardService freeBoardService;

	@RequestMapping("/freeBoardMain")
    public ModelAndView main(@ModelAttribute Paging paging){
        ModelAndView mav = new ModelAndView();

        Map<String, Object> response = freeBoardService.freeBoardList(paging);

        mav.setViewName("freeBoardMain");
        mav.addObject("response", response);
        return mav;
    }

	@RequestMapping("/searchBoard")
	@ResponseBody
    public Map<String, Object> searchBoard(@RequestBody Paging paging) {
        return freeBoardService.freeBoardList(paging);
    }
	
	@RequestMapping(value = "/freeBoardInsert")
	public String freeBoardInsert(){
		return "freeBoardInsert";
	}

	@RequestMapping("/freeBoardInsertPro")
	@ResponseBody
	public ResponseEntity<Map<String,Object>> freeBoardInsertPro(HttpServletRequest request, @RequestBody FreeBoardDto dto) throws Exception {
		Map<String, Object> response = new HashMap<>();
		
		try {
			int num = freeBoardService.freeBoardInsertPro(dto);
			
			response.put("num", num);
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch(Exception e) {
			throw new BusinessException(ExceptionCode.GENERAL_EXCEPTION);
		}
	}

	@RequestMapping("/freeBoardDetail")
	@ResponseBody
	public ModelAndView freeBoardDetail(HttpServletRequest request){
		FreeBoardDto dto = freeBoardService.getDetailByNum(Integer.parseInt(request.getParameter("num")));
		return new ModelAndView("freeBoardDetail", "freeBoardDto", dto);
	}

	@RequestMapping("/freeBoardModify")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> freeBoardModify(HttpServletRequest request, @RequestBody FreeBoardDto dto){
		try {
			freeBoardService.freeBoardModify(dto);
			
			return new ResponseEntity<Map<String, Object>>(HttpStatus.OK);
		} catch(Exception e) {
			throw new BusinessException(ExceptionCode.GENERAL_EXCEPTION);
		}
	}


	@RequestMapping("/freeBoardDelete")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> FreeBoardDelete(@RequestParam int num)  throws ServletException, IOException {
		try {
			freeBoardService.FreeBoardDelete(num);
			
			return new ResponseEntity<Map<String, Object>>(HttpStatus.OK);
		} catch(Exception e) {
			throw new BusinessException(ExceptionCode.GENERAL_EXCEPTION);
		}
	}
	
	@RequestMapping("/freeBoardDeleteAll")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> FreeBoardDeleteAll(String[] arr) throws Exception {
		try {
			freeBoardService.FreeBoardDeleteAll(arr);
			
			return new ResponseEntity<Map<String, Object>>(HttpStatus.OK);
		} catch(Exception e) {
			throw new BusinessException(ExceptionCode.GENERAL_EXCEPTION);
		}
	}
}