package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Hotel;
import com.example.repository.HotelRepository;

/**
 * ホテル情報を操作するサービスクラス.
 * 
 * @author hikaru.tsuda
 *
 */
@Service
@Transactional
public class HotelService {

	@Autowired
	private HotelRepository repository;

	/**
	 * 受け取った価格より安いホテルを返すメソッド　(未入力の場合は全件検索).
	 * 
	 * @param price 価格
	 * @return ホテル情報一覧
	 */
	public List<Hotel> findByPrice(String price) {
		if (price == "") {
			List<Hotel> hotelList = repository.findAll();
			return hotelList;
		} else {
			List<Hotel> hotelList = repository.findByPrice(Integer.parseInt(price));
			return hotelList;
		}
	}
}