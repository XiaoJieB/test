package com.luobo.repository;

import com.luobo.entity.Score;

public interface ScoreRepository extends BaseRepository<Score,Long> {

	Score findByWorkId(Long workId);
}
