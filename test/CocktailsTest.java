import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import com.ongroa.cocktail.Cocktail;
import com.ongroa.cocktail.CocktailsAndQuiz;
import com.ongroa.cocktail.Osszetevo;

public class CocktailsTest {

	@Test
	public void test_duplicate_elements_in_quiz() {
		CocktailsAndQuiz c = new CocktailsAndQuiz();
		c.parseCocktails();
		List<Integer> ret = c.pickRandomCocktails();
		Set<Integer> s = new HashSet<Integer>();
		for (Integer i : ret) {
			s.add(i);
		}
		Assert.assertEquals("Duplicate elements in quiz list: " + ret, 
			ret.size(), s.size());
	}

	@Test
	public void test_osszetevok_equal() {
		Osszetevo o1 = null;
		Osszetevo o2 = null;

		o1 = new Osszetevo("mennyiseg1", "name1");
		o2 = new Osszetevo("mennyiseg1", "name1");
		assertObjectsEqual(true, o1, o2);
		
		o1 = new Osszetevo("mennyiseg1", "name1");
		o2 = new Osszetevo("menniseg2", "name2");
		assertObjectsEqual(false, o1, o2);
		
		o1 = new Osszetevo("mennyiseg1", "name1");
		o2 = new Osszetevo("menniseg2", "name1");
		assertObjectsEqual(false, o1, o2);
		
		o1 = new Osszetevo("mennyiseg1", "name1");
		o2 = new Osszetevo("menniseg1", "name1");
		assertObjectsEqual(false, o1, o2);
		
		o1 = new Osszetevo("mennyiseg1", "name1");
		o2 = new Osszetevo("menniseg1", "");
		assertObjectsEqual(false, o1, o2);
	}

	@Test
	public void test_cocktails_equal() {
		Cocktail c1 = null;
		Cocktail c2 = null;

		c1 = new Cocktail("n1", "a1", "p1", "d1", "f1");
		c1.addOsszetevo(new Osszetevo("mennyiseg1", "name1"));
		c2 = new Cocktail("n1", "a1", "p1", "d1", "f1");
		c2.addOsszetevo(new Osszetevo("mennyiseg1", "name1"));
		assertObjectsEqual(true, c1, c2);

		c1 = new Cocktail("n1", "a1", "p1", "d1", "f1");
		c1.addOsszetevo(new Osszetevo("mennyiseg1", "name1"));
		c1.addOsszetevo(new Osszetevo("mennyiseg2", "name2"));
		c2 = new Cocktail("n1", "a1", "p1", "d1", "f1");
		c2.addOsszetevo(new Osszetevo("mennyiseg2", "name2"));
		c2.addOsszetevo(new Osszetevo("mennyiseg1", "name1"));
		assertObjectsEqual(true, c1, c2);

		c1 = new Cocktail("n1", "a1", "p1", "d1", "f1");
		c1.addOsszetevo(new Osszetevo("mennyiseg1", "name1"));
		c1.addOsszetevo(new Osszetevo("mennyiseg1", "name1"));
		c2 = new Cocktail("n1", "a1", "p1", "d1", "f1");
		c2.addOsszetevo(new Osszetevo("mennyiseg1", "name1"));
		assertObjectsEqual(true, c1, c2);

		c1 = new Cocktail("n1", "a1", "p1", "d1", "f1");
		c1.addOsszetevo(new Osszetevo("mennyiseg1", "name1"));
		c1.addOsszetevo(new Osszetevo("mennyiseg2", "name2"));
		c2 = new Cocktail("n1", "a1", "p1", "d1", "f1");
		c2.addOsszetevo(new Osszetevo("mennyiseg1", "name1"));
		c2.addOsszetevo(new Osszetevo("mennyiseg2", "name2"));
		assertObjectsEqual(true, c1, c2);

		c1 = new Cocktail("n1", "a1", "p1", "d1", "f1");
		c1.addOsszetevo(new Osszetevo("mennyiseg1", "name1"));
		c2 = new Cocktail("n1", "a2", "p1", "d1", "f1");
		c2.addOsszetevo(new Osszetevo("mennyiseg1", "name1"));
		assertObjectsEqual(false, c1, c2);

		c1 = new Cocktail("n1", "a1", "p1", "d1", "f1");
		c1.addOsszetevo(new Osszetevo("mennyiseg1", "name1"));
		c2 = new Cocktail("n1", "a1", "p1", "d1", "f1");
		c2.addOsszetevo(new Osszetevo("mennyiseg2", "name1"));
		assertObjectsEqual(false, c1, c2);

		c1 = new Cocktail("n1", "a1", "p1", "d1", "f1");
		c2 = new Cocktail("n1", "a1", "p1", "d1", "f1");
		c2.addOsszetevo(new Osszetevo("mennyiseg2", "name2"));
		assertObjectsEqual(false, c1, c2);

		c1 = new Cocktail("n1", "a1", "p1", "d1", "f1");
		c1.addOsszetevo(new Osszetevo("mennyiseg1", "name1"));
		c1.addOsszetevo(new Osszetevo("mennyiseg2", "name2"));
		c1.addOsszetevo(new Osszetevo("mennyiseg3", "name3"));
		c2 = new Cocktail("n1", "a1", "p1", "d1", "f1");
		c2.addOsszetevo(new Osszetevo("mennyiseg1", "name1"));
		c2.addOsszetevo(new Osszetevo("mennyiseg2", "name2"));
		assertObjectsEqual(false, c1, c2);

		c1 = new Cocktail("n1", "a1", "p1", "d1", "f1");
		c1.addOsszetevo(new Osszetevo("mennyiseg1", "name1"));
		c1.addOsszetevo(new Osszetevo("mennyiseg2", "name2"));
		c2 = new Cocktail("n1", "a1", "p1", "d1", "f1");
		c2.addOsszetevo(new Osszetevo("mennyiseg1", "name1"));
		c2.addOsszetevo(new Osszetevo("mennyiseg2", "name2"));
		c2.addOsszetevo(new Osszetevo("mennyiseg3", "name3"));
		assertObjectsEqual(false, c1, c2);

	}
	
	@Test
	public void test_add_cocktail_twice() {
		Cocktail c1 = null;
		CocktailsAndQuiz c = new CocktailsAndQuiz();
		c1 = new Cocktail("n1", "a1", "p1", "d1", "f1");
		c.addCocktail(c1);
		c.addCocktail(c1);
		Assert.assertEquals("\nDuplicate addCocktail()", 1, c.getCocktails().size());
		
	}

	@Ignore
	private void assertObjectsEqual(boolean b, Object o1, Object o2) {
		String msgEquals = String.format("\n%s ==\n %s", o1, o2);
		String msgNotEquals = String.format("\n%s !=\n %s", o1, o2);
		if (b) {
		Assert.assertTrue(msgNotEquals, o1.equals(o2));
		} else {
			Assert.assertFalse(msgEquals, o1.equals(o2));
		}
		
	}

}
