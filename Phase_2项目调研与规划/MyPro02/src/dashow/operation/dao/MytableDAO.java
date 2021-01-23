package dashow.operation.dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.metadata.CollectionMetadata;
import org.hibernate.type.Type;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import dashow.model.Mytable;

/**
 * 
 * MytableDAO.java create by fooxe on 2016年4月24日 下午7:18:16
 *
 */
@Transactional
public class MytableDAO {
	private static final Logger log = LoggerFactory.getLogger(MytableDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String AGE = "age";
	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected void initDao() {
		// do nothing
	}

	/**
	 * 新增
	 * 
	 * @param transientInstance
	 */
	@Transactional(rollbackFor = { Exception.class })
	public void save(Mytable transientInstance) {
		log.debug("saving Mytable instance");
		try {
			System.out.println("MytableDAO save start");
			getCurrentSession().save(transientInstance);
			System.out.println("MytableDAO save successful");
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/**
	 * 删除
	 * 
	 * @param persistentInstance
	 */
	public void delete(Mytable persistentInstance) {
		log.debug("deleting Mytable instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/**
	 * 修改
	 * 
	 * @param persistentInstance
	 */

	public void update(Mytable persistentInstance) {
		log.debug("update Mytable instance");
		try {
			getCurrentSession().update(persistentInstance);
			System.out.println("更新成功");
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	/**
	 * 以id为关键词进行 对象查询
	 * 
	 * @param id
	 * @return
	 */
	public Mytable findById(Integer id) {
		log.debug("getting Mytable instance with id: " + id);
		try {
			System.out.println("start find object mid=" + id);
			Mytable instance = (Mytable) getCurrentSession().get(
					"dashow.model.Mytable", id);
			System.out.println("end find find object " + instance.getName());
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/**
	 * key word search
	 * 
	 * @param keyword
	 * @return
	 */
	public List<Mytable> findByField(String keyword) {
		List<Mytable> result = new ArrayList<Mytable>();
		List<Mytable> list = findAll();
		System.out.println("MyDAO 层 findByField进行基于元数据的关键词查询,rows="
				+ list.size() + ";keyword=" + keyword);
		for (Mytable my : list) {
			ClassMetadata myMetadate = sessionFactory
					.getClassMetadata(Mytable.class);
			String[] propertiesNames = myMetadate.getPropertyNames();
			Object[] propertiesValues = myMetadate.getPropertyValues(my);
			Type[] propertyTypes = myMetadate.getPropertyTypes();

			for (int i = 0; i < propertiesValues.length; i++) {
				System.out.println("propertiesValues[" + i + "]="
						+ propertiesValues[i]);
				if (propertiesValues[i] instanceof Integer) {
					if (propertiesValues[i].toString().equals(keyword)) {
						result.add(my);
						System.out.print("A=instanceof Integer==result.size()="
								+ result.size());
					}
				}
				if (propertiesValues[i] instanceof String) {
					if (propertiesValues[i].equals(keyword)) {
						result.add(my);
						System.out.print("B=instanceof String===result.size()="
								+ result.size());
					}
				}

			}
		}
		return result;
	}

	public List<Mytable> findAll() {
		log.debug("finding all Mytable instances");
		try {
			String queryString = "from Mytable";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/******************************************************************/

	public List<Mytable> findByExample(Mytable instance) {
		log.debug("finding Mytable instance by example");
		try {

			List<Mytable> results = (List<Mytable>) getCurrentSession()
					.createCriteria("dashow.model.Mytable")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@Transactional(readOnly = true)
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Mytable instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Mytable as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			System.out.println("queryObject.list().size()="
					+ queryObject.list().size());
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Mytable> findByName(Object name) {
		System.out.println("按名查找�?��…�?");
		return findByProperty(NAME, name);
	}

	/**
	 * 模糊查询
	 * 
	 * @param name
	 * @return
	 */
	public List<Mytable> findByBlurry(String name) {
		String hql = "from mytable as m where m.age<?";
		Query queryObject = getCurrentSession().createQuery(hql);
		queryObject.setParameter(0, name);
		System.out.println("模糊查询�?��…�?");

		return findByProperty(NAME, name);
	}

	public List<Mytable> findByLike(String name) {
		String hql = "from mytable as m where m.name like Ling%";
		Query queryObject = getCurrentSession().createQuery(hql);
		queryObject.setParameter(0, name);
		System.out.println("模糊查询�?��…�?");
		return findByProperty(NAME, name);
	}

	/**
	 * 
	 * @param age
	 * @return
	 */
	public List<Mytable> findBySQL(Object age) {
		// SQL查询
		String sql = "select {my.*} from Mytable{my}";
		Query queryObject = getCurrentSession().createSQLQuery(sql);
		// 条件查询
		Criteria cr = getCurrentSession().createCriteria(Mytable.class);

		return findByProperty(AGE, age);
	}

	public List<Mytable> findByAge(Object age) {
		return findByProperty(AGE, age);
	}

	/**
	 * 集合过滤,二次查询;
	 * 
	 * @param list
	 * @return
	 */
	public List<Mytable> listFilter(List<Mytable> list) {
		for (Mytable m : list) {
			Collection<Mytable> myCollection = getCurrentSession()
					.createFilter(m.getName(), "where this.age='20'").list();
		}
		getCurrentSession().createFilter(null, "");
		return null;
	}

	public Mytable merge(Mytable detachedInstance) {
		log.debug("merging Mytable instance");
		try {
			Mytable result = (Mytable) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Mytable instance) {
		log.debug("attaching dirty Mytable instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Mytable instance) {
		log.debug("attaching clean Mytable instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * 查询 主键为mid,持久化类的元数据".
	 * 
	 * @param mid
	 */
	public void getMetaDate(Integer mid) {
		Mytable my = (Mytable) getCurrentSession().get(Mytable.class,
				new Integer(mid));
		ClassMetadata myMetadate = sessionFactory
				.getClassMetadata(Mytable.class);
		String[] propertiesNames = myMetadate.getPropertyNames();
		Object[] propertiesValues = myMetadate.getPropertyValues(my);
		for (int i = 0; i < propertiesNames.length; i++) {
			System.out.println("属�?:" + propertiesNames[i] + "="
					+ propertiesValues[i]);
		}
	}

	/***
	 * 获取"集合的元数据"
	 * 
	 * @param mid
	 */
	public void getCollectionDate(Integer mid) {
		Mytable my = (Mytable) getCurrentSession().get(Mytable.class,
				new Integer(mid));
		sessionFactory.getCollectionMetadata("com.");

		CollectionMetadata cMeta = sessionFactory
				.getCollectionMetadata("dashow.model");// 集合属�?的角色名�?由持久化类的名称加上集合属�?的名称组�?
		System.out.println(cMeta.getRole());
		System.out.println(cMeta.getKeyType());
		System.out.println(cMeta.getElementType());
	}

	/**
	 * 流动结果�?
	 * 
	 * @param list
	 * @return
	 */
	public List<Mytable> scrollView(List<Mytable> list) {
		Query q = getCurrentSession().createQuery("from mytable");
		ScrollableResults sr = q.scroll(ScrollMode.SCROLL_INSENSITIVE);
		while (sr.next()) {
			Mytable mytabel = (Mytable) sr.get(0);
		}

		return null;
	}

	public static MytableDAO getFromApplicationContext(ApplicationContext ctx) {
		return (MytableDAO) ctx.getBean("mytableDAO");
	}
}