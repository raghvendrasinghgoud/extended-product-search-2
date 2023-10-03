package com.nagarro.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import com.nagarro.categories.Availability;
import com.nagarro.categories.Gender;
import com.nagarro.categories.Size;

@Entity
public class Tshirt implements Comparable<Tshirt> {
	@Id
	private String id;
	private String name;
	private String color;
	@Enumerated(EnumType.STRING)
	private Gender gender;
	@Enumerated(EnumType.STRING)
	private Size size;
	private float price;
	private float rating;
	@Enumerated(EnumType.STRING)
	private Availability availability;
	/**
	 * @param id
	 * @param name
	 * @param color
	 * @param gender
	 * @param size
	 * @param price
	 * @param rating
	 * @param availability
	 */
	public Tshirt(String id, String name, String color, Gender gender, Size size, float price, float rating,
			Availability availability) {
		super();
		this.id = id;
		this.name = name;
		this.color = color;
		this.gender = gender;
		this.size = size;
		this.price = price;
		this.rating = rating;
		this.availability = availability;
	}
	/**
	 * 
	 */
	public Tshirt() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Size getSize() {
		return size;
	}
	public void setSize(Size size) {
		this.size = size;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public Availability getAvailability() {
		return availability;
	}
	public void setAvailability(Availability availability) {
		this.availability = availability;
	}
	
	@Override
	public String toString() {
		return "Tshirt [id=" + id + ", name=" + name + ", color=" + color + ", gender=" + gender + ", size=" + size
				+ ", price=" + price + ", rating=" + rating + ", availability=" + availability + "]";
	}
	@Override
	public int compareTo(Tshirt o) {
		if(this.price<o.price) return 1;
		else if(this.price>o.price) return -1;
		else return 0;
	}
	
	
}
