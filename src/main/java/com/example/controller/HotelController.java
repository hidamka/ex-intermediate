package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Hotel;
import com.example.service.HotelService;

/**
 * ホテル情報を操作するコントローラークラス.
 * 
 * @author hikaru.tsuda
 *
 */
@Controller
@RequestMapping("/hotel")
public class HotelController {
	
	@Autowired
	private HotelService service;
	
	@RequestMapping("")
	public String index() {
		return "hotels/hotel-seach";
	}
	
	@RequestMapping("/seach")
	public String seach(String price, Model model) {
		if(price == null) {
			List<Hotel> hotelList =	service.findAll();
			model.addAttribute("hotelList", hotelList);
		}else {
			List<Hotel> hotelList =	service.findByPrice(Integer.parseInt(price));
			model.addAttribute("hotelList", hotelList);
		}
		return "redirect:/hotel";
	}
}
