package src.week2;

public class Main {

    public static void main(String[] args) {

        String environment;

        if (args.length == 0) {
            environment = "development";
        } else {
            environment = args[0];
        }
        String fileName;

        if (environment.equals("production")) {
            fileName = "config-production.txt";
        } else if (environment.equals("staging")) {
            fileName = "config-staging.txt";
        } else if (environment.equals("development")) {
            fileName = "config-development.txt";
        } else {
            System.out.println("Unknown environment: " + environment);
            return;
        }

        ConfigParser config = new ConfigParser(fileName);

        System.out.println(config.get("dbname"));
        System.out.println(config.get("host"));
        System.out.println(config.get("application.name"));
    }
}