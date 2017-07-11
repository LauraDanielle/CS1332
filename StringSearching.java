import java.util.ArrayList;
import java.util.List;

public class StringSearching implements StringSearchingInterface {

    @Override
    public List<Integer> boyerMoore(CharSequence pattern, CharSequence text) {
        if ((pattern == null) || (pattern.length() == 0) || (text == null)) {
            throw new IllegalArgumentException();
        }
        List<Integer> list = new ArrayList<Integer>();
        if ((text.length() == 0) || (pattern.length() > text.length())) {
            list = null;
        } else {
            int[] table = buildLastTable(pattern);

            int jump = pattern.length() - 1;
            int pat = pattern.length() - 1;
            int i = pattern.length() - 1;
            while (i < text.length()) {
                if (jump < 0) {
                    jump++;
                } else if (jump > text.length()) {
                    jump--;
                }
                if (text.charAt(jump) == pattern.charAt(pat)) {
                    if (pat == 0) {
                        list.add(jump);

                    }
                    jump--;
                    pat--;
                } else {
                    jump += (pat + 1)
                            - Math.min(pat, 1 + table[text.charAt(jump)]);
                    pat = pattern.length() - 1;
                }
                i++;
            }
        }

        return list;
    }

    @Override
    public int[] buildLastTable(CharSequence pattern) {
        if (pattern == null) {
            throw new IllegalArgumentException();
        }
        int[] array = new int[Character.MAX_VALUE + 1];
        for (int j = 0; j < array.length; j++) {
            array[j] = -1;
        }
        for (int i = 0; i < pattern.length(); i++) {
            array[pattern.charAt(i)] = i;
        }
        return array;
    }

    @Override
    public int generateHash(CharSequence current, int length) {
        if ((current == null) || (length < 0) || (current.length() > length)) {
            throw new IllegalArgumentException();
        }

        int hash = 0;
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                hash = hash + current.charAt(i)
                        * exponent(BASE, (length - 1 - i));
            }
        }
        return hash;
    }

    private int exponent(int base, int power) {
        if (power == 0) {
            return 1;
        }
        return base * exponent(base, --power);

    }

    @Override
    public int updateHash(int oldHash, int length, char oldChar, char newChar) {

        return (oldHash - oldChar * exponent(BASE, length - 1)) * BASE
                + newChar * exponent(BASE, 0);
    }

    @Override
    public List<Integer> rabinKarp(CharSequence pattern, CharSequence text) {
        if ((pattern == null) || (pattern.length() == 0) || (text == null)) {
            throw new IllegalArgumentException();
        }
        List<Integer> list = new ArrayList<Integer>();
        if ((text.length() == 0) || (pattern.length() > text.length())) {
            list = null;
        } else {
            int hashPat = generateHash(pattern, pattern.length());

            int hashTextOrg = generateTextHash(text, pattern.length());
            if ((hashPat == hashTextOrg) && (isMatch(text, pattern, 0))) {

                list.add(0);

            }

            for (int i = 0; i < text.length() - pattern.length(); i++) {
                hashTextOrg = updateHash(hashTextOrg, pattern.length(),
                        text.charAt(i), text.charAt(i + pattern.length()));

                int index = i + 1;
                if ((hashPat == hashTextOrg) && (isMatch(text, pattern, index))) {
                    list.add(index);

                }

            }
        }

        return list;
    }

    /**
     * Helper function to get the first substring of the text
     * 
     * @param [text] full text
     * @param [length] length of pattern
     * @return hash of inital substring of text
     */
    private int generateTextHash(CharSequence text, int length) {
        CharSequence initialText = "";
        for (int hash = 0; hash < length; hash++) {
            initialText = "" + initialText + text.charAt(hash);
        }

        return generateHash(initialText, length);
    }

    /**
     * helper functino that tests every character if hashes are equal
     * 
     * @param [text] full text
     * @param [pattern] pattern to match
     * @param [index] spot in text to start character match searching
     * @return true if pattern/text match and false otherwise
     */
    private boolean isMatch(CharSequence text, CharSequence pattern, int index) {
        for (int j = 0; j < pattern.length() - 1; j++) {
            if (pattern.charAt(j) != text.charAt(j + index)) {
                return false;
            }
        }
        return true;
    }
}
