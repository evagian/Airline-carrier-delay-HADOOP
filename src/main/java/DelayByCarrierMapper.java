/**
 * Created by eva on 3/12/17.
 */
import java.io.IOException;
import au.com.bytecode.opencsv.CSVParser;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Mapper;

public class DelayByCarrierMapper extends
        Mapper<LongWritable, Text, Text, LongWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {

        if (key.get() > 0) {

            String[] lines = new CSVParser().parseLine(value.toString());
            if ((!(lines[0].equals("FL_DATE")))&&(!(lines[1].equals("")))&& (!(lines[7].equals(""))) ) {

                String carrier = lines[1];

                Long delay = (long) Double.parseDouble(lines[7]);
                context.write(new Text(carrier), new LongWritable(delay));

            }
        }


        }
    }





