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
			addEmptyLineToPanel(panel);

			Cocktail quizCocktail = quizCocktails.get(idx);

			String name = quizCocktail.getName();
			Cocktail refCocktail = mMain.getCocktail(name);
			quizCocktail.evaluateOsszetevok(refCocktail);

			panel.add(new JLabel(idx + 1 + "."));
			panel.add(new JLabel(refCocktail.getName()));
			panel.add(new JLabel(name));

			addToPanel(mMain.ALAPSZESZ, panel, refCocktail, quizCocktail);
			addToPanel(mMain.POHAR, panel, refCocktail, quizCocktail);
			addEmptyLineToPanel(panel);

			List<Osszetevo> refOsszetevok = refCocktail.getOsszetevokSorted();
			List<Osszetevo> quizOsszetevok = quizCocktail.getOsszetevokSorted();
			for (int c = 0; c < Math.max(refCocktail.getOsszetevok().size(),
					quizCocktail.getOsszetevok().size()); c++) {
				panel.add(new JLabel(mMain.OSSZETEVO));
				if (refCocktail.getOsszetevok().size() > c) {
					String s = String.format("%s %s",
							refOsszetevok.get(c).getMennyiseg(),
							refOsszetevok.get(c).getNev());
					labelRef = new JLabel(s); 
				} else {
					labelRef = new JLabel("---"); 
				}
				panel.add(labelRef);
				if (quizCocktail.getOsszetevok().size() > c) {
					String s = String.format("%s %s",
							quizOsszetevok.get(c).getMennyiseg(),
							quizOsszetevok.get(c).getNev());
					labelQuiz = new JLabel(s); 
					if (quizOsszetevok.get(c).isValid()) {
						color = Color.GREEN;
						mMain.incNofGoodAnswers();
					} else {
						color = Color.RED;
						mMain.incNofWrongAnswers();
					}
				} else {
					labelQuiz = new JLabel("---"); 
					color = Color.RED;
					mMain.incNofWrongAnswers();
				}
				labelQuiz.setForeground(color);
				panel.add(labelQuiz);
			}

			addEmptyLineToPanel(panel);
			addToPanel(mMain.DISZITES, panel, refCocktail, quizCocktail);
			addToPanel(mMain.FAJTA, panel, refCocktail, quizCocktail);
			addEmptyLineToPanel(panel);
		}
		panel.add(new JLabel("Eredmény:"));
		panel.add(new JLabel("Hibátlan koktélok:"));
		panel.add(new JLabel("Helyes válaszok:"));

		panel.add(new JLabel(""));
		String msg = mMain.getGoodCocktailsResult();
		panel.add(new JLabel(msg));
		msg = mMain.getGoodAnswersResult();
		panel.add(new JLabel(msg));
		
		add(scrollPane);
		pack();
	}

	private void addEmptyLineToPanel(JPanel panel) {
		panel.add(new JLabel(""));
		panel.add(new JLabel(""));
		panel.add(new JLabel(""));
	}

	private void addToPanel(String s, JPanel panel, Cocktail refCocktail, 
			Cocktail quizCocktail) {
		JLabel labelRef = null;
		JLabel labelQuiz = null;
		Color color;
		String answer = null;
		if (s.equals(mMain.ALAPSZESZ)) {
			labelRef = new JLabel(refCocktail.getAlapszesz());
			answer = quizCocktail.getAlapszesz();
		}
		if (s.equals(mMain.POHAR)) {
			labelRef = new JLabel(refCocktail.getPohar());
			answer = quizCocktail.getPohar();
		}
		if (s.equals(mMain.DISZITES)) {
			labelRef = new JLabel(refCocktail.getDiszites());
			answer = quizCocktail.getDiszites();
		}
		if (s.equals(mMain.FAJTA)) {
			labelRef = new JLabel(refCocktail.getFajta());
			answer = quizCocktail.getFajta();
		}
		if (answer.equals("")) {
			answer = String.format("%s", "---");
		}
		labelQuiz = new JLabel(answer);
		color = getColor(labelRef, labelQuiz);
		labelQuiz.setForeground(color);
		panel.add(new JLabel(s));
		panel.add(labelRef);
		panel.add(labelQuiz);
	}

	private Color getColor(JLabel labelRef, JLabel labelQuiz) {
		if (labelRef.getText().equals(labelQuiz.getText())) {
			return Color.GREEN;
		} else {
			return Color.RED;
		}
	}
}
