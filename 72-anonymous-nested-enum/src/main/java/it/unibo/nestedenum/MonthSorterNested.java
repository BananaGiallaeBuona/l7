package it.unibo.nestedenum;
//this code doesn't pass the sorting test
import java.time.Month;
import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {
    public enum enumMonth {
        JANUARY(31),
        FEBRUARY(28),
        MARCH(31),
        APRIL(30),
        MAY(31),
        JUNE(30),
        JULY(31),
        AUGUST(31),
        SEPTEMBER(30),
        OCTOBER(31),
        NOVEMBER(30),
        DECEMBER(31);

        private final int days;
        enumMonth(final int i) {
            this.days = i;
        } 
        public int getDays() {
            return days;
        }

        public static enumMonth fromString(String str){
            str = str.toLowerCase();
            enumMonth found = null;
            for (final enumMonth mese : enumMonth.values()){
                if (mese.name().toLowerCase().equals(str)){
                    return mese;
                }
                else {
                    if (mese.name().startsWith(str)) {
                        if (found != null) { //if we enter here a 2nd time is a problem
                                        //because we have already found a compatible month 
                            throw new IllegalArgumentException("Ambiguous prefix: '" + str + "'");
                        }
                        found = mese;
                    }  


                }
            }
            if (found != null) { //
                throw new IllegalArgumentException("there isn't any month associable to : '" + str + "'");
            
            }
            return found;
    }
}

    @Override
    public Comparator<String> sortByDays() {
        final Comparator<String> comparator = new Comparator<String>() {
            
            @Override
            public int compare(final String o1, final String o2) {
                try {
                    final int SMALLER = -1;
                    final int EQUAL = 0;
                    final int BIGGER = 1;
                    final enumMonth m1 = enumMonth.fromString(o1);
                    final enumMonth m2 = enumMonth.fromString(o2);
                    
                    if (m1.getDays() < m2.getDays()){
                        return SMALLER;
                    }
                    if (m1.getDays() > m2.getDays()){
                        return BIGGER;
                    }
                    return EQUAL;
                } catch (NullPointerException e) {
                    throw new IllegalArgumentException("Comparator received a null value", e);
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Comparator received an unparseable string", e);
                }
                
            }
        };
        return comparator;
    }

    @Override
    public Comparator<String> sortByOrder() {
        final Comparator<String> comparator = new Comparator<String>() {
            
            @Override
            public int compare(final String o1, final String o2) {
                try{
                    final int BEFORE = -1;
                    final int SAME = 0;
                    final int AFTER = 1;
                    final enumMonth m1 = enumMonth.fromString(o1);
                    final enumMonth m2 = enumMonth.fromString(o2);
                    
                    if (m1.ordinal() < m2.ordinal()){
                        return BEFORE;
                    }
                    if (m1.ordinal() > m2.ordinal()){
                        return AFTER;
                    }
                    return SAME;
                } catch (NullPointerException e) {
                    throw new IllegalArgumentException("Comparator received a null value", e);
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Comparator received an unparseable string", e);
                }
            }
                    
        };
        return comparator;
    }
}
