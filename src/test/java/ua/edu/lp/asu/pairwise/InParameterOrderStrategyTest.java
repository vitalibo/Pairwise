package ua.edu.lp.asu.pairwise;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static ua.edu.lp.asu.pairwise.TestHelper.*;

public class InParameterOrderStrategyTest {

    @DataProvider
    public Object[][] samplesGeneratePairs() {
        return new Object[][]{
            {
                Arrays.asList(
                    parameter("A", "a1", "a2", "a3"),
                    parameter("B", "b1", "b2", "b3"),
                    parameter("C", "c1", "c2", "c3")),
                Arrays.asList(
                    pairs(tuple("A", "a1"), tuple("B", "b1")), pairs(tuple("A", "a1"), tuple("B", "b2")),
                    pairs(tuple("A", "a1"), tuple("B", "b3")), pairs(tuple("A", "a2"), tuple("B", "b1")),
                    pairs(tuple("A", "a2"), tuple("B", "b2")), pairs(tuple("A", "a2"), tuple("B", "b3")),
                    pairs(tuple("A", "a3"), tuple("B", "b1")), pairs(tuple("A", "a3"), tuple("B", "b2")),
                    pairs(tuple("A", "a3"), tuple("B", "b3")), pairs(tuple("A", "a1"), tuple("C", "c1")),
                    pairs(tuple("A", "a1"), tuple("C", "c2")), pairs(tuple("A", "a1"), tuple("C", "c3")),
                    pairs(tuple("A", "a2"), tuple("C", "c1")), pairs(tuple("A", "a2"), tuple("C", "c2")),
                    pairs(tuple("A", "a2"), tuple("C", "c3")), pairs(tuple("A", "a3"), tuple("C", "c1")),
                    pairs(tuple("A", "a3"), tuple("C", "c2")), pairs(tuple("A", "a3"), tuple("C", "c3")),
                    pairs(tuple("B", "b1"), tuple("C", "c1")), pairs(tuple("B", "b1"), tuple("C", "c2")),
                    pairs(tuple("B", "b1"), tuple("C", "c3")), pairs(tuple("B", "b1"), tuple("C", "c1")),
                    pairs(tuple("B", "b1"), tuple("C", "c2")), pairs(tuple("B", "b2"), tuple("C", "c3")),
                    pairs(tuple("B", "b3"), tuple("C", "c1")), pairs(tuple("B", "b3"), tuple("C", "c2")),
                    pairs(tuple("B", "b3"), tuple("C", "c3")))},
            {
                Arrays.asList(
                    parameter("A", "a1", "a2", "a3"),
                    parameter("B", "b1", "b2"),
                    parameter("C", "c1", "c2", "c3", "c4")),
                Arrays.asList(
                    pairs(tuple("A", "a1"), tuple("B", "b1")), pairs(tuple("A", "a1"), tuple("B", "b2")),
                    pairs(tuple("A", "a2"), tuple("B", "b1")), pairs(tuple("A", "a2"), tuple("B", "b2")),
                    pairs(tuple("A", "a2"), tuple("B", "b1")), pairs(tuple("A", "a2"), tuple("B", "b2")),
                    pairs(tuple("A", "a1"), tuple("C", "c1")), pairs(tuple("A", "a1"), tuple("C", "c2")),
                    pairs(tuple("A", "a1"), tuple("C", "c3")), pairs(tuple("A", "a1"), tuple("C", "c4")),
                    pairs(tuple("A", "a2"), tuple("C", "c1")), pairs(tuple("A", "a2"), tuple("C", "c2")),
                    pairs(tuple("A", "a2"), tuple("C", "c3")), pairs(tuple("A", "a2"), tuple("C", "c4")),
                    pairs(tuple("A", "a3"), tuple("C", "c1")), pairs(tuple("A", "a3"), tuple("C", "c2")),
                    pairs(tuple("A", "a3"), tuple("C", "c3")), pairs(tuple("A", "a3"), tuple("C", "c4")),
                    pairs(tuple("B", "b1"), tuple("C", "c1")), pairs(tuple("B", "b1"), tuple("C", "c2")),
                    pairs(tuple("B", "b1"), tuple("C", "c3")), pairs(tuple("B", "b1"), tuple("C", "c4")),
                    pairs(tuple("B", "b2"), tuple("C", "c1")), pairs(tuple("B", "b2"), tuple("C", "c2")),
                    pairs(tuple("B", "b2"), tuple("C", "c3")), pairs(tuple("B", "b2"), tuple("C", "c4")))}
        };
    }

    @Test(dataProvider = "samplesGeneratePairs")
    public void testGeneratePairs(List<Parameter<?>> parameters, List<Case> expected) {
        List<Case> pairs = InParameterOrderStrategy
            .generatePairs(parameters)
            .stream()
            .flatMap(List::stream)
            .collect(Collectors.toList());

        Assert.assertEquals(pairs.size(), size(parameters));
        Assert.assertEquals(pairs.size(), expected.size());
        Assert.assertTrue(pairs.containsAll(expected));
    }

    @DataProvider
    public Object[][] samplesHorizontalGrowth() {
        return new Object[][]{
            {
                Collections.singletonList(
                    pairs(tuple("A", "a1"), tuple("B", "b1"))),
                new ArrayList<>(Arrays.asList(
                    pairs(tuple("A", "a1"), tuple("C", "c1")), pairs(tuple("B", "b1"), tuple("C", "c1")))),
                Collections.singletonList(
                    pairs(tuple("A", "a1"), tuple("B", "b1"), tuple("C", "c1")))},
            {
                Arrays.asList(
                    pairs(tuple("A", "a1"), tuple("B", "b1")), pairs(tuple("A", "a1"), tuple("B", "b2")),
                    pairs(tuple("A", "a1"), tuple("B", "b3")), pairs(tuple("A", "a2"), tuple("B", "b1")),
                    pairs(tuple("A", "a2"), tuple("B", "b2")), pairs(tuple("A", "a2"), tuple("B", "b3")),
                    pairs(tuple("A", "a3"), tuple("B", "b1")), pairs(tuple("A", "a3"), tuple("B", "b2")),
                    pairs(tuple("A", "a3"), tuple("B", "b3"))),
                new ArrayList<>(Arrays.asList(
                    pairs(tuple("A", "a1"), tuple("C", "c1")), pairs(tuple("A", "a1"), tuple("C", "c2")),
                    pairs(tuple("A", "a1"), tuple("C", "c3")), pairs(tuple("A", "a2"), tuple("C", "c1")),
                    pairs(tuple("A", "a2"), tuple("C", "c3")), pairs(tuple("A", "a3"), tuple("C", "c1")),
                    pairs(tuple("A", "a3"), tuple("C", "c2")), pairs(tuple("A", "a3"), tuple("C", "c3")),
                    pairs(tuple("B", "b1"), tuple("C", "c1")), pairs(tuple("B", "b1"), tuple("C", "c2")),
                    pairs(tuple("B", "b1"), tuple("C", "c3")), pairs(tuple("B", "b2"), tuple("C", "c1")),
                    pairs(tuple("B", "b2"), tuple("C", "c3")), pairs(tuple("B", "b3"), tuple("C", "c1")),
                    pairs(tuple("B", "b3"), tuple("C", "c2")), pairs(tuple("B", "b3"), tuple("C", "c3")))),
                Arrays.asList(
                    pairs(tuple("A", "a1"), tuple("B", "b1"), tuple("C", "c3")),
                    pairs(tuple("A", "a1"), tuple("B", "b2"), tuple("C", "c1")),
                    pairs(tuple("A", "a1"), tuple("B", "b3"), tuple("C", "c2")),
                    pairs(tuple("A", "a2"), tuple("B", "b1"), tuple("C", "c1")),
                    pairs(tuple("A", "a2"), tuple("B", "b2"), tuple("C", "c3")),
                    pairs(tuple("A", "a2"), tuple("B", "b3"), tuple("C", "c3")),
                    pairs(tuple("A", "a3"), tuple("B", "b1"), tuple("C", "c2")),
                    pairs(tuple("A", "a3"), tuple("B", "b2"), tuple("C", "c3")),
                    pairs(tuple("A", "a3"), tuple("B", "b3"), tuple("C", "c1")))}
        };
    }

    @Test(dataProvider = "samplesHorizontalGrowth")
    public void testHorizontalGrowth(List<Case> original, List<Case> pairs, List<Case> expected) {
        List<Case> cases = InParameterOrderStrategy
            .horizontalGrowth(original, pairs);

        Assert.assertEquals(cases.size(), expected.size());
        Assert.assertTrue(cases.containsAll(expected));
        Assert.assertTrue(pairs.isEmpty());
    }

    @DataProvider
    public Object[][] samplesVerticalGrowth() {
        return new Object[][]{
            {
                new ArrayList<>(Arrays.asList(
                    pairs(tuple("A", "a1"), tuple("B", "b1")), pairs(tuple("B", "b1"), tuple("C", "c1")))),
                Collections.singletonList(
                    pairs(tuple("A", "a1"), tuple("B", "b1"), tuple("C", "c1")))},
            {
                new ArrayList<>(Arrays.asList(
                    pairs(tuple("A", "a1"), tuple("C", "c1")), pairs(tuple("A", "a1"), tuple("C", "c2")),
                    pairs(tuple("A", "a1"), tuple("C", "c3")), pairs(tuple("A", "a2"), tuple("C", "c1")),
                    pairs(tuple("A", "a2"), tuple("C", "c2")), pairs(tuple("A", "a2"), tuple("C", "c3")),
                    pairs(tuple("A", "a3"), tuple("C", "c1")), pairs(tuple("A", "a3"), tuple("C", "c2")),
                    pairs(tuple("A", "a3"), tuple("C", "c3")), pairs(tuple("B", "b1"), tuple("C", "c1")),
                    pairs(tuple("B", "b1"), tuple("C", "c2")), pairs(tuple("B", "b1"), tuple("C", "c3")),
                    pairs(tuple("B", "b2"), tuple("C", "c1")), pairs(tuple("B", "b2"), tuple("C", "c2")),
                    pairs(tuple("B", "b2"), tuple("C", "c3")), pairs(tuple("B", "b3"), tuple("C", "c1")),
                    pairs(tuple("B", "b3"), tuple("C", "c2")), pairs(tuple("B", "b3"), tuple("C", "c3")))),
                Arrays.asList(
                    pairs(tuple("A", "a1"), tuple("B", "b1"), tuple("C", "c1")),
                    pairs(tuple("A", "a1"), tuple("B", "b1"), tuple("C", "c2")),
                    pairs(tuple("A", "a1"), tuple("B", "b1"), tuple("C", "c3")),
                    pairs(tuple("A", "a2"), tuple("B", "b2"), tuple("C", "c1")),
                    pairs(tuple("A", "a2"), tuple("B", "b2"), tuple("C", "c2")),
                    pairs(tuple("A", "a2"), tuple("B", "b2"), tuple("C", "c3")),
                    pairs(tuple("A", "a3"), tuple("B", "b3"), tuple("C", "c1")),
                    pairs(tuple("A", "a3"), tuple("B", "b3"), tuple("C", "c2")),
                    pairs(tuple("A", "a3"), tuple("B", "b3"), tuple("C", "c3")))}
        };
    }

    @Test(dataProvider = "samplesVerticalGrowth")
    public void testVerticalGrowth(List<Case> pairs, List<Case> expected) {
        List<Case> cases = InParameterOrderStrategy
            .verticalGrowth(pairs);

        Assert.assertEquals(cases.size(), expected.size());
        Assert.assertTrue(cases.containsAll(expected));
        Assert.assertTrue(pairs.isEmpty());
    }

    private static int size(List<Parameter<?>> parameters) {
        return IntStream.range(0, parameters.size() - 1).reduce(0, (sum, i) ->
            IntStream.range(i + 1, parameters.size()).reduce(0, (inner, j) -> {
                return inner + parameters.get(i).size() * parameters.get(j).size();
            }) + sum);
    }

}