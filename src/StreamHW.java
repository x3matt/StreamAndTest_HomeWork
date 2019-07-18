
import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamHW {

    public static List<Integer> returnSquareRoot(List<Integer> numbers){
        return numbers
        		.stream()
        		.map(i -> Math.sqrt(i.doubleValue()))
        		.map(i -> i.intValue())
        		.collect(Collectors.toList());
    }

    public static List<Integer> getAgeFromUsers(List<User> user){
        return user
        		.stream()
        		.map(User::getAge)
        		.collect(Collectors.toList());
    }

    public static List<Integer> getDistinctAges(List<User> users){
        return users
        		.stream()
        		.map(User::getAge)
        		.distinct()
        		.collect(Collectors.toList());
    }

    public static List<User> getLimitedUserList(List<User> users, int limit){
        return users
        		.stream()
        		.limit(limit)
        		.collect(Collectors.toList());
    }

    public static Integer countUsersOlderThen25(List<User> users){
        return (int) users
        		.stream()
        		.filter(u -> u.getAge()>25)
        		.count();
    }

    public static List<String> mapToUpperCase(List<String> strings){
        return strings.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    public static Integer sum(List<Integer> integers){
        return integers
        		.stream()
        		.reduce(0, (i1,i2) -> i1+i2);
    }

    public static List<Integer> skip(List<Integer> integers, Integer toSkip){
        return integers
        		.stream()
        		.skip((long)toSkip)
        		.collect(Collectors.toList());
    }

    public static List<String> getFirstNames(List<String> names){
        return names
        		.stream()
        		.map(a -> a.split(" "))
        		.map(a -> a[0])
        		.collect(Collectors.toList());
    }

    public static List<String> getDistinctLetters(List<String> names){
    	throw new NotImplementedException();
    }


    public static String separateNamesByComma(List<User> users){
        return users
        		.stream()
        		.map(User::getName)
        		.collect(Collectors.joining(", "));
    }

    public static double getAverageAge(List<User> users){
        return users
        		.stream()
        		.collect(Collectors.averagingInt(User::getAge));
    }

    public static Integer getMaxAge(List<User> users){
    	return users
    			.stream()
    			.map(User::getAge)
    			.reduce((a,b) -> a>b?a:b).get();
    }
    public static Integer getMinAge(List<User> users) {
    	return users
    			.stream()
    			.map(User::getAge)
    			.reduce((a,b) -> a<b?a:b).get();
    }

    public static Map<Boolean, List<User>> partionUsersByGender(List<User> users){
        return users
        		.stream()
        		.collect(Collectors.groupingBy(User::isMale));
    }

    public static Map<Integer, List<User>> groupByAge(List<User> users){
        return users
        		.stream()
        		.collect(Collectors.groupingBy(User::getAge));
    }

    public static Map<Boolean, Map<Integer, List<User>>> groupByGenderAndAge(List<User> users){
        return users
        		.stream()
        		.collect(Collectors.groupingBy(User::isMale,Collectors.groupingBy(User::getAge)));
    }

    public static Map<Boolean, Long> countGender(List<User> users){
        return users
        		.stream()
        		.collect(Collectors.groupingBy(User::isMale,Collectors.counting()));
    }

    public static boolean anyMatch(List<User> users, int age){
        return users
        		.stream()
        		.anyMatch(a->  a.getAge()==age?true:false);
    }

    public static boolean noneMatch(List<User> users, int age){
        return users
        		.stream()
        		.allMatch(a -> a.getAge()!=age?true:false);
    }

    public static Optional<User> findAny(List<User> users, String name){
        return users
        		.stream()
        		.findAny()
        		.filter(a -> a.getName()==name);
    }

    public static List<User> sortByAge(List<User> users){
        return users
        		.stream()
        		.sorted((a,b) -> a.getAge()-b.getAge())
        		.collect(Collectors.toList());
    }

    public static Stream<Integer> getBoxedStream(IntStream stream){
        return stream.boxed();
    }

    public static List<Integer> generateFirst10PrimeNumbers(){
        throw new NotImplementedException();
    }

    public static boolean isPrime(int number) {
        return IntStream.rangeClosed(2, number/2).noneMatch(i -> number%i == 0);
    }

    public static List<Integer> generate10RandomNumbers(){
    	throw new NotImplementedException(); 
    }

    public static User findOldest(List<User> users){
        return users
        		.stream()
        		.reduce((a,b) -> a.getAge()>b.getAge()?a:b).get();
    }

    public static int sumAge(List<User> users){
    	return users.stream().map(User::getAge).reduce((a,b) -> a+b).get();
    }

    public static IntSummaryStatistics ageSummaryStatistics(List<User> users){
    	throw new NotImplementedException(); 
    }

}