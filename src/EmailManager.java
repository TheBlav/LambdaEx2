import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

class EmailManager {

    private static <T> List<T> filteredByPredicate (List<T> list , Predicate<T> predicate){
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if(predicate.test(t))
                result.add(t);
        }
        return result;
    };
    private static <T> void consumeList (List<T> list, Consumer<T> consumer){
        for (T t : list) {
            consumer.accept(t);
        }
    };

    public static void main(String[] args) {
        List<Email> emailList = createEmailList();
        //tylko wysłane emaile
        List<Email> sentEmails = filteredByPredicate(emailList, email -> email.isSent());
        System.out.println("Wysłane maile:");
        consumeList(sentEmails, email -> System.out.println(email));
        List<Email> bartEmails1 = filteredByPredicate(emailList, email -> email.getRecipient().equals("bart@example.com"));

        
        //filtrowanie maili, w których nadawca lub odbiorca ma wskazany adres email
        System.out.println("Maile przefiltrowane na podstawie nadawcy lub odbiorcy");
        consumeList(bartEmails1, email -> System.out.println(email));
    }





    private static List<Email> createEmailList() {
        List<Email> emails = new ArrayList<>();
        emails.add(new Email(
                "joe@example.com",
                "barbara@example.com",
                "Kup bułki",
                "Cześć!, Kup proszę bułki, gdy będziesz wracać z pracy.",
                false)
        );
        emails.add(new Email(
                "carl@example.com",
                "joe@example.com",
                "Nowa inwestycja",
                "Siema! Musimy omówić temat nowej inwestycji, pasuje Ci jutro?",
                true)
        );
        emails.add(new Email(
                "joe@example.com",
                "bart@example.com",
                "Wypad na miasto",
                "Cześć. Idziemy dzisiaj wieczorem z chłopakami na miasto. Dołączasz do nas?",
                true)
        );
        return emails;
    }
}