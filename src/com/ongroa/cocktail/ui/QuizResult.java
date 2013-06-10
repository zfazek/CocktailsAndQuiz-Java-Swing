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
import com.ongroa.cocktail.Osszetevo;

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

			Cocktail quizCocktail = quizCocktails.get(idx);
			String name = quizCocktail.getName();
			Cocktail refCocktail = mMain.getCocktail(name);
			if (refCocktail.equals(quizCocktail)) {
				mMain.incNofGoodCocktails();
			}
			panel.add(new JLabel(idx + 1 + "."));
			panel.add(new JLabel(refCocktail.getName()));
			panel.add(new JLabel(name));

			panel.add(new JLabel(mMain.ALAPSZESZ));
			labelRef = new JLabel(refCocktail.getAlapszesz());
			panel.add(labelRef);
			answer = quizCocktail.getAlapszesz();
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
			answer = quizCocktail.getPohar();
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

			List<Osszetevo> refOsszetevok = refCocktail.getOsszetevokSorted();
			List<Osszetevo> quizOsszetevok = quizCocktail.getOsszetevokSorted();
			for (int c = 0; c < refCocktail.getOsszetevok().size(); c++) {
				panel.add(new JLabel(mMain.OSSZETEVO));
				String s = String.format("%s %s",
						refOsszetevok.get(c).getMennyiseg(),
						refOsszetevok.get(c).getNev());
				labelRef = new JLabel(s); 
				panel.add(labelRef);
				if (quizCocktail.getOsszetevok().size() > c) {
					String s2 = String.format("%s %s",
							quizOsszetevok.get(c).getMennyiseg(),
							quizOsszetevok.get(c).getNev());
					labelQuiz = new JLabel(s2);
					panel.add(labelQuiz);
					color = getColor(new JLabel(), labelQuiz);
					labelQuiz.setForeground(color);
				} else {
					labelQuiz = new JLabel("---");
					panel.add(labelQuiz);
					color = getColor(new JLabel(), labelQuiz);
					labelQuiz.setForeground(color);
				}
			}
			for (int c = refCocktail.getOsszetevok().size(); 
					c < quizCocktail.getOsszetevok().size();
					c++) {
				panel.add(new JLabel(mMain.OSSZETEVO));
				if (refCocktail.getOsszetevok().size() > c) {
					String s1 = String.format("%s %s",
							refOsszetevok.get(c).getMennyiseg(),
							refOsszetevok.get(c).getNev());
					panel.add(new JLabel(s1));
				} else {
					panel.add(new JLabel("---"));
				}
				if (quizCocktail.getOsszetevok().size() > c) {
					String s2 = String.format("%s %s",
							quizOsszetevok.get(c).getMennyiseg(),
							quizOsszetevok.get(c).getNev());
					labelQuiz = new JLabel(s2);
					labelQuiz.setForeground(Color.RED);
					panel.add(labelQuiz);
				} else {
					panel.add(new JLabel("---"));
				}
			}
			panel.add(new JLabel(""));
			panel.add(new JLabel(""));
			panel.add(new JLabel(""));

			panel.add(new JLabel(mMain.DISZITES));
			labelRef = new JLabel(refCocktail.getDiszites());
			panel.add(labelRef);
			answer = quizCocktail.getDiszites();
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
			answer = quizCocktail.getFajta();
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
