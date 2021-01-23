package dashow.struts2.forms;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

import dashow.model.BaseEntity;

/**
 * AbstractMytable entity provides the base persistence definition of the
 * Mytable entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractMytable extends BaseEntity implements
		java.io.Serializable {

	// Fields

	private Integer mid;
	private String name;
	private Integer age;

	// Constructors

	/** default constructor */
	public AbstractMytable() {
	}

	/** full constructor */
	public AbstractMytable(String name, Integer age) {
		this.name = name;
		this.age = age;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "MID", unique = true, nullable = false)
	public Integer getMid() {
		return this.mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	@Column(name = "name", nullable = false, length = 20)
	public String getName() {
		return this.name;
	}
	@RequiredStringValidator(message = "请输入用户名!")
	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "age", nullable = false)
	public Integer getAge() {
		return this.age;
	}
	@RequiredStringValidator(message = "请输入密�?")
	public void setAge(Integer age) {
		this.age = age;
	}

}