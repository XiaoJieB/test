package com.luobo.service.impl;

import com.luobo.entity.Score;
import com.luobo.repository.BaseRepository;
import com.luobo.repository.ScoreRepository;
import com.luobo.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * . Description: Date: 2019/3/22 11:50
 *
 * @author: ws
 * @version: 1.0
 */
@Service
public class ScoreServiceImpl extends BaseServiceImpl<Score,Long> implements ScoreService {

	@Autowired
	private ScoreRepository repository;

	@Override
	public BaseRepository<Score, Long> getDao() {
		return repository;
	}

	@Override
	public Score findByWorkId(Long workId) {
		return repository.findByWorkId(workId);
	}
}
