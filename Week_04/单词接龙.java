class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordlist) {
        Set<String> wordSet = new HashSet<>(wordlist);
        wordSet.remove(beginWord);
        if (beginWord.length() != endWord.length()) return 0;
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(beginWord);
        visited.add(beginWord);
        int step = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                if (checkWord(word, endWord, wordSet, visited, queue)) return step + 1;
            }
            step++;
        }
        return 0;
    }

    private boolean checkWord(String word, String endWord, Set<String> wordSet, Set<String> visited, Queue<String> queue) {
        char[] wordList = word.toCharArray();
        for (int i = 0; i < wordList.length; i++) {
            char currWord = wordList[i];
            for (char j = 'a'; j <= 'z'; j++) {
                if (currWord == j) continue;
                wordList[i] = j;
                String tmpWord = String.valueOf(wordList);
                if (wordSet.contains(tmpWord)) {
                    if (tmpWord.equals(endWord)) return true;
                    if (!visited.contains(tmpWord)) {
                        queue.add(tmpWord);
                        visited.add(tmpWord);
                    }
                }
            }
            wordList[i] = currWord;
        }
        return false;
    }
}