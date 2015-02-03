package org.apache.hadoop.Aggregation;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.TimeZone;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.SequenceFile.CompressionType;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import com.google.protobuf.CodedInputStream;
import com.inadco.logs.pipeline.codegen.Logs.FASClick;

public class HdfsUtil {
    
    public static class AggregateMapper 
    extends Mapper<Text,BytesWritable, Text, Text> {
    	@Override
    	public void map(Text key, BytesWritable value, Context context) throws IOException, InterruptedException {
    		
    		//Text tKey=new Text();
    	    //tKey.set(key.toString().getBytes());
    		try{
    		com.inadco.logs.pipeline.codegen.Logs.FASClick.Builder clickBuilder =
    		 com.inadco.logs.pipeline.codegen.Logs.FASClick.newBuilder();
    		com.inadco.logs.pipeline.codegen.Logs.FASClick click = 
    				clickBuilder.mergeFrom(value.getBytes(), 0, value.getLength()).build();
    		
    	    Text tKey=new Text();
    	    tKey.set(click.getBrowserName());
    	    
    	    Text tVal=new Text();
    	    
    	    int init=new Random().nextInt();
    	    int first=new Random().nextInt();
    	    int last=new Random().nextInt();
    	    
    	    String counts=new Integer(init).toString()+","+new Integer(first).toString()+","+new Integer(last).toString();
    	    tVal.set(counts);
    	    context.write(tKey,tVal);
    		}catch(Exception ie){
    			System.err.println("exception is "+ie.getMessage());
    		}
    	   }

    }
    public static class AggregateReducer 
    extends Reducer<Text,Text,Text,Text> {
	protected void reduce(Text key, Iterable<Text> values, Context ctx) throws IOException,
        InterruptedException {
        
		for (Text value : values) {
        	ctx.write(key,new Text(value.getBytes()));
        	
        }
    }

    }
    
    private FileSystem fs;
    private Configuration conf;
    private final String REDUCER_OUTPUT="part";
    private final String SUCCESS_MARKER="_SUCCESS";  
    
    
    public void init() throws IOException{
        conf=new Configuration();
        fs = FileSystem.get(conf);
        log("Initialization of filesystem done ..");
    }

    public void moveSource(Path currentHour,Path mrInput) throws IOException{
        if(!fs.exists(mrInput)){
            fs.mkdirs(mrInput);
        }else{
            //log("Last Aggregation instance failed so aggregating last hour's files also");
        }
        FileStatus[] sourceFiles=fs.listStatus(currentHour);
        System.out.println("current path is "+currentHour.getName());
        System.out.println("source files are "+sourceFiles.length);
        for(FileStatus src : sourceFiles){
            log("Moving "+src.getPath().toString());
            fs.rename(src.getPath(),mrInput);
        }

    }
    
    public void recoverLastFailedMove(Path mrOutPath,Path shortHistory) throws IOException{
        if(hasSuccessMarker(mrOutPath)){
            log("Unmoved Files from last success run of MR present , moving them ...");
            FileStatus[] mroutfiles=fs.listStatus(mrOutPath);
	    int index=0;
            for(FileStatus f : mroutfiles){ 
                if(isReducerOutputFile(f.getPath())){
                    /*Please See - aggregated files are named 11-13-2014-08.00 but if there is a failed
                    move that file will be called 11-13-2014-08 , so if there is a file without .00 we will know
                    this is because there was a filed move in previous run */ 
                    fs.rename(f.getPath(),new Path(shortHistory,getFileName()+"-r-"+intToString(index++)));
                }
            }
        }

        fs.delete(mrOutPath,true);
        log("Deleted mroutput path from last run");

    }

    public boolean isReducerOutputFile(Path file){
        return file.getName().contains(REDUCER_OUTPUT);
    }

    public boolean hasSuccessMarker(Path mroutput) throws IOException{
        return fs.exists(new Path(mroutput,SUCCESS_MARKER));

    }


    public Path createPath(String path) throws IOException{
        Path p=new Path(path);
        if(!fs.exists(p)){
            fs.mkdirs(p);
        }
        return p;
    }

    public void deleteMROutput(Path mrout) throws IOException{
        //if success marker not present then delete no matter what
        if(!hasSuccessMarker(mrout)){
            fs.delete(mrout,true);  
        }else{
            //if success marker is there part file should not be there
            FileStatus[] outFiles=fs.listStatus(mrout);

            boolean status=true;
            for(FileStatus file : outFiles){
                if(file.getPath().getName().contains(REDUCER_OUTPUT)){
                    status=false;
                }
            }

            if(status){
                fs.delete(mrout,true);
            }
        }
    }



    public void moveMROutput(Path mrOutput,Path hourPath) throws IOException{

        //Don't move if the success marker is not present
        if(!hasSuccessMarker(mrOutput)){
            return;
        }

        FileStatus[] out=fs.listStatus(mrOutput);
        int index=0;

        for(FileStatus parts : out){
            String filename=parts.getPath().getName();
            if(filename.contains(REDUCER_OUTPUT)){
                String hourlyFileName=getFileName()+"."+intToString(index++);
                Path finalPath=new Path(hourPath,hourlyFileName);
                if(fs.rename(parts.getPath(),finalPath)){
                    log("MR output moved successfully");
                }
            }
        }

    }

    public String getFileName(){
        Calendar cal=new GregorianCalendar();
        cal.setTimeZone(TimeZone.getTimeZone("PST"));
        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        int day=cal.get(Calendar.DAY_OF_MONTH);
        int hour=cal.get(Calendar.HOUR_OF_DAY); 
        return intToString(++month)+"-"+intToString(day)+"-"+intToString(year)+"-"+intToString(hour);

    }

    public String intToString(int i){
        if(i<10){
            return "0"+Integer.toString(i);
        }
        return Integer.toString(i);
    }

    public void startAggregationMR(Path mrInput,Path mrOutput) throws IOException{
        Job job = new Job(conf, "Merge Sequence Files");
        job.setJarByClass(Merge.class);
        job.setMapperClass(AggregateMapper.class);
        //job.setCombinerClass(AggregateReducer.class);
        job.setReducerClass(AggregateReducer.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        job.setInputFormatClass(SequenceFileInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        //final Configuration conf=job.getConfiguration();
        //conf.set("mapreduce.textoutputformat.separator","TENDULKAR");
        //SequenceFileOutputFormat.setCompressOutput(job, true);
        //SequenceFileOutputFormat.setOutputCompressionType(job,CompressionType.BLOCK);
        FileInputFormat.addInputPath(job,mrInput);
        FileOutputFormat.setOutputPath(job,mrOutput);

        //Will be useful for debugging when files from short_history are missing in between
        logFileNamesForThisBatch(mrInput);
        try{
            if(job.waitForCompletion(true)){
                log("Aggregation Succeded");
                deleteMRInput(mrInput);  
            } else {
                log("Aggregation Failed !!");
            }


        }catch(Exception ie){
            logException("Exception occured in starting MR "+ie.getMessage());
        }
    }

    public void logFileNamesForThisBatch(Path mrinput) throws IOException {
        FileStatus[] inputFiles=fs.listStatus(mrinput);
        log("Total files for this batch "+inputFiles.length);
        for(FileStatus input : inputFiles){
            log(input.getPath().getName());
        }
    }

    public void deleteMRInput(Path mrInPath) throws IOException{
        if(fs.exists(mrInPath)){
            fs.delete(mrInPath,true);
        }
    }

    public void log(String str){
        System.out.println(str);
    }

    public void logException(String str){
        System.err.println(str);
    }
}
