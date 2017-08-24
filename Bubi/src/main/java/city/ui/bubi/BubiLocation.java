package city.ui.bubi;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class BubiLocation implements Serializable {
	
	private long id;
	
	private double lat;
	
	private double lon;
	
	private String name;
	
	private String code;
	
	private String type;
	
	private int bikes;
	
	private int spaces;
	
	public BubiLocation() {
		
	}

	public static final class Builder {
		private long id;
		
		private double lat;
		
		private double lon;
		
		private String name;
		
		private String code;
		
		private String type;
		
		private int bikes;
		
		private int spaces;
		
		
        
		public Builder setId(long id) {
			this.id = id;
			return this;
		}
		
		public Builder setLat(double lat) {
			this.lat = lat;
			return this;
		}

		public Builder setLon(double lon) {
			this.lon = lon;
			return this;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setCode(String code) {
			this.code = code;
			return this;
		}

		public Builder setType(String type) {
			this.type = type;
			return this;
		}

		public Builder setBikes(int bikes) {
			this.bikes = bikes;
			return this;
		}

		public Builder setSpaces(int spaces) {
			this.spaces = spaces;
			return this;
		}

        public BubiLocation build() { return new BubiLocation(this); }
    }
	
	public BubiLocation(final Builder builder) {

		id = builder.id;
		lat = builder.lat;
		lon = builder.lon;
		name = builder.name;
		code = builder.code;
		type = builder.type;
		bikes = builder.bikes;
		spaces = builder.spaces;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setBikes(int bikes) {
		this.bikes = bikes;
	}

	public void setSpaces(int spaces) {
		this.spaces = spaces;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bikes;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		long temp;
		temp = Double.doubleToLongBits(lat);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(lon);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + spaces;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BubiLocation other = (BubiLocation) obj;
		if (bikes != other.bikes)
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(lat) != Double.doubleToLongBits(other.lat))
			return false;
		if (Double.doubleToLongBits(lon) != Double.doubleToLongBits(other.lon))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (spaces != other.spaces)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BubiLocation [id=" + id + ", lat=" + lat + ", lon=" + lon + ", name=" + name + ", code=" + code
				+ ", type=" + type + ", bikes=" + bikes + ", spaces=" + spaces + "]";
	} 
	
	
}
