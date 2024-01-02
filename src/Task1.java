import java.util.ArrayList;
import java.util.List;

class Word {
    private String content;

    public Word(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}

class Line {
    private List<Word> words;

    public Line() {
        this.words = new ArrayList<>();
    }

    public void addWord(Word word) {
        words.add(word);
    }

    public List<Word> getWords() {
        return words;
    }
}

class Page {
    private List<Line> lines;

    public Page() {
        this.lines = new ArrayList<>();
    }

    public void addLine(Line line) {
        lines.add(line);
    }

    public List<Line> getLines() {
        return lines;
    }
}

public class Task1 {
    public static void main(String[] args) {
        Word word1 = new Word("Привет");
        Word word2 = new Word("мир");

        Line line1 = new Line();
        line1.addWord(word1);
        line1.addWord(word2);

        Page page1 = new Page();
        page1.addLine(line1);

        List<Line> lines = page1.getLines();
        for (Line line : lines) {
            List<Word> words = line.getWords();
            for (Word word : words) {
                System.out.print(word.getContent() + " ");
            }
            System.out.println();
        }
    }
}