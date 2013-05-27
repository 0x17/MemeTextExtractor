package org.andreschnabel.memetextextractor;

public class Meme {
	
	public String path;
	public String textDe;
	public String textEn;
	
	public Meme(String path, String textDe, String textEn) {
		super();
		this.path = path;
		this.textDe = textDe;
		this.textEn = textEn;
	}

	@Override
	public String toString() {
		return "Meme [path=" + path + ", textDe=" + textDe + ", textEn="
				+ textEn + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + ((textDe == null) ? 0 : textDe.hashCode());
		result = prime * result + ((textEn == null) ? 0 : textEn.hashCode());
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
		Meme other = (Meme) obj;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		if (textDe == null) {
			if (other.textDe != null)
				return false;
		} else if (!textDe.equals(other.textDe))
			return false;
		if (textEn == null) {
			if (other.textEn != null)
				return false;
		} else if (!textEn.equals(other.textEn))
			return false;
		return true;
	}
	
	
	

}
