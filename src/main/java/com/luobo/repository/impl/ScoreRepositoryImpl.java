package com.luobo.repository.impl;

import com.luobo.entity.Score;
import com.luobo.repository.ScoreRepository;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by ws
 * On 2/2/2017.2:30 PM
 */
@Repository
public class ScoreRepositoryImpl extends BaseRepositoryImpl<Score,Long> implements ScoreRepository {

	@Override
	public Score findByWorkId(Long workId) {
		String hql = "from Score s where s.bigWorkId = :workId";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("workId",workId);
		List<Score> scores = query.list();
		if (scores != null && scores.size() != 0) {
			return scores.get(0);
		}
		return null;
	}
}