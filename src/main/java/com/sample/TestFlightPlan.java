package com.sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


import com.minisheep.bean.Plan;
import com.minisheep.bean.Resource;

public class TestFlightPlan{
	private static List<Plan> lsPlans;
	private static List<Resource> lsResources;
	private static int position;
	private static int MIN = 0xfffffff;
	
//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//		super.run();
//		try{
//			lsResources.get(position).setEnableWork(false);
//			//System.out.println("enableWork:" + lsResources.get(j).isEnableWork());
//			Thread.sleep(10000);
//		}catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		
//	}
	
	public static void main(String[] args) throws IOException{
		PointRuleEngine pointRuleEngine = new PointRuleEngineImpl();
		
		//setData();
		
		while(true){
			InputStream is = System.in;
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			String input = br.readLine();
			if(null != input && "s".equals(input)){
				System.out.println("初始化规则引擎...");
				pointRuleEngine.initEngine();
				System.out.println("初始化规则引擎结束.");
			}else if("e".equals(input)){
				//final Resource resource = new Resource();
				lsPlans = new ArrayList<Plan>();  //所有的任务,只能进行模拟
				lsResources = new ArrayList<Resource>(); //所有的人
				
				lsResources = setPerson();  //获取人员所有信息
				lsPlans = setPlan(); //获取任务所有信息
				
				for(int i = 0;i<lsPlans.size();i++){  //模拟任务到来
					int planStartTime = lsPlans.get(i).getPlanStartTime();  //任务开始时间
					int planEndTime = lsPlans.get(i).getPlanEndTime();  //任务结束时间
					String titleName = lsPlans.get(i).getPlanTitle();  //任务的类别标题
					
					position = 0;	
					MIN = 0xfffffff;
					System.out.println("任务" + (i + 1) + "来了");
					System.out.println("任务开始时间为:" + planStartTime + " 结束时间为:" + planEndTime + " 任务的类别为:" + titleName);
					boolean find = false;
					List<Integer> ls = new ArrayList<Integer>();  //存放之前的节点
					for(int j=0;j<lsResources.size();j++){
						lsResources.get(j).getPlan().setPlanStartTime(planStartTime);
						lsResources.get(j).getPlan().setPlanEndTime(planEndTime);
						lsResources.get(j).getPlan().setPlanTitle(titleName);
						
						
						//BUG，满足条件就会加上时间,所以要再加条规则，判断是否已经被选中 
						pointRuleEngine.executeRuleEngine(lsResources.get(j));  
						
						if(lsResources.get(j).isEnableWork() != false){  //这个人可以胜任这项工作
							find = true;
							
							if(lsResources.get(j).getRemainMinute() <= MIN){
								ls.add(j);   //这个要放在里面
								MIN = lsResources.get(j).getRemainMinute();  //获取当前体力最大的那个人
								//System.out.println("该人员工作了:" + MIN + "小时");
								position = j;  //当前节点
								//System.out.println("postion:" + position);
								
							}
					
							//System.out.println("当前体力花的最少的人为:" + (position+1));
						}
					}
					for(int size = 0;size < ls.size() - 1;size++){
						lsResources.get(ls.get(size)).setEnableWork(false);  //前面的人员还不能工作
						lsResources.get(ls.get(size)).setWorking(true);  //执行引擎的条件
						pointRuleEngine.executeRuleEngine(lsResources.get(ls.get(size)));
						lsResources.get(ls.get(size)).setWorking(false);  //执行完条件清空
						//System.out.println("编号" + (ls.get(size) + 1) + ",可以开始工作的时间:" + lsResources.get(ls.get(size)).getStartWorkTime());
					}
					if(find != true){
						System.out.println("该任务没有可用的人手,分配失败!");
					}else{
						//遍历完所有的人后再做决定可以按体力合理分配,选择时常多的人优先分配
						
						System.out.println("人员" + lsResources.get(position).getId() + "被分配!"+" 人员类别为:" + lsResources.get(position).getCategory());
						System.out.println("该人员Id编号为:" + lsResources.get(position).getId());
						System.out.println("分配到的任务为:" + "任务" + (i + 1));
						//lsResources.get(j).setWorking(true);  //已经被分配了
						System.out.println("该" + (position + 1) + "号人员可以再开始执行任务的时间为:" + 
								lsResources.get(position).getStartWorkTime() + "时");
						lsResources.get(position).setEnableWork(false); //分配任务后这个不能工作了
						
					}
					System.out.println("------------------------------------------------");
					
				}
				
				/*
				//set航班任务起始
				resource.getPlan().setPlanEndTime(16);  //任务结束时间
				resource.getPlan().setPlanStartTime(8);  //任务开始时间
				resource.setStartWorkTime(2); //工人开始上班的时间
				resource.setEndWorkTime(17); //工人结束上班的时间
				resource.setRemainMinute((17-2) * 60);
				resource.setWorking(false);
				pointRuleEngine.executeRuleEngine(resource);
				if(resource.isEnableWork() != false){
					System.out.println("人员可分配!");
					System.out.println("该人员剩余分钟数为:" + resource.getRemainMinute() + "分钟");
					System.out.println("下次可以开始执行任务的时间为:" + resource.getStartWorkTime() + "点");
				}else{
					System.out.println("人员是不可分配的!");
				}
				
				*/
			}else if("r".equals(input)){
				System.out.println("刷新规则文件...");
				pointRuleEngine.refreshEnginRule();
				System.out.println("刷新规则文件结束.");
			}
		}
	}

	private static List<Plan> setPlan() {
		List<Plan> lsPlans = new ArrayList<Plan>();
		Plan plan1 = new Plan();  //假设有6个任务依次到来
		Plan plan2 = new Plan();
		Plan plan3 = new Plan();
		Plan plan4 = new Plan();
		Plan plan5 = new Plan();
		Plan plan6 = new Plan();
		Plan plan7 = new Plan();
		
		//8点开始,12点结束
		plan1.setPlanStartTime(8);
		plan1.setPlanEndTime(12);
		plan1.setPlanTitle("机务勤务A");
		
		//8点开始,10点结束
		plan2.setPlanStartTime(9); 
		plan2.setPlanEndTime(10);
		plan2.setPlanTitle("机务勤务B");

		//9点开始,11点结束
		plan3.setPlanStartTime(10);
		plan3.setPlanEndTime(11);
		plan3.setPlanTitle("配餐员");
		
		//14点开始,17结束
		plan4.setPlanStartTime(12);
		plan4.setPlanEndTime(15);
		plan4.setPlanTitle("引导车");
		
		//12点开始,15点结束     
		plan5.setPlanStartTime(15);
		plan5.setPlanEndTime(16);
		plan5.setPlanTitle("引导车");
		
		//12点开始,15点结束
		plan6.setPlanStartTime(9);
		plan6.setPlanEndTime(11);
		plan6.setPlanTitle("配餐员");
		
		//12点开始,15点结束     
		plan7.setPlanStartTime(16);
		plan7.setPlanEndTime(17);
		plan7.setPlanTitle("引导车");
		
		lsPlans.add(plan1);
		lsPlans.add(plan2);
		lsPlans.add(plan3);
		lsPlans.add(plan4);
		lsPlans.add(plan5);
		lsPlans.add(plan6);
		lsPlans.add(plan7);
		
		return lsPlans;
	}

	private static List<Resource> setPerson() {
		// TODO Auto-generated method stub
		List<Resource> lsRes = new ArrayList<Resource>();
		
		Resource person1 = new Resource();
		Resource person2 = new Resource();
		Resource person3 = new Resource();
		Resource person4 = new Resource();
		Resource person5 = new Resource();
		Resource person6 = new Resource();
		Resource person7 = new Resource();
		Resource person8 = new Resource();
		Resource person9 = new Resource();
		Resource person10 = new Resource();

		//8点上班,17点下班，剩余时间,目前暂无任务
		person1.setId(1);
		person1.setStartWorkTime(8);  //工作开始时间
		person1.setEndWorkTime(17);
		person1.setRemainMinute(0);  //已工作时长
		person1.setWorking(false);  
		person1.setCategory("机务勤务A");
		
		//8点上班,17点下班,剩余时间,暂无任务
		person2.setId(2);
		person2.setStartWorkTime(8);
		person2.setEndWorkTime(17);
		person2.setRemainMinute(0);
		person2.setWorking(false);
		person2.setCategory("机务勤务B");
		
		//8点上班,17点下班,剩余时间,暂无任务
		person3.setId(3);
		person3.setStartWorkTime(8);
		person3.setEndWorkTime(12);
		person3.setRemainMinute(0);
		person3.setWorking(false);
		person3.setCategory("引导车");
		
		//8点上班,17点下班,剩余时间,暂无任务
		person4.setId(4);
		person4.setStartWorkTime(8);
		person4.setEndWorkTime(17);
		person4.setRemainMinute(0);
		person4.setWorking(false);
		person4.setCategory("引导车");
				
		//8点上班,17点下班,剩余时间,暂无任务
		person5.setId(5);
		person5.setStartWorkTime(8);
		person5.setEndWorkTime(17);
		person5.setRemainMinute(0);
		person5.setWorking(false);
		person5.setCategory("引导车");
				
		//8点上班,17点下班,剩余时间,暂无任务
		person6.setId(6);
		person6.setStartWorkTime(8);
		person6.setEndWorkTime(17);
		person6.setRemainMinute(0);
		person6.setWorking(false);
		person6.setCategory("机务勤务B");
		
		//8点上班,17点下班,剩余时间,暂无任务
		person7.setId(7);
		person7.setStartWorkTime(8);
		person7.setEndWorkTime(17);
		person7.setRemainMinute(0);
		person7.setWorking(false);
		person7.setCategory("配餐员");
		
		//8点上班,17点下班,剩余时间,暂无任务
		person8.setId(8);
		person8.setStartWorkTime(8);
		person8.setEndWorkTime(17);
		person8.setRemainMinute(0);
		person8.setWorking(false);
		person8.setCategory("配餐员");
		
		//8点上班,17点下班,剩余时间,暂无任务
		person9.setId(9);
		person9.setStartWorkTime(8);
		person9.setEndWorkTime(17);
		person9.setRemainMinute(0);
		person9.setWorking(false);
		person9.setCategory("机务勤务B");
		
		//8点上班,17点下班，剩余时间,目前暂无任务
		person10.setId(10);
		person10.setStartWorkTime(8);  //工作开始时间
		person10.setEndWorkTime(17);
		person10.setRemainMinute(0);
		person10.setWorking(false);  
		person10.setCategory("机务勤务A");
				
		lsRes.add(person1); 
		lsRes.add(person2); 
		lsRes.add(person3); 
		lsRes.add(person4);
		lsRes.add(person5);
		lsRes.add(person6);
		lsRes.add(person7);
		lsRes.add(person8);
		//lsRes.add(person9);
		//lsRes.add(person10);
		return lsRes;
	}
}
