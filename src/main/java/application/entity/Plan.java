package application.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

/**
 * Entidad Plan
 * Clase padre de los tipos de planes (Excursion, Fly, Hotel)
 * Tiene una relación con la clase Travel
 * @author Grupo 10
 * @version v1.0
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "plans")
public class Plan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	@Column
	protected String name;
	@Column
	protected String description;
	@Column
	protected boolean finished;
	@Column
	protected String country;
	@Column
	protected Integer day_start;
	@Column
	protected Integer month_start;
	@Column
	protected Integer year_start;
	@Column
	protected Integer day_end;
	@Column
	protected Integer month_end;
	@Column
	protected Integer year_end;
	
	@ManyToOne
	private Travel travel;

	public Plan(String name, String description, String country, Travel travel, Integer day_start, Integer month_start, Integer year_start, Integer day_end, Integer month_end, Integer year_end, boolean finished) {
		this.name = name;
		this.description = description;
		this.finished = finished;
		this.country = country;
		this.day_start = day_start;
		this.month_start = month_start;
		this.year_start = year_start;
		this.day_end = day_end;
		this.month_end = month_end;
		this.year_end = year_end;
		this.travel = travel;
	}

	public Plan() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getDay_start() {
		return day_start;
	}

	public void setDay_start(Integer day_start) {
		this.day_start = day_start;
	}

	public Integer getMonth_start() {
		return month_start;
	}

	public void setMonth_start(Integer month_start) {
		this.month_start = month_start;
	}

	public Integer getYear_start() {
		return year_start;
	}

	public void setYear_start(Integer year_start) {
		this.year_start = year_start;
	}

	public Integer getDay_end() {
		return day_end;
	}

	public void setDay_end(Integer day_end) {
		this.day_end = day_end;
	}

	public Integer getMonth_end() {
		return month_end;
	}

	public void setMonth_end(Integer month_end) {
		this.month_end = month_end;
	}

	public Integer getYear_end() {
		return year_end;
	}

	public void setYear_end(Integer year_end) {
		this.year_end = year_end;
	}

	public void setTravel(Travel t) {
		this.travel = t;
	}

}
