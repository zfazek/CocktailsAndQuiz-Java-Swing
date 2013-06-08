package com.ongroa.cocktail.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import com.ongroa.cocktail.CocktailsAndQuiz;

public class UiSwing 
extends JFrame 
implements ActionListener {
	private static final long serialVersionUID = 1L;

	private CocktailsAndQuiz mMain;

	private JButton buttonAdd;
	private JButton buttonExit;
	private JButton buttonQuiz;
	
	private SpinnerNumberModel model;
	private JSpinner spinner;

	public UiSwing(CocktailsAndQuiz main) {
		mMain = main;
		createGUI();
	}

	private void createGUI() {
		setVisible(true);
		setTitle("Cocktail And Quiz");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		model = new SpinnerNumberModel(1, 1, 10, 1);
		buttonAdd = new JButton("Add");
		buttonAdd.addActionListener(this);
		buttonQuiz = new JButton("Quiz");
		buttonQuiz.addActionListener(this);
		buttonExit = new JButton("Exit");
		buttonExit.addActionListener(this);
		FlowLayout layoutNorth = new FlowLayout(FlowLayout.LEFT);
		FlowLayout layoutSouth = new FlowLayout(FlowLayout.RIGHT);
		JPanel panelNorth = new JPanel();
		panelNorth.setLayout(layoutNorth);
		spinner = new JSpinner(model);
		panelNorth.add(new JLabel("Number of quiz questions:"));
		panelNorth.add(spinner);
		JPanel panelSouth = new JPanel();
		panelSouth.setLayout(layoutSouth);
		panelSouth.add(buttonAdd);
		panelSouth.add(buttonQuiz);
		panelSouth.add(buttonExit);
		add(panelNorth, BorderLayout.NORTH);
		add(panelSouth, BorderLayout.SOUTH);
		pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == buttonAdd) {
			mMain.addNewCocktail();
		}
		if (source == buttonQuiz) {
			mMain.setNofQuizCocktails((Integer)spinner.getValue());
			mMain.startQuiz();
		}
		if (source == buttonExit) {
			this.dispose();
		}
	}

}
