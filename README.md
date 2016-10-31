### Pairwise

[![Build Status](https://travis-ci.org/vitalibo/Pairwise.svg?branch=master)](https://travis-ci.org/vitalibo/Pairwise)

Pairwise is open-source library for the generating minimal set of test combinations. The project is implemented In-Parameter-Order (IPO) a strategy for generate test cases.

#### Installation

```
mvn clean install
```

Now you can use library in your projects, include in `pom.xml` the following dependency

```
<dependency>
    <groupId>ua.edu.lp.asu</groupId>
    <artifactId>pairwise</artifactId>
    <version>0.3.2-SNAPSHOT</version>
</dependency>
```

#### Usage

Example using generating test cases

```
Pairwise pairwise = new Pairwise.Builder()
    .withParameters(Arrays.asList(
        new Parameter<>("Platform", Platform.x86, Platform.ia64, Platform.amd64),
        new Parameter<>("CPUs", "Single", "Dual", "Quad"),
        new Parameter<>("RAM", "128MB", "1GB", "4GB", "64GB"),
        new Parameter<>("HDD", Type.SCSI, Type.IDE),
        new Parameter<>("Operating system", new NT4(), new Win2K(), new WinXP(), new Win2K3()),
        new Parameter<>("Internet Explorer", 4.0, 5.0, 5.5, 6.0),
        new Parameter<>("Application", new SQLServer(), new Exchange(), new Office())))
    .build();

for (Case o : pairwise) {
    // TODO: write your code here
}
```

This is output of generating test cases. Each of the following lines represents one generated test case. 

```
No. | Platform | CPUs   | RAM   | HDD  | Operating system | Internet Explorer | Application 
----+----------+--------+-------+------+------------------+-------------------+-------------
  0 | amd64    | Quad   | 128MB | IDE  | NT4@5910e440     | 4.0               | SQLServer   
  1 | x86      | Single | 1GB   | SCSI | NT4@5910e440     | 5.0               | Office      
... | ...      | ...    | ...   | ...  | ...              | ...               | ...             
 17 | ia64     | Dual   | 4GB   | SCSI | WinXP@533ddba    | 5.5               | Exchange 
```

Also, you can use pairwise testing with TestNG as @DataProvider

```
@DataProvider
public Object[][] source() {
    return new Pairwise.Builder()
        .withParameter(new Parameter<>("Type", "Primary", "Logical", "Single", "Span", "Stripe"))
        .withParameter(new Parameter<>("Size", 10, 100, 500, 1000, 5000, 10000, 40000))
        .withParameter(new Parameter<>("File system", Fs.FAT, Fs.FAT32, Fs.NTFS))
        .withParameter(new Parameter<>("Cluster size", 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536))
        .withParameter(new Parameter<>("Compression", true, false))
        .build()
        .toTestNG();
}

@Test(dataProvider = "source")
public void test(String type, Integer size, Fs fs, Integer cluster, Boolean compression) {
    // TODO: write your code here
}
```

or running parameterized test in JUnit 4

```
@Parameterized.Parameters
public static Collection<Object[]> source() {
    return new Pairwise.Builder()
        .withParameter(new Parameter<>("Type", Type.CD, Type.DVD))
        .withParameter(new Parameter<>("Recording Speed", 2, 4, 8, 16, 24, 36, 52))
        .withParameter(new Parameter<>("File System", Fs.ISO, Fs.UDF, Fs.HFS, Fs.ISO9660))
        .withParameter(new Parameter<>("Capacity", new Capacity("700Mb"), new Capacity("4.7Gb")))
        .build()
        .toJUnit();
}
```