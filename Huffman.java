import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Huffman {

    // Do NOT add any variables (instance or static)

    /**
     * Builds a frequency map of characters for the given string.
     *
     * This should just be the count of each character. No character not in the
     * input string should be in the frequency map.
     *
     * @param s
     *            the string to map
     * @return the character -> Integer frequency map
     */
    public static Map<Character, Integer> buildFrequencyMap(String s) {
        if (s == null) {
            throw new IllegalArgumentException();
        }
        int currLength = 0;
        Integer frequency = 0;
        Map<Character, Integer> freqMap = new HashMap<Character, Integer>();
        while (currLength < s.length()) {
            if (freqMap.containsKey(s.charAt(currLength))) {
                frequency = freqMap.get(s.charAt(currLength));
                frequency++;
                freqMap.put(s.charAt(currLength), frequency);
            } else {
                freqMap.put(s.charAt(currLength), 1);
            }
            currLength++;
        }
        return freqMap;
    }

    /**
     * Build the Huffman tree using the frequencies given.
     *
     * Add the nodes to the tree based on their natural ordering (the order
     * given by the compareTo method). The frequency map will not necessarily
     * come from the {@code buildFrequencyMap()} method. Every entry in the map
     * should be in the tree.
     *
     * @param freq
     *            the frequency map to represent
     * @return the root of the Huffman Tree
     */
    public static HuffmanNode buildHuffmanTree(Map<Character, Integer> freq) {
        if (freq == null) {
            throw new IllegalArgumentException();
        }

        Set<Character> key = freq.keySet();
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<HuffmanNode>();
        for (Character e : key) {
            HuffmanNode element = new HuffmanNode(e, freq.get(e));
            queue.add(element);

        }
        while (queue.size() > 1) {
            HuffmanNode node1 = queue.remove();
            HuffmanNode node2 = queue.remove();
            HuffmanNode parent = new HuffmanNode(node1, node2);
            queue.add(parent);
        }
        return queue.remove();
    }

    /**
     * Traverse the tree and extract the encoding for each character in the
     * tree.
     *
     * The tree provided will be a valid huffman tree but may not come from the
     * {@code buildHuffmanTree()} method.
     *
     * @param tree
     *            the root of the Huffman Tree
     * @return the map of each character to the encoding string it represents
     */
    public static Map<Character, EncodedString> buildEncodingMap(
            HuffmanNode tree) {
        if (tree == null) {
            throw new IllegalArgumentException();
        }
        Map<Character, EncodedString> map =
                new HashMap<Character, EncodedString>();

        EncodedString string = new EncodedString();
        if (tree.getLeft() == null) {
            Character chacha = tree.getCharacter();
            string.zero();
            map.put(chacha, string);
        } else {
            buildEncodingMap(tree, string, map);
        }
        return map;
    }

    /**
     * Recursive helper function to build an encoded map; checks whether there
     * are children or not
     *
     * @param [node] The node you use to recurse through function
     * @param [string] encoded string that keeps track of all bytes added to it;
     *        will also be manipulated to move call up one node
     * @param [map] The map your adding encoded string/character into
     */
    private static void buildEncodingMap(HuffmanNode node,
            EncodedString string, Map<Character, EncodedString> map) {

        if (node.getLeft() != null) {
            string.zero();
            buildEncodingMap(node.getLeft(), string, map);
            string.remove();
            string.one();
            buildEncodingMap(node.getRight(), string, map);
            string.remove();

        } else {
            EncodedString temp = new EncodedString();
            temp.concat(string);
            Character chacha = node.getCharacter();
            map.put(chacha, temp);
        }

    }

    /**
     * Encode each character in the string using the map provided.
     *
     * If a character in the string doesn't exist in the map ignore it.
     *
     * The encoding map may not necessarily come from the
     * {@code buildEncodingMap()} method, but will be correct for the tree given
     * to {@code decode()} when decoding this method's output.
     *
     * @param encodingMap
     *            the map of each character to the encoding string it represents
     * @param s
     *            the string to encode
     * @return the encoded string
     */
    public static EncodedString encode(
            Map<Character, EncodedString> encodingMap, String s) {
        if ((encodingMap == null) || (s == null)) {
            throw new IllegalArgumentException();
        }
        int length = 0;
        EncodedString string = new EncodedString();
        while (length < s.length()) {
            Character chacha = s.charAt(length);
            if (encodingMap.containsKey(chacha)) {
                EncodedString newStr = encodingMap.get(chacha);
                string.concat(newStr);
            }
            length++;
        }
        return string;
    }

    /**
     * Decode the encoded string using the tree provided.
     *
     * The encoded string may not necessarily come from {@code encode()}, but
     * will be a valid string for the given tree.
     *
     * (tip: use StringBuilder to make this method faster -- concatenating
     * strings is SLOW.)
     *
     * @param tree
     *            the tree to use to decode the string
     * @param es
     *            the encoded string
     * @return the decoded string
     */
    public static String decode(HuffmanNode tree, EncodedString es) {
        if ((tree == null) || (es == null)) {
            throw new IllegalArgumentException();
        }

        StringBuilder builder = new StringBuilder();
        Iterator<Byte> itr = es.iterator();
        HuffmanNode node = tree;
        while (itr.hasNext()) {
            if (itr.next() == (byte) 0) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
            if (node == null) {
                Character chacha = tree.getCharacter();
                builder.append(chacha);
                node = tree;
            } else if (node.getLeft() == null) {
                Character chacha = node.getCharacter();
                builder.append(chacha);
                node = tree;
            }
        }

        return builder.toString();
    }
}