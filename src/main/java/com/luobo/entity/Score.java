package com.luobo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * . Description: Date: 2019/3/11 10:38
 *
 * @author: ws
 * @version: 1.0
 */
@Entity
@Table(name = "score")
public class Score {
	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "created")
	private Long created = System.currentTimeMillis();

	private Double selfScore;

	private String selfScoreStr;//tag:xx,xx,xx,xx

	private Double selfRatio;

	private Double mutualScore;

	private String mutualScoreStr;

	private Double mutualRatio;

	private Double teacherScore;

	private String teacherScoreStr;

	private Double teacherRatio;

	private Double totalScore;

	private String totalScoreStr;

	private String remark;

	private Long bigWorkId;

	public Long getBigWorkId() {
		return bigWorkId;
	}

	public void setBigWorkId(Long bigWorkId) {
		this.bigWorkId = bigWorkId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCreated() {
		return created;
	}

	public void setCreated(Long created) {
		this.created = created;
	}

	public String getSelfScoreStr() {
		return selfScoreStr;
	}

	public void setSelfScoreStr(String selfScoreStr) {
		this.selfScoreStr = selfScoreStr;
	}

	public String getMutualScoreStr() {
		return mutualScoreStr;
	}

	public void setMutualScoreStr(String mutualScoreStr) {
		this.mutualScoreStr = mutualScoreStr;
	}

	public String getTeacherScoreStr() {
		return teacherScoreStr;
	}

	public void setTeacherScoreStr(String teacherScoreStr) {
		this.teacherScoreStr = teacherScoreStr;
	}

	public String getTotalScoreStr() {
		return totalScoreStr;
	}

	public void setTotalScoreStr(String totalScoreStr) {
		this.totalScoreStr = totalScoreStr;
	}

	public Double CalcTotalScore(Double selfScore, Double selfRatio, Double mutualScore, Double mutualRatio,
		Double teacherScore, Double teacherRatio) {
		Double totalScore = selfScore * selfRatio + mutualScore * mutualRatio + teacherScore * teacherRatio;
		return totalScore;
	}

	public Double getSelfScore() {
		return selfScore;
	}

	public void setSelfScore(Double selfScore) {
		this.selfScore = selfScore;
	}

	public Double getSelfRatio() {
		return selfRatio;
	}

	public void setSelfRatio(Double selfRatio) {
		this.selfRatio = selfRatio;
	}

	public Double getMutualScore() {
		return mutualScore;
	}

	public void setMutualScore(Double mutualScore) {
		this.mutualScore = mutualScore;
	}

	public Double getMutualRatio() {
		return mutualRatio;
	}

	public void setMutualRatio(Double mutualRatio) {
		this.mutualRatio = mutualRatio;
	}

	public Double getTeacherScore() {
		return teacherScore;
	}

	public void setTeacherScore(Double teacherScore) {
		this.teacherScore = teacherScore;
	}

	public Double getTeacherRatio() {
		return teacherRatio;
	}

	public void setTeacherRatio(Double teacherRatio) {
		this.teacherRatio = teacherRatio;
	}

	public Double getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Double totalScore) {
		this.totalScore = totalScore;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
