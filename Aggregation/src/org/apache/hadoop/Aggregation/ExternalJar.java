package org.apache.hadoop.Aggregation;

import java.io.File;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class ExternalJar {
	private static void addJarToDistributedCache(
	        Class classToAdd, Configuration conf)
	    throws IOException {
	 
	    // Retrieve jar file for class2Add
	    String jar = classToAdd.getProtectionDomain().
	            getCodeSource().getLocation().
	            getPath();
	    File jarFile = new File(jar);
	 
	    // Declare new HDFS location
	    Path hdfsJar = new Path("/user/hadoopi/lib/"
	            + jarFile.getName());
	 
	    // Mount HDFS
	    FileSystem hdfs = FileSystem.get(conf);
	 
	    // Copy (override) jar file to HDFS
	    hdfs.copyFromLocalFile(false, true,
	        new Path(jar), hdfsJar);
	 
	    // Add jar to distributed classPath
	    DistributedCache.addFileToClassPath(hdfsJar, conf);
	}

}
