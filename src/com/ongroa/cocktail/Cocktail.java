package com.ongroa.cocktail;

import java.util.ArrayList;
import java.util.List;

public class Cocktail {

	public static final String NAME = "name";
	public static final String ALAPSZESZ = "alapszesz";
	public static final String POHAR = "pohar";
	public static final String DISZ = "disz";
	public static final String FAJTA = "fajta";
	public static final String OSSZETEVOK = "osszetevok";

	private String name;
	private String alapszesz;
	private String pohar;
	private String diszites;
	private String fajta;
	private List<Osszetevo> osszetevok;

	public Cocktail() {
		osszetevok = new ArrayList<Osszetevo>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlapszesz() {
		return alapszesz;
	}

	public void setAlapszesz(String alapszesz) {
		this.alapszesz = alapszesz;
	}

	public String getPohar() {
		return pohar;
	}

	public void setPohar(String pohar) {
		this.pohar = pohar;
	}

	public String getDiszites() {
		return diszites;
	}

	public void setDiszites(String diszites) {
		this.diszites = diszites;
	}

	public String getFajta() {
		return fajta;
	}

	public void setFajta(String fajta) {
		this.fajta = fajta;
	}

	public List<Osszetevo> getOsszetevok() {
		return osszetevok;
	}

	public void setOsszetevok(List<Osszetevo> osszetevok) {
		this.osszetevok = osszetevok;
	}

	public void addOsszetevo(Osszetevo o) {
		osszetevok.add(o);
	}

	@Override
	public String toString() {
		String ret = String.format("Nev: %s, Alapszesz: %s, Pohar: %s, Diszites: %s, Fajta: %s\n",
				name, alapszesz, pohar, diszites, fajta);
		ret += "Osszetevok:\n";
		for (Osszetevo o : osszetevok) {
			ret += o.toString();
		}
		return ret;
	}

	@Override
	public boolean equals(Object obj) {
		boolean osszetevokEquals = true;
		if (obj == null) return false;
		if (obj == this) return true;
		if (! (obj instanceof Cocktail)) return false;
		Cocktail o = (Cocktail)obj;
		return o.alapszesz.equals(this.alapszesz) &&
				o.pohar.equals(this.pohar) &&
				o.diszites.equals(this.diszites) &&
				o.fajta.equals(this.fajta) &&
				osszetevokEquals;
	}
}