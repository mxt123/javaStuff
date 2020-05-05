import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JacquardIndex

{        
    private static final String NONE_FOUND = "NONE FOUND";
    private final DocumentCategory doctorSeuss = new DocumentCategory("doctorSeuss", Arrays.asList("the","cat","in","the","hat","came","back"));
    private final DocumentCategory lFrankBaum = new DocumentCategory("lFrankBaum",Arrays.asList("the","wizard","of","oz"));
    private final DocumentCategory jrrTolkien = new DocumentCategory("jrrTolkien",Arrays.asList("the","fellowship","of","the","ring"));
    private final DocumentCategory eCline = new DocumentCategory("eCline",Arrays.asList("ready","player","one"));
    private final DocumentCategory kKesey = new DocumentCategory("kKesey",Arrays.asList("one","flew","over","the","cuckoos","nest"));
    private final List<DocumentCategory> categories = Arrays.asList(doctorSeuss,lFrankBaum,jrrTolkien,eCline,kKesey);

    //public List<String> stanfordCoreNLP lemmatization 
    // lemmatize
    // spellcheck
    // use the synonym matchers
    // JI = a intersect b / a union b JD = 1-JI
    private double getJacquardIndex(final List<String> bagOfWords, final DocumentCategory category)
    {        
        final double countSharedMembers = category.countSharedMembers(bagOfWords);
        final double countTotalMembers = category.countTotalMembers(bagOfWords);
        final double index = countSharedMembers / countTotalMembers; 
        return index;        
    }
    public Map<DocumentCategory,Double> getIndices (final List<String> bagOfWords)
    {
        final Map<DocumentCategory,Double> result = new HashMap<DocumentCategory,Double>();

        for ( final DocumentCategory category: categories)
        {
            result.put(category,getJacquardIndex(bagOfWords, category));            
        }

        return result;
        
    }

    public String getCategory(final List<String> bagOfWords)
    {
        final Map<DocumentCategory,Double> indices = getIndices (bagOfWords);
        final Iterator<Map.Entry<DocumentCategory,Double>> it = indices.entrySet().iterator();
        Double highestScore = 0D;
        String match = NONE_FOUND;
        
        while(it.hasNext())
        {
            final Map.Entry<DocumentCategory,Double> possibleMatch = it.next();
            final double index = possibleMatch.getValue();

            if (highestScore < index )
            {
                highestScore = index;
                match = possibleMatch.getKey().getCategoryName();
            }

        }

        return match;

    }

    public static void main(final String[] args)
    {
        final List<String> test1 = Arrays.asList("the","cat","in","the","hat");   
        final List<String> test2 = Arrays.asList("ready","player");   
        final List<String> test3 = Arrays.asList("ThE", "OF");   
        final List<String> test4 = Arrays.asList("wIzArD");   
        final List<String> test5 = Arrays.asList("fellowship");
        final JacquardIndex matcher = new JacquardIndex();
        System.out.println(matcher.getCategory(test1));
        System.out.println(matcher.getCategory(test2));
        System.out.println(matcher.getCategory(test3));
        System.out.println(matcher.getCategory(test4));
        System.out.println(matcher.getCategory(test5));    
    }
}