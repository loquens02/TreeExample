package config;

/**
 *  java common constant https://stackoverflow.com/questions/3913529/how-can-i-have-a-constant-variable-common-to-all-classes-in-a-package
 */
public interface Config {
    static final String FILE_PATH= "./export";

    static final String NAME_ALL= FILE_PATH+ "/input.csv";

    static final String NAME_DELETE= FILE_PATH+ "/delete.csv";
    static final String NAME_DELETE_COMPARE= FILE_PATH+ "/delete_compare.csv";
    static final String NAME_DELETE_RESULT= FILE_PATH+ "/delete_result.csv";

    static final String NAME_INPUT_RESULT= FILE_PATH+ "/input_result.csv";

    static final int CSV_RAND_COUNT= 100;
    static final int MAIN_RAND_COUNT= 100;
    static final int RAND_RANGE= 1000;
}
