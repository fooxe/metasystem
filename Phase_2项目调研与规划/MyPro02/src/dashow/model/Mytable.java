package dashow.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import dashow.struts2.forms.AbstractMytable;

/**
 * Mytable entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "mytable", catalog = "ecommerce")
public class Mytable extends AbstractMytable implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Mytable() {
	}

	/** full constructor */
	public Mytable(String name, Integer age) {
		super(name, age);
	}

	@Override
	public Mytable clone()  {
		// TODO Auto-generated method stub
		Mytable mytable=null;
		try {
			mytable=(Mytable)super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return mytable;
	}

}
