package com.minisheep.bean;

public class Resource {
	/*
	 * 人员指派任务用setPlanAlloc
	 */
	
	//资源类型
	private int category;
	
	//资源名称(和任务键值对可以关联)
	private String resourceName; 
	
	//资源是否是普通工作周,true代表是普通工作周
	private boolean isWorkDay;
	
	//资源上班时间开始(周几)
	private String startWorkDay;
	
	//资源上班时间结束(周几)
	private String endWorkDay;
	
	//资源上班时间开始(几点)
	private String startWorkTime;
	
	//资源上班时间结束(几点) 
	private String endWorkTime;
	
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

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
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

	public String getStartWorkDay() {
		return startWorkDay;
	}

	public void setStartWorkDay(String startWorkDay) {
		this.startWorkDay = startWorkDay;
	}

	public String getEndWorkDay() {
		return endWorkDay;
	}

	public void setEndWorkDay(String endWorkDay) {
		this.endWorkDay = endWorkDay;
	}

	public String getStartWorkTime() {
		return startWorkTime;
	}

	public void setStartWorkTime(String startWorkTime) {
		this.startWorkTime = startWorkTime;
	}

	public String getEndWorkTime() {
		return endWorkTime;
	}

	public void setEndWorkTime(String endWorkTime) {
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

	
}
