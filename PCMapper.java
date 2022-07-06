package KpiByAirport;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

public class PCMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, KpiByAirport.TextArrayWritable> {

    public void map(LongWritable key, Text value, OutputCollector<Text, KpiByAirport.TextArrayWritable> output, Reporter reporter)
        throws IOException {

        String line = value.toString();
        String[] data = line.split(",");

        if(data[1].equals("locality")) {
            return;
        }

        String pontual = "0";

        if (Double.parseDouble(data[4]) < 15.0) {
            pontual = "1";
        }

        String[] outValue = {data[3], pontual};

        output.collect(
            new Text(data[1] + "\t"),
            new KpiByAirport.TextArrayWritable( outValue )
        );
    }
}
