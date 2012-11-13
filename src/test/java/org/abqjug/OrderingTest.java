package org.abqjug;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.fest.assertions.Assertions.assertThat;
import static org.testng.Assert.*;


public class OrderingTest {
    private StarWarsEpisode aNewHope;
    private StarWarsEpisode empireStrikesBack;
    private StarWarsEpisode returnOfTheJedi;
    private StarWarsEpisode phantomMenace;
    private StarWarsEpisode attackOfTheClones;
    private StarWarsEpisode revengeOfTheSith;
    private StarWarsCharacter hanSolo;
    private StarWarsCharacter lukeSkywalker;
    private StarWarsCharacter princessLeia;
    private StarWarsCharacter landoCalrissian;
    private StarWarsCharacter bobaFett;
    private Ordering<String> byLengthOrdering;

    public class StarWarsEpisode {
        private String name;
        private int number;
        private int year;

        public StarWarsEpisode(String name, int number, int year) {
            this.name = name;
            this.number = number;
            this.year = year;
        }

        public int getYear() {
            return year;
        }

        public String getName() {
            return name;
        }

        public int getNumber() {
            return number;
        }


    }

    public class StarWarsCharacter implements Comparable<StarWarsCharacter> {
        private String name;
        private StarWarsEpisode firstAppearance;

        public StarWarsCharacter(String name, StarWarsEpisode firstAppearance) {
            this.name = name;
            this.firstAppearance = firstAppearance;
        }

        public String getName() {
            return name;
        }

        public StarWarsEpisode getEpisodeFirstAppearance() {
            return firstAppearance;
        }

        public String toString() {
            return name;
        }

        public int compareTo(StarWarsCharacter o) {
            return this.name.compareTo(o.name) +
                    this.firstAppearance.getYear() -
                    o.firstAppearance.getYear();
        }
    }

    public class StarWarsEpisodeYearComparator
            implements Comparator<StarWarsEpisode> {
        public int compare(StarWarsEpisode o1, StarWarsEpisode o2) {
            return o1.getYear() - o2.getYear();
        }
    }

    public class StarWarsCharacterYearComparator
            implements Comparator<StarWarsCharacter> {
        public int compare(StarWarsCharacter o1, StarWarsCharacter o2) {
            return o1.getEpisodeFirstAppearance().getYear() - o2.getEpisodeFirstAppearance().getYear();
        }
    }

    public class StarWarsCharacterNameComparator implements Comparator<StarWarsCharacter> {
        public int compare(StarWarsCharacter o1, StarWarsCharacter o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }


    @BeforeMethod(groups = "ordering")
    public void setUp() {
        aNewHope = new StarWarsEpisode("A New Hope", 4, 1977);
        empireStrikesBack = new StarWarsEpisode("The Empire Strikes Back", 5, 1980);
        returnOfTheJedi = new StarWarsEpisode("Return Of The Jedi", 6, 1983);
        phantomMenace = new StarWarsEpisode("The Phantom Menace", 1, 1999);
        attackOfTheClones = new StarWarsEpisode("Attack Of The Clones", 1, 2002);
        revengeOfTheSith = new StarWarsEpisode("Revenge Of The Sith", 1, 2005);

        hanSolo = new StarWarsCharacter("Han Solo", aNewHope);
        lukeSkywalker = new StarWarsCharacter("Luke Skywalker", aNewHope);
        princessLeia = new StarWarsCharacter("Princess Leia", aNewHope);
        landoCalrissian = new StarWarsCharacter("Lando Calrissian", empireStrikesBack);
        bobaFett = new StarWarsCharacter("Boba Fett", empireStrikesBack);

        byLengthOrdering = new Ordering<String>() {
            public int compare(String left, String right) {
                return (left.length() - right.length());
            }
        };
    }


    @Test(groups = "ordering")
    public void testOrdering() {
        assertEquals(Ordering.from(new StarWarsEpisodeYearComparator())
                .max(aNewHope, phantomMenace),
                phantomMenace);
    }

    @Test(groups = "ordering")
    public void testCompoundOrdering() {
        assertEquals(Ordering.from(new StarWarsCharacterYearComparator())
                .compound(new StarWarsCharacterNameComparator()).
                        sortedCopy(
                                Lists.newArrayList(bobaFett, princessLeia,
                                        landoCalrissian, lukeSkywalker,
                                        hanSolo)).toString(),
                "[Han Solo, Luke Skywalker, Princess Leia, " +
                        "Boba Fett, Lando Calrissian]");
    }

    @Test(groups = "ordering")
    public void testAdHocOrdering() {
        Ordering<String> byLengthOrdering = new Ordering<String>() {
            public int compare(String left, String right) {
                return (left.length() - right.length());
            }
        };
        assertEquals(byLengthOrdering.max(hanSolo.getName(),
                lukeSkywalker.getName(), princessLeia.getName()),
                "Luke Skywalker");
    }


    @Test(groups = "ordering")
    public void orderingExplicit() {
        //Which is better? Phantom Menace or A New Hope?
        assertEquals(Ordering.explicit
                (phantomMenace, attackOfTheClones, revengeOfTheSith, returnOfTheJedi, aNewHope, empireStrikesBack)
                .max(revengeOfTheSith, aNewHope), aNewHope);
    }

    @Test(groups = "ordering")
    public void orderingExplicitAnotherTest() {
        StarWarsEpisode planetOfTheApes = new StarWarsEpisode("Planet of the Apes", 12, 1965);

        //Which is better? Planet of the Apes or A New Hope?
        try {
            Ordering.explicit
                    (phantomMenace, attackOfTheClones, revengeOfTheSith, returnOfTheJedi, aNewHope, empireStrikesBack)
                    .max(planetOfTheApes, aNewHope);
        } catch (Exception ive) {
            assertTrue(true);
        }
    }


    @Test(groups = "ordering")
    public void testNullsLast() {
        assertEquals(
                byLengthOrdering.nullsLast()
                        .sortedCopy(Arrays.asList(hanSolo.getName(), null, lukeSkywalker.getName(), null,
                                princessLeia.getName())).toString(),
                "[Han Solo, Princess Leia, Luke Skywalker, null, null]");

    }

    @Test(groups = "ordering")
    public void testNullsFirst() {
        assertEquals(
                byLengthOrdering.nullsFirst()
                        .sortedCopy(Arrays.asList(hanSolo.getName(), null, lukeSkywalker.getName(), null,
                                princessLeia.getName())).toString(),
                "[null, null, Han Solo, Princess Leia, Luke Skywalker]");

    }

    @Test(groups = "ordering")
    public void testIsOrdered() {
        assertTrue(
                byLengthOrdering.isOrdered(Arrays.asList(hanSolo.getName(),
                        princessLeia.getName(), lukeSkywalker.getName(), lukeSkywalker.getName())));

    }

    @Test(groups = "ordering")
    public void testIsStrictlyOrdered() {
        assertFalse(
                byLengthOrdering.isStrictlyOrdered(Arrays.asList(hanSolo.getName(),
                        princessLeia.getName(), lukeSkywalker.getName(), lukeSkywalker.getName())));

    }

    @Test(groups = "ordering")
    public void testBinarySearch() {
        StarWarsCharacterNameComparator starWarsCharacterNameComparator = new StarWarsCharacterNameComparator();
        StarWarsCharacter key = new StarWarsCharacter("Princess Leia", null);
        assertEquals(
                Ordering.from(starWarsCharacterNameComparator).binarySearch(Arrays.asList(bobaFett, hanSolo,
                        landoCalrissian, lukeSkywalker, princessLeia), key), 4);

    }

    @Test(groups = "ordering")
    public void testNaturalOrdering() {
        assertEquals(
                Ordering.natural().sortedCopy(
                        Arrays.asList(bobaFett, hanSolo, lukeSkywalker, princessLeia, landoCalrissian)).toString(),
                "[Boba Fett, Han Solo, Lando Calrissian, Luke Skywalker, Princess Leia]");

    }
}
