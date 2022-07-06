package KpiByAirline;

import java.io.IOException;
import java.util.*;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

public class PCReducer extends MapReduceBase implements Reducer<Text, KpiByAirline.TextArrayWritable, Text, Text> {

  public void reduce(Text key, Iterator<KpiByAirline.TextArrayWritable> values, OutputCollector<Text, Text> output,
      Reporter reporter) throws IOException {

    Double kpi01 = 0.0;
    Double kpi14 = 0.0;
    int count01 = 0;
    int count14 = 0;

    while (values.hasNext()) {
      String[] data = values.next().toStrings();

      if (Objects.equals(data[0], "DEP")) {
        kpi01 += Integer.parseInt(data[1]);
        count01 ++;
      }
      else {
        kpi14 += Integer.parseInt(data[1]);
        count14 ++;
      }
    }

    kpi01 = kpi01/count01;
    kpi14 = kpi14/count14;

    String outValue = "kpi01: " + kpi01.toString() + "\tkpi14: " +  kpi14.toString();

    output.collect(key, new Text( outValue ));
  }
}
