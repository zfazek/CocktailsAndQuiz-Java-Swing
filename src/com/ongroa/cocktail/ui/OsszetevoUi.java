package com.ongroa.cocktail.ui;

import javax.swing.JComboBox;

public class OsszetevoUi {

	private JComboBox<String> cbMennyiseg;
	private JComboBox<String> cbName;

	public OsszetevoUi(JComboBox<String> mennyiseg, 
			JComboBox<String> name) {
		cbMennyiseg = mennyiseg;
		cbName = name;
	}
	
	public JComboBox<String> getCbMennyiseg() {
		return cbMennyiseg;
	}
	
	public void setCbMennyiseg(JComboBox<String> cbMennyiseg) {
		this.cbMennyiseg = cbMennyiseg;
	}
	
	public JComboBox<String> getCbName() {
		return cbName;
	}
	
	public void setCbName(JComboBox<String> cbName) {
		this.cbName = cbName;
	}
	
}
