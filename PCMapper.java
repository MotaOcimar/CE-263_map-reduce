package PaymentByCountry;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

public class PCMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, DoubleWritable> {

    public void map(LongWritable key, Text value, OutputCollector<Text, DoubleWritable> output, Reporter reporter)
        throws IOException {

        String line = value.toString();
        String[] data = line.split(",");

        if(data[2].equals("Price")) {
            return;
        }

        output.collect(
            new Text(data[7] + "\t"),
            new DoubleWritable( Double.parseDouble(data[2]) )
        );
    }
}
