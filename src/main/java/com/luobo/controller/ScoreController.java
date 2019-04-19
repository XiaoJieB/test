package com.luobo.controller;

import com.luobo.entity.BigWork;
import com.luobo.entity.Score;
import com.luobo.service.BigWorkService;
import com.luobo.service.ScoreService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * . Description: Date: 2019/3/22 11:45
 *
 * @author: ws
 * @version: 1.0
 */
@RequestMapping("score")
@Controller
public class ScoreController {

	@Autowired
	ScoreService scoreService;
	@Autowired
	BigWorkService bigWorkService;

	@RequestMapping("/save")
	@ResponseBody
	public Map<String,Object> addScore(Score score)
		throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		BigWork bigWork = bigWorkService.get(score.getBigWorkId());
		if (bigWork.getScore() != null) {
			result.put("msg", "自评分数已提交！");
			result.put("code", "201");
			return result;
		}
		scoreService.save(score);
		Score score1 = scoreService.findByWorkId(bigWork.getId());
		bigWork.setScore(score1);
		bigWorkService.update(bigWork);
		result.put("msg", "分数评价成功！");
		result.put("code", "0");
		return result;
	}
}
