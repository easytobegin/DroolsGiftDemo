package com.sample;

import com.minisheep.bean.PointDomain;

public interface PointRuleEngine {
	/*
	 * 初始化规则引擎
	 */
	public void initEngine();
	
	/*
	 * 刷新规则引擎中的规则
	 */
	public void refreshEnginRule();
	
	/*
	 * 执行规则引擎
	 */
	public void executeRuleEngine(final PointDomain pointDomain);
}
