package dsa_sorting.class_problems;

import java.util.ArrayList;
import java.util.List;

public class P5_LibraryPlatform {

    public interface Reservable {
        void reserve(String memberId);
        String getReservedBy();
    }

    public interface Downloadable {
        void download();
    }

    public static abstract class Resource implements Comparable<Resource> {
        protected final String id;

        public Resource(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        @Override
        public int compareTo(Resource o) {
            return this.id.compareTo(o.id);
        }
    }

    public static class PhysicalBook extends Resource implements Reservable {
        private String reservedBy;

        public PhysicalBook(String id) {
            super(id);
        }

        @Override
        public void reserve(String memberId) {
            this.reservedBy = memberId;
        }

        @Override
        public String getReservedBy() {
            return reservedBy;
        }
    }

    public static class EBook extends Resource implements Downloadable {
        public EBook(String id) {
            super(id);
        }

        @Override
        public void download() {
            // download logic
        }
    }

    public static class Library {
        // Resources kept in sorted order by ID in an array/list
        private final List<Resource> resources = new ArrayList<>();

        public String addResource(Resource resource) {
            int idx = binarySearch(resource.getId());
            if (idx >= 0) {
                return "duplicate rejected";
            }
            int insertionIndex = -(idx + 1);
            resources.add(insertionIndex, resource);
            return resource.getId() + " added";
        }

        public String reserveResource(String id, String memberId) {
            int idx = binarySearch(id);
            if (idx < 0) {
                return id + " not found";
            }
            Resource r = resources.get(idx);
            if (r instanceof Reservable) {
                ((Reservable) r).reserve(memberId);
                return id + " reserved for " + memberId;
            } else {
                return id + " rejected: reserve unsupported";
            }
        }

        public String downloadResource(String id) {
            int idx = binarySearch(id);
            if (idx < 0) {
                return id + " not found";
            }
            Resource r = resources.get(idx);
            if (r instanceof Downloadable) {
                ((Downloadable) r).download();
                return id + " downloaded";
            } else {
                return id + " rejected: download unsupported";
            }
        }

        public String findResource(String id) {
            int idx = binarySearch(id);
            if (idx >= 0) {
                return id + " found at index " + idx;
            } else {
                return id + " not found";
            }
        }

        private int binarySearch(String id) {
            int left = 0;
            int right = resources.size() - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                int cmp = resources.get(mid).getId().compareTo(id);
                if (cmp == 0) {
                    return mid;
                } else if (cmp < 0) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return -(left + 1);
        }
    }

    public static void main(String[] args) {
        Library library = new Library();

        System.out.println(library.addResource(new PhysicalBook("B1")));
        System.out.println(library.addResource(new PhysicalBook("B1")));
        System.out.println(library.addResource(new EBook("E1")));

        System.out.println(library.reserveResource("B1", "M1"));
        System.out.println(library.downloadResource("B1"));
        System.out.println(library.downloadResource("E1"));
        System.out.println(library.findResource("E1"));
    }
}
