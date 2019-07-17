import org.junit.Test;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class StreamHWTest {

    @Test
    public void shouldMapStringsToUpperCase() {
        List<String> input = asList("This", "is", "java", "8");
        List<String> result = StreamHW.mapToUpperCase(input);
        assertEquals(result, asList("THIS", "IS", "JAVA", "8"));
    }

    @Test
    public void shouldReturnSquareRoot() {
        List<Integer> numbers = asList(1, 4, 16, 256);
        List<Integer> squares = StreamHW.returnSquareRoot(numbers);
        assertEquals(squares, asList(1, 2, 4, 16));
    }

    @Test
    public void shouldReturnAgeFromUser() {
        List<User> users = User.getUsersWithAge(18, 20);
        List<Integer> ageFromUsers = StreamHW.getAgeFromUsers(users);
        assertEquals(ageFromUsers, asList(18, 20));
    }

    @Test
    public void shouldReturnFirstTwo() {
        List<User> users = User.getUsersWithAge(18, 20, 21, 22, 23);
        users = StreamHW.getLimitedUserList(users, 2);
        assertEquals(users, asList(users.get(0), users.get(1)));
    }

    @Test
    public void shouldReturnNumberOfUsersOlderThen25() {
        List<User> users = User.getUsersWithAge(18, 20, 21, 22, 23, 24, 25, 26);
        Integer count = StreamHW.countUsersOlderThen25(users);
        assertTrue(count == 1);
    }

    @Test
    public void shouldReturnDistinctAges() {
        List<User> users = User.getUsersWithAge(18, 20, 20, 21, 22, 22, 23, 24, 25, 26);
        List<Integer> distinctAges = StreamHW.getDistinctAges(users);
        assertEquals(distinctAges, asList(18, 20, 21, 22, 23, 24, 25, 26));
    }

    @Test
    public void shouldSumIntegersInCollection() {
        List<Integer> integers = asList(1, 2, 3, 4, 5);
        Integer result = StreamHW.sum(integers);
        Integer assertResult = 1 + 2 + 3 + 4 + 5;
        assertEquals(result, assertResult);
    }

    @Test
    public void shouldSkipInCollection() {
        List<Integer> integers = asList(1, 2, 3, 4, 5);
        List<Integer> result = StreamHW.skip(integers, 2);
        assertEquals(result,asList(3, 4, 5));
    }

    @Test
    public void shouldReturnFirstNames() {
        List<String> names = asList("Homer Simpson", "Marge Simpson", "Bart Simpson", "Kent Brockman");
        List<String> result = StreamHW.getFirstNames(names);
        assertEquals(result, asList("Homer", "Marge", "Bart", "Kent"));
    }

    @Test
    public void shouldReturnDistinctLetters() {
        List<String> names = asList("Homer Simpson", "Marge Simpson", "Bart Simpson", "Kent Brockman");
        List<String> result = StreamHW.getDistinctLetters(names);
        assertEquals(result, asList("H", "o", "m", "e", "r", " " , "S", "i", "p", "s", "n", "M", "a", "g", "B", "t", "K", "c", "k"));
    }

    @Test
    public void shouldSeparateNamesByComma() {
        List<User> input = asList(
                new User("Homer"),
                new User("Maggie"),
                new User("Bart"));
        String result = StreamHW.separateNamesByComma(input);
        assertEquals(result, "Homer, Maggie, Bart");
    }

    @Test
    public void shouldPerformCalculations(){
        List<User> users = User.getUsersWithAge(10, 20, 30);
        assertEquals(StreamHW.getMinAge(users), (Integer) 10);
        assertEquals(StreamHW.getMaxAge(users), (Integer) 30);
        assertEquals(StreamHW.getAverageAge(users), (double)(10+20+30)/3, 0);
    }

    @Test
    public void shouldPartitionByGender() {
        User homer = new User("Homer", true);
        User bart = new User("Bart", true);
        User maggie = new User("Maggie",false);
        User lisa = new User("Lisa", false);
        List<User> input = asList(homer, bart, maggie, lisa);
        Map<Boolean, List<User>> result = StreamHW.partionUsersByGender(input);
        assertEquals(result.get(true), asList(homer, bart));
        assertEquals(result.get(false), asList(maggie, lisa));
    }

    @Test
    public void shouldGroupByAge() {
        User homer = new User("Homer", 50);
        User bart = new User("Bart", 12);
        User maggie = new User("Maggie",2);
        User lisa = new User("Lisa", 8);
        List<User> input = asList(homer, bart, maggie, lisa);
        Map<Integer, List<User>> result = StreamHW.groupByAge(input);
        assertEquals(result.get(50), asList(homer));
        assertEquals(result.get(12), asList(bart));
        assertEquals(result.get(8), asList(lisa));
        assertEquals(result.get(2), asList(maggie));
    }

    @Test
    public void shouldGroupByGenderAndAge() {
        User homer = new User("Homer", 50, true);
        User bart = new User("Bart", 12, true);
        User maggie = new User("Maggie",2, false);
        User lisa = new User("Lisa", 8, false);
        List<User> input = asList(homer, bart, maggie, lisa);
        Map<Boolean, Map<Integer, List<User>>> result = StreamHW.groupByGenderAndAge(input);
        assertEquals(result.get(true).get(50), asList(homer));
        assertEquals(result.get(true).get(12), asList(bart));
        assertEquals(result.get(false).get(8), asList(lisa));
        assertEquals(result.get(false).get(2), asList(maggie));
    }

    @Test
    public void shouldCountGender() {
        User homer = new User("Homer", 50, true);
        User bart = new User("Bart", 12, true);
        User maggie = new User("Maggie",2, false);
        User lisa = new User("Lisa", 8, false);
        List<User> input = asList(homer, bart, maggie, lisa);
        Map<Boolean, Long> result = StreamHW.countGender(input);
        assertEquals(result.get(true),(Long) 2L);
        assertEquals(result.get(false),(Long) 2L);
    }

    @Test
    public void shouldMatchAge(){
        List<User> users = User.getUsersWithAge(10, 20, 30);
        assertTrue(StreamHW.anyMatch(users, 10));
    }

    @Test
    public void shouldNoneMatchAge(){
        List<User> users = User.getUsersWithAge(10, 20, 30);
        assertTrue(StreamHW.noneMatch(users, 40));
    }

    @Test
    public void shouldFindAnyUser(){
        User homer = new User("Homer", true);
        User bart = new User("Bart", true);
        User maggie = new User("Maggie",false);
        User lisa = new User("Lisa", true);
        List<User> users = asList(homer, bart, maggie, lisa);
        Optional<User> user = StreamHW.findAny(users, "Homer");
        assertTrue(user.isPresent());
    }

    @Test
    public void shouldSortByAge(){
        User homer = new User("Homer", 50);
        User bart = new User("Bart", 12);
        User maggie = new User("Maggie",2);
        User lisa = new User("Lisa", 8);
        List<User> users = asList(homer, bart, maggie, lisa);
        List<User> sorted = StreamHW.sortByAge(users);
        assertEquals(sorted, asList(maggie, lisa, bart, homer));
    }

    @Test
    public void shouldFindOldest(){
        User homer = new User("Homer", 50);
        User bart = new User("Bart", 12);
        User maggie = new User("Maggie",2);
        User lisa = new User("Lisa", 8);
        List<User> users = asList(homer, bart, maggie, lisa);
        User oldest = StreamHW.findOldest(users);
        assertEquals(oldest, homer);
    }

    @Test
    public void shouldSumAgeAsInt(){
        User homer = new User("Homer", 50);
        User bart = new User("Bart", 12);
        User maggie = new User("Maggie",2);
        User lisa = new User("Lisa", 8);
        List<User> users = asList(homer, bart, maggie, lisa);
        int sumAge = StreamHW.sumAge(users);
        int assertAge = 50+12+2+8;
        assertEquals(sumAge, assertAge);
    }

    @Test
    public void shouldGenerateAgeSummaryStatistics(){
        User homer = new User("Homer", 50);
        User bart = new User("Bart", 12);
        User maggie = new User("Maggie",2);
        User lisa = new User("Lisa", 8);
        List<User> users = asList(homer, bart, maggie, lisa);
        IntSummaryStatistics statistics = StreamHW.ageSummaryStatistics(users);
        assertEquals(statistics.getAverage(), (double)(50+12+2+8)/4, 0);
        assertEquals(statistics.getCount(),4L);
        assertEquals(statistics.getMax(),50);
        assertEquals(statistics.getMin(),2);
    }

    @Test
    public void shouldConvertToBoxedStream(){
        List<Integer> numbers = asList(1, 2, 3);
        IntStream intStream = numbers.stream().mapToInt(value -> value);
        Stream<Integer> boxedStream = StreamHW.getBoxedStream(intStream);
        assertTrue(boxedStream.count() == 3);
    }

    @Test
    public void shouldBeEmptyStream(){
        Stream<Integer> numberStream = null; //create empty stream
        assertNotNull(numberStream);
        assertTrue(numberStream.count() == 0);
    }

    @Test
    public void shouldGenerateFirstPrimeNumbers(){
        List<Integer> primeNumbers = StreamHW.generateFirst10PrimeNumbers();
        assertEquals(primeNumbers, asList(2,3,5,7,11,13, 17,19, 23, 29));
    }

    @Test
    public void shouldGenerate10RandomNumbers(){
        List<Integer> randomNumbers = StreamHW.generate10RandomNumbers();
        assertTrue(randomNumbers.size()==10);
    }
}