package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Team;
import com.example.repository.TeamRepository;

/**
 * チームを操作するサービスクラス.
 * 
 * @author hikaru.tsuda
 *
 */
@Service
@Transactional
public class TeamService {

	@Autowired
	private TeamRepository repository;

	/**
	 * リポジトリクラスのfindAll()を呼び出して全件検索.
	 * 
	 * @return チーム一覧
	 */
	public List<Team> findAll() {
		return repository.findAll();
	}

	/**
	 * リポジトリクラスのload()を呼び出して1件検索.
	 * 
	 * @param ID
	 * @return チーム情報
	 */
	public Team load(Integer id) {
		return repository.load(id);
	}

}
