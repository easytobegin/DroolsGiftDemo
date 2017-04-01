package com.sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.drools.compiler.compiler.PackageBuilder;

import org.drools.core.RuleBase;
import org.drools.core.StatefulSession;
import org.drools.core.rule.Package;

import com.minisheep.bean.PointDomain;
import com.minisheep.bean.Resource;
import com.minisheep.bean.RuleBaseFacatory;

public class PointRuleEngineImpl implements PointRuleEngine{
	private RuleBase ruleBase;
	public void initEngine() {
		// TODO Auto-generated method stub
		System.setProperty("drools.dateformat", "yyyy-MM-dd HH:mm:ss");
		ruleBase = RuleBaseFacatory.getRuleBase();
		
		try {
			PackageBuilder backageBuilder = getPackageBuilderFromDrlFile();
			ruleBase.addPackages(backageBuilder.getPackages());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//刷新引擎
	public void refreshEnginRule() {
		// TODO Auto-generated method stub
		ruleBase = RuleBaseFacatory.getRuleBase();
		Package[] packages = ruleBase.getPackages();
		for(Package pg : packages){
			ruleBase.removePackage(pg.getName());
		}
		
		initEngine();
	}
	
	

	public void executeRuleEngine(final Resource resource) {
		// TODO Auto-generated method stub
		if(ruleBase == null){
			return;
		}
		
		if(null == ruleBase.getPackages()){
			return;
		}
		
		if(ruleBase.getPackages().length == 0){
			return;
		}
		StatefulSession statefulSession = ruleBase.newStatefulSession();
		statefulSession.insert(resource);
		
		statefulSession.fireAllRules();
		
		statefulSession.dispose();
	}
	
	/*
	 * 从Drl规则文件中读取规则
	 * @return
	 * @throws Exception
	 */
	private PackageBuilder getPackageBuilderFromDrlFile() throws Exception{
		//获取测试脚本文件
		List<String> drlFilePath = getTestDrlFile();
		
		//System.out.println("driFilePath:" + drlFilePath);
		//装载测试脚本文件
		List<Reader> readers = readRuleFromDrlFile(drlFilePath);

		PackageBuilder backageBuilder = new PackageBuilder();
		
		for(Reader r : readers){
			backageBuilder.addPackageFromDrl(r);
		}
		
		if(backageBuilder.hasErrors()){
			throw new Exception(backageBuilder.getErrors().toString());
		}
		return backageBuilder;
	}

	/*
	 * 脚本文件路径
	 */
	private List<Reader> readRuleFromDrlFile(List<String> drlFilePath){
		if(null == drlFilePath){
			return null;
		}
		if(0 == drlFilePath.size()){
			return null;
		}
		
		List<Reader> readers = new ArrayList<Reader>();
		
		for(String ruleFilePath : drlFilePath){
			try {
				readers.add(new FileReader(new File(ruleFilePath)));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return readers;	
	}
	
	
	/*
	 * 获取测试规则文件
	 *
	 */
	private List<String> getTestDrlFile() {
		// TODO Auto-generated method stub
		List<String> drlFilePath = new ArrayList<String>();
		//drlFilePath.add("/Users/minisheep/Documents/workspace/rockDrools/src/main/resources/rules/addpoint.drl");
		//drlFilePath.add("/Users/minisheep/Documents/workspace/rockDrools/src/main/resources/rules/subpoint.drl");
		drlFilePath.add("/Users/minisheep/Documents/workspace/rockDrools/src/main/resources/rules/resource.drl");
		return drlFilePath;
	}
	
}
