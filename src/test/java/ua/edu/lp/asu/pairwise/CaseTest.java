package ua.edu.lp.asu.pairwise;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ua.edu.lp.asu.pairwise.TestHelper.*;

public class CaseTest {

    @DataProvider
    public Object[][] samplesOfMatchedCases() {
        return new Object[][]{
            {case_(a("DVD"), b(8)), case_(a("DVD"), b(8))},
            {case_(a("CD"), c("UDF")), case_(a("CD"), b(8), c("UDF"))},
            {case_(a("CD"), b(null), c("UDF")), case_(a("CD"), b(8), c("UDF"))},
            {case_(a(null), b(4), c("ISO"), d(null), e(null)), case_(a(null), b(4), c(null), d("Start"), e(null))},
            {case_(a(null), b(null), c("ISO"), d("Start"), e(null)), case_(a(null), b(4), c(null), d("Start"), e(null))},
            {case_(a(null), b(null), c("ISO"), d("Start"), e(null)), case_(a("DVD"), b(4), c("ISO"), d("Start"), e("4.7Gb"))},
            {case_(a("CD"), b(null), c("ISO"), d("Start"), e(null)), case_(a(null), b(4), c("ISO"), d(null), e(null))},
            {case_(a("CD"), b(8), c("UDF"), d("No"), e("700Mb")), case_(a(null), b(8), c(null), d("No"), e("700Mb"))},
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
            {case_(a("DVD"), b(4)), case_(a("DVD"), b(8))},
            {case_(a("CD"), c("UDF")), case_(a("DVD"), b(8), c("ISO"))},
            {case_(a("CD"), b(null), c("UDF")), case_(a("DVD"), b(8), c("UDF"))},
            {case_(a("CD"), b(4), c("UDF")), case_(a("DVD"), b(8), c("UDF"))},
        };
    }

    @Test(dataProvider = "samplesOfNotMatchedCases")
    public void testNotMatched(Case first, Case second) {
        boolean matched = first.matches(second);

        Assert.assertFalse(matched);
    }

}