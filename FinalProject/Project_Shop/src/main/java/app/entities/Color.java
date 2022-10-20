package app.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Color")
public class Color {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "colorId")
	private Integer colorId ;
	@Column(name = "colorName")
	private String colorName;
	@Column(name = "Status")
	private Boolean status;
	
	@OneToMany(mappedBy = "color",fetch = FetchType.EAGER)
	private Set<ProductColor> productColors;
}
