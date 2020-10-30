package com.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.domain.BoardVO;
import com.board.domain.Page;
import com.board.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Inject
	BoardService service;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model) throws Exception {
		List<BoardVO> list = null;
		list = service.list();
		
		model.addAttribute("list", list);
		
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public void write() throws Exception {
		
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(BoardVO vo) throws Exception {
		
		service.write(vo);
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public void view(@RequestParam("bno") int bno, Model model) throws Exception {
		BoardVO vo = service.view(bno);
		
		model.addAttribute("view", vo);

	}
	
	// 게시물 수정
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modify(@RequestParam("bno") int bno, Model model) throws Exception {
		BoardVO vo = service.view(bno);
		
		model.addAttribute("view", vo);

	}
	
	// 게시물 수정
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(BoardVO vo) throws Exception {
		service.modify(vo);
		
		return "redirect:/board/view?bno=" + vo.getBno();

	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam("bno") int bno) throws Exception {
		service.delete(bno);
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	public void listpage(Model model, @RequestParam("num") int num) throws Exception {
		Page page = new Page();
		
		page.setNum(num);
		page.setCount(service.count());  

		List list = null; 
		list = service.listPage(page.getDisplayPost(), page.getPostNum());

		model.addAttribute("list", list);   
		
		/*
		 * model.addAttribute("pageNum", page.getPageNum());
		 * 
		 * model.addAttribute("startPageNum", page.getStartPageNum());
		 * model.addAttribute("endPageNum", page.getEndPageNum());
		 * 
		 * model.addAttribute("prev", page.getPrev()); model.addAttribute("next",
		 * page.getNext());
		 */
		
		model.addAttribute("page", page);

		model.addAttribute("select", num);

		/*
		 * int count = service.count();
		 * 
		 * int postNum = 10;
		 * 
		 * int pageNum = (int)Math.ceil((double)count/postNum);
		 * 
		 * int displayPost = (num - 1) * postNum;
		 * 
		 * // 한번에 표시할 페이징 번호의 갯수 int pageNum_cnt = 10;
		 * 
		 * // 표시되는 페이지 번호 중 마지막 번호 int endPageNum = (int)(Math.ceil((double)num /
		 * (double)pageNum_cnt) * pageNum_cnt);
		 * 
		 * // 표시되는 페이지 번호 중 첫번째 번호 int startPageNum = endPageNum - (pageNum_cnt - 1);
		 * 
		 * // 마지막 번호 재계산 int endPageNum_tmp = (int)(Math.ceil((double)count /
		 * (double)pageNum_cnt));
		 * 
		 * if(endPageNum > endPageNum_tmp) { endPageNum = endPageNum_tmp; }
		 * 
		 * boolean prev = startPageNum == 1 ? false : true; boolean next = endPageNum *
		 * pageNum_cnt >= count ? false : true;
		 * 
		 * List list = null; list = service.listPage(displayPost, postNum);
		 * 
		 * 
		 * model.addAttribute("list", list); model.addAttribute("pageNum", pageNum);
		 * 
		 * // 시작 및 끝 번호 model.addAttribute("startPageNum", startPageNum);
		 * model.addAttribute("endPageNum", endPageNum);
		 * 
		 * // 이전 및 다음 model.addAttribute("prev", prev); model.addAttribute("next",
		 * next);
		 * 
		 * // 현재 페이지 model.addAttribute("select", num);
		 */
	}
}
