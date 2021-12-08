public class Cat extends Animal {

    // useful for the actForTestMethod function
    private final String forTestMethod = "Thinking";

    public Cat() { super("Cat"); }

    public void act_forTestMethod() {
        // TODO : How could you invoke the parent act method from here with action parameter : the String forTestMethod
        super.act(forTestMethod);
    }

}
