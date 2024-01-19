package beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import core.MockCore;
import core.Student;


@ManagedBean
@RequestScoped
public class SearchBean implements Serializable {
	private static final long serialVersionUID = -426721429642192283L;
	private long studentNumber;
	private Student student;

	public SearchBean() {
	}

	public String search() {
		student = MockCore.getStudent(); // Hard coded simulation, not really searching !
		return "score";
	}

	// This method is used by the web page to validate the input student number.
	public void validateNumber(FacesContext context, UIComponent componentToValidate, Object value) throws ValidatorException {
		long number = (Long) value;

		if (number < 20199999) {
			System.out.println("validated");
			FacesMessage message = new FacesMessage("Cannot find scores of students before 2001");
			throw new ValidatorException(message);
		}
	}

	public long getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(long studentNumber) {
		this.studentNumber = studentNumber;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
}
