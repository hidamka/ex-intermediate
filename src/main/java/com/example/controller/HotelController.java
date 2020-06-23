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

	/**
	 * 検索画面にフォワード.
	 * 
	 * @return 検索画面
	 */
	@RequestMapping("")
	public String index(Model model) {
		return "hotels/hotel-seach";
	}

	/**
	 * 価格を受け取り、検索結果を格納して検索画面にフォワード.
	 * 
	 * @param price 価格
	 * @param model ホテル情報一覧
	 * @return 検索画面
	 */
	@RequestMapping("/seach")
	public String seach(String price, Model model) {
		if (price != "") {
			if (Integer.parseInt(price) > 500000 || Integer.parseInt(price) < 5000) {
				model.addAttribute("error", "5,000円以上500,000円以下で入力してください");
				return "hotels/hotel-seach";
			}
		}
		List<Hotel> hotelList = service.findByPrice(price);
		model.addAttribute("hotelList", hotelList);

		return "hotels/hotel-seach";
	}
}
