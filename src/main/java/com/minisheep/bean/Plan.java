package com.minisheep.bean;

import java.util.HashMap;

public class Plan {	
	@Override
	public String toString() {
		return "Plan [planTitle=" + planTitle + ", totalCost=" + totalCost + ", planStartTime=" + planStartTime
				+ ", planEndTime=" + planEndTime + ", finishProcess=" + finishProcess + ", planAllocToResource="
				+ planAllocToResource + "]";
	}
	//任务标题(和人员名称可以形成键值对)
	private String planTitle;
	
	//投入时间
	private int totalCost;
	
	//开始时间
	private String planStartTime;
	
	//结束时间
	private String planEndTime;
	
	//完成度(0-100代表 0% ~ 100%)
	private int finishProcess;

	//任务分配需要分配的人和资源
	private HashMap<String, String> planAllocToResource;
	
	public HashMap<String, String> getPlanAllocToResource() {
		return planAllocToResource;
	}

	public void setPlanAllocToResource(HashMap<String, String> planAllocToResource) {
		this.planAllocToResource = planAllocToResource;
	}

	public String getPlanTitle() {
		return planTitle;
	}

	public void setPlanTitle(String planTitle) {
		this.planTitle = planTitle;
	}

	public int getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}

	public String getPlanStartTime() {
		return planStartTime;
	}

	public void setPlanStartTime(String planStartTime) {
		this.planStartTime = planStartTime;
	}

	public String getPlanEndTime() {
		return planEndTime;
	}

	public void setPlanEndTime(String planEndTime) {
		this.planEndTime = planEndTime;
	}

	public int getFinishProcess() {
		return finishProcess;
	}

	public void setFinishProcess(int finishProcess) {
		this.finishProcess = finishProcess;
	}
}
