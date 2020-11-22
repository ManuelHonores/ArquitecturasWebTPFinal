package application.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entidad Fly, va a heredar de la clase Plan.
 * @author Grupo 10
 * @version v1.0
 */

@Entity
@Table(name= "flies")
public class Fly extends Plan{
	@Column
	private String company;
	@Column
	private Integer day_departure_date;
	@Column
	private Integer month_departure_date;
	@Column
	private Integer year_departure_date;
	@Column
	private int departure_hour;
	@Column
	private String departure_airport;
	@Column
	private Integer day_return_date;
	@Column
	private Integer month_return_date;
	@Column
	private Integer year_return_date;
	@Column
	private int return_hour;
	@Column
	private String return_airport;
	@Column
	private String reserve_code;
	@Column
	private String info_airplane;
	@Column
	private int scale_time;

	public Fly(){}

	public Fly(String company, Integer day_departure_date, Integer month_departure_date, Integer year_departure_date, int departure_hour, String departure_airport, Integer day_return_date, Integer month_return_date, Integer year_return_date, int return_hour, String return_airport, String reserve_code, String info_airplane, int scale_time, String place, int duration, String name, String description, String country, Travel travel, Integer day_start_date, Integer month_start_date, Integer year_start_date, Integer day_end_date, Integer month_end_date, Integer year_end_date, boolean finished) {
		super(name, description, country, travel, day_start_date, month_start_date, year_start_date, day_end_date, month_end_date, year_end_date, finished);
		this.company = company;
		this.day_departure_date = day_departure_date;
		this.month_departure_date = month_departure_date;
		this.year_departure_date = year_departure_date;
		this.departure_hour = departure_hour;
		this.departure_airport = departure_airport;
		this.day_return_date = day_return_date;
		this.month_return_date = month_return_date;
		this.year_return_date = year_return_date;
		this.return_hour = return_hour;
		this.return_airport = return_airport;
		this.reserve_code = reserve_code;
		this.info_airplane = info_airplane;
		this.scale_time = scale_time;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Integer getDay_departure_date() {
		return day_departure_date;
	}

	public void setDay_departure_date(Integer day_departure_date) {
		this.day_departure_date = day_departure_date;
	}

	public Integer getMonth_departure_date() {
		return month_departure_date;
	}

	public void setMonth_departure_date(Integer month_departure_date) {
		this.month_departure_date = month_departure_date;
	}

	public Integer getYear_departure_date() {
		return year_departure_date;
	}

	public void setYear_departure_date(Integer year_departure_date) {
		this.year_departure_date = year_departure_date;
	}

	public int getDeparture_hour() {
		return departure_hour;
	}

	public void setDeparture_hour(int departure_hour) {
		this.departure_hour = departure_hour;
	}

	public String getDeparture_airport() {
		return departure_airport;
	}

	public void setDeparture_airport(String departure_airport) {
		this.departure_airport = departure_airport;
	}

	public Integer getDay_return_date() {
		return day_return_date;
	}

	public void setDay_return_date(Integer day_return_date) {
		this.day_return_date = day_return_date;
	}

	public Integer getMonth_return_date() {
		return month_return_date;
	}

	public void setMonth_return_date(Integer month_return_date) {
		this.month_return_date = month_return_date;
	}

	public Integer getYear_return_date() {
		return year_return_date;
	}

	public void setYear_return_date(Integer year_return_date) {
		this.year_return_date = year_return_date;
	}

	public int getReturn_hour() {
		return return_hour;
	}

	public void setReturn_hour(int return_hour) {
		this.return_hour = return_hour;
	}

	public String getReturn_airport() {
		return return_airport;
	}

	public void setReturn_airport(String return_airport) {
		this.return_airport = return_airport;
	}

	public String getReserve_code() {
		return reserve_code;
	}

	public void setReserve_code(String reserve_code) {
		this.reserve_code = reserve_code;
	}

	public String getInfo_airplane() {
		return info_airplane;
	}

	public void setInfo_airplane(String info_airplane) {
		this.info_airplane = info_airplane;
	}

	public int getScale_time() {
		return scale_time;
	}

	public void setScale_time(int scale_time) {
		this.scale_time = scale_time;
	}
}
