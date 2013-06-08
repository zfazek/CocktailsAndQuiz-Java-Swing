package com.ongroa.cocktail;

public class Osszetevo {
	
	public static String MENNYISEG = "mennyiseg";
	public static String UNIT = "unit";
	public static String NAME = "name";
	
	private String mennyiseg;
	private String unit;
	private String nev;

	public Osszetevo() {
		
	}
	
	public Osszetevo(String m, String u, String n) {
		this.mennyiseg = m;
		this.unit = u;
		this.nev = n;
	}
	
	public String getMennyiseg() {
		return mennyiseg;
	}
	
	public void setMennyiseg(String mennyiseg) {
		this.mennyiseg = mennyiseg;
	}
	
	public String getUnit() {
		return unit;
	}
	
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public String getNev() {
		return nev;
	}
	
	public void setNev(String name) {
		this.nev = name;
	}
	
	@Override
	public String toString() {
		String ret = String.format("\tMennyiseg: %s,  Unit: %s, Nev: %s\n",
				mennyiseg, unit, nev);
		return ret;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj == this) return true;
		if (! (obj instanceof Osszetevo)) return false;
		Osszetevo o = (Osszetevo)obj;
		return o.mennyiseg.equals(this.mennyiseg) &&
				o.unit.equals(this.unit) &&
				o.nev.equals(this.nev);
	}
}
