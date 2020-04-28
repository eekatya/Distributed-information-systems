import java.util.ArrayList;

public abstract class DAO<T> {
    public abstract void insertBatch(ArrayList<T> list);
    public abstract void insertPreparedStatement(T el);
    public abstract void insertStatement(T el);
    public abstract T getById(int id);
    public abstract void update(T el);
    public abstract void delete(T el);
    public abstract ArrayList<T> getAll();
}
/*    boolean insertNodeUsingPreparedStatement(Node node);

    boolean insertNodeUsingStatement(Node node);

    void insertBatch(List<Node> nodes);

    Optional<Node> getNodeById(Integer id);

    boolean updateNode(Node node);

    boolean deleteNode(Integer id);*/