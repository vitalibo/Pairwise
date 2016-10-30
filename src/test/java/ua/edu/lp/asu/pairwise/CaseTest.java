package ua.edu.lp.asu.pairwise;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ua.edu.lp.asu.pairwise.TestHelper.*;

public class CaseTest {

    @DataProvider
    public Object[][] samplesOfMatchedCases() {
        return new Object[][]{
            {pairs("DVD", 8), pairs("DVD", 8)},
            {pairs("CD", null, "UDF"), pairs("CD", 8, "UDF")},
            {pairs(null, 4, "ISO", null, null), pairs(null, 4, null, "Start", null)},
            {pairs(null, null, "ISO", "Start", null), pairs(null, 4, null, "Start", null)},
            {pairs(null, null, "ISO", "Start", null), pairs("DVD", 4, "ISO", "Start", "4.7Gb")},
            {pairs("CD", null, "ISO", "Start", null), pairs(null, 4, "ISO", null, null)},
            {pairs("CD", 8, "UDF", "No", "700Mb"), pairs(null, 8, null, "No", "700Mb")},
            {pairs(tuple("a", "CD"), tuple("c", "UDF")), pairs(tuple("a", "CD"), tuple("b", 8), tuple("c", "UDF"))}
        };
    }

    @Test(dataProvider = "samplesOfMatchedCases")
    public void testMatched(Case first, Case second) {
        boolean matched = first.matches(second);

        Assert.assertTrue(matched);
    }

    @DataProvider
    public Object[][] samplesOfNotMatchedCases() {
        return new Object[][]{
            {pairs("DVD", 4), pairs("DVD", 8)},
            {pairs("CD", 4, "UDF"), pairs("DVD", 8, "UDF")},
            {pairs("CD", null, "UDF"), pairs("DVD", 8, "UDF")},
            {pairs(tuple("a", "CD"), tuple("c", "UDF")), pairs(tuple("a", "CD"), tuple("b", 8), tuple("c", "ISO"))}
        };
    }

    @Test(dataProvider = "samplesOfNotMatchedCases")
    public void testNotMatched(Case first, Case second) {
        boolean matched = first.matches(second);

        Assert.assertFalse(matched);
    }

}