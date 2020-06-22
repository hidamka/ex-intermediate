package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Team;
import com.example.service.TeamService;

/**
 * 野球チームを操作するコントローラー.
 * 
 * @author hikaru.tsuda
 *
 */
@Controller
@RequestMapping("team")
public class TeamController {
	
	@Autowired
	private TeamService service;
	
	/**
	 * チーム一覧画面にフォワード.
	 * 
	 * @return チーム一覧画面
	 */
	@RequestMapping("")
	public String index(Model model) {
		List<Team> teamList = service.findAll();
		model.addAttribute("teamList", teamList);
		return "baseball-team/baseball-teams";
	}
	
	/**
	 * リクエストスコープにチーム情報を格納してチーム詳細画面に遷移.
	 * 
	 * @param ID
	 * @return チーム情報
	 */
	@RequestMapping("/showDetail")
	public String showDetail(Integer id,Model model) {
		Team team = service.load(id);
		model.addAttribute("team", team);
		return "baseball-team/baseball-team";
	}

}
