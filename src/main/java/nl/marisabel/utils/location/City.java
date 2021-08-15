
package nl.marisabel.utils.location;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class City {

	@SerializedName("geonameid")
	@Expose
	private Integer geonameid;
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("population")
	@Expose
	private Integer population;

	public Integer getGeonameid() {
		return geonameid;
	}

	public void setGeonameid(Integer geonameid) {
		this.geonameid = geonameid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPopulation() {
		return population;
	}

	public void setPopulation(Integer population) {
		this.population = population;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(City.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)))
				.append('[');
		sb.append("geonameid");
		sb.append('=');
		sb.append(((this.geonameid == null) ? "<null>" : this.geonameid));
		sb.append(',');
		sb.append("name");
		sb.append('=');
		sb.append(((this.name == null) ? "<null>" : this.name));
		sb.append(',');
		sb.append("population");
		sb.append('=');
		sb.append(((this.population == null) ? "<null>" : this.population));
		sb.append(',');
		if (sb.charAt((sb.length() - 1)) == ',') {
			sb.setCharAt((sb.length() - 1), ']');
		} else {
			sb.append(']');
		}
		return sb.toString();
	}

}
