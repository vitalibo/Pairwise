package ua.edu.lp.asu.pairwise;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ua.edu.lp.asu.pairwise.TestHelper.*;

public class CaseTest {

    @DataProvider
    public Object[][] samplesOfMatchedCases() {
        return new Object[][]{
            {case_("DVD", 8), case_("DVD", 8)},
            {case_("CD", null, "UDF"), case_("CD", 8, "UDF")},
            {case_(null, 4, "ISO", null, null), case_(null, 4, null, "Start", null)},
            {case_(null, null, "ISO", "Start", null), case_(null, 4, null, "Start", null)},
            {case_(null, null, "ISO", "Start", null), case_("DVD", 4, "ISO", "Start", "4.7Gb")},
            {case_("CD", null, "ISO", "Start", null), case_(null, 4, "ISO", null, null)},
            {case_("CD", 8, "UDF", "No", "700Mb"), case_(null, 8, null, "No", "700Mb")},
            {case_(pair("a", "CD"), pair("c", "UDF")), case_(pair("a", "CD"), pair("b", 8), pair("c", "UDF"))}
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
            {case_("DVD", 4), case_("DVD", 8)},
            {case_("CD", 4, "UDF"), case_("DVD", 8, "UDF")},
            {case_("CD", null, "UDF"), case_("DVD", 8, "UDF")},
            {case_(pair("a", "CD"), pair("c", "UDF")), case_(pair("a", "CD"), pair("b", 8), pair("c", "ISO"))}
        };
    }

    @Test(dataProvider = "samplesOfNotMatchedCases")
    public void testNotMatched(Case first, Case second) {
        boolean matched = first.matches(second);

        Assert.assertFalse(matched);
    }

}