package spring.jh.myapp.hr.model;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class EmpVO {

	@Min(value=207, message="사원번호는 207 이상")
	private int employeeId;
	@Pattern(regexp="[//w가-힣]+", message="이름 입력")
	@Size(max=10, message="데이터 베이스 제약조건 위배(20byte)")
	private String firstName;
	@Pattern(regexp="[//w가-힣]+", message="성 입력")
	@Size(max=12, message="데이터 베이스 제약조건 위배(25byte)")
	private String lastName;
	@Pattern(regexp="[a-zA-Z0-9](-|\\s)\\d{3,4}(-|\\s)\\d{4}$", message="핸드폰 번화번호 양식에 맞춰주세요")
	private String email;
	private String phoneNumber;
	@Past
	private java.sql.Date hireDate;
	private String jobId;
	@Digits(integer=6, fraction=2, message="잘못된 급여액(6자리 이상 불가)")
	private double salary;
	@DecimalMax(value="0.99", message="보너스율은 1 미만입니다.")
	private double commissionPct;
	private int managerId;
	private int departmentId;
	private String jobTitle;

	
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public java.sql.Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(java.sql.Date hireDate) {
		this.hireDate = hireDate;
	}
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public double getCommissionPct() {
		return commissionPct;
	}
	public void setCommissionPct(double commissionPct) {
		this.commissionPct = commissionPct;
	}
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	@Override
	public String toString() {
		return "EmpVO [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", phoneNumber=" + phoneNumber + ", hireDate=" + hireDate + ", jobId=" + jobId + ", salary="
				+ salary + ", commissionPct=" + commissionPct + ", managerId=" + managerId + ", departmentId="
				+ departmentId + ", jobTitle=" + jobTitle + "]";
	}
	

	


	


	


	
	
}
