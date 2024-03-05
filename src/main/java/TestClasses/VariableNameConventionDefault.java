package TestClasses;

import java.util.ArrayList;

public class VariableNameConventionDefault  {


    // Valid Dynamic Variables
    public int validVariable1  = 0;
    private int validVariable2 = 0;
    protected int validVariable3 = 0;

    // Valid Static Variables
    public static int VALID_VARIABLE4  = 0;
    private static int VALID_VARIABLE_5 = 0;
    protected static int VALIDVARIABLE6 = 0;

    // Invalid Dynamic Variables
    public int InvalidVariable1  = 0;
    private int invalid_variable2 = 0;
    protected int invalidVariable_3 = 0;

    // Invalid Dynamic Variables
    public static int InvalidVariable4  = 0;
    private static int invalid_variable5 = 0;
    protected static int invalidVariable_6 = 0;


    public VariableNameConventionDefault(String BadExample1, int BAD_EXAMPLE_2) {
        // Valid Local Variables
        int validLocalVariable1  = 0;
        validLocalVariable1      = -1;

        // Invalid Local Variables
        int invalid_local_variable = 0;
        int InvalidLocalVariable   = 0;
        int invalidLocal_Variable  = 0;
    }


    private void method2(String goodExample1) {
        // Valid Local Variables
        int validLocalVariable1  = 0;
        validLocalVariable1      = -1;

        // Invalid Local Variables
        int invalid_local_variable = 0;
        int InvalidLocalVariable   = 0;
        int invalidLocal_Variable  = 0;
    }

}
