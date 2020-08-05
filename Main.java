
public class Main {

    public static void main(String[] args) {
        ConfigParser config;

        /**
         * Check and validate arguments passed to command line
         * If no argument is passed filename should default to config.txt
         */
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("staging")) {
                config = new ConfigParser("config-staging.txt");
            } else if (args[0].equalsIgnoreCase("development")) {
                config = new ConfigParser("config-dev.txt");
            }else {
                System.out.println("Invalid input environment. Please enter a valid Environment.");
                return;
            }
        } else {
            config = new ConfigParser("config.txt");
        }

        System.out.println(config.get("dbname"));
        System.out.println(config.get("application.name"));


    }
}
