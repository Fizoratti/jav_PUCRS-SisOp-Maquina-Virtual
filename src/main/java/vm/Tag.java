package vm;

public class Tag {

    private static String tag(String _color, String _label) {
        return (char)27 + _color + _label + (char)27 + "[0m" + " :";
    }
    


    public static String red(String _label) {
        return (char)27 + COLOR_RED + _label + (char)27 + "[0m";
    }
    public static String green(String _label) {
        return (char)27 + COLOR_GREEN + _label + (char)27 + "[0m";
    }
    public static String yellow(String _label) {
        return (char)27 + COLOR_YELLOW + _label + (char)27 + "[0m";
    }
    public static String blue(String _label) {
        return (char)27 + COLOR_BLUE + _label + (char)27 + "[0m";
    }



    private static String COLOR_RED     = "[31m";
    private static String COLOR_GREEN   = "[32m";
    private static String COLOR_YELLOW  = "[33m";
    private static String COLOR_BLUE    = "[34m";
    private static String COLOR_VIOLET    = "[35m";



    private static String LABEL_VM      = "    [ VM ]";
    private static String LABEL_CPU     = "   [ CPU ]";
    private static String LABEL_MEMORY  = "[ Memory ]";
    private static String LABEL_SETUP   = "(Setup)";
    

    public static String VM     = tag(COLOR_VIOLET, LABEL_VM);
    public static String CPU    = tag(COLOR_VIOLET, LABEL_CPU);
    public static String MEMORY = tag(COLOR_VIOLET, LABEL_MEMORY);
    
    public static String SETUP  = tag(COLOR_YELLOW, LABEL_SETUP);
}
