package dashow.struts2.action;

import java.util.List;

import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import dashow.model.Mytable;
import dashow.service.iserver.IMyService;

/**
 * Created by fooxe on 2015/12/20.
 */
@Namespace("/mytest")
@Controller
@Scope("prototype")
@Results({

		@Result(name = "error", location = "/error.jsp"),
		@Result(name = "loading", location = "/mytest/my-edit.jsp"),
		@Result(name = "update", location = "/mytest/my.jsp"),
		@Result(name = "delete", type = "redirect", location = "my!list.action"),
		@Result(name = "list", location = "/mytest/my-list.jsp"),
		@Result(name = "invalid.token", location = "/error.jsp")

})
@InterceptorRefs({
		@InterceptorRef(value = "tokenSession", params = { "includeMethods",
				"update" }),
		@InterceptorRef(value = "defaultStack", params = { "includeMethods",
				"update" }) })
public class MyAction extends ActionSupport {// implements Preparable
	@Autowired
	private IMyService myService=null;
	private List<Mytable> list=null;
	private Mytable mytable=null;
	private Integer flag=null;
	private String keyword = null;// 搜索词

	// 单击新增或修改时，跳转到edit界面
	public String loading() throws Exception {
		mytable=new Mytable();
		if (flag == null) {
			System.out.println("loading 方法执行,新增一条信息");
			mytable = null;
		} else {
			System.out.println("loading 方法执行，保存 mid=" + flag + " 的信息");
			mytable = myService.findById(flag);			
			System.out.println("mytable="+mytable+",(mytable==null)=" + mytable == null);// 输出为faluse
			
			/**
			 * Field[] fields = mytable.getClass().getFields(); for (Field field
			 * : fields) { Object ob = field.get(mytable); //
			 * mem.setAccessible(true); System.out.println(field.get(mytable));
			 * }
			 */
		//	System.out.println("Name="+mytable.getName());

		}
		return "loading";
	}

	public String update() throws Exception {
		 
		System.out.println("enter update method,flag=" + flag);
		System.out.println("enter update method,mytable.md=" + mytable.getMid());
		if ((mytable.getMid()) == null) {
			myService.create(mytable);			
			System.out.println("update方法执行， 新增信息");
		} else {
			myService.update(mytable);			
			System.out.println("update方法执行，保存 mid=" + flag + " 的信息");
		}
		
		System.out.println("update end");
		return "update";
	}

	public String delete() throws Exception {
		System.out.println(" delete method flag=" + flag);
		myService.delete(flag);
		return "delete";
	}

	public String list() throws Exception {
		System.out.println("findAll start ");
		list = myService.findAll();
		System.out.println("find all query end");
		return "list";
	}

	public String findByField() throws Exception {
		System.out.println("findByField start ");
		list = myService.findByField(keyword);
		if (list.size() < 1)
			System.out.println("没有你需要的元数据查询结果");
		else
			addActionMessage("查询结果如下：******************");
		// Map request = (Map) ActionContext.getContext().get("request");
		// request.put("list", list);
		System.out.println("find all query end");
		return "list";
	}

	/*************** getter and setter ************************/

	public List<Mytable> getList() {
		return list;
	}

	public void setList(List<Mytable> list) {
		this.list = list;
	}

	public Mytable getMytable() {
		return mytable;
	}

	public void setMytable(Mytable mytable) {
		this.mytable = mytable;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

}
