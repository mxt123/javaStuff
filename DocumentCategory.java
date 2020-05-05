import java.util.List;

public class DocumentCategory
{
    private String categoryName;
    private List<String> bagOfWords;

    public String getCategoryName() {
        return categoryName;
    }

    public List<String> getBagOfWords() {
        return bagOfWords;
    }

    public DocumentCategory(String categoryName, List<String> bagOfWords) {
        this.categoryName = categoryName;
        this.bagOfWords = bagOfWords;
    }

    public int countSharedMembers(List<String> wordsToCompare)
    {
        int count = 0;

        for (final String word : wordsToCompare)
        {
            if (bagOfWords.contains(word.toLowerCase())){
                count ++;
            }
        }

        return count;
    }

    public int countTotalMembers(List<String> wordsToCompare)
    {
        return this.bagOfWords.size() + wordsToCompare.size();
    }
}