package PaymentByCountry;

import java.io.IOException;
import java.util.*;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

public class PCReducer extends MapReduceBase implements Reducer<Text, DoubleWritable, Text, DoubleWritable> {

  public void reduce(Text key, Iterator<DoubleWritable> values, OutputCollector<Text, DoubleWritable> output,
      Reporter reporter) throws IOException {

    Double totalPrice = 0.0;
    while (values.hasNext()) {
      totalPrice += values.next().get();
    }

    output.collect(key, new DoubleWritable(totalPrice));
  }
}
