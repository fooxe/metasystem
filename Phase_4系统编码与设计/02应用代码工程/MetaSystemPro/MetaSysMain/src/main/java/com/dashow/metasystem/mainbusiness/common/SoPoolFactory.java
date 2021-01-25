package com.dashow.metasystem.main.common;

//import com.al.common.exception.BaseException;
//import com.asiainfo.crm.order.common.OrderMDA;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 业务规则线程池工厂
 * 
 */
public class SoPoolFactory {
//	private static final Log log = Log.getLog(SoPoolFactory.class);

	//线程池维护线程的最小数量
	public static int CORE_POOL_SIZE = 18;

	//线程池维护线程的最大数量
	public static int MAX_POOL_SIZE = 30;

	//线程池维护线程所允许的空闲时间
	public static long KEEP_ALIVE_TIME = 600;

	//线程池维护线程所允许的空闲时间单位
	private static TimeUnit TIME_UNIT = TimeUnit.SECONDS;

	//有界队列最大长度
	//通过反射方式获取最大队列，需要确保他的类型可见，如果类型不可见反射没法拿到类的属性。
	public static int MAX_QUEUE_SIZE = 1000;

	//有界队列，当使用有限的 MAX_POOL_SIZE 时，有界队列（如 ArrayBlockingQueue）有助于防止资源耗尽，但是可能较难调整和控制。
	private static ArrayBlockingQueue<Runnable> queue = null;

	//将成员属性改为protected是为了让外部类可以将线程池引用置null，当然这里是存在风险的。
	private static ThreadPoolExecutor INSTANCE = null;

	/**
	 * 单例模式初始化线程池
	 */
	public static synchronized ThreadPoolExecutor getInstance() {
		if (INSTANCE == null) {
			queue = new ArrayBlockingQueue<Runnable>(MAX_QUEUE_SIZE);
			INSTANCE = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TIME_UNIT, queue,
					new ThreadPoolExecutor.CallerRunsPolicy());
//			log.debug("初始化线程池:CORE_POOL_SIZE={},MAX_POOL_SIZE={},KEEP_ALIVE_TIME={},MAX_QUEUE_SIZE={}", CORE_POOL_SIZE,
//					MAX_POOL_SIZE, KEEP_ALIVE_TIME, MAX_QUEUE_SIZE);
		}
		return INSTANCE;
	}

	/**
	 * 获取线程池
	 * @return
	 */
//	public static ThreadPoolExecutor getSoPoolFactory() {
//		//获取线程池实例
//		ThreadPoolExecutor executor = null;
//		try {
//			executor = getInstance();
//			//通过反射修改队列大小并且创建新的队列;
//			if (executor != null
//					&& (!OrderMDA.CORE_POOL_SIZE.equals(SoPoolFactory.CORE_POOL_SIZE)
//							|| !OrderMDA.MAX_POOL_SIZE.equals(SoPoolFactory.MAX_POOL_SIZE)
//							|| !OrderMDA.KEEP_ALIVE_TIME.equals(SoPoolFactory.KEEP_ALIVE_TIME) || !OrderMDA.MAX_QUEUE_SIZE
//							.equals(SoPoolFactory.MAX_QUEUE_SIZE))) {
//				executor = getNewExecutor(executor, OrderMDA.CORE_POOL_SIZE, OrderMDA.MAX_POOL_SIZE, OrderMDA.KEEP_ALIVE_TIME,
//						OrderMDA.MAX_QUEUE_SIZE);
//			}
//		} catch (Exception e) {
////			log.error("获取线程池发生异常{}", e);
//			throw new BaseException("获取线程池发生异常", e);
//		}
//
//		return executor;
//	}

	/**
	 * 依据新的队列创建新的线程池
	 * @param executor
	 * @param corePoolSize
	 * @param maxPoolSize
	 * @param keepAliveTime
	 * @param maxQueueSize
	 * @return
	 * @throws BaseException
	 */
//	public static ThreadPoolExecutor getNewExecutor(ThreadPoolExecutor executor, Integer corePoolSize,
//			Integer maxPoolSize, Long keepAliveTime, Integer maxQueueSize) throws BaseException {
//		Class<?> cls = null;
//		try {
//			cls = Class.forName("com.asiainfo.crm.order.busi.task.SoPoolFactory");
//			//按过去执行已提交任务的顺序发起一个有序的关闭，但是不接受新任务。如果已经关闭，则调用没有其他作用
//			executor.shutdown();
//			//将原先的线程池引用值为null;
//			INSTANCE = null;
//			Field field = null;
//			//通过反射重新设置线程池参数
//			field = cls.getField("CORE_POOL_SIZE");
//			field.set(cls, corePoolSize);
//			field = cls.getField("MAX_POOL_SIZE");
//			field.set(cls, maxPoolSize);
//			field = cls.getField("KEEP_ALIVE_TIME");
//			field.set(cls, keepAliveTime);
//			field = cls.getField("MAX_QUEUE_SIZE");
//			field.set(cls, maxQueueSize);
//			//创建新的线程池;
//			executor = getInstance();
//		} catch (IllegalArgumentException e) {
////			log.error("动态修改队列获取线程池实例发生非法参数异常:CORE_POOL_SIZE={},MAX_POOL_SIZE={},KEEP_ALIVE_TIME={},MAX_QUEUE_SIZE={}",
////					CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, MAX_QUEUE_SIZE);
//			throw new BaseException("动态修改队列获取线程池实例发生非法参数异常:CORE_POOL_SIZE=" + CORE_POOL_SIZE
//					+ ",MAX_POOL_SIZE=" + MAX_POOL_SIZE + ",KEEP_ALIVE_TIME=" + KEEP_ALIVE_TIME + ",MAX_QUEUE_SIZE="
//					+ MAX_QUEUE_SIZE, e);
//		} catch (IllegalAccessException e) {
////			log.error("动态修改队列获取线程池实例发生安全权限异常:{}", e);
//			throw new BaseException( "动态修改队列获取线程池实例发生安全权限异常", e);
//		} catch (ClassNotFoundException e) {
////			log.error("动态修改队列获取线程池实例发生类没有发现异常:{}", e);
//			throw new BaseException( "动态修改队列获取线程池实例发生类没有发现异常", e);
//		} catch (NoSuchFieldException e) {
////			log.error("动态修改队列获取线程池实例发生类没有相关属性异常:{}", e);
//			throw new BaseException( "动态修改队列获取线程池实例发生类没有相关属性异常", e);
//		}
//		return executor;
//	}
}