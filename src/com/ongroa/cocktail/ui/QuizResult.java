package com.ongroa.cocktail.ui;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.ongroa.cocktail.Cocktail;
import com.ongroa.cocktail.CocktailsAndQuiz;

@SuppressWarnings("serial")
public class QuizResult extends JFrame {

	private CocktailsAndQuiz mMain;
	
	public CocktailsAndQuiz getMain() {
		return mMain;
	}

	public void setMain(CocktailsAndQuiz mMain) {
		this.mMain = mMain;
	}

	public QuizResult(CocktailsAndQuiz main) {
		mMain = main;
		createGui();
	}

	private void createGui() {
		List<Cocktail> quizCocktails = mMain.getQuizCocktails();
		int columns = 30;
		setTitle("Result");
		JLabel labelRef;
		JLabel labelQuiz;
		Color color;
		String answer;
		setVisible(true);
		JPanel panel = new JPanel();
		JScrollPane scrollPane = new JScrollPane(panel);
		panel.setLayout(new GridLayout(0, 3));
		JTextField tf = new JTextField("", columns);
		tf.setVisible(false);
		panel.add(tf);
		panel.add(new JLabel("Referencia"));
		panel.add(new JLabel("Te válaszod"));
		for (int idx = 0; idx < quizCocktails.size(); idx++) {
			panel.add(new JLabel(""));
			panel.add(new JLabel(""));
			panel.add(new JLabel(""));

			String name = quizCocktails.get(idx).getName();
			Cocktail refCocktail = mMain.getCocktail(name);
			if (refCocktail.equals(quizCocktails.get(idx))) {
				mMain.incNofGoodCocktails();
			}
			panel.add(new JLabel(idx + 1 + "."));
			panel.add(new JLabel(refCocktail.getName()));
			panel.add(new JLabel(name));

			panel.add(new JLabel(mMain.ALAPSZESZ));
			labelRef = new JLabel(refCocktail.getAlapszesz());
			panel.add(labelRef);
			answer = quizCocktails.get(idx).getAlapszesz();
			if (answer.equals("")) {
				answer = String.format("%s", "---");
			}
			labelQuiz = new JLabel(answer);
			panel.add(labelQuiz);
			color = getColor(labelRef, labelQuiz);
			labelQuiz.setForeground(color);
			
			panel.add(new JLabel(mMain.POHAR));
			labelRef = new JLabel(refCocktail.getPohar());
			panel.add(labelRef);
			answer = quizCocktails.get(idx).getPohar();
			if (answer.equals("")) {
				answer = String.format("%s", "---");
			}
			labelQuiz = new JLabel(answer);
			panel.add(labelQuiz);
			color = getColor(labelRef, labelQuiz);
			labelQuiz.setForeground(color);
			
			panel.add(new JLabel(mMain.DISZITES));
			labelRef = new JLabel(refCocktail.getDiszites());
			panel.add(labelRef);
			answer = quizCocktails.get(idx).getDiszites();
			if (answer.equals("")) {
				answer = String.format("%s", "---");
			}
			labelQuiz = new JLabel(answer);
			panel.add(labelQuiz);
			color = getColor(labelRef, labelQuiz);
			labelQuiz.setForeground(color);
			
			panel.add(new JLabel(mMain.FAJTA));
			labelRef = new JLabel(refCocktail.getFajta());
			panel.add(labelRef);
			answer = quizCocktails.get(idx).getFajta();
			if (answer.equals("")) {
				answer = String.format("%s", "---");
			}
			labelQuiz = new JLabel(answer);
			panel.add(labelQuiz);
			color = getColor(labelRef, labelQuiz);
			labelQuiz.setForeground(color);
			
			panel.add(new JLabel(""));
			panel.add(new JLabel(""));
			panel.add(new JLabel(""));

		}
		panel.add(new JLabel("Eredmény:"));
		panel.add(new JLabel("Hibátlan koktélok:"));
		panel.add(new JLabel("Helyes válaszok:"));

		panel.add(new JLabel(""));
		System.out.println(mMain.getNofGoodCocktails());
		String msg = String.format("%d/%d = %.2f%%",
				mMain.getNofGoodCocktails(),
				mMain.getNofQuizCocktails(),
				100.0 * mMain.getNofGoodCocktails() /
				(mMain.getNofQuizCocktails()));
		panel.add(new JLabel(msg));
		msg = String.format("%d/%d = %.2f%%",
				mMain.getNofGoodAnswers(),
				mMain.getNofGoodAnswers()+mMain.getNofWrongAnswers(),
				100.0 * mMain.getNofGoodAnswers() /
				(mMain.getNofGoodAnswers()+mMain.getNofWrongAnswers()));
		panel.add(new JLabel(msg));

		add(scrollPane);
		mMain.clearQuizCocktails();
		pack();
	}

	private Color getColor(JLabel labelRef, JLabel labelQuiz) {
		if (labelRef.getText().equals(labelQuiz.getText())) {
			mMain.incNofGoodAnswers();
			return Color.GREEN;
		} else {
			mMain.incNofWrongAnswers();
			return Color.RED;
		}
	}
}
