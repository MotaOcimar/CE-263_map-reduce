
Dá um clean start:
```sh
stop-dfs.sh
stop-yarn.sh

hdfs namenode -format

start-dfs.sh
start-yarn.sh
```


Cria as pastas necessárias:
```sh
hdfs dfs -mkdir /user
hdfs dfs -mkdir /user/omsf
hdfs dfs -mkdir /user/omsf/kpi
hdfs dfs -mkdir /user/omsf/kpi/input
hdfs dfs -ls /user/omsf/kpi/input
```

Passa o arquivo de input:
```sh
hdfs dfs -put ~/Documentos/1.ITA/2022.1/CE-263/Exame/Input/*.* /user/omsf/kpi/input
hdfs dfs -ls /user/omsf/kpi
hdfs dfs -ls /user/omsf/kpi/input/
```

Compila o código java, Roda e gera o output:
```sh
hdfs dfs -rm -r /user/omsf/kpi/output
javac -d . -cp (hadoop classpath) PCMapper.java PCReducer.java Driver.java TextArrayWritable.java
jar cfm kpi.jar Manifest.txt KpiByAirline/*.class
hadoop jar kpi.jar omsf
hdfs dfs -cat /user/omsf/kpi/output/part-00000 > output.txt
```
