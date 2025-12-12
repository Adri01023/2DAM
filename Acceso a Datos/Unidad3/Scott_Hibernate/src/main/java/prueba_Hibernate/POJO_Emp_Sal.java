package prueba_Hibernate;

public class POJO_Emp_Sal {
	String ename;
	Integer quantity;
	public POJO_Emp_Sal(String ename, Integer quantity) {
		this.ename = ename;
		this.quantity = quantity;
	}
	
	public String getEname() {
		return ename;
	}
	
	public void setEname(String ename) {
		this.ename = ename;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return ename + " : " + quantity;
	}
	
}
