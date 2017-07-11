import java.util.Map;
import java.util.Set;

public class DisjointSets<T> implements DisjointSetsInterface<T> {
    //Should be a map of data to its parent; root data maps to itself.
    private Map<T, Node> set;

    /**
     * @param setItems the initial items (sameSet and merge will never be called
     * with items not in this set, this set will never contain null elements).
     */
    public DisjointSets(Set<T> setItems) {
        // TODO
    }

    @Override
    public boolean sameSet(T u, T v) {
        return false;
    }

    @Override
    public void merge(T u, T v) {

    }

    private class Node {
        //Fill in whatever methods or variables you believe are needed by node
        //here.  Should be O(1) space. This means no arrays, data structures,
        //etc.
    }
}
