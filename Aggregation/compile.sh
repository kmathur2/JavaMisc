file=merge.jar
if [ -f $file ];
	then
	rm merge.jar
fi
mkdir bin/
javac -cp lib/hadoop-core-0.20.2-cdh3u3.jar:lib/commons-cli-1.2.jar:lib/hadoop-lzo-0.4.14.jar -d bin/ src/Merge.java src/HdfsUtil.java
jar -cvf merge.jar -C bin/ .
mv merge.jar lib/

