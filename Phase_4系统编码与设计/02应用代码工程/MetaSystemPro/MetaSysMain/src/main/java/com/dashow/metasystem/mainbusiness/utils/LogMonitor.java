package com.al.crm.busicommon;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

public class LogMonitor {
	
	@SerializedName("总耗时")
	private long totalUseTime;
	/**
	 * 耗时监控级别
	 * 单位：毫秒
	 */
	@SerializedName("监控级别(毫秒)")
	private long monitorLevel =10;
	
	/**
	 * 过滤级别
	 * 单位：毫秒
	 */
	@SerializedName("过滤级别(毫秒)")
	private long filterLevel =1000;
	
	/**
	 * 耗时排名范围
	 * 单位：毫秒
	 */
	@SerializedName("TOP范围")
	private int listRange =10 ;
	
	/**
	 * 统计明细
	 * 默认不统计
	 */
	@SerializedName("是否统计明细")
	private boolean isStatDetail = false;

	
	@SerializedName("耗时排名")
	private List<LogCat> topLogs= new ArrayList<LogCat>();
	
	@SerializedName("耗时明细")
	private List<String> LogCats = new LinkedList<String>();	
	 
	private transient Map<Date,String> LogCatMap = new HashMap<Date,String>();	
	
	private transient List<Date> LogDate = new ArrayList<Date>();


	public LogMonitor(){}
	
	/**
	 * 
	 * @param monitorLevel 监控级别(毫秒)
	 * @param listRange 排名范围
	 * @param filterLevel 过滤级别(毫秒)
	 * @param isStatDetail 是否写详细日志
	 */
	public LogMonitor(long monitorLevel,int listRange,long filterLevel,boolean isStatDetail){
		this.monitorLevel=monitorLevel;
		this.listRange = listRange;
		this.isStatDetail = isStatDetail;
		this.filterLevel = filterLevel;
	}
	
	
	public long getFilterLevel() {
		return filterLevel;
	}

//	public void setFilterLevel(long filterLevel) {
//		this.filterLevel = filterLevel;
//	}

	public long getTotalUseTime() {
		return totalUseTime;
	}
	
	private void calcTotalUseTime(){
//		this.totalUseTime = this.totalUseTime +useTime;
		this.totalUseTime = Collections.max(this.LogDate).getTime() - Collections.min(this.LogDate).getTime();

	}
	
	public void put(LogCat log){
		LogDate.add(log.getInTime());
		LogDate.add(log.getOutTime());
		if(log.getUseTime()	>= this.monitorLevel && this.isStatDetail){			
			this.LogCatMap.put(log.getInTime(),log.toString());
		}
//		this.calcTotalUseTime(); //计算总耗时
		
		//统计top 耗时方法
		if(this.topLogs.size() < this.listRange){
//			this.topLogs.add(log);
			this.putAndSort(log);
		}else{
			for (int i = this.listRange-1; i >=0; i--) {
				LogCat car = this.topLogs.get(i);
				if(car.getUseTime()<log.getUseTime()){
					topLogs.set(i, log);
					this.sort();
					break;
				}
			}
		}
	}
	
	public void  build(){	
		if(this.LogCatMap != null ||this.LogCatMap.size()!=0){
			Set set = this.LogCatMap.keySet();
			int size = set.size();
			for (int i = 0; i < size; i++) {
				Date key = this.getMinDate(set);
				String value = this.LogCatMap.get(key);
				LogCats.add(value);
				this.LogCatMap.remove(key);
				set.remove(key);				
			}			
		}
		this.calcTotalUseTime(); //计算总耗时
	}	
	
	private Date getMaxDate(Collection c){
		return (Date) Collections.max(c);
		
	}
	private Date getMinDate(Collection c){
		return (Date) Collections.min(c);
	}
	
	private void putAndSort(LogCat car){
		this.topLogs.add(car);
		
		Collections.sort(this.topLogs,new Comparator<LogCat>(){  
            public int compare(LogCat c1, LogCat c2) { 
            	long u1 = c1.getUseTime();
            	long u2 = c2.getUseTime();
            	if(u1 < u2){
            		return -1;
            	}else if(u1 > u2){
            		return 1;
            	}
                return 0;  
            }	              
        });
		Collections.reverse(this.topLogs);
//		System.out.println(topLogs);
	}
	
	private void sort(){		
		Collections.sort(this.topLogs,new Comparator<LogCat>(){  
            public int compare(LogCat c1, LogCat c2) { 
            	long u1 = c1.getUseTime();
            	long u2 = c2.getUseTime();
            	if(u1 < u2){
            		return -1;
            	}else if(u1 > u2){
            		return 1;
            	}
                return 0;  
            }	              
        });		
		Collections.reverse(this.topLogs);

	}

	
	public static void main(String[] args){
		Random r = new Random();
		LogMonitor lm = new LogMonitor();
		
		for(int i=0;i<10;i++){
			LogCat l = new LogCat();
			l.setInTime();
			
			try {
				Thread.sleep(r.nextInt(30));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			l.setOutTime();
			lm.put(l);
			
		}		
		
		Gson g = new GsonBuilder().setDateFormat("yyyy/MM/dd HH:mm:ss SSSS").create();
		String ss = g.toJson(lm);
		System.out.println(ss);		

		
	}
}
