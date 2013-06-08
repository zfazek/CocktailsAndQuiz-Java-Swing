package com.ongroa.cocktail.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ongroa.cocktail.Cocktail;
import com.ongroa.cocktail.CocktailsAndQuiz;
import com.ongroa.cocktail.Osszetevo;

@SuppressWarnings("serial")
public class AddCocktailSwing
extends JFrame
implements ActionListener {

	private CocktailsAndQuiz mMain;
	private int MAX_NOF_COCKTAILS = 9;
	//	private int NOF_COCKTAILS = 0;
	private int quizIndex;
	private List<Integer> idxs;

	private JComboBox<String> cbName;
	private JComboBox<String> cbAlapszesz;
	private JComboBox<String> cbPohar;
	private List<OsszetevoUi> mOsszetevokUi;
	private JComboBox<String> cbDisz;
	private JComboBox<String> cbFajta;
	private JButton buttonClose;
	private JButton buttonOk;
	private JButton buttonDelete;
	private JLabel labelIdx;
	private JTextField textName;

	public AddCocktailSwing(CocktailsAndQuiz main) {
		mMain = main;
		mOsszetevokUi = new ArrayList<OsszetevoUi>();
		mMain.setNofGoodAnswers(0);
		mMain.setNofGoodCocktails(0);
		quizIndex = 0;
		createGui();
		addItems();
		if (mMain.getMode() == CocktailsAndQuiz.QUIZ_MODE) {
			idxs = mMain.pickRandomCocktails();
			textName.setText(mMain.getCocktails().
					get(idxs.get(quizIndex)).getName());
		}
		pack();
	}

	private void addItems() {
		List<String> l = null;
		l = mMain.getNevek();
		cbName.addItem("");
		for (String string : l) {
			cbName.addItem(string);
		}
		l = mMain.getAlapszeszek();
		cbAlapszesz.addItem("");
		for (String string : l) {
			cbAlapszesz.addItem(string);
		}
		l = mMain.getPoharak();
		cbPohar.addItem("");
		for (String string : l) {
			cbPohar.addItem(string);
		}
		l = mMain.getDiszitesek();
		cbDisz.addItem("");
		for (String string : l) {
			cbDisz.addItem(string);
		}
		l = mMain.getFajtak();
		cbFajta.addItem("");
		for (String string : l) {
			cbFajta.addItem(string);
		}
		l = mMain.getMennyisegek();
		for (OsszetevoUi o : mOsszetevokUi) {
			o.getCbMennyiseg().addItem("");
			for (String string : l) {
				o.getCbMennyiseg().addItem(string);
			}
		}
		l = mMain.getOsszetevoNevek();
		for (OsszetevoUi o : mOsszetevokUi) {
			o.getCbName().addItem("");
			for (String string : l) {
				o.getCbName().addItem(string);
			}
		}
	}

	private void createGui() {
		boolean editable = true;
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panelCenter = new JPanel();
		panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.PAGE_AXIS));

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel.add(new JLabel(mMain.NEV));
		cbName = new JComboBox<String>();
		if (mMain.getMode() == CocktailsAndQuiz.ADD_MODE) {
			setTitle("Add New Cocktail");
			cbName.setEditable(editable);
			cbName.addActionListener(this);
			panel.add(cbName);
			labelIdx = new JLabel(mMain.getCocktails().size() + 
					" db. koktél");
			panel.add(labelIdx);
		}
		textName = new JTextField("", 30);
		if (mMain.getMode() == CocktailsAndQuiz.QUIZ_MODE) {
			editable = false;
			setTitle("Cocktail Quiz");
			textName.setEditable(editable);
			panel.add(textName);
			labelIdx = new JLabel(quizIndex + 1 + ". koktél");
			panel.add(labelIdx);
		}
		panelCenter.add(panel);

		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel.add(new JLabel(mMain.ALAPSZESZ));
		cbAlapszesz = new JComboBox<String>();
		cbAlapszesz.setEditable(editable);
		panel.add(cbAlapszesz);
		panelCenter.add(panel);

		panel.add(new JLabel(mMain.POHAR));
		cbPohar = new JComboBox<String>();
		cbPohar.setEditable(editable);
		panel.add(cbPohar);
		panelCenter.add(panel);

		for (int i = 0; i < MAX_NOF_COCKTAILS; i++) {
			JPanel panelCocktail = new JPanel();
			panelCocktail.setLayout(new FlowLayout(FlowLayout.LEFT));
			JComboBox<String> cbMennyiseg = new JComboBox<String>();
			cbMennyiseg.setEditable(editable);
			JComboBox<String> cbUnit = new JComboBox<String>();
			cbUnit.setEditable(editable);
			JComboBox<String> cbName = new JComboBox<String>();
			cbName.setEditable(editable);
			OsszetevoUi osszetevoUi = new OsszetevoUi(cbMennyiseg, 	cbName);
			panelCocktail.add(new JLabel(i+1+". összetevő: "));
			panelCocktail.add(new JLabel(mMain.MENNYISEG));
			panelCocktail.add(cbMennyiseg);
//			panelCocktail.add(new JLabel(mMain.UNIT));
//			panelCocktail.add(cbUnit);
			panelCocktail.add(new JLabel(mMain.NEV));
			panelCocktail.add(cbName);
			mOsszetevokUi.add(osszetevoUi);
			panelCenter.add(panelCocktail);
		}

		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel.add(new JLabel(mMain.DISZITES));
		cbDisz = new JComboBox<String>();
		cbDisz.setEditable(editable);
		panel.add(cbDisz);
		panelCenter.add(panel);

		panel.add(new JLabel(mMain.FAJTA));
		cbFajta = new JComboBox<String>();
		cbFajta.setEditable(editable);
		panel.add(cbFajta);
		panelCenter.add(panel);

		JPanel panelSouth = new JPanel();
		FlowLayout layoutSouth = new FlowLayout(FlowLayout.RIGHT);
		panelSouth.setLayout(layoutSouth);
		if (mMain.getMode() == CocktailsAndQuiz.ADD_MODE) {
			buttonDelete = new JButton("Delete");
			panelSouth.add(buttonDelete);
			buttonDelete.addActionListener(this);
		}
		buttonClose = new JButton("Close");
		buttonClose.addActionListener(this);
		buttonOk = new JButton("Ok");
		buttonOk.addActionListener(this);
		panelSouth.add(buttonClose);
		panelSouth.add(buttonOk);

		add(panelCenter, BorderLayout.CENTER);
		add(panelSouth, BorderLayout.SOUTH);
		//		addDummyValues();
		pack();
	}

	@SuppressWarnings("unused")
	private void addDummyValues() {
		cbName.addItem("a");
		cbAlapszesz.addItem("b");
		cbPohar.addItem("c");
		cbDisz.addItem("d");
		cbFajta.addItem("e");
		mOsszetevokUi.get(0).getCbMennyiseg().addItem("1");
		mOsszetevokUi.get(0).getCbName().addItem("n");
		mOsszetevokUi.get(1).getCbMennyiseg().addItem("2");
		mOsszetevokUi.get(1).getCbName().addItem("nn");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == buttonOk) {
			if (mMain.getMode() == CocktailsAndQuiz.ADD_MODE) {
				addNewCocktail();
			}
			if (mMain.getMode() == CocktailsAndQuiz.QUIZ_MODE) {
				addNewCocktailToQuiz();
				if (mMain.getQuizCocktails().size() == 
						mMain.getNofQuizCocktails()) {
					this.dispose();
					mMain.evaluateQuiz();
				} else {
					quizIndex++;
					clearComboBoxes();
					textName.setText(mMain.getCocktails().
							get(idxs.get(quizIndex)).getName());
					labelIdx.setText(quizIndex + 1 + ". koktél");
				}
			}
		}
		if (source == buttonDelete) {
			if (! cbName.getSelectedItem().toString().equals("")) {
				int ret = JOptionPane.showConfirmDialog(this,
						"Are you sure you want to delete this cocktail?",
						"Warning",
						JOptionPane.YES_NO_OPTION);
				if (ret == 0) {
					mMain.removeCocktail(cbName.getSelectedItem().toString());
					this.dispose();
				}
			}
		}
		if (source == buttonClose) {
			this.dispose();
		}
		if (source == cbName) {
			fillCocktail();
		}
	}

	private void fillCocktail() {
		Cocktail cocktail = mMain.getCocktail(cbName.getSelectedItem().
				toString());
		if (cocktail == null) {
			cocktail = new Cocktail();
			//			cocktail.setAlapszesz("");
			//			cocktail.setPohar("");
			//			cocktail.setDiszites("");
			//			cocktail.setFajta("");
			//			for (int i = 0; i < MAX_NOF_COCKTAILS; i++) {
			//				Osszetevo osszetevo = new Osszetevo();
			//				osszetevo.setMennyiseg("");
			//				osszetevo.setUnit("");
			//				osszetevo.setNev("");
			//				cocktail.addOsszetevo(osszetevo);
			//			}
		}
		cbAlapszesz.setSelectedItem(cocktail.getAlapszesz());
		cbPohar.setSelectedItem(cocktail.getPohar());
		cbDisz.setSelectedItem(cocktail.getDiszites());
		cbFajta.setSelectedItem(cocktail.getFajta());
		for (int i = 0; i < MAX_NOF_COCKTAILS; i++) {
			mOsszetevokUi.get(i).getCbMennyiseg().setSelectedItem("");
			mOsszetevokUi.get(i).getCbName().setSelectedItem("");
		}
		for (int i = 0; i < cocktail.getOsszetevok().size(); i++) {
			mOsszetevokUi.get(i).getCbMennyiseg().setSelectedItem(
					cocktail.getOsszetevok().get(i).getMennyiseg());
			mOsszetevokUi.get(i).getCbName().setSelectedItem(
					cocktail.getOsszetevok().get(i).getNev());
		}
	}

	private void addNewCocktail() {
		if (! cbName.getSelectedItem().toString().equals("") &&
				! cbAlapszesz.getSelectedItem().equals("") &&
				! cbPohar.getSelectedItem().equals("") &&
				! cbDisz.getSelectedItem().equals("") &&
				! cbFajta.getSelectedItem().equals("")) {
			Cocktail cocktail = getCocktailFromGui();
			// modify
			if (mMain.getNevek().contains(cbName.getSelectedItem().toString())) { 
				int ret = JOptionPane.showConfirmDialog(this,
						"Are you sure you want to modify this cocktail?",
						"Warning",
						JOptionPane.YES_NO_OPTION);
				if (ret == 0) {
					mMain.removeCocktail(cbName.getSelectedItem().toString());
					mMain.addCocktailAndWriteToFile(cocktail);
				}
				// add
			} else {
				mMain.addCocktailAndWriteToFile(cocktail);
				this.dispose();
			}
		}
	}

	private Cocktail getCocktailFromGui() {
		Cocktail cocktail = new Cocktail();
		cocktail.setName(cbName.getSelectedItem().toString());
		cocktail.setAlapszesz(cbAlapszesz.getSelectedItem().toString());
		cocktail.setPohar(cbPohar.getSelectedItem().toString());
		cocktail.setDiszites(cbDisz.getSelectedItem().toString());
		cocktail.setFajta(cbFajta.getSelectedItem().toString());
		int i = 0;
		while (i < MAX_NOF_COCKTAILS &&
				! mOsszetevokUi.get(i).getCbName().getSelectedItem().
				equals("") &&
				! mOsszetevokUi.get(i).getCbMennyiseg().getSelectedItem().
				equals("")) {
			Osszetevo osszetevo = new Osszetevo();
			osszetevo.setMennyiseg(mOsszetevokUi.get(i).getCbMennyiseg().
					getSelectedItem().toString());
			osszetevo.setNev(mOsszetevokUi.get(i).getCbName().
					getSelectedItem().toString());
			cocktail.addOsszetevo(osszetevo);
			i++;
		}
		return cocktail;
	}

	private void addNewCocktailToQuiz() {
		Cocktail cocktail = new Cocktail();
		cocktail.setName(textName.getText());
		cocktail.setAlapszesz(cbAlapszesz.getSelectedItem().toString());
		cocktail.setPohar(cbPohar.getSelectedItem().toString());
		cocktail.setDiszites(cbDisz.getSelectedItem().toString());
		cocktail.setFajta(cbFajta.getSelectedItem().toString());
		int i = 0;
		while (i < MAX_NOF_COCKTAILS &&
				! mOsszetevokUi.get(i).getCbName().getSelectedItem().
				equals("") &&
				! mOsszetevokUi.get(i).getCbMennyiseg().getSelectedItem().
				equals("")) {
			Osszetevo osszetevo = new Osszetevo();
			osszetevo.setMennyiseg(mOsszetevokUi.get(i).getCbMennyiseg().
					getSelectedItem().toString());
			osszetevo.setNev(mOsszetevokUi.get(i).getCbName().
					getSelectedItem().toString());
			cocktail.addOsszetevo(osszetevo);
			i++;
		}
		mMain.addCocktailToQuiz(cocktail);
	}

	private void clearComboBoxes() {
		cbAlapszesz.setSelectedIndex(0);
		cbPohar.setSelectedIndex(0);
		cbDisz.setSelectedIndex(0);
		cbFajta.setSelectedIndex(0);
		for (int i = 0; i < MAX_NOF_COCKTAILS; i++) {
			mOsszetevokUi.get(i).getCbMennyiseg().setSelectedIndex(0);
			mOsszetevokUi.get(i).getCbName().setSelectedIndex(0);
		}
	}
}
