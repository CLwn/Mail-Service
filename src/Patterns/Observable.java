package Patterns;

public interface Observable {
    void attach(Object o);
    void detach(Object o);
    void advise();
}
