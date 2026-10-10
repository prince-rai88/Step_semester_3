package data_structures.class_problems;

public class P3_LibraryCatalog {
    public static class BookRecord {
        public String isbn;
        public String title;
        
        public BookRecord(String isbn, String title) {
            this.isbn = isbn;
            this.title = title;
        }
    }

    /**
     * Efficiently checks if a given ISBN exists in the sorted catalog and retrieves its title.
     * 
     * Time Complexity: O(log n) where n is the number of books in the catalog. We halve the search 
     *                  space at each step, making it much faster than O(n) for frequent queries.
     * Space Complexity: O(1) as we only use a few pointer variables for the binary search.
     * 
     * @param catalog Sorted array of BookRecords by ISBN in ascending order
     * @param targetIsbn The ISBN to search for
     * @return The title of the book if found, or "Not Found"
     */
    public static String findBook(BookRecord[] catalog, String targetIsbn) {
        int left = 0;
        int right = catalog.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = catalog[mid].isbn.compareTo(targetIsbn);
            
            if (comparison == 0) {
                return catalog[mid].title;
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return "Not Found";
    }

    public static void main(String[] args) {
        BookRecord[] catalog = {
            new BookRecord("0001112223", "Introduction to Algebra"),
            new BookRecord("0002223334", "Beginning Python"),
            new BookRecord("0003334445", "Classic Mythology"),
            new BookRecord("0004445556", "Data and Society"),
            new BookRecord("0005556667", "European History")
        };
        
        System.out.println("Sample 1: " + findBook(catalog, "0003334445"));
        System.out.println("Sample 2: " + findBook(catalog, "0009998887"));
    }
}
