package com.minisheep.bean;

public class Resource {
	/*
	 * 人员指派任务用setPlanAlloc
	 */
	public Resource(){
		plan = new Plan();
	}

	private String category;
	
	//id
	private int id;
	//资源名称(和任务键值对可以关联)
	private String resourceName; 
	
	//资源是否是普通工作周,true代表是普通工作周
	private boolean isWorkDay;
	
	//资源上班时间开始(周几)
	private int startWorkDay;
	
	//资源上班时间结束(周几)
	private int endWorkDay;
	
	//资源上班时间开始(几点,换算成分钟)
	private int startWorkTime;
	
	//资源上班时间结束(几点,换算成分钟) 
	private int endWorkTime;
	
	//能量,100%代表不可用了,用0-100表示
	private int energy;
	
	//剩余可用时常
	private int remainMinute;
	
	//设置任务(名称为planTitle,飞机任务)
	private String planAlloc;
	
	//是否在执行其他任务
	private boolean isWorking;
	
	//Plan成员变量
	private Plan plan;
	
	//能否胜任此项工作
	private boolean enableWork;

	public boolean isEnableWork() {
		return enableWork;
	}

	public void setEnableWork(boolean enableWork) {
		this.enableWork = enableWork;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public int getRemainMinute() {
		return remainMinute;
	}

	public void setRemainMinute(int remainMinute) {
		this.remainMinute = remainMinute;
	}

	public boolean getIsWorking() {
		return isWorking;
	}

	public void setWorking(boolean isWorking) {
		this.isWorking = isWorking;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public boolean getIsWorkDay() {
		return isWorkDay;
	}

	public void setIsWorkDay(boolean isWorkDay) {
		this.isWorkDay = isWorkDay;
	}

	public int getStartWorkDay() {
		return startWorkDay;
	}

	public void setStartWorkDay(int startWorkDay) {
		this.startWorkDay = startWorkDay;
	}

	public int getEndWorkDay() {
		return endWorkDay;
	}

	public void setEndWorkDay(int endWorkDay) {
		this.endWorkDay = endWorkDay;
	}

	public int getStartWorkTime() {
		return startWorkTime;
	}

	public void setStartWorkTime(int startWorkTime) {
		this.startWorkTime = startWorkTime;
	}

	public int getEndWorkTime() {
		return endWorkTime;
	}

	public void setEndWorkTime(int endWorkTime) {
		this.endWorkTime = endWorkTime;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public String getPlanAlloc() {
		return planAlloc;
	}

	public void setPlanAlloc(String planAlloc) {
		this.planAlloc = planAlloc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
}
