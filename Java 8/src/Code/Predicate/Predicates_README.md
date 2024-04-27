# Example Programs using Predicate:

[1. Check length of the String.](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Java%208/src/Code/Predicate/PredicateCheckLength.java)
[2. Check if list is Empty.](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Java%208/src/Code/Predicate/PredicateIsEmpty.java)
[3. Check if Integer is greater.](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Java%208/src/Code/Predicate/PredicateIsGreater.java)
[4. Code using Predicate Joining.](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Java%208/src/Code/Predicate/PredicateJoining.java)
[5. Check if name starts with K.](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Java%208/src/Code/Predicate/PredicateNameStartWithK.java)
[6. Remove null and Empty String.](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Java%208/src/Code/Predicate/PredicateRemoveNullEmptyStrings.java)
[7. Authenticate User](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Java%208/src/Code/Predicate/PredicateUserAuthentication.java)
[8. Check if allowed in Pub.](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Java%208/src/Code/Predicate/PredicateIsAllowedInPub.java)

[All Codes](https://github.com/Rajeev-singh-git/Java_Interview_Question/tree/main/Java%208/src/Code/Predicate)

# Questions

### Q1. Which of the following abstract methods is present in the Predicate interface?

A. test()
B. apply()
C. get()
D. accept()

Answer: A

Explanation: The Predicate functional interface contains only one abstract method: test()

### Q2. Which of the following is the static method present in the Predicate interface?

A. test()
B. and()
C. or()
D. isEqual()

Answer: D

Explanation: The Predicate functional interface contains only one static method: isEqual()

### Q3. Which of the following default methods is present in the Predicate interface?

A. and()
B. or()
C. negate()
D. All of the above
Answer: D

Explanation: The Predicate functional interface contains the following three default methods: and(), or(), negate()

### Q4. Which of the following is Predicate interface declaration?

```java
interface Predicate<T>
{
public boolean test(T t);
}

```

Explanation: Predicate interface can take only one type parameter which represents the input type. We are not required to specify the return type because the return type is always boolean.

### Q5. Which of the following is a valid Predicate to check whether the given Integer is divisible by 10 or not?

A. Predicate<Integer> p = i -> i%10 == 10;
B. Predicate<Integer,Boolean> p =i->i%10==0;
C. Predicate<Boolean,Integer> p =i->i%10==0;
D. None of the above
Answer: A
Explanation: The correct Predicate to check if an Integer is divisible by 10 would be option A, where the lambda expression checks if the Integer modulo 10 is equal to 0, indicating divisibility by 10.

### Q6. Which of the following statements is valid regarding Predicate functional interface?

A. Predicate Functional interface present in java.util.function package
B. It is introduced in java 1.8 version
C. We can use Predicate to implement conditional checks
D. It is possible to join 2 predicates into a single predicate also.
E. All the above
Answer: E

### Q7. Which of the following is a valid Predicate to check whether the given user is admin or not?

A. Predicate<User> p=user->user.getRole().equals("Admin");
B. Predicate<Boolean> p=user->user.getRole().equals("Admin");
C. Predicate<User> p=(user,s="admin")->user.getRole().equals(s);
D. None of the above
Answer: A

### Q8. Consider the following Predicates

Predicate<Integer> p1=i->i%2==0;
Predicate<Integer> p1=i->i>10;
Which of the following are invalid ?
A. p1.and(p2)
B. p1.or(p2)
C. p1.negate(p2)
D. p1.negate()

Answer: C
Explanation: negate() method won't take any argument
