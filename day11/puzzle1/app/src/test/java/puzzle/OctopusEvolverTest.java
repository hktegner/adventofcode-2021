package puzzle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static puzzle.AppUtil.toIntList;

import java.util.List;
import org.junit.jupiter.api.Test;

class OctopusEvolverTest {

  @Test
  void evolve_deadSimple() {
    var map = new LevelMap(
        List.of(
            List.of(8, 3),
            List.of(7, 5)
        )
    );
    var evolver = new OctopusEvolver(map);
    assertEquals(0, evolver.evolveSteps(1));
    assertEquals(
        """
            94
            86
            """,
        map.toString()
    );
    assertEquals(2, evolver.evolveSteps(1));
    assertEquals(
        """
            07
            09
            """,
        map.toString()
    );
  }

  @Test
  void evolve_simple() {
    var map = new LevelMap(
        List.of(
            List.of(9, 8, 7),
            List.of(8, 4, 7),
            List.of(9, 9, 6)
        )
    );
    assertEquals(
        """
            987
            847
            996
            """,
        map.toString()
    );

    var evolver = new OctopusEvolver(map);
    var flashed = evolver.evolveSteps(1);
    assertEquals(
        """
            000
            000
            000
            """,
        map.toString()
    );
  }

  @Test
  void evolve_largeExample() {
    var map = new LevelMap(
        List.of(
            toIntList("5483143223"),
            toIntList("2745854711"),
            toIntList("5264556173"),
            toIntList("6141336146"),
            toIntList("6357385478"),
            toIntList("4167524645"),
            toIntList("2176841721"),
            toIntList("6882881134"),
            toIntList("4846848554"),
            toIntList("5283751526")
        )
    );
    var evolver = new OctopusEvolver(map);
    // step 1
    var flashCount = 0;
    assertEquals(0, evolver.evolveSteps(1));
    assertEquals(
        """
            6594254334
            3856965822
            6375667284
            7252447257
            7468496589
            5278635756
            3287952832
            7993992245
            5957959665
            6394862637
            """,
        map.toString()
    );
    // step 2
    assertEquals(35, evolver.evolveSteps(1));
    assertEquals(
        """
            8807476555
            5089087054
            8597889608
            8485769600
            8700908800
            6600088989
            6800005943
            0000007456
            9000000876
            8700006848
            """,
        map.toString()
    );
    // step 3
    assertEquals(45, evolver.evolveSteps(1));
    assertEquals(
        """
            0050900866
            8500800575
            9900000039
            9700000041
            9935080063
            7712300000
            7911250009
            2211130000
            0421125000
            0021119000
            """,
        map.toString()
    );
    // step 4
    assertEquals(16, evolver.evolveSteps(1));
    assertEquals(
        """
            2263031977
            0923031697
            0032221150
            0041111163
            0076191174
            0053411122
            0042361120
            5532241122
            1532247211
            1132230211
            """,
        map.toString()
    );

    // step 5
    evolver.evolveSteps(1);
    assertEquals(
        """
            4484144000
            2044144000
            2253333493
            1152333274
            1187303285
            1164633233
            1153472231
            6643352233
            2643358322
            2243341322
            """,
        map.toString()
    );

    // step 6
    evolver.evolveSteps(1);
    assertEquals(
        """
            5595255111
            3155255222
            3364444605
            2263444496
            2298414396
            2275744344
            2264583342
            7754463344
            3754469433
            3354452433
            """,
        map.toString()
    );

    // step 7
    evolver.evolveSteps(1);
    assertEquals(
        """
            6707366222
            4377366333
            4475555827
            3496655709
            3500625609
            3509955566
            3486694453
            8865585555
            4865580644
            4465574644
            """,
        map.toString()
    );

    // step 8
    evolver.evolveSteps(1);
    assertEquals(
        """
            7818477333
            5488477444
            5697666949
            4608766830
            4734946730
            4740097688
            6900007564
            0000009666
            8000004755
            6800007755
            """,
        map.toString()
    );

    // step 9
    evolver.evolveSteps(1);
    assertEquals(
        """
            9060000644
            7800000976
            6900000080
            5840000082
            5858000093
            6962400000
            8021250009
            2221130009
            9111128097
            7911119976
            """,
        map.toString()
    );

    // step 10
    evolver.evolveSteps(1);
    assertEquals(
        """
            0481112976
            0031112009
            0041112504
            0081111406
            0099111306
            0093511233
            0442361130
            5532252350
            0532250600
            0032240000
            """,
        map.toString()
    );

  }

}