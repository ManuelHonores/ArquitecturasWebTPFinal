package application.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entidad Excursion, va a heredar de la clase Plan.
 * @author Grupo 10
 * @version v1.0
 */

@Entity
@Table(name= "excursions")
public class Excursion extends Plan{

	@Column
	private String place;
	@Column
	private int duration;
	
	public Excursion() {
		
	}

	public Excursion(String place, int duration, String name, String description, String country, Travel travel, Integer day_start_date, Integer month_start_date, Integer year_start_date, Integer day_end_date, Integer month_end_date, Integer year_end_date, boolean finished) {
		super(name, description, country, travel, day_start_date, month_start_date, year_start_date, day_end_date, month_end_date, year_end_date, finished);
		this.place = place;
		this.duration = duration;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
}
