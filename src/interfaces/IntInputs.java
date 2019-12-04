package interfaces;

import java.util.Arrays;
import java.util.stream.IntStream;

public interface IntInputs extends Reader {

    default IntStream get_int_inputs_stream(String delimiter){
        return Arrays.stream(get_puzzle_input().split(delimiter))
                .mapToInt(Integer::parseInt);
    }

    default int[] get_int_inputs(String delimiter){
        return get_int_inputs_stream(delimiter).toArray();
    }


}
