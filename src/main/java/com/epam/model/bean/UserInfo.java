package com.epam.model.bean;

import java.io.Serializable;
import java.util.Date;

import com.epam.model.annotation.ColumnName;
import com.epam.model.annotation.CompositionModel;
import com.epam.model.annotation.Model;

@Model(name = "user_info")
public class UserInfo implements Serializable {

	private static final long serialVersionUID = -4952876483224668288L;

	@ColumnName(name = "id_user")
	private Integer idUser = null;

	@ColumnName(name = "id_image")
	private Integer idImage = null;

	@CompositionModel(name = "image")
	private Image image;

	@ColumnName(name = "first_name")
	private String firstName;

	@ColumnName(name = "last_name")
	private String lastName;

	@ColumnName(name = "gender")
	private String gender;

	@ColumnName(name = "country")
	private String country;

	@ColumnName(name = "city")
	private String city;

	@ColumnName(name = "description")
	private String description;

	@ColumnName(name = "date_of_birth")
	private Date dateOfBirth;

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Integer getIdImageUser() {
		return idImage;
	}

	public void setIdImageUser(Integer idImageUser) {
		this.idImage = idImageUser;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
}
