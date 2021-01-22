package dashow.service;




import java.util.List;

/**
 * Created by fooxe on 2015/12/20.
 *
 */

public interface IBaseService<T> {
    //public IBaseDao baseDao=null;

    public T find(Class<T> clazz, int id);//查询对象
    public T persist(T t);//添加对象�?
    public T merge(T t);//保存对象
    public T remove(Class<? extends T> clazz, int id);//删除对象�?
    public T remove(T t);//删除对象
    public List<T> listAll(Class<? extends T> clazz);//列出�?��对象�?
    public List<T> listJpql(String jpql);//列出JPQL描述的对象；

    public int getTotal(Class<? extends T> clazz);
    public List<T> list(Class<? extends T> clazz, int firstResult, int maxItems);//分页列出对象�?


    public void create(T instance);
    public void save(T instance);
    public void delete(T instance);
    public List<T> list(String hql);
    public  int getTotalCount(String hql);
    public List<T> list(String hql, int star, int maxSize);
	//public void login(LoginForm loginForm);
}
