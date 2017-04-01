package com.sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.minisheep.bean.Resource;

public class TestFlightPlan {
	public static void main(String[] args) throws IOException{
		PointRuleEngine pointRuleEngine = new PointRuleEngineImpl();
		while(true){
			InputStream is = System.in;
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			String input = br.readLine();
			if(null != input && "s".equals(input)){
				System.out.println("初始化规则引擎...");
				pointRuleEngine.initEngine();
				System.out.println("初始化规则引擎结束.");
			}else if("e".equals(input)){
				final Resource resource = new Resource();
				resource.setWorking(false);
				pointRuleEngine.executeRuleEngine(resource);
				if(resource.getIsWorking() != false){
					System.out.println("人员已分配!");
				}
			}else if("r".equals(input)){
				System.out.println("刷新规则文件...");
				pointRuleEngine.refreshEnginRule();
				System.out.println("刷新规则文件结束.");
			}
		}
	}
}
