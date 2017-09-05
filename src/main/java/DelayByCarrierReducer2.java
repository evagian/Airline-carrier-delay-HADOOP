/**
 * Created by eva on 3/12/17.
 */
import java.io.IOException;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Reducer;

public class DelayByCarrierReducer2 extends
        Reducer<Long, Text, Long, Text> {
    @Override
    protected void reduce(Long key, java.lang.Iterable<Text> values, Context context)
            throws IOException, InterruptedException {

            String finalValue = values.toString();
            context.write(key, new Text(finalValue));


    }
}
