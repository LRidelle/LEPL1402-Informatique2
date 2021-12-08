import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Evaluator {

    public BiFunction<Boolean, Boolean, Boolean> xor_gate() {
        return (a, b) -> a != b;
    }

    public BiFunction<Boolean, Boolean, Boolean> or_gate() {
        return (a, b) -> a || b;
    }

    public BiFunction<Boolean, Boolean, Boolean> and_gate() {
        return (a, b) -> a && b;
    }

    public Function<Boolean, Boolean> not_gate() {
        return (a) -> !a;
    }

    // Should return a map with the results stored in map ( use HashMap )
    // Keys are "SUM", "CarryOut"
    // TODO WARNING : USE HERE ONLY the previously defined method to compute result
    //  (as inginious will prevent you to cheat to directly invoke logical operators)
    public Map<String, Boolean> evaluate_circuit(Boolean a, Boolean b, Boolean carry_in) {
        HashMap result = new HashMap();
        Boolean outXorAB = xor_gate().apply(a,b);
        Boolean sum = xor_gate().apply(outXorAB, carry_in);
        result.put("SUM", sum);
        Boolean outAndAB = and_gate().apply(a,b);
        Boolean outAndDCin = and_gate().apply(outXorAB,carry_in);
        Boolean carry_out = or_gate().apply(outAndAB,outAndDCin);
        result.put("CarryOut", carry_out);
        return result;
    }

}