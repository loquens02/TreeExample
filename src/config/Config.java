package config;

/**
 *  java common constant https://stackoverflow.com/questions/3913529/how-can-i-have-a-constant-variable-common-to-all-classes-in-a-package
 */
public interface Config {
    static final String FILE_PATH= "./export";
    static final String NAME="randInt";

    static final int CSV_RAND_COUNT= 10;
    static final int MAIN_RAND_COUNT= 100;
    static final int RAND_RANGE= 100;
}
