current=$1
mrinput=$2
mroutput=$3
short=$4

hadoop fs -rm /merge-test/short_history/*
hadoop fs -rmr /merge-test/current/
hadoop fs -mkdir /merge-test/current/
hadoop fs -put testdata/* /merge-test/current/

hadoop jar merge.jar org.apache.hadoop.Aggregation.Merge $current $mrinput $mroutput $short
