/**
 * Created by eva on 3/12/17.
 */
import java.io.IOException;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
//input
//AA	0.17035300362735556	65.12225396001038
//AS	0.09582483571266712	53.01253325450784


public class DelayByCarrierMapper2 extends
        Mapper<LongWritable, Text, Long, Text> {
    @Override
    protected void map(LongWritable keys, Text values, Context context)
            throws IOException, InterruptedException {


      String line = values.toString();
        String[] chars = line.split("\\t+");
        Text outputKey = new Text();
        Text outputValue = new Text();

        String filename = ((FileSplit) context.getInputSplit()).getPath().getName();
       
       if (filename.contains("part")) {

        if (chars.length > 2) {

       
            String carrier = chars[0].toString();
            Long percent = (long) Double.parseDouble(chars[1]);
            Long avgDelay = (long) Double.parseDouble(chars[2]);

            String s = (new String(carrier).toString()) + "\t" +
                    (new Long(avgDelay).toString());

            context.write(percent, new Text(s));
        }
    }

    }
    }

