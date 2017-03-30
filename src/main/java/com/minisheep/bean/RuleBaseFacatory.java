package com.minisheep.bean;

import org.drools.core.RuleBase;
import org.drools.core.RuleBaseFactory;



public class RuleBaseFacatory {
	private static RuleBase ruleBase;
	
	public static RuleBase getRuleBase(){
		return null != ruleBase ? ruleBase : RuleBaseFactory.newRuleBase();
	}
}
