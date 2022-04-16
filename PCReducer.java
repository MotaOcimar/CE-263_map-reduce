package PaymentByCountry;

import java.io.IOException;
import java.util.*;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

public class PCReducer extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable> {

  public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, IntWritable> output,
      Reporter reporter) throws IOException {

    int frequency = 0;
    while (values.hasNext()) {
      frequency += values.next().get();
    }

    output.collect(key, new IntWritable(frequency));
  }
}
