package KpiByAirport;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;

import java.util.*;
import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.util.*;



public class Driver {
  public static void main(String[] args) {

    JobClient my_client = new JobClient();

    // Create a configuration object for the job
    JobConf job_conf = new JobConf(Driver.class);
    job_conf.setJobName("KpiByAirport");

    job_conf.setOutputKeyClass(Text.class);
    job_conf.setOutputValueClass(KpiByAirport.TextArrayWritable.class);

    job_conf.setMapperClass(KpiByAirport.PCMapper.class);
    job_conf.setReducerClass(KpiByAirport.PCReducer.class);

    
    // Set input and output directories using command line arguments,
    // arg[0] = name of the user

    FileInputFormat.addInputPath(job_conf, new Path("/user/" + args[0] + "/kpi/input"));
    FileOutputFormat.setOutputPath(job_conf, new Path("/user/" + args[0] + "/kpi/output"));

    my_client.setConf(job_conf);
    try {
      JobClient.runJob(job_conf);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
