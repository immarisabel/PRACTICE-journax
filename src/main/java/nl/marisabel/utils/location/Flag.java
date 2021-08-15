
package nl.marisabel.utils.location;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Flag {

	@SerializedName("file")
	@JsonIgnore
	private String file;
	@SerializedName("emoji")
	@JsonIgnore
	private String emoji;
	@SerializedName("unicode")
	@JsonIgnore
	private String unicode;

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getEmoji() {
		return emoji;
	}

	public void setEmoji(String emoji) {
		this.emoji = emoji;
	}

	public String getUnicode() {
		return unicode;
	}

	public void setUnicode(String unicode) {
		this.unicode = unicode;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(Flag.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)))
				.append('[');
		sb.append("file");
		sb.append('=');
		sb.append(((this.file == null) ? "<null>" : this.file));
		sb.append(',');
		sb.append("emoji");
		sb.append('=');
		sb.append(((this.emoji == null) ? "<null>" : this.emoji));
		sb.append(',');
		sb.append("unicode");
		sb.append('=');
		sb.append(((this.unicode == null) ? "<null>" : this.unicode));
		sb.append(',');
		if (sb.charAt((sb.length() - 1)) == ',') {
			sb.setCharAt((sb.length() - 1), ']');
		} else {
			sb.append(']');
		}
		return sb.toString();
	}

}
