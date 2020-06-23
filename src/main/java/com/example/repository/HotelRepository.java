package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Hotel;

/**
 * ホテル情報を操作するレポジトリクラス.
 * 
 * @author hikaru.tsuda
 */
@Repository
public class HotelRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Hotel> HOTEL_ROW_MAPPER = (rs, i) -> {
		Hotel hotel = new Hotel();
		hotel.setId(rs.getInt("id"));
		hotel.setAreaName(rs.getString("area_name"));
		hotel.setHotelName(rs.getString("hotel_name"));
		hotel.setAddress(rs.getString("address"));
		hotel.setNearestStation(rs.getString("nearest_station"));
		hotel.setPrice(rs.getInt("price"));
		hotel.setParking(rs.getString("parking"));
		return hotel;
	};

	/**
	 * 受け取った価格以下のホテルを検索するメソッド.
	 * 
	 * @param price 価格
	 * @return ホテル情報一覧(価格降順)
	 */
	public List<Hotel> findByPrice(Integer price) {
		String sql = "SELECT area_name, hotel_name, address, nearest_station, price, parking FROM hotels WHERE price <= :price ORDER BY price;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("price", price);
		List<Hotel> hotelList = template.query(sql, param, HOTEL_ROW_MAPPER);

		return hotelList;
	}

	/**
	 * 全件検索メソッド.
	 * 
	 * @return 全ホテル情報一覧(値段降順)
	 */
	public List<Hotel> findAll() {
		String sql = "SELECT area_name, hotel_name, address, nearest_station, price, parking FROM hotels ORDER BY price;";
		List<Hotel> hotelList = template.query(sql, HOTEL_ROW_MAPPER);
		return hotelList;
	}

}
