package application.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entidad Hotel, va a heredar de la clase Plan.
 * @author Grupo 10
 * @version v1.0
 */

@Entity
@Table(name= "hotels")
public class Hotel extends Plan{

	@Column
	private String nameHotel;

	public Hotel(String name, String description, String country, Travel travel, Integer day_start_date, Integer month_start_date, Integer year_start_date, Integer day_end_date, Integer month_end_date, Integer year_end_date, String nameHotel, boolean finished) {
		super(name, description, country, travel, day_start_date, month_start_date, year_start_date, day_end_date, month_end_date, year_end_date, finished);
		this.nameHotel = nameHotel;
	}

	public Hotel() {
	}

	public String getNameHotel() {
		return nameHotel;
	}

	public void setNameHotel(String name) {
		this.nameHotel = name;
	}

}
