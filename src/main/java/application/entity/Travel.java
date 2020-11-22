package application.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entidad Travel
 * Tiene una relación con la clase User
 * @author Grupo 10
 * @version v1.0
 */


@Entity
@Table(name = "travels")
public class Travel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String name;
	@Column
	private String destinity_city;
	// Agrego continente para hacer el reporte por zona geográfica, en este caso, por continente.
	@Column
	private String continent;
	@Column
	private Integer day_start_date;
	@Column
	private Integer month_start_date;
	@Column
	private Integer year_start_date;
	@Column
	private Integer day_end_date;
	@Column
	private Integer month_end_date;
	@Column
	private Integer year_end_date;
	@Column
	private String description;

	@ManyToOne
	private User user;

	@OneToMany(mappedBy = "travel")
	private List<Plan> plans;

	public Travel() {
	}

	public Travel(String name, String destinity_city, String continent, Integer day_start_date, Integer month_start_date, Integer year_start_date, Integer day_end_date, Integer month_end_date, Integer year_end_date, String description, User user) {
		this.name = name;
		this.destinity_city = destinity_city;
		this.continent = continent;
		this.day_start_date = day_start_date;
		this.month_start_date = month_start_date;
		this.year_start_date = year_start_date;
		this.day_end_date = day_end_date;
		this.month_end_date = month_end_date;
		this.year_end_date = year_end_date;
		this.description = description;
		this.user = user;
		this.plans = new ArrayList<Plan>();
		// TENGO QUE PASAR UNA LISTA DE PLANES???
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDestinity_city() {
		return destinity_city;
	}

	public void setDestinity_city(String destinity_city) {
		this.destinity_city = destinity_city;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public Integer getDay_start_date() {
		return day_start_date;
	}

	public void setDay_start_date(Integer day_start_date) {
		this.day_start_date = day_start_date;
	}

	public Integer getMonth_start_date() {
		return month_start_date;
	}

	public void setMonth_start_date(Integer month_start_date) {
		this.month_start_date = month_start_date;
	}

	public Integer getYear_start_date() {
		return year_start_date;
	}

	public void setYear_start_date(Integer year_start_date) {
		this.year_start_date = year_start_date;
	}

	public Integer getDay_end_date() {
		return day_end_date;
	}

	public void setDay_end_date(Integer day_end_date) {
		this.day_end_date = day_end_date;
	}

	public Integer getMonth_end_date() {
		return month_end_date;
	}

	public void setMonth_end_date(Integer month_end_date) {
		this.month_end_date = month_end_date;
	}

	public Integer getYear_end_date() {
		return year_end_date;
	}

	public void setYear_end_date(Integer year_end_date) {
		this.year_end_date = year_end_date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Plan> getPlans() {
		return plans;
	}

	public void setPlans(List<Plan> plans) {
		this.plans = plans;
	}

	public void addPlan(Plan p) {
		this.plans.add(p);
	}
}
