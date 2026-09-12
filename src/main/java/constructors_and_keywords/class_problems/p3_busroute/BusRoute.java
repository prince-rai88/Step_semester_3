package constructors_and_keywords.class_problems.p3_busroute;

public class BusRoute implements Comparable<BusRoute> {
    private String routeCode;
    private String routeName;
    private int priority;

    public BusRoute(String routeCode, String routeName, int priority) {
        this.routeCode = routeCode;
        this.routeName = routeName;
        this.priority = priority;
    }

    public BusRoute(String routeCode, String routeName) {
        this(routeCode, routeName, 5);
    }

    @Override
    public int compareTo(BusRoute other) {
        int pDiff = Integer.compare(this.priority, other.priority);
        if (pDiff != 0) return pDiff;
        
        return this.routeCode.compareToIgnoreCase(other.routeCode);
    }

    public static BusRoute[] rankRoutes(BusRoute[] routes) {
        for (int i = 0; i < routes.length; i++) {
            for (int j = i + 1; j < routes.length; j++) {
                if (routes[i].compareTo(routes[j]) > 0) {
                    BusRoute temp = routes[i];
                    routes[i] = routes[j];
                    routes[j] = temp;
                }
            }
        }
        return routes;
    }
}
