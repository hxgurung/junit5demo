package com.vogella.unittest.services;

import static com.vogella.unittest.model.Race.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import com.vogella.unittest.model.Movie;
import com.vogella.unittest.model.Race;
import com.vogella.unittest.model.Ring;
import lombok.experimental.Tolerate;
import org.junit.jupiter.api.*;

import com.vogella.unittest.model.TolkienCharacter;
import com.vogella.unittest.services.DataService;

class DataServiceTest {

    // TODO initialize before each test
    DataService dataService;

    @BeforeEach
    void setUp(){
        dataService = new DataService();
    }

    @Test
    void ensureThatInitializationOfTolkeinCharactorsWorks() {
        TolkienCharacter frodo = new TolkienCharacter("Frodo", 33, HOBBIT);

        // TODO check that age is 33
        assertEquals(33,frodo.age, "Frodo should be 33");
        // TODO check that name is "Frodo"
        assertEquals("Frodo",frodo.getName(),"Wrong Name");
        // TODO check that name is not "Frodon"
        assertNotEquals("Gurung",frodo.getName(),"Correct name is Frodo");
        //fail("not yet implemented");
    }


    @Test
    void checkInheritance() {
        TolkienCharacter tolkienCharacter = dataService.getFellowship().get(0);
        // TODO check that tolkienCharacter.getClass is not a movie class
        assertFalse(Movie.class.isAssignableFrom(tolkienCharacter.getClass()));
        assertTrue(TolkienCharacter.class.isAssignableFrom(tolkienCharacter.getClass()));
        //fail("not yet implemented");
    }

    @Test
    void ensureFellowShipCharacterAccessByNameReturnsNullForUnknownCharacter1() {
        // TODO imlement a check that dataService.getFellowshipCharacter returns null for an
        // unknow felllow, e.g. "Lars"
        TolkienCharacter fellowshipCharacter = dataService.getFellowshipCharacter("Lars");
        assertNull(fellowshipCharacter);
        //fail("not yet implemented");
    }

    @Test
    void ensureFellowShipCharacterAccessByNameReturnsNullForUnknownCharacter() {
        TolkienCharacter fellowshipCharacter = dataService.getFellowshipCharacter("Lars");
        assertNull(fellowshipCharacter);
    }

    @Test
    void ensureFellowShipCharacterAccessByNameWorksGivenCorrectNameIsGiven1() {
        // TODO imlement a check that dataService.getFellowshipCharacter returns a fellow for an
        // existing felllow, e.g. "Frodo"
        TolkienCharacter fellowshipCharacter = dataService.getFellowshipCharacter("Frodo");
        assertNotNull(fellowshipCharacter);
       // fail("not yet implemented");
    }
    @Test
    void ensureFellowShipCharacterAccessByNameWorksGivenCorrectNameIsGiven() {
        TolkienCharacter fellowshipCharacter = dataService.getFellowshipCharacter("Frodo");
        assertNotNull(fellowshipCharacter);
    }

    @Test
    void ensureThatEqualsWorksForCharaters() {
        Object jake = new TolkienCharacter("Jake", 43, HOBBIT);
        Object sameJake = jake;
        Object jakeClone = new TolkienCharacter("Jake", 12, HOBBIT);
        // TODO check that:
        // jake is equal to sameJake
        assertEquals(jake,jake);
        // jake is not equal to jakeClone
        assertNotEquals(jake,jakeClone);

        //fail("not yet implemented");
    }

    @Test
    void ensureThatFrodoAndGandalfArePartOfTheFellowsip() {

        List<TolkienCharacter> fellowship = dataService.getFellowship();

        // TODO check that Frodo and Gandalf are part of the fellowship
        TolkienCharacter frodo = new TolkienCharacter("Frodo", 33, HOBBIT);
        TolkienCharacter gandalf = new TolkienCharacter("Gandalf",2020, MAIA);
        assertTrue(fellowship.contains(frodo));
        assertTrue(fellowship.contains(gandalf));

        //fail("not yet implemented");
    }

    @Test
    void ensureThatOneRingBearerIsPartOfTheFellowship() {

        List<TolkienCharacter> fellowship = dataService.getFellowship();

        // TODO test that at least one ring bearer is part of the fellowship
        Map<Ring, TolkienCharacter> ringBearers = dataService.getRingBearers();
        assertTrue(ringBearers.values().stream().anyMatch(ringBearer -> fellowship.contains(ringBearer)));
       // fail("not yet implemented");
    }

    // TODO Use @RepeatedTest(int) to execute this test 1000 times
    @RepeatedTest(1000)
    @Tag("slow")
    @DisplayName("Ensure that we can call getFellowShip multiple times ")
    void ensureThatWeCanRetrieveFellowshipMultipleTimes() {
        dataService = new DataService();
        assertNotNull(dataService.getFellowship());
      //  fail("this should run 1000 times");
    }

    @Test
    void ensureOrdering() {
        List<TolkienCharacter> fellowship = dataService.getFellowship();

        // ensure that the order of the fellowship is:
        // frodo, sam, merry,pippin, gandalf,legolas,gimli,aragorn,boromir

        assertEquals(dataService.getFellowshipCharacter("Frodo"), fellowship.get(0));
        assertEquals(dataService.getFellowshipCharacter("Sam"), fellowship.get(1));
        assertEquals(dataService.getFellowshipCharacter("Merry"), fellowship.get(2));
        assertEquals(dataService.getFellowshipCharacter("Pippin"), fellowship.get(3));
        assertEquals(dataService.getFellowshipCharacter("Gandalf"), fellowship.get(4));
        assertEquals(dataService.getFellowshipCharacter("Legolas"), fellowship.get(5));
        assertEquals(dataService.getFellowshipCharacter("Gimli"), fellowship.get(6));
        assertEquals(dataService.getFellowshipCharacter("Aragorn"), fellowship.get(7));
        assertEquals(dataService.getFellowshipCharacter("Boromir"), fellowship.get(8));

        //fail("not yet implemented");
    }

    @Test
    void ensureAge() {
        List<TolkienCharacter> fellowship = dataService.getFellowship();

        // TODO test ensure that all hobbits and men are younger than 100 years

        // TODO also ensure that the elfs, dwars the maia are all older than 100 years

        assertTrue(fellowship.stream().filter( fellow-> fellow.getRace().equals(HOBBIT) ||
                fellow.getRace().equals(Race.MAN)).allMatch(fellow -> fellow.age <100));



        assertTrue(fellowship.stream().filter(fellow -> fellow.getRace().equals(Race.ELF)
                        || fellow.getRace().equals(Race.DWARF)
                        || fellow.getRace().equals(Race.MAIA))
                        .allMatch(fellow -> fellow.age > 100));





        //fail("not yet implemented");



        // HINT fellowship.stream might be useful here
    }

    @Test
    void ensureThatFellowsStayASmallGroup() {

        List<TolkienCharacter> fellowship = dataService.getFellowship();

        // TODO Write a test to get the 20 element from the fellowship throws an
        // IndexOutOfBoundsException
        assertThrows(IndexOutOfBoundsException.class, () -> fellowship.get(20));

       // fail("not yet implemented");
    }

}