import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;

import com.ongroa.cocktail.CocktailsAndQuiz;


public class CocktailsTest {

	@Test
	public void test() {
		CocktailsAndQuiz c = new CocktailsAndQuiz();
		c.parseCocktails();
		List<Integer> ret = c.pickRandomCocktails();
		System.out.println(ret);
		Set<Integer> s = new HashSet<Integer>();
		for (Integer i : ret) {
			s.add(i);
		}
		Assert.assertEquals("Duplicate elements in quiz list", 
			ret.size(), s.size());
	}

}
