package ua.edu.lp.asu.pairwise;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static ua.edu.lp.asu.pairwise.TestHelper.parameter;

public class PairwiseTest {

    @DataProvider
    public Object[][] samplesParameters() {
        return new Object[][]{{
            Arrays.asList(
                parameter("A", "a1", "a2", "a3"),
                parameter("B", "b1", "b2", "b3"),
                parameter("C", "c1", "c2", "c3"))}, {

            Arrays.asList(
                parameter("Browser", "Chrome", "Firefox", "Internet Explorer", "Safari"),
                parameter("Page", "Link", "Image", "Description"),
                parameter("Product", "Phone", "Movie", "Computer", "Blender", "Microwave", "Book", "Sweater"),
                parameter("Click", "Link", "Image", "Description"))}, {

            Arrays.asList(
                parameter("Type", "CD", "DVD"),
                parameter("Recording Speed", 2, 4, 8, 16, 24, 36, 52),
                parameter("File System", "ISO", "UDF", "HFS", "ISO9660"),
                parameter("Multisession", "No", "Start", "Continue"),
                parameter("Capacity", "100Mb", "700Mb", "4.7Gb"))}, {

            Arrays.asList(
                parameter("Type", "Primary", "Logical", "Single", "Span", "Stripe", "Mirror", "RAID-5"),
                parameter("Size", 10, 100, 500, 1000, 5000, 10000, 40000),
                parameter("Format method", "quick", "slow"),
                parameter("File system", "FAT", "FAT32", "NTFS"),
                parameter("Cluster size", 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536),
                parameter("Compression", true, false))}};
    }

    @Test(dataProvider = "samplesParameters")
    public void testBuild(List<Parameter<?>> parameters) {
        Pairwise pairwise = new Pairwise.Builder()
            .withParameters(parameters)
            .build();

        Assert.assertEquals(pairwise.getParameters(), parameters);
        Assert.assertFalse(pairwise.getCases().isEmpty());
        Assert.assertTrue(pairwise.verify().isEmpty());
    }

    @Test(dataProvider = "samplesParameters")
    public void testVerify(List<Parameter<?>> parameters) {
        Pairwise pairwise = new Pairwise.Builder()
            .withParameters(parameters)
            .build();

        Assert.assertTrue(pairwise.verify().isEmpty());

        pairwise.getCases().remove(0);
        Assert.assertFalse(pairwise.verify().isEmpty());
    }

    @Test(dataProvider = "samplesParameters")
    public void testOrder(List<Parameter<?>> parameters) {
        Pairwise pairwise = new Pairwise.Builder()
            .withParameters(parameters)
            .build();

        pairwise.stream()
            .map(Map::entrySet)
            .map(Collection::stream)
            .map(c -> c.map(Map.Entry::getValue)
                .collect(Collectors.toList()))
            .forEach(l -> IntStream.range(0, l.size())
                .forEach(i -> Assert.assertTrue(parameters.get(i).contains(l.get(i)))));
    }

}