package core;

public interface Page {

    void open();
    void refresh();
    void waitFor(long millisec);

}
