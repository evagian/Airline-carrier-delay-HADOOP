/**
 * Created by eva on 3/13/17.
 */

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;


public class DelayByCarrierComb extends Configured implements Tool {

    private static final String Temp = "tmp";
    private static final String Final = "fnl";


    public int run(String[] args) throws Exception {

        Configuration conf = getConf();
        FileSystem fs = FileSystem.get(conf);

        Job job = new Job(conf, "Job1");
        job.setJarByClass(DelayByCarrierComb.class);

        job.setMapperClass(DelayByCarrierMapper.class);
        job.setReducerClass(DelayByCarrierReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        //TextInputFormat.addInputPath(job, new Path(args[0]));
        //job.setInputFormatClass(TextInputFormat.class);

        //job.setInputFormatClass(TextInputFormat.class);
       //job.setOutputFormatClass(TextOutputFormat.class);

        TextInputFormat.addInputPath(job, new Path(args[0]));
        TextOutputFormat.setOutputPath(job, new Path(Temp));
        //FileOutputFormat.setOutputPath(conf, outputPath)

        job.waitForCompletion(true);


        Job job2 = new Job(conf, "Job 2");
        job2.setJarByClass(DelayByCarrierComb.class);

        job2.setMapperClass(DelayByCarrierMapper2.class);
        job2.setReducerClass(DelayByCarrierReducer2.class);

        job2.setOutputKeyClass(LongWritable.class);
        job2.setOutputValueClass(Text.class);

        job2.setInputFormatClass(TextInputFormat.class);
        //job2.setOutputFormatClass(TextOutputFormat.class);

        TextInputFormat.addInputPath(job2, new Path(Temp));
        TextOutputFormat.setOutputPath(job2, new Path(Final));

        return job2.waitForCompletion(true) ? 0 : 1;

    }


    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        if (args.length != 2) {
            System.err.println("Enter valid number of arguments <Inputdirectory>  <Outputlocation>");
            System.exit(0);
        }
        ToolRunner.run(new Configuration(), new DelayByCarrierComb(), args);
    }
}






