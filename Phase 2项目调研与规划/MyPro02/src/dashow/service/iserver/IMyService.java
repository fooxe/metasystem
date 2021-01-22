package dashow.service.iserver;

import java.util.List;

import dashow.model.Mytable;
import dashow.service.IBaseService;
/**
 * 可以通过抽取类的接口进行生成
 *IMyService.java create by fooxe on 2016年4月24日 下午7:14:00 
 *
 */
public interface IMyService {//extends IBaseService<T>{
	public abstract List<Mytable> findAll();
	public abstract void delete(Integer id);
	public abstract Mytable findById(Integer flag);
	public abstract void update(Mytable mytable);
	public abstract List<Mytable> findByField(String keyword) throws Exception;
	public abstract void create(Mytable mytable);

}