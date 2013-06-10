package com.ongroa.cocktail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.ongroa.cocktail.ui.AddCocktailSwing;
import com.ongroa.cocktail.ui.QuizResult;
import com.ongroa.cocktail.ui.UiSwing;

public class CocktailsAndQuiz {

	private final String fileName = "cocktails.txt";

	@SuppressWarnings("unused")
	private UiSwing mUi;
	private int mMode;

	private int mNofQuizCocktails = 1;
	private int mNofGoodCocktails;
	private int mNofGoodAnswers;
	private int mNofWrongAnswers;
	private List<Integer> mQuizCocktailIdxs;

	private List<Cocktail> mCocktails;
	private List<Cocktail> mQuizCocktails;

	public final String NEV = "Név:";
	public final String POHAR = "Pohár:";
	public final String ALAPSZESZ = "Alapszesz:";
	public final String MENNYISEG = "Mennyiség:";
	public final String OSSZETEVO = "Összetevő:";
	public final String UNIT = "Mértékegység:";
	public final String DISZITES = "Díszitése:";
	public final String FAJTA = "Fajtája:";

	public static final int ADD_MODE = 0;
	public static final int QUIZ_MODE = 1;

	public CocktailsAndQuiz() {
		mCocktails = new ArrayList<Cocktail>();
		mQuizCocktailIdxs = new ArrayList<Integer>();
		mQuizCocktails = new ArrayList<Cocktail>();
	}
	
	public void startGui() {
		mUi = new UiSwing(this);
	}

	public List<String> getNevek() {
		Set<String> ret = new HashSet<String>();
		for (Cocktail cocktail : mCocktails) {
			ret.add(cocktail.getName());
		}
		List<String> list = new ArrayList<String>(ret);
		java.util.Collections.sort(list);
		return list;
	}

	public List<String> getAlapszeszek() {
		Set<String> ret = new HashSet<String>();
		for (Cocktail cocktail : mCocktails) {
			ret.add(cocktail.getAlapszesz());
		}
		List<String> list = new ArrayList<String>(ret);
		java.util.Collections.sort(list);
		return list;
	}

	public List<String> getPoharak() {
		Set<String> ret = new HashSet<String>();
		for (Cocktail cocktail : mCocktails) {
			ret.add(cocktail.getPohar());
		}
		List<String> list = new ArrayList<String>(ret);
		java.util.Collections.sort(list);
		return list;
	}

	public List<String> getDiszitesek() {
		Set<String> ret = new HashSet<String>();
		for (Cocktail cocktail : mCocktails) {
			ret.add(cocktail.getDiszites());
		}
		List<String> list = new ArrayList<String>(ret);
		java.util.Collections.sort(list);
		return list;
	}

	public List<String> getFajtak() {
		Set<String> ret = new HashSet<String>();
		for (Cocktail cocktail : mCocktails) {
			ret.add(cocktail.getFajta());
		}
		List<String> list = new ArrayList<String>(ret);
		java.util.Collections.sort(list);
		return list;
	}

	public List<String> getMennyisegek() {
		Set<String> ret = new HashSet<String>();
		for (Cocktail cocktail : mCocktails) {
			for (Osszetevo o : cocktail.getOsszetevok()) {
				ret.add(o.getMennyiseg());
			}
		}
		List<String> list = new ArrayList<String>(ret);
		java.util.Collections.sort(list);
		return list;
	}

	public List<String> getOsszetevoNevek() {
		Set<String> ret = new HashSet<String>();
		for (Cocktail cocktail : mCocktails) {
			for (Osszetevo o : cocktail.getOsszetevok()) {
				ret.add(o.getNev());
			}
		}
		List<String> list = new ArrayList<String>(ret);
		java.util.Collections.sort(list);
		return list;
	}

	public List<Cocktail> getCocktails() {
		return mCocktails;
	}

	public int getMode() {
		return mMode;
	}

	public void setMode(int mMode) {
		this.mMode = mMode;
	}

	public int getNofQuizCocktails() {
		return mNofQuizCocktails;
	}

	public void setNofQuizCocktails(int mNofQuizCocktails) {
		this.mNofQuizCocktails = mNofQuizCocktails;
	}

	public List<Integer> getQuizCocktailIdxs() {
		return mQuizCocktailIdxs;
	}

	public void setQuizCocktailIdxs(List<Integer> mQuizCocktails) {
		this.mQuizCocktailIdxs = mQuizCocktails;
	}

	public List<Cocktail> getQuizCocktails() {
		return mQuizCocktails;
	}

	public void setQuizCocktails(List<Cocktail> mQuizCocktails) {
		this.mQuizCocktails = mQuizCocktails;
	}

	public int getNofGoodCocktails() {
		return mNofGoodCocktails;
	}

	public void setNofGoodCocktails(int mNofGoodCocktails) {
		this.mNofGoodCocktails = mNofGoodCocktails;
	}

	public void incNofGoodCocktails() {
		mNofGoodCocktails++;
	}

	public int getNofGoodAnswers() {
		return mNofGoodAnswers;
	}

	public void setNofGoodAnswers(int mNofGoodAnswers) {
		this.mNofGoodAnswers = mNofGoodAnswers;
	}

	public void incNofGoodAnswers() {
		mNofGoodAnswers++;
	}

	public int getNofWrongAnswers() {
		return mNofWrongAnswers;
	}

	public void setNofWrongAnswers(int mNofWrongAnswers) {
		this.mNofWrongAnswers = mNofWrongAnswers;
	}

	public void incNofWrongAnswers() {
		mNofWrongAnswers++;
	}

	public List<Integer> pickRandomCocktails() {
		List<Integer> ret = new ArrayList<Integer>();
		int n = 0;
		int size = mCocktails.size();
		while (n < mNofQuizCocktails) {
			Random r = new Random();
			int i = r.nextInt(size);
			if (! ret.contains(i)) {
				ret.add(i);
				n++;
			}
		}
		return ret;
	}

	public void parseCocktails() {
		mCocktails.clear(); 
		Object obj = null;
		JSONParser parser = new JSONParser();
		try {
			obj = parser.parse(new FileReader(fileName));
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			File f = new File(fileName);
			try {
				f.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONArray array = (JSONArray)obj;
		if (array != null) {
			for (int i = 0; i < array.size(); i++) {
				JSONObject c = (JSONObject)array.get(i);
				Cocktail cocktail = new Cocktail();
				cocktail.setName(c.get(Cocktail.NAME).toString());
				cocktail.setAlapszesz(c.get(Cocktail.ALAPSZESZ).toString());
				cocktail.setPohar(c.get(Cocktail.POHAR).toString());
				cocktail.setDiszites(c.get(Cocktail.DISZ).toString());
				cocktail.setFajta(c.get(Cocktail.FAJTA).toString());
				List<Osszetevo> osszetevok = new ArrayList<Osszetevo>();
				JSONArray array1 = (JSONArray)c.get(Cocktail.OSSZETEVOK);
				for (int j = 0; j < array1.size(); j++) {
					JSONObject c1 = (JSONObject)array1.get(j);
					Osszetevo o = new Osszetevo();
					o.setMennyiseg(c1.get(Osszetevo.MENNYISEG).toString());
//					o.setUnit(c1.get(Osszetevo.UNIT).toString());
					o.setNev(c1.get(Osszetevo.NAME).toString());
					osszetevok.add(o);
				}
				cocktail.setOsszetevok(osszetevok);
				mCocktails.add(cocktail);
			}
			//			printCocktails();
		}
	} 

	public void addNewCocktail() {
		parseCocktails();
		setMode(ADD_MODE);
		new AddCocktailSwing(this);
	}

	public void startQuiz() {
		parseCocktails();
		setMode(QUIZ_MODE);
		new AddCocktailSwing(this);
	}



	@SuppressWarnings("unused")
	private void printCocktails() {
		for (Cocktail c : mCocktails) {
			System.out.println(c);
		}
	}

	@SuppressWarnings("unused")
	private void printQuizCocktails() {
		for (Cocktail c : mQuizCocktails) {
			System.out.println(c);
		}
	}

	public void addCocktail(Cocktail cocktail) {
		String name = cocktail.getName();
		if (! getNevek().contains(name)) {
			mCocktails.add(cocktail);
		}
	}
	
	public void addCocktailAndWriteToFile(Cocktail cocktail) {
		String name = cocktail.getName();
		if (! getNevek().contains(name)) {
			mCocktails.add(cocktail);
			writeCocktailsToFile();
		}
	}

	public void addCocktailToQuiz(Cocktail cocktail) {
		mQuizCocktails.add(cocktail);
//		printQuizCocktails();
	}

	public void clearQuizCocktails() {
		mQuizCocktailIdxs.clear();
		mQuizCocktails.clear();
		mNofGoodAnswers = 0;
		mNofGoodCocktails = 0;
		mNofWrongAnswers = 0;
	}

	public Cocktail getCocktail(String name) {
		if (name.equals("")) {
			return null;
		}
		for (Cocktail c : mCocktails) {
			if (c.getName().equals(name)) {
				return c;
			}
		}
		return null;
	}

	public void removeCocktail(String name) {
		for (int i = 0; i < mCocktails.size(); i++) {
			if (mCocktails.get(i).getName().equals(name)) {
				mCocktails.remove(i);
			}
		}
		mCocktails.remove(getCocktail(name));
		writeCocktailsToFile();
	}

	@SuppressWarnings("unchecked")
	private void writeCocktailsToFile() {
		JSONArray cocktails = new JSONArray();
		for (Cocktail cocktail : mCocktails) {
			JSONObject obj = new JSONObject();
			obj.put(Cocktail.NAME, JSONObject.escape(cocktail.getName()));
			obj.put(Cocktail.ALAPSZESZ, cocktail.getAlapszesz());
			obj.put(Cocktail.POHAR, cocktail.getPohar());
			obj.put(Cocktail.DISZ, cocktail.getDiszites());
			obj.put(Cocktail.FAJTA, cocktail.getFajta());
			JSONArray osszetevok = new JSONArray();
			for (Osszetevo osszetevo : cocktail.getOsszetevok()) {
				JSONObject obj1 = new JSONObject();
				obj1.put(Osszetevo.MENNYISEG, osszetevo.getMennyiseg());
				obj1.put(Osszetevo.NAME, osszetevo.getNev());
				osszetevok.add(obj1);
			}
			obj.put(Cocktail.OSSZETEVOK, osszetevok);
			cocktails.add(obj);
		}
		PrintWriter out = null;
		try {
			out = new PrintWriter(fileName);
			out.println(cocktails.toString());
			out.close();
		} catch (FileNotFoundException e) {}
		finally {
			if (out != null) {
				out.close();
			}
		}
	}

	public void evaluateQuiz() {
		List<Cocktail> quizCocktails = getQuizCocktails();
		String ref;
		String answer;
		for (int idx = 0; idx < quizCocktails.size(); idx++) {
			Cocktail quizCocktail = quizCocktails.get(idx);
			String name = quizCocktail.getName();
			Cocktail refCocktail = getCocktail(name);
			if (refCocktail.equals(quizCocktail)) {
				incNofGoodCocktails();
			}
			ref = refCocktail.getAlapszesz();
			answer = quizCocktail.getAlapszesz();
			if (answer.equals("")) {
				answer = String.format("%s", "---");
			}
			setValid(ref, answer);

			panel.add(new JLabel(POHAR));
			ref = new JLabel(refCocktail.getPohar());
			panel.add(ref);
			answer = quizCocktail.getPohar();
			if (answer.equals("")) {
				answer = String.format("%s", "---");
			}
			answer = new JLabel(answer);
			panel.add(answer);
			setValid(ref, answer);
			answer.setForeground(color);

//			panel.add(new JLabel(""));
//			panel.add(new JLabel(""));
//			panel.add(new JLabel(""));

			for (int c = 0; c < refCocktail.getOsszetevok().size(); c++) {
				panel.add(new JLabel(MENNYISEG));
				panel.add(new JLabel(refCocktail.getOsszetevok().get(c).getMennyiseg()));
				if (quizCocktail.getOsszetevok().size() > c) {
					panel.add(new JLabel(quizCocktail.getOsszetevok().get(c).getMennyiseg()));
				} else {
					panel.add(new JLabel("---"));
				}
				panel.add(new JLabel(NEV));
				panel.add(new JLabel(refCocktail.getOsszetevok().get(c).getNev()));
				if (quizCocktail.getOsszetevok().size() > c) {
					panel.add(new JLabel(quizCocktail.getOsszetevok().get(c).getNev()));
				} else {
					panel.add(new JLabel("---"));
				}
//				panel.add(new JLabel(""));
//				panel.add(new JLabel(""));
//				panel.add(new JLabel(""));
			}

			panel.add(new JLabel(DISZITES));
			ref = new JLabel(refCocktail.getDiszites());
			panel.add(ref);
			answer = quizCocktail.getDiszites();
			if (answer.equals("")) {
				answer = String.format("%s", "---");
			}
			answer = new JLabel(answer);
			panel.add(answer);
			setValid(ref, answer);
			answer.setForeground(color);

			panel.add(new JLabel(FAJTA));
			ref = new JLabel(refCocktail.getFajta());
			panel.add(ref);
			answer = quizCocktail.getFajta();
			if (answer.equals("")) {
				answer = String.format("%s", "---");
			}
			answer = new JLabel(answer);
			panel.add(answer);
			setValid(ref, answer);
			answer.setForeground(color);

//			panel.add(new JLabel(""));
//			panel.add(new JLabel(""));
//			panel.add(new JLabel(""));
		}
		new QuizResult(this);
	}

	private void setValid(String ref, String answer) {
		if (ref.equals(answer)) {
			incNofGoodAnswers();
		} else {
			incNofWrongAnswers();
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				CocktailsAndQuiz c = new CocktailsAndQuiz();
				c.startGui();
			}
		});

	}

}
