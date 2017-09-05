
/**
 * Created by eva on 3/12/17.
 */
import java.io.IOException;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.util.*;


public class DelayByCarrierReducer extends
        Reducer<Text, LongWritable, Text, Text> {
    @Override
    protected void reduce(Text key, java.lang.Iterable<LongWritable> values, Context context)
            throws IOException, InterruptedException {

        double flightCount = 0;
        double delay = 0;
        double delaySum = 0;
        double totalFlightCount = 0;

        for(LongWritable value: values){
            totalFlightCount = totalFlightCount+1;
            delay = value.get();
            if (delay>=15) {
                delaySum += value.get();
                flightCount = flightCount+1;

            }
        }

        String s =  (new Double(flightCount/totalFlightCount).toString()) + "\t" +
                (new Double(delaySum/flightCount).toString()) ;

			context.write(key, new Text(s));
    }
}