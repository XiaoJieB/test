package com.luobo.service;

import com.luobo.entity.Score;

public interface ScoreService extends BaseService<Score,Long>{

	Score findByWorkId(Long workId);
}
