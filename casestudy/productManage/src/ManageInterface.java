public interface ManageInterface<E> {
    E create();

    E update();

    E delete();

    void displayAll();
}
