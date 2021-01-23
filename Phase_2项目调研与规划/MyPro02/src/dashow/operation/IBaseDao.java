package dashow.operation;

import java.util.List;


public interface IBaseDao {
	public abstract <T> void save(T transientInstance);

	public abstract <T> void delete(T persistentInstance);

	//public abstract <T> T findById(dashow.hibernate.CartId id);

	public abstract <T> List findByExample(T instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findAll();

	public abstract <T> T merge(T detachedInstance);

	public abstract <T> void attachDirty(T instance);

	public abstract <T> void attachClean(T instance);

}
