package dashow.service.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dashow.model.Mytable;
import dashow.operation.dao.MytableDAO;
import dashow.service.iserver.IMyService;

/**
 * 
 * MyService.java create by fooxe on 2016年4月24日 下午7:08:45
 *
 */
@Service("myService")
public class MyService implements IMyService {
	@Autowired
	private MytableDAO mytableDAO = null;

	@Override
	public void create(Mytable mytable) {
		// TODO Auto-generated method stub
		mytableDAO.save(mytable);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method
		System.out.println("execute savice delete method" + id);
		mytableDAO.delete(mytableDAO.findById(id));
	}

	@Override
	public void update(Mytable mytable) {
		// TODO Auto-generated method stub
		mytableDAO.update(mytable);
	}

	@Override
	public Mytable findById(Integer flag) {
		return mytableDAO.findById(flag);
	}

	@Override
	public List<Mytable> findAll() {
		return mytableDAO.findAll();
	}

	@Override
	public List<Mytable> findByField(String keyword) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("myService metadate query");
		List<Mytable> list = mytableDAO.findByField(keyword);
		return list;
	}

	private void getMetadate(Integer flag) {
		mytableDAO.getMetaDate(flag);
	}

}
