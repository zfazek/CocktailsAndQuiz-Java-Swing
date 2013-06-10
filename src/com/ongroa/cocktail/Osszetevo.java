package com.ongroa.cocktail;

public class Osszetevo implements Comparable<Osszetevo> {
	
	public static String MENNYISEG = "mennyiseg";
	public static String UNIT = "unit";
	public static String NAME = "name";
	
	private String mennyiseg;
	private String nev;
	private boolean valid;

	public Osszetevo() {
		
	}
	
	public Osszetevo(String m, String n) {
		this.mennyiseg = m;
		this.nev = n;
	}
	
	public String getMennyiseg() {
		return mennyiseg;
	}
	
	public void setMennyiseg(String mennyiseg) {
		this.mennyiseg = mennyiseg;
	}
	
	public String getNev() {
		return nev;
	}
	
	public void setNev(String name) {
		this.nev = name;
	}
	
	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	@Override
	public String toString() {
		String ret = String.format("\tÖsszetevő: %s %s\n",
				mennyiseg, nev);
		return ret;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj == this) return true;
		if (! (obj instanceof Osszetevo)) return false;
		Osszetevo o = (Osszetevo)obj;
		return o.mennyiseg.equals(this.mennyiseg) &&
				o.nev.equals(this.nev);
	}

	@Override
	public int compareTo(Osszetevo o) {
		return this.nev.compareToIgnoreCase(o.nev);
	}

}
