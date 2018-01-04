package ultrapower.hadoop;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class App {
	
    public static void main( String[] args ) throws Exception {    	
    	String input = "hdfs://10.0.0.160:9000/chengxj/in/" + args[0];
    	String output = "hdfs://10.0.0.160:9000/chengxj/out/" + args[1];
    	Job job = Job.getInstance();
    	job.setJarByClass(App.class);
    	job.setJobName("countMaxTemperature");
    	FileInputFormat.addInputPath(job, new Path(input));
    	FileOutputFormat.setOutputPath(job, new Path(output));
    	job.setMapperClass(MaxTemperatureMapper.class);
//    	job.setCombinerClass(MaxTemperatureReducer.class);
    	job.setReducerClass(MaxTemperatureReducer.class);
    	job.setOutputKeyClass(Text.class);
    	job.setOutputValueClass(IntWritable.class);
    	System.exit(job.waitForCompletion(true)?0:1);
    }
    
}